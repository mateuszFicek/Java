import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Cryptographer crypter = new Cryptographer();
        System.out.println("Wpisz 1 jesli chcesz zaszyfrowac ROT11.\nWpisz 2 jesli chcesz odszyfrowac ROT11.\n");
        System.out.println("Wpisz 3 jeśli chcesz zaszyfrowac Polibiuszem.\nWpisz 4 jesli chcesz odszyfrowac Polibiuszem.\n");
        int wybor;
        Scanner wyborWczytanie = new Scanner(System.in);
        wybor = wyborWczytanie.nextInt();
        switch (wybor){
            case 1:{
                Cryptographer.cryptfile(args[0], args[1], new ROT11());
                break;
            }
            case 2:{
                Cryptographer.decryptfile(args[0], args[1], new ROT11());
                break;
            }
            case 3:{
                Cryptographer.cryptfile(args[0], args[1], new Polibiusz());
                break;
            }
            case 4:{
                Cryptographer.decryptfile(args[0], args[1], new Polibiusz());
                break;
            }
        }
    }
}
