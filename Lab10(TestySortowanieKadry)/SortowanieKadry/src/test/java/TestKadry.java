import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class TestKadry {

    @Test
    public void testListyOsob(){
        Kadry kadra = new Kadry();
        Pracownik pracownik = new PracownikEtatowy();
        kadra.add(pracownik);
        assertEquals(1,kadra.kadra.size());
    }

    @Test
    public void testZnajdowaniaPracownika(){
        Pracownik pracownik1 = new PracownikEtatowy();
        pracownik1.wynagrodzenieBrutto = 3000;

        pracownik1.pesel = "123456789";

        Kadry kadry = new Kadry();
        kadry.add(pracownik1);

        assertEquals(pracownik1, kadry.find("123456789"));
    }

    @Test
    public void testSortowania(){
        Pracownik pracownik1 = new PracownikEtatowy();
        pracownik1.wynagrodzenieBrutto = 3000;
        Pracownik pracownik2 = new PracownikEtatowy();
        pracownik2.wynagrodzenieBrutto = 4000;
        Pracownik pracownik3 = new PracownikEtatowy();
        pracownik3.wynagrodzenieBrutto = 1400;

        Kadry kadry = new Kadry();
        kadry.add(pracownik1);
        kadry.add(pracownik2);
        kadry.add(pracownik3);

        kadry.sort();
        LinkedList<Pracownik> kadra = new LinkedList<>();
        kadra.add(pracownik3);
        kadra.add(pracownik1);
        kadra.add(pracownik2);
        assertEquals(kadra, kadry.kadra);
    }
}
