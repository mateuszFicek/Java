import org.junit.Test;

import static org.junit.Assert.*;

public class TestWynagrodzenie {

    @Test
    public void wynagrodzenieNetto(){
        Pracownik pracownik = new PracownikEtatowy();
        pracownik.wynagrodzenieBrutto = 3000;

        assertEquals(2400.0, pracownik.wynagrodzenieNetto(), 0);
    }

    @Test
    public void porownanieWynagrodzenia() {
        Pracownik p1 = new PracownikEtatowy();
        p1.wynagrodzenieBrutto = 2400;
        Pracownik p2 = new PracownikEtatowy();
        p2.wynagrodzenieBrutto = 3400;

        PracownikComparator pracownikComparator = new PracownikComparator();
        pracownikComparator.compare(p1, p2);

        assertEquals(-1, pracownikComparator.compare(p1, p2));
    }
}
