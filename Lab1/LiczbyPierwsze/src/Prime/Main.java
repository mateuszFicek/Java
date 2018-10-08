package Prime;

import JIn.JIn;

public class Main {

    public static void main(String[] args) {
        System.out.println("Podaj liczbe, a wypisane zostana wszystkie liczby pierwsze mniejsze od niej: ");
        int number = JIn.getInt();
        Prime n = new Prime(number);
        n.getPrimeNumbers(number);
        System.out.println("Oto wszystkie liczby pierwsze mniejsze badz rowne " + number);
    }
}
