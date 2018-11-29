package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Controller {
    //Chart
    @FXML
    BarChart<String, Double> histogram;

    //Buttons
    @FXML
    Button addValueButton;
    @FXML
    Button removeValueButton;

    //TextFields
    @FXML
    TextField xValue;
    @FXML
    TextField yValue;

    //Axis
    @FXML
    CategoryAxis xAxis;
    @FXML
    NumberAxis yAxis;

    private XYChart.Series series = new XYChart.Series();
    int indexOfValueToDelete;

    public void initialize() {
        series.nameProperty().setValue("Histogram");
        histogram.getData().add(series);
    }

    @FXML
    void typeStarted() {
        if (xValue.getText().equals("") || yValue.getText().equals("")) {
            addValueButton.setDisable(true);
        } else {
            addValueButton.setDisable(false);
        }
    }

    @FXML
    void addValueOnClick(){
        boolean inChart = false;
        for(Object dataAdded: series.getData()) {
            XYChart.Data data = (XYChart.Data) dataAdded;
            if (data.getXValue().equals(xValue.getText())) {
                data.setYValue(Double.parseDouble(yValue.getText()));
                inChart = true;
            } else {
                inChart = false;
            }
        }
        if(!inChart){
            XYChart.Data<String, Double> dataToAdd = new XYChart.Data<String, Double>(xValue.getText(), Double.parseDouble(yValue.getText()));
            series.getData().add(dataToAdd);
            dataToAdd.getNode().setOnMousePressed((MouseEvent event) ->{
                yValue.setText(dataToAdd.getYValue().toString());
                xValue.setText(dataToAdd.getXValue());
                xValue.setDisable(true);
                removeValueButton.setDisable(false);
                addValueButton.setText("Edit");
                addValueButton.setDisable(false);
                indexOfValueToDelete = series.getData().indexOf(dataToAdd);
            });
            xValue.setText("");
            yValue.setText("");
            xValue.setDisable(false);
            removeValueButton.setDisable(true);
            addValueButton.setText("Add");
            addValueButton.setDisable(true);
        }
    }


    @FXML
    void removeButtonPressed(){
        series.getData().remove(indexOfValueToDelete);
        xValue.setText("");
        yValue.setText("");
        xValue.setDisable(false);
        removeValueButton.setDisable(true);
        addValueButton.setText("Add");
        addValueButton.setDisable(true);
    }

}