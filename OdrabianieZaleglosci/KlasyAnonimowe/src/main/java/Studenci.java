public class Studenci { // klasa zewnętrzna

    interface Student { // stworzenie interfejsu
        void coMowiPrzedKolokwium();
    }

    public void studenciePowiedz() { // metoda klasy zewnętrznej

        Student ambitny = new Student() { // tworzenie klasy anonimowej, która nie ma własnej nazwy
            @Override
            public void coMowiPrzedKolokwium() { // nadpisanie metody z interfejsu
                System.out.println("Ambitny: Przecież wszystko było na wykładach...");
            }
        };

        Student panikarz = new Student() {
            @Override
            public void coMowiPrzedKolokwium() {
                System.out.println("Panikarz: Przecież ja nic nie umiem, a kolokwium już jutro...");
            }
        };

        Student alkoholik = new Student() {
            @Override
            public void coMowiPrzedKolokwium() {
                System.out.println("Alkoholik: Jak zdam to idę oblać to kolokwium, a jak nie zdam to idę zapić smutki!");
            }
        };

        Student spioch = new Student() {
            @Override
            public void coMowiPrzedKolokwium() {
                System.out.println("Spioch: Kolokwium już jutro, ale lepiej się dobrze wyspać niż się uczyć...");
            }
        };

        // wywołanie metod z każdej klasy anonimowej
        ambitny.coMowiPrzedKolokwium();
        panikarz.coMowiPrzedKolokwium();
        alkoholik.coMowiPrzedKolokwium();
        spioch.coMowiPrzedKolokwium();
    }

    public static void main(String[] args) {
        Studenci studenci = new Studenci(); // stworzenie obiektu klasy zewnętrznej
        studenci.studenciePowiedz(); // wywołanie funkcji klasy zewnętrznej, która zawiera klasy anonimowe
    }
}