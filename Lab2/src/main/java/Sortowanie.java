import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Sortowanie implements Comparator<Pracownik> {

    @Override
    public  int compare(Pracownik o, Pracownik t1) {
        Pracownik left = (Pracownik)o;
        Pracownik right =(Pracownik)t1;
        if(left.wynagrodzenie_brutto>right.wynagrodzenie_brutto)
            return 1;
        else if(left.wynagrodzenie_brutto<right.wynagrodzenie_brutto)
            return -1;
        else
            return 0;
    }

}