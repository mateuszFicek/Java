import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static LinkedList<Shape> lista = new LinkedList<Shape>();

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int wybor;
        boolean exit = true;
        while(exit){
            menu();
            wybor = in.nextInt();
            switch (wybor){
                case 1:{
                    dodaj();
                    break;
                }
                case 2:{
                    wyswietl();
                    break;
                }
                case 3:{
                    exit = false;
                    break;
                }
                default:{
                    System.out.println("Bledny wybor, ponow probe");
                    wybor = in.nextInt();
                }
            }

        }
    }

    public static void menu(){
        System.out.println("1.Dodaj ksztalt do listy\n2.Przegladaj ksztalty\n3.Wyjdz z programu\nWybor: ");
    }

    public static void dodaj(){
        Scanner in = new Scanner(System.in);
        int wybor;
        String nazwa;
        System.out.println("1.Dodaj kolo\n2.Dodaj kwadrat\n3.Dodaj trojkat");
        wybor = in.nextInt();
        switch (wybor){
            case 1:{
                System.out.println("Podaj nazwe kola");
                in = new Scanner(System.in);
                nazwa = in.nextLine();
                lista.add(new Circle(nazwa));
                break;
            }
            case 2:{
                System.out.println("Podaj nazwe kwadratu");
                in = new Scanner(System.in);
                nazwa = in.nextLine();
                lista.add(new Square(nazwa));
                break;
            }
            case 3:{
                System.out.println("Podaj nazwe trojkata");
                in = new Scanner(System.in);
                nazwa = in.nextLine();
                lista.add(new Triangle(nazwa));
                break;
            }
            default:{
                System.out.println("Bledny wybor. Sprobuj jeszcze raz: ");
                dodaj();
                break;
            }
        }
    }

    public static void wyswietl(){
        for(Shape s : lista){
            System.out.println("Nazwa ksztatu : " + s.name);
            s.draw();
        }
    }
}
