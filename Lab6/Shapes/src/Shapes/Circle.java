package Shapes;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;

public class Circle extends DrawShape {
    public Circle(int len,String nazwa,int x, int y){
        super(len,nazwa,x,y);

    }

    public void draw(Graphics graphic) {
        Graphics2D g = (Graphics2D) graphic;
        g.setPaint(new GradientPaint(new Point2D.Double(0,0),new Color(0, 0, 150),new Point2D.Double(length,length), new Color(0,150,0)));
        myPath = new Path2D.Double();
        Shape circle = new Ellipse2D.Double(x,y,length,length);
        myPath.append(circle,true);


        g.fill(myPath);
    }
}
