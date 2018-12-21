import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int wybor;
        DB baza = new DB();
        boolean stan = true;
        while(stan){
            Scanner rd = new Scanner(System.in);
            baza.menu();
            wybor = rd.nextInt();
            switch (wybor){
                case 1:
                    System.out.println("Podaj nazwisko autora: ");
                    String surname = rd.nextLine();
                    baza.selectByAuthor(surname);
                    break;

                case 2:
                    System.out.println("Podaj ISBN ksiązki: ");
                    String isbn = rd.nextLine();
                    baza.selectByISBN(isbn);
                    break;

                case 3:
                    rd = new Scanner(System.in);
                    System.out.println("Podaj ISBN książki: ");
                    String isbnn = rd.nextLine();
                    System.out.println("Podaj tytuł książki: ");
                    String title = rd.nextLine();
                    System.out.println("Podaj autora ksiazki: ");
                    String name = rd.nextLine();
                    System.out.println("Podaj rok wydania ksiazki: ");
                    String year = rd.nextLine();
                    baza.addBook(isbnn, title, name, year);
                    break;
                case 4:
                    stan = false;
                    break;
                default:
                    System.out.println("Blędny wybór, spróbuj jeszcze raz.");
                    break;
            }
        }
    }
}
