import org.junit.Test;

import static org.junit.Assert.*;

public class ATest {

    @Test
    public void metTest1() {
        A x = new A();
        assertEquals("pierwszy", x.met(1));
    }

    @Test
    public void metTest2(){
        A x = new A();
        assertEquals("drugi", x.met(2));
    }

    @Test
    public void metTest3(){
        A x = new A();
        assertEquals("inny", x.met(3));
    }
}