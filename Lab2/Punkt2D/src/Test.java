import java.util.LinkedList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        LinkedList<Punkt3D> punkty = new LinkedList<Punkt3D>();
        System.out.println("1.Wczytaj punkt\n2.Wyswietl wszystkie punkty\n3.Oblicz odleglosc\n4.Zakoncz");
        Scanner input = new Scanner(System.in);
        for(int j = 0; j<3; j++){
            System.out.println("Co teraz? ");
            int n = input.nextInt();
        switch (n){
            case 1: {
                System.out.println("Podaj pierwsza wspolrzedna");
                double a = input.nextDouble();
                System.out.println("Podaj druga wspolrzedna");
                double b = input.nextDouble();
                System.out.println("Podaj trzecia wspolrzedna");
                double c = input.nextDouble();
                Punkt3D p = new Punkt3D(a,b,c);
                punkty.add(p);
                break;
            }
            case 2: {
                for(int i = 0; i < punkty.size(); i++){
                    System.out.println("Punkt " + punkty.get(i).getX() +" "+ punkty.get(i).getY() + " " + punkty.get(i).getZ());
                }
                break;
            }
            case 3:{
                break;
            }
            case 4:{

            }

        }


        }
}}
