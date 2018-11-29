public class Circle extends Shape{
    private int x;
    Circle(String n, int dlg){
        super(n);
        this.x = dlg;
    }

    @Override
    public void draw(){
        for (int i = -x; i <= x; i++) {
            for (int j = -x; j <= x; j++) {
                if (i * i + j * j <= x * x) System.out.print("o ");
                else System.out.print("  ");
            }
            System.out.print(System.lineSeparator());
        }
    }
}
