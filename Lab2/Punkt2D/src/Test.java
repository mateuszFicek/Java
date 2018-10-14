import java.util.LinkedList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        LinkedList<Punkt3D> punkty = new LinkedList<>();
        System.out.println("1.Wczytaj punkt\n2.Wyswietl wszystkie punkty\n3.Oblicz odleglosc\n4.Zakoncz");
        Scanner input = new Scanner(System.in);
        boolean stan = true;
        while(stan){
            System.out.println("Co teraz? ");
            int n = input.nextInt();
        switch (n){
            case 1: {
                System.out.println("Podaj pierwsza wspolrzedna: ");
                double a = input.nextDouble();
                System.out.println("Podaj druga wspolrzedna: ");
                double b = input.nextDouble();
                System.out.println("Podaj trzecia wspolrzedna: ");
                double c = input.nextDouble();
                System.out.println("Podaj nazwe punktu: ");
                input = new Scanner(System.in);
                String m = input.nextLine();
                Punkt3D p = new Punkt3D(a,b,c,m);
                punkty.add(p);
                break;
            }
            case 2: {
                for(int i = 0; i < punkty.size(); i++){
                    System.out.println("Punkt "+ punkty.get(i).getName()+" " + punkty.get(i).getX() +" "+ punkty.get(i).getY() + " " + punkty.get(i).getZ());
                }
                break;
            }
            case 3:{
                System.out.println("Podaj dokladna nazwe pierwszego punktu: ");
                input = new Scanner(System.in);
                String first = input.nextLine();
                System.out.println("Podaj dokladna nazwe drugiego punktu: ");
                input = new Scanner(System.in);
                String second = input.nextLine();
                int fir = -1;
                int sec = -1;
                for(int i=0; i < punkty.size(); i++){
                    if(punkty.get(i).name.equals(first)) {
                        fir = i;
                    }
                    if(punkty.get(i).name.equals(second))
                        sec = i;
                }
                if(fir == -1 || sec == -1){
                    System.out.println("Nie odnaleziono jednego z punktow.");
                    break;
                }
                else{
                    System.out.println("Odleglosc miedzy punktami "+punkty.get(fir).name + " "+ punkty.get(sec).name + " wynosi " + punkty.get(fir).distance(punkty.get(sec)));
                }
                break;
            }
            case 4:{
                stan = false;
                break;
            }
            default:
                System.out.println("Błędny wybór. Spróbuj ponownie. Kliknij 4, aby wyjść.");

        }
        }
}}
