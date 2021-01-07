package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ihmProbaDensity extends Application {

    private double lambda=1;

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


        buttonEntrer.setOnAction(f -> {
            try {
                this.lambda = Double.parseDouble(fieldLambda.getText());
                stage.close();
            }
            catch (NumberFormatException formatException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur !");
                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Entrer un lambda de type double!");

                alert.showAndWait();
            }
            if (lambda<0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur !");
                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Entrer un lambda superieure à 0.");
                alert.showAndWait();
            }
        });

        boxLambda.getChildren().addAll(labelLambda, fieldLambda);

        boxAll.getChildren().addAll(boxLambda, buttonEntrer);
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
