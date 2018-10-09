public class Punkt2D {
    protected double x;
    protected double y;
    Punkt2D(double _x, double _y){
        x = _x;
        y = _y;
    }

    public double getX(){
        return x;
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

    public double distance(Punkt2D n){
        double dist = Math.sqrt(Math.pow((this.getX() - n.getX()),2) + Math.pow((this.getY() - n.getY()),2));
        return dist;
    }
}
