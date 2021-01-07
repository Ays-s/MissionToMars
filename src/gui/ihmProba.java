package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import spaceship.Probability;
import controller.Simulation;
import spaceship.U1;
import spaceship.U2;

public class ihmProba extends Application {
    private Probability probabilityDistribution;

    public ihmProba(Probability probabilityDistribution){
        this.probabilityDistribution = probabilityDistribution;
    }

    @Override
    public void start(Stage stage) {

        // New window (Stage)
        VBox boxAll = new VBox();
        HBox boxAlpha = new HBox();
        HBox boxEpsilon = new HBox();
        HBox boxResU1 = new HBox();
        HBox boxResU2 = new HBox();
        Label labelAlpha = new Label("Alpha :");
        Label labelEpsilon = new Label("Epsilon :");
        TextField fieldAlpha = new TextField("0.999");
        TextField fieldEpsilon = new TextField("0.01");
        Label labelResTextU1 = new Label("Nombre de U1: ");
        Label labelResFieldU1 = new Label("");
        Label labelResTextU2 = new Label("Nombre de U2: ");
        Label labelResFieldU2 = new Label("");

        Button buttonCalc = new Button("Calculer");

        buttonCalc.setOnAction(f -> {
            float alpha = Float.parseFloat(fieldAlpha.getText());
            float epsilon = Float.parseFloat(fieldEpsilon.getText());
            U1 u1 = new U1(this.probabilityDistribution);
            U2 u2 = new U2(this.probabilityDistribution);
            try {
                labelResFieldU1.setText(Float.toString(Simulation.number_of_simulation( u1 , epsilon, alpha)));
                labelResFieldU2.setText(Float.toString(Simulation.number_of_simulation( u2 , epsilon, alpha)));
            }
            catch (NumberFormatException formatException) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur !");
                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Entrer des nombre de type float!");

                alert.showAndWait();
            }
            if (alpha<0 || alpha>1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur !");
                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Entrer un nombre de simulation entre 1 et 2000000!");
                alert.showAndWait();
            }
        });

        boxAlpha.getChildren().addAll(labelAlpha, fieldAlpha);
        boxEpsilon.getChildren().addAll(labelEpsilon, fieldEpsilon);
        boxResU1.getChildren().addAll(labelResTextU1, labelResFieldU1);
        boxResU2.getChildren().addAll(labelResTextU2, labelResFieldU2);
        boxAll.getChildren().addAll(boxAlpha, boxEpsilon, buttonCalc, boxResU1, boxResU2);
        boxAll.setSpacing(15);
        boxAll.setPadding(new Insets(10,10, 10,10));

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(boxAll);

        Scene secondScene = new Scene(secondaryLayout, 340, 180);
        stage.setTitle("Calcule nombre de simulation");
        stage.setScene(secondScene);

        // Set position of second window, related to primary window.
        stage.setX(stage.getX() + 200);
        stage.setY(stage.getY() + 100);

        stage.show();
    }
}
