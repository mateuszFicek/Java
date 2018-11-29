package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import Shapes.Circle;
import Shapes.Square;
import Shapes.Triangle;
import javax.swing.*;
import Shapes.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        JFrame frame = new JFrame();
        DrawingPanel panel = new DrawingPanel();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        panel.shapes.add(new Circle(100,"circ1",50,50));
        panel.shapes.add(new Triangle(20,"trai1",120, 120));
        panel.shapes.add(new Square(70,"rect1",40,200));
        panel.shapes.add(new Circle(40,"circ2",700,50));
        panel.shapes.add(new Triangle(200,"trai2",420, 120));
        panel.shapes.add(new Square(150,"rect2",403,200));
        frame.setContentPane(panel);
        frame.setSize(800,800);
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
