public class Main {
    public static void main(String[] args){
        Punkt2D n = new Punkt2D(3, 4, "asda");
        Punkt2D m = new Punkt2D(0,0,"dasda");
        m.setX(3);
        m.setY(4);
        System.out.println(n.distance(m));

        Punkt3D a = new Punkt3D(1,1,1, "dasd");
        Punkt3D b = new Punkt3D(2,2,2,"dasdd");

        System.out.println(a.distance(b));
    }
}
