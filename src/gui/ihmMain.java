package gui;

import com.sun.javafx.scene.control.IntegerField;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import spaceship.Item;
import spaceship.Simulation;
import spaceship.U2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ihmMain extends Application {

    @Override
    public void start(Stage stage) {

        int nbMort = 0;

        VBox root = new VBox();

        // Menu Edit
        Menu menuEdit = new Menu("Edit");

        Menu subMenu = new Menu("Modify item file");
        MenuItem menuItemPhase1 = new MenuItem("Phase 1");
        subMenu.getItems().add(menuItemPhase1);
        MenuItem menuItemPhase2 = new MenuItem("Phase 2");
        subMenu.getItems().add(menuItemPhase2);
        menuEdit.getItems().add(subMenu);

        menuItemPhase1.setOnAction(e -> {
            ihmEdit edit = new ihmEdit("src/ressources/Phase-1.txt");
            edit.start(stage);
        });

        menuItemPhase2.setOnAction(e -> {
            ihmEdit edit = new ihmEdit("src/ressources/Phase-2.txt");
            edit.start(stage);
        });

        // Bar menu
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menuEdit);

        // propriétés de simulation
        HBox simulationProperty = new HBox();
        Label labelNbrSimulation = new Label("Nombre de simulation :");
        TextField fieldNbrSimulation = new TextField("0");

        simulationProperty.getChildren().addAll(labelNbrSimulation, fieldNbrSimulation);

        // Résultats de simulation
        VBox resultat = new VBox();
        HBox resultatBudget = new HBox();
        HBox resultatMort = new HBox();
        Label labelResultatBudgetMoyen = new Label("Budget moyen : " );
        Label labelResultatBudgetMax = new Label("Budget max : " );
        Label labelResultatMortMoyen = new Label("Mort moyen : " );
        Label labelResultatMortMax = new Label("Mort max : " );
        resultatBudget.getChildren().addAll(labelResultatBudgetMoyen, labelResultatBudgetMax);
        resultatMort.getChildren().addAll(labelResultatMortMoyen, labelResultatMortMax);
        resultat.getChildren().addAll(resultatBudget, resultatMort);

        resultatBudget.setSpacing(50);
        resultatMort.setSpacing(50);

        // Bouton lancer
        Button buttonSimulate = new Button("Simuler");

        buttonSimulate.setOnAction(e -> {
            int nbrSimulation = Integer.parseInt(fieldNbrSimulation.getText());
            int budget1, budgetMax = 0, sumBudget = 0;
            int mort1, mortMax = 0, sumMort = 0;
            System.out.println("simulation " + nbrSimulation);
            ArrayList<Item> listItemsPhase1 = Simulation.loadItems("src/ressources/Phase-1.txt");
            ArrayList<U2> list_Phase1 = Simulation.loadU2(listItemsPhase1);
            Simulation sim = new Simulation();
            for (int i =0; i<nbrSimulation;i++){
            int[] resultatSimulation  = sim.runSimulation(list_Phase1);
            budget1 = resultatSimulation[0];
            mort1 = resultatSimulation[1];
            sumBudget += budget1;
            sumMort += mort1;
            if (budget1>budgetMax){budgetMax = budget1;}
            if (mort1>mortMax){mortMax = mort1;}
            }
            labelResultatBudgetMoyen.setText("Budget moyen : " + sumBudget/nbrSimulation);
            labelResultatBudgetMax.setText("Budget max : " + budgetMax);
            labelResultatMortMoyen.setText("Mort moyen : " + sumMort/nbrSimulation);
            labelResultatMortMax.setText("Mort max : " + mortMax);
        });


        // Ajout sur la VBox
        root.getChildren().addAll(menuBar, simulationProperty,resultat, buttonSimulate);


        // Gestion scene
        Scene scene = new Scene(root, 450, 400);
        stage.setTitle("MissionToMars");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
