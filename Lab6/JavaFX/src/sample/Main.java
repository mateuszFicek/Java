package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.ObservableFaceArray;
import javafx.scene.shape.Rectangle;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.chart.XYChart.Series;


    public class Main extends Application {
        final static String ocena1 = "1";
        final static String ocena2 = "2";
        final static String ocena3 = "3";
        final static String ocena4 = "4";
        final static String ocena5 = "5";
        private EventHandler<ActionEvent> actionEventEventHandler;

        @Override public void start(Stage stage) {
            stage.setTitle("Histogram");
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            BarChart chart = new BarChart(xAxis, yAxis, getChartData());
            chart.setTitle("Country Summary");
            xAxis.setLabel("Country");
            yAxis.setLabel("Value");

            Button up = new Button("Dodaj 1.");

            Scene scene = new Scene(chart, 800, 600);

            stage.setScene(scene);
            stage.show();
        }
        public static void main (String[] args){
            launch(args);
        }

        private ObservableList<Series<String, Double>> getChartData(){
            double value = 1;

            ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();

            Series<String, Double> dane = new Series<>();

            dane.setName("Java");

            for(int i=1; i<5; i++){
                dane.getData().add(new XYChart.Data(Integer.toString(i), value));
                value = value + 4 * 2;
            }
            if (dane.getData() == null) {
                dane.setData(FXCollections.<XYChart.Series<String, Number>>observableArrayList());
            }
            data.addAll(dane);

            return data;
        }
    }