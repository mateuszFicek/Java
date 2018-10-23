public class Square extends Shape {
    Square(String n){
        super(n);
    }

    @Override
    public void draw(){
        System.out.println("[][][][][][][][]");
        System.out.println("[]            []");
        System.out.println("[]            []");
        System.out.println("[]            []");
        System.out.println("[]            []");
        System.out.println("[]            []");
        System.out.println("[][][][][][][][]");
    }
}
