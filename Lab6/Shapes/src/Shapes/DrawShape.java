package Shapes;

import java.awt.*;
import java.awt.geom.Path2D;

    public abstract class DrawShape {
        public String name;
        public int length;
        public int y;
        public int x;
        public Path2D myPath;


        public DrawShape(int len, String nazwa,int x, int y){
            length=len;
            name = nazwa;
            this.x = x;
            this.y = y;
        }

    public abstract void draw(Graphics g);
}