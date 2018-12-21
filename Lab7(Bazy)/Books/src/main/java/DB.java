import java.sql.*;

public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn =
                    DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/mficek",
                            "mficek","a78AJdNbQ33DEhZJ");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }catch(Exception e){e.printStackTrace();}
    }

    public void listNames(){
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            rs = stmt.executeQuery("SELECT * FROM books");

            while(rs.next()){
                String name = rs.getString(1);
                System.out.println("Uzytkownik: "+name);
            }
        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }
    public void createTable() throws SQLException {
        stmt = conn.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE tabela1 (" + "priKey INT NOT NULL AUTO_INCREMENT, " + "nazwisko VARCHAR(64), PRIMARY KEY (priKey))");

    }

    public void addUser() throws SQLException {
        stmt = conn.createStatement();
        stmt.executeUpdate(
                "INSERT INTO tabela1 (nazwisko) "
                        + "values ('Bobek')");
    }

    public void selectByISBN(String id){
        try {
            connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM books");
            while(rs.next()){
                  String name = rs.getString(1);
                if(name.equals(id)){
                    printInformation();
                }
            }
        }catch (SQLException ex){
            System.err.println("Got an exception!");
            System.err.println(ex.getMessage());
    }
    finally {
            cleanResources();
            dropConnection();
        }
    }

    public void selectByAuthor(String surname){
        try {
            connect();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE author like(?)");
            ps.setObject(1,"% "+surname);

            rs = ps.executeQuery();

            printInformation();
        }catch (SQLException ex){
            // handle any errors

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            dropConnection();
        }
    }

    public void addBook(String isbn, String title, String author, String year){
        try {
            connect();
            String query = "INSERT INTO books (isbn,title,author,year)" + "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,isbn);
            preparedStatement.setString(2,title);
            preparedStatement.setString(3,author);
            preparedStatement.setString(4,year);
            preparedStatement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            dropConnection();
        }
    }

    private void dropConnection() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) { } // ignore
            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) { } // ignore

            stmt = null;
        }
    }

    public void cleanResources(){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) { } // ignore
            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) { } // ignore

            stmt = null;
        }
    }
    public void printInformation() throws SQLException {
        System.out.println("Uzytkownik: "+rs.getString(1));
        System.out.println("Tytul: "+rs.getString(2));
        System.out.println("Autor: "+rs.getString(3));
        System.out.println("Rok wydania: "+rs.getString(4));
    }

    public void menu(){
        System.out.println("Menu:");
        System.out.println("1. Wyszukaj książkę po autorze.");
        System.out.println("2. Wyszukaj książkę po numerze ISBN.");
        System.out.println("3. Dodaj książkę.");
        System.out.println("4. Wyjdź z programu.");
    }
}