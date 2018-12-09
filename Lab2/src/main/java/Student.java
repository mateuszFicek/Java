public class Student extends Pracownik {

    public Student(double wynagrodzenie){
        super(wynagrodzenie);
    }

    @Override
    public double wynagrodzenie_netto() {
        return 0;
    }
}