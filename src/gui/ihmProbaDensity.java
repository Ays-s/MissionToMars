package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ihmProbaDensity extends Application {

    private double lambda=1;
    String probaType;

    public ihmProbaDensity(String probaType) {
        // Constructeur de la classe
        super();
        this.probaType = probaType;
    }

    public double getLambda() {
        return this.lambda;
    }

    @Override
    public void start(Stage stage) {

        StackPane secondaryLayout = new StackPane();
        Scene secondScene = new Scene(secondaryLayout, 380, 130);
        // New window (Stage)
        VBox boxAll = new VBox();
        HBox boxLambda = new HBox();

        Label labelLambda = new Label("Paramètre de la distribution :");

        TextField fieldLambda = new TextField("1");

        Button buttonEntrer = new Button("Entrer");
        Button buttonVisualiser = new Button("Visualiser");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Poids suplémentaire");
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);
        XYChart.Series series = new XYChart.Series();
        series.setName("Fusée");

        buttonVisualiser.setOnAction(f -> {
            this.lambda = Double.parseDouble(fieldLambda.getText());
            lineChart.getData().removeAll();
            series.getData().removeAll();
            if (probaType=="exponential"){
                series.getData().add(new XYChart.Data(0, loiExp(lambda,0 )));
                series.getData().add(new XYChart.Data(1, loiExp(lambda,1 )));
                series.getData().add(new XYChart.Data(2, loiExp(lambda,2 )));
                series.getData().add(new XYChart.Data(3, loiExp(lambda,3 )));
                series.getData().add(new XYChart.Data(4, loiExp(lambda,4 )));
                series.getData().add(new XYChart.Data(5, loiExp(lambda,5 )));
                series.getData().add(new XYChart.Data(6, loiExp(lambda,6 )));
                series.getData().add(new XYChart.Data(7, loiExp(lambda,7 )));
                series.getData().add(new XYChart.Data(8, loiExp(lambda,8 )));
                series.getData().add(new XYChart.Data(9, loiExp(lambda,9 )));
                series.getData().add(new XYChart.Data(10, loiExp(lambda,10 )));
                series.getData().add(new XYChart.Data(11, loiExp(lambda,11 )));
                lineChart.getData().add(series);
            }
            if (!boxAll.getChildren().contains(lineChart)) {
                boxAll.getChildren().add(lineChart);
                stage.setHeight(500);
                stage.setWidth(500);
            }
        });

        buttonEntrer.setOnAction(f -> {
            this.lambda = Double.parseDouble(fieldLambda.getText());
            //populating the series with data
            stage.close();
        });

        boxLambda.getChildren().addAll(labelLambda, fieldLambda);



        lineChart.setTitle("Répartition de la densité:");

        boxAll.getChildren().addAll(boxLambda, buttonEntrer, buttonVisualiser);
        boxAll.setSpacing(15);
        boxAll.setPadding(new Insets(10,10, 10,10));

        secondaryLayout.getChildren().add(boxAll);

        stage.setTitle("Choix de la distribution de probabilité");
        stage.setScene(secondScene);

        stage.show();
    }

    private double loiExp(double Aplha, int x){
        double res = 1 - Math.exp( -this.lambda * x);
        return res;
    }
}
