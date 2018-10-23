public class Triangle extends Shape {
    Triangle(String n){
        super(n);
    }

    @Override
    public void draw(){
        System.out.println("        ^    ");
        System.out.println("      ^   ^  ");
        System.out.println("    ^       ^");
        System.out.println("  ^           ^");
        System.out.println("^^^^^^^^^^^^^^^^^");
    }
}
