public abstract class Pracownik {
    public Pesel PESEL;
    public double wynagrodzenie_brutto;

    public Pracownik(double wynagrodzenie){
        wynagrodzenie_brutto = wynagrodzenie;
    }

    public abstract double wynagrodzenie_netto();
}
