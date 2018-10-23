import java.util.LinkedList;

public abstract class Shape{
    public String name;
    /**
     * Metoda rysujaca w konsoli dany kształt
     */
    Shape(String name){
        this.name = name;
    }

    public abstract void draw();
}