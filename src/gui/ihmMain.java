package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import spaceship.Item;
import spaceship.Simulation;
import spaceship.U1;
import spaceship.U2;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ihmMain extends Application {

    @Override
    public void start(Stage stage) {

        int nbMort = 0;
        AtomicInteger nbrLancer = new AtomicInteger();

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
        TextField fieldNbrSimulation = new TextField("1");

        simulationProperty.getChildren().addAll(labelNbrSimulation, fieldNbrSimulation);

        // Résultats de simulation
        HBox resultat = new HBox();
        VBox resultatData = new VBox();

        Label labelResultatBudgetMax = new Label("Budget max : " );
        Label labelResultatMortMax = new Label("Mort max : " );

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> resultatChart = new BarChart<String,Number>(xAxis, yAxis);
        resultatChart.setTitle("Resultat simulation fusée");

        xAxis.setLabel("Parameter");
        yAxis.setLabel("Average");

        xAxis.setCategories(FXCollections.observableArrayList(mort, budget));

        resultat.getChildren().addAll(resultatChart, resultatData);

        resultatData.getChildren().addAll(labelResultatMortMax, labelResultatBudgetMax);




        // Bouton lancer
        Button buttonSimulate = new Button("Simuler");

        buttonSimulate.setOnAction(e -> {
            nbrLancer.addAndGet(1);
            calcData(fieldNbrSimulation,  labelResultatBudgetMax,labelResultatMortMax,
                     resultatChart, nbrLancer.get());
        });

        // Bouton clear data
        Button buttonClear = new Button("Clear data");

        buttonClear.setOnAction(e -> {
            nbrLancer.set(0);
            resultatChart.getData().clear();
        });


        //Box bouton
        HBox boxButton = new HBox();
        boxButton.getChildren().addAll(buttonSimulate, buttonClear);


        // Ajout sur la VBox
        root.getChildren().addAll(menuBar, simulationProperty,resultat, boxButton);

        // Gestion scene
        Scene scene = new Scene(root, 650, 500);
        stage.setTitle("MissionToMars");
        stage.setScene(scene);
        stage.show();
    }

    final static String mort = "Mort *100";
    final static String budget = "Budget";

    private void calcData(TextField fieldNbrSimulation, Label labelResultatBudgetMax, Label labelResultatMortMax,
                          BarChart<String, Number> resultatChart, int nbrLancer) {
        String nameU1 = "U1";
        String nameU2 = "U2";
        int nbrSimulation = Integer.parseInt(fieldNbrSimulation.getText());
        int budget1, budget2, budgetMax = 0, sumBudget1 = 0, sumBudget2 = 0;
        int mort1, mort2, mortMax = 0, sumMort1 = 0, sumMort2 = 0;
        int maxMortType =0, maxBudgetType=0;
        System.out.println("simulation " + nbrSimulation);
        ArrayList<Item> listItemsPhase1 = Simulation.loadItems("src/ressources/Phase-1.txt");
        ArrayList<U1> list_Phase1U1 = Simulation.loadU1(listItemsPhase1);
        ArrayList<U2> list_Phase1U2 = Simulation.loadU2(listItemsPhase1);
        Simulation sim = new Simulation();
        for (int i =0; i<nbrSimulation;i++){
        int[] resultatSimulation1  = sim.runSimulation(list_Phase1U1);
        budget1 = resultatSimulation1[0];
        mort1 = resultatSimulation1[1];
        sumBudget1 += budget1;
        sumMort1 += mort1;
        int[] resultatSimulation2  = sim.runSimulation(list_Phase1U2);
        budget2 = resultatSimulation2[0];
        mort2 = resultatSimulation2[1];
        System.out.println(mort1 + "  " +mort2);
        sumBudget2 += budget2;
        sumMort2 += mort2;
        if (budget1>=budgetMax){budgetMax = budget1; maxBudgetType=1;}
        if (budget2>=budgetMax){budgetMax = budget2; maxBudgetType=2;}
        if (mort1>=mortMax){mortMax = mort1; maxMortType=1;}
        if (mort2>=mortMax){mortMax = mort2; maxMortType=2;}
        }

        labelResultatBudgetMax.setText("Budget max : " + budgetMax + ", pour U"+maxBudgetType);
        labelResultatMortMax.setText("Mort max : " + mortMax+ ", pour U"+maxMortType);

        if (nbrLancer>1){nameU1+=" #"+nbrLancer;nameU2+=" #"+nbrLancer;}

        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName(nameU1);
        series1.getData().add(new XYChart.Data<String, Number>(mort, (float) sumMort1/nbrSimulation* 100));
        series1.getData().add(new XYChart.Data<String, Number>(budget, sumBudget1/nbrSimulation));

        XYChart.Series series2 = new XYChart.Series<>();
        series2.setName(nameU2);
        series2.getData().add(new XYChart.Data<String, Number>(mort, (float) sumMort2/nbrSimulation* 100));
        series2.getData().add(new XYChart.Data<String, Number>(budget, sumBudget2/nbrSimulation));

        resultatChart.getData().addAll(series1, series2);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
