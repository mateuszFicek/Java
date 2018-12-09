import java.util.LinkedList;

public class Main {
    public static LinkedList<Pracownik> lista = new LinkedList<Pracownik>();

    public static void main(String[] args){
        lista.add(new PracownikEtat(2500));
        lista.add(new PracownikEtat(1100));
        lista.add(new PracownikEtat(1500));
        lista.add(new PracownikEtat(3000));
        for (Pracownik p: lista) {
            System.out.println(p.wynagrodzenie_brutto);
        }


        lista.sort(new Sortowanie());

        System.out.println("Posortowana");

        for (Pracownik p: lista) {
            System.out.println(p.wynagrodzenie_brutto);
        }
    }
}