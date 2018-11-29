package sample;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import Shapes.*;

public class DrawingPanel extends Panel{
    List<DrawShape> shapes = new LinkedList<DrawShape>();
    public DrawingPanel(){
        MyMouseAdapter myMouseAdapter = new MyMouseAdapter(this);
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);

    }


    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(DrawShape a : shapes){
            a.draw(g);
        }
    }

}