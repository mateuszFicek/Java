public class Punkt3D extends Punkt2D {
    private double z;
    Punkt3D(double Px, double Py, double Pz, String Pname){
        super(Px,Py, Pname);
        this.z = Pz;
    }
    public double getZ(){
        return this.z;
    }
    public void setZ(double nZ){
        this.z = nZ;
    }

    public double distance(Punkt3D n){
        return Math.sqrt(Math.pow((this.getX() - n.getX()),2) + Math.pow((this.getY() - n.getY()),2) + Math.pow((this.getZ() - n.getZ()),2));
    }
}
