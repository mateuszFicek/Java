import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();
            int n = scanner.nextInt();
            switch (n){
                case 1:
                    DataBase db = new DataBase();
                    db.printAllWorkers();
                    break;
                case 2:
                    addWorker();
                    break;
                case 3:
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    private static void printMenu(){
        System.out.println("\n1. Wyświetl wszystkich pracowników");
        System.out.println("2. Dodaj nowego pracownika");
        System.out.println("3. Wyjdź z programu");
    }

    private static void addWorker() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Podaj pesel pracownika: ");
        String pesel = br.readLine();
        System.out.println("Podaj imie pracownika: ");
        String imie = br.readLine();
        System.out.println("Podaj nazwisko pracownika: ");
        String nazwisko = br.readLine();
        System.out.println("Podaj wynagrodzenie pracownika: ");
        double wynagrodzenie = Double.parseDouble(br.readLine());
        Pracownik worker = new Pracownik(pesel, imie, nazwisko, wynagrodzenie);
        DataBase db = new DataBase();
        db.addWorker(worker);
    }
}