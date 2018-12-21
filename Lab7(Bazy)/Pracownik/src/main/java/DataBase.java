import java.sql.*;
import java.util.ArrayList;

public class DataBase{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    public void connect() throws SQLException {
        int numbOfAttempts = 0;
        while (numbOfAttempts < 3) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                conn =
                        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/mficek",
                                "mficek", "a78AJdNbQ33DEhZJ");
                break;
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                numbOfAttempts += 1;
            } catch (Exception e) {
                numbOfAttempts += 1;
                e.printStackTrace();
            }
        }
        if (numbOfAttempts == 3){
            throw new SQLException();
        }

    }

    public void printAllWorkers() {
        try {
            connect();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM workers");

            printAll();
        } catch (SQLException ex) {
        } finally {
            cleanRes();
        }
    }


    public void addWorker(Pracownik worker){
        try {
            connect();
            stmt = conn.createStatement();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO workers (pesel, imie, nazwisko, wynagrodzenie)" + "VALUES (?,?,?,?)");
            statement.setString(1, worker.getPesel());
            statement.setString(2, worker.getImie());
            statement.setString(3, worker.getNazwisko());
            statement.setDouble(4, worker.getWynagrodzenie());
            statement.executeUpdate();

        } catch (SQLException ex) {
        } finally {
            cleanRes();
        }
    }

    private void cleanRes() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) {
            }
            rs = null;
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
            }

            stmt = null;
        }
    }

    private void printAll() throws SQLException {
        while (rs.next()) {

            String pesel = rs.getString(1);
            String name = rs.getString(2);
            String surname = rs.getString(3);
            Double wynagrodzenie = rs.getDouble(4);
            Pracownik worker = new Pracownik(pesel, name, surname, wynagrodzenie);
            worker.print();
        }
    }

    private void addWorkersFromQueryToList(ArrayList<Pracownik> workerList) throws SQLException {
        while (rs.next()) {

            String pesel = rs.getString(1);
            String name = rs.getString(2);
            String surname = rs.getString(3);
            Double wynagrodzenie = rs.getDouble(4);
            Pracownik worker = new Pracownik(pesel, name, surname, wynagrodzenie);
            workerList.add(worker);
        }
    }

}