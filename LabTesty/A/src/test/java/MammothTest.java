import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class MammothTest {

    private Mammoth m;
    File x;
    @BeforeClass
    public void fileInit(){
        try{
        x = new File("file.txt");
        x.createNewFile();
        System.out.println("Utworzono plik");

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public void fileClose() {
        if (x.delete()) {
            System.out.println("Usunieto plik");
        }
    }

    @Before
    public void init(){
        this.m = new Mammoth();
    }
    @Test(expected=InadequateFoodException.class)
    public void eatMeat() {
        Food meat = new Meat();
        Food other = new Food();
        m.eat(meat);
    }

    @Test
    public void eat() {
        try {
            Food meat = new Meat();
            m.eat(meat);
            fail("Expected exception");
        }
        catch (InadequateFoodException expc){
            assertEquals("I don't like meat", expc.getMessage());
        }
    }
}