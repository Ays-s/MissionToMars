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
import spaceship.Simulation;
import spaceship.U1;
import spaceship.U2;

public class ihmProbaDensity extends Application {

    private double lambda=1;

    public double getLambda() {
        return this.lambda;
    }

    @Override
    public void start(Stage stage) {

        // New window (Stage)
        VBox boxAll = new VBox();
        HBox boxLambda = new HBox();

        Label labelLambda = new Label("Paramètre de la distribution :");

        TextField fieldLambda = new TextField("1");

        Button buttonEntrer = new Button("Entrer");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Probabilité");
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);
        XYChart.Series seriesU1 = new XYChart.Series();
        seriesU1.setName("U1");
        XYChart.Series seriesU2 = new XYChart.Series();
        seriesU1.setName("U2");


        buttonEntrer.setOnAction(f -> {
            this.lambda = Double.parseDouble(fieldLambda.getText());
            //populating the series with data
            seriesU1.getData().add(new XYChart.Data(1, 23));
            seriesU1.getData().add(new XYChart.Data(2, 14));
            seriesU1.getData().add(new XYChart.Data(3, 15));
            seriesU1.getData().add(new XYChart.Data(4, 24));
            seriesU1.getData().add(new XYChart.Data(5, 34));
            seriesU1.getData().add(new XYChart.Data(6, 36));
            seriesU1.getData().add(new XYChart.Data(7, 22));
            seriesU1.getData().add(new XYChart.Data(8, 45));
            seriesU1.getData().add(new XYChart.Data(9, 43));
            seriesU1.getData().add(new XYChart.Data(10, 17));
            seriesU1.getData().add(new XYChart.Data(11, 29));
            seriesU1.getData().add(new XYChart.Data(12, 25));
            stage.close();
        });

        boxLambda.getChildren().addAll(labelLambda, fieldLambda);



        lineChart.setTitle("Répartition de la densité:");

        boxAll.getChildren().addAll(boxLambda, buttonEntrer);
        boxAll.setSpacing(15);
        boxAll.setPadding(new Insets(10,10, 10,10));

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(boxAll);

        Scene secondScene = new Scene(secondaryLayout, 380, 130);
        stage.setTitle("Choix de la distribution de probabilité");
        stage.setScene(secondScene);

        stage.show();
    }
}
