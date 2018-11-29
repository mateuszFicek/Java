import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        DB baza = new DB();
        baza.addBook("123","Book Title", "Me Fi", "1998");
    }
}
