public class Circle extends Shape{
    Circle(String n){
        super(n);
    }

    @Override
    public void draw(){
        System.out.println("         o");
        System.out.println("    o         o");
        System.out.println("  o             o");
        System.out.println(" o               o");
        System.out.println("o                 o");
        System.out.println(" o                o");
        System.out.println("  o             o");
        System.out.println("    o         o");
        System.out.println("         o");
    }
}
