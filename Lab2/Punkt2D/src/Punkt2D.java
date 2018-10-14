public class Punkt2D {
    private double x;
    private double y;
    public String name;
    Punkt2D(double _x, double _y, String _n){
        x = _x;
        y = _y;
        name = _n;
    }

    public double getX(){
        return x;
    }

    public String getName() {
        return name;
    }

    public double getY(){
        return y;
    }

    public void setX(double n){
        this.x = n;
    }

    public void setY(double m){
        this.y = m;
    }
    public void setName(String n){
        this.name = n;
    }

    public double distance(Punkt2D n){
        double dist = Math.sqrt(Math.pow((this.getX() - n.getX()),2) + Math.pow((this.getY() - n.getY()),2));
        return dist;
    }
}
