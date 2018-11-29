import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {
        HBox root = new HBox();
        root.setPadding(new Insets(25));
        Rectangle rect = new Rectangle(25, 100, 50, 50);
        rect.setFill(Color.CADETBLUE);
        Line line = new Line(2,3,40,500);
        Button prevBtn = new Button("Previous");
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("First");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}