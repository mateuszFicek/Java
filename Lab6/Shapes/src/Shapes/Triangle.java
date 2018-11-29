package Shapes;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;


public class Triangle extends DrawShape {

    public Triangle(int len,String nazwa,int x, int y){
        super(len,nazwa,x,y);
    }
    @Override
    public void draw(Graphics graphic){
        Graphics2D g = (Graphics2D) graphic;
        g.setPaint(new GradientPaint(new Point2D.Double(0,0),new Color(0, 0, 150),new Point2D.Double(20,20), new Color(0,150,0)));
        myPath = new Path2D.Double();
        double firstX = x;
        double firstY = y;
        double height = length*Math.sqrt(3)/2;
        double half = length/2.0;
        double X2 = x+length;
        double X3 = x+half;
        double Y3 = y+height;


        myPath.moveTo(firstX, firstY);
        myPath.lineTo(X3,Y3);
        myPath.lineTo(X2,firstY);
        myPath.closePath();
        g.fill(myPath);
    }
}
