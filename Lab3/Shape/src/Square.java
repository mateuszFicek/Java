public class Square extends Shape {
    private int x;
    Square(String n, int dlg){
        super(n);
        this.x = dlg;
    }

    @Override
    public void draw(){
        for (int j = 0; j < x; j++) {

            for (int i = 0; i < x; i++) {
                System.out.print("x");
            }
            System.out.print(System.lineSeparator());
        }
    }
}
