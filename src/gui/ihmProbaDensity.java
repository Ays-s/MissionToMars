package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

        buttonEntrer.setOnAction(f -> {
            this.lambda = Double.parseDouble(fieldLambda.getText());
            stage.close();
        });

        boxLambda.getChildren().addAll(labelLambda, fieldLambda);

        boxAll.getChildren().addAll(boxLambda, buttonEntrer);
        boxAll.setSpacing(15);
        boxAll.setPadding(new Insets(10,10, 10,10));

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(boxAll);

        Scene secondScene = new Scene(secondaryLayout, 340, 180);
        stage.setTitle("Choix de la distribution de probabilité");
        stage.setScene(secondScene);

        // Set position of second window, related to primary window.
        stage.setX(stage.getX() + 200);
        stage.setY(stage.getY() + 100);

        stage.show();
    }
}
