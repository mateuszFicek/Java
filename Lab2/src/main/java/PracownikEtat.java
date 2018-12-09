public class PracownikEtat extends Pracownik {

    public PracownikEtat(double wynagrodzenie){
        super(wynagrodzenie);
    }

    @Override
    public double wynagrodzenie_netto() {
        return 0;
    }
}