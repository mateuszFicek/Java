class LambdaReklamy {
    interface Sklep { // stworzenie interfejsu
        void reklama();
    }

    public void wyswietlReklamy() {

        // Tworzenie wyrażenia lambda
        // Najpierw podajemy typ naszego parametru, który jest opcjonalny
        // Następnie nazwę oraz znak =(), który świadczy o tym,że
        // nadpisujemy metodę z interfejsu Sklep
        // następnie strzałka, która oddziela parametry od kodu
        // i na samym końcu kod, który ma być wykonany
        Sklep Tesco = () -> System.out.println("Dużo, tanio, Tesco!");

        Sklep Lidl = () -> System.out.println("Lidl - mądry wybór!");

        Sklep Biedronka = () -> System.out.println("Codziennie niskie ceny!");

        Tesco.reklama();
        Lidl.reklama();
        Biedronka.reklama();
    }


    public static void main(String[] args) {
        LambdaReklamy reklamy = new LambdaReklamy();
        reklamy.wyswietlReklamy();
    }
}