class ReklamyAnonimowe{
    interface Sklep { // stworzenie interfejsu
        void reklama();
    }
    // stworzenie funkcji klasy zewnętrznej
    public void wyswietlReklamy(){
        // stworzenie nowej klasy anonimowej, która implementuje metodę
        // z interfejsu Sklep
        Sklep Tesco = new Sklep() {
            @Override
            public void reklama() {
                System.out.println("Dużo, tanio, Tesco!");
            }
        };

        Sklep Lidl = new Sklep() {
            @Override
            public void reklama() {
                System.out.println("Lidl - mądry wybór!");
            }
        };

        Sklep Biedronka = new Sklep() {
            @Override
            public void reklama() {
                System.out.println("Codziennie niskie ceny!");
            }
        };
        // wywołanie metod reklama() każdej klasy anonimowej
        Tesco.reklama();
        Lidl.reklama();
        Biedronka.reklama();
    }

    public static void main(String[] args) {
        ReklamyAnonimowe reklamy = new ReklamyAnonimowe();
        reklamy.wyswietlReklamy();
    }
}
