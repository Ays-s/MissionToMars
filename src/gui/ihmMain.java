package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import spaceship.Item;
import spaceship.Simulation;
import spaceship.U1;
import spaceship.U2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class ihmMain extends Application {

    File file;

    @Override
    public void start(Stage stage) {

        int nbMort = 0;
        String pathToSave = "";

        AtomicInteger nbrLancer = new AtomicInteger();

        VBox root = new VBox();

        // Menu Edit
        Menu menuEdit = new Menu("Modification des paramètres");

        Menu subMenu = new Menu("Modifier le fichier");
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
        simulationProperty.setPrefHeight(50);
        simulationProperty.setSpacing(5);
        simulationProperty.setAlignment(Pos.CENTER);

        Label labelNbrSimulation = new Label("Nombre de simulations :");
        TextField fieldNbrSimulation = new TextField("1");

        simulationProperty.getChildren().addAll(labelNbrSimulation, fieldNbrSimulation);

        // Résultats de simulation
        HBox resultat = new HBox();
        VBox resultatData = new VBox();

        Label labelResultatBudgetMax = new Label("Budget maximum : \n\n" );
        Label labelResultatMortMax = new Label("Nombre maximum de morts : \n\n" );

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> resultatChart = new BarChart<String,Number>(xAxis, yAxis);
        resultatChart.setTitle("Résultats simulation(s) pour U1 et U2");

        xAxis.setLabel("Paramètres");
        yAxis.setLabel("Moyenne");

        xAxis.setCategories(FXCollections.observableArrayList(mort, budget));

        resultat.getChildren().addAll(resultatChart, resultatData);

        resultatData.getChildren().addAll(labelResultatBudgetMax, labelResultatMortMax);


        // checkbox de sauvegarde
        CheckBox checkBox1 = new CheckBox("Enregistrer les données");
        checkBox1.setOnAction( e -> {
            if (checkBox1.isSelected()){System.out.println("enregistrer On");
                FileChooser fileChooser = new FileChooser();

                //Set extension filter for text files
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                //Show save file dialog
                file = fileChooser.showSaveDialog(stage);
                System.out.println(file);}
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Enregistrement");

                // Header Text: null
                alert.setHeaderText(null);
                alert.setContentText("Arret de l'enregistrement!\nVos enregistrements sont enregistré à: "+file);



                alert.showAndWait();
                System.out.println("enregistrer Off");}
        });

        // Bouton lancer
        Button buttonSimulate = new Button("Lancer la simulation");

        buttonSimulate.setOnAction(e -> {
            nbrLancer.addAndGet(1);
            try {
                calcData(fieldNbrSimulation, labelResultatBudgetMax, labelResultatMortMax,
                             resultatChart, nbrLancer.get(), checkBox1.isSelected());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        });

        // Bouton clear data
        Button buttonClear = new Button("Réinitialiser les données");

        buttonClear.setOnAction(e -> {
            nbrLancer.set(0);
            resultatChart.getData().clear();
        });

        //Ajout des boutons simuler et réinitialiser et de sauvegarde
        resultatData.getChildren().addAll(buttonSimulate, buttonClear, checkBox1);
        resultatData.setSpacing(20);


        // Ajout sur la VBox
        root.getChildren().addAll(menuBar, simulationProperty, resultat);

        // Gestion scene
        Scene scene = new Scene(root, 700, 490);
        stage.setTitle("Mission To Mars");
        stage.setScene(scene);
        stage.show();
    }

    final static String mort = "Morts (*100)";
    final static String budget = "Budgets";

    private void calcData(TextField fieldNbrSimulation, Label labelResultatBudgetMax, Label labelResultatMortMax,
                          BarChart<String, Number> resultatChart, int nbrLancer, boolean sauv) throws IOException {
        String nameU1 = "U1";
        String nameU2 = "U2";
        int nbrSimulation = Integer.parseInt(fieldNbrSimulation.getText());
        int budget1, budget2, budgetMax = 0, sumBudget1 = 0, sumBudget2 = 0;
        int mort1, mort2, mortMax = 0, sumMort1 = 0, sumMort2 = 0;
        int maxMortType =0, maxBudgetType=0;

        System.out.println("Simulations : " + nbrSimulation);
        ArrayList<Item> listItemsPhase1 = Simulation.loadItems("src/ressources/Phase-1.txt");
        ArrayList<U1> list_Phase1U1 = Simulation.loadU1(listItemsPhase1);
        ArrayList<U2> list_Phase1U2 = Simulation.loadU2(listItemsPhase1);

        int[] list_nbMortU1 = new int[nbrSimulation];
        int[] list_nbMortU2 = new int[nbrSimulation];
        int[] list_budgetU1 = new int[nbrSimulation];
        int[] list_budgetU2 = new int[nbrSimulation];


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

            if (sauv){
                list_budgetU1[i] = budget1;
                list_nbMortU1[i] = mort1;
                list_budgetU2[i] = budget2;
                list_nbMortU2[i] = mort2;
            }

            sumBudget2 += budget2;
            sumMort2 += mort2;
            if (budget1>=budgetMax){budgetMax = budget1; maxBudgetType=1;}
            if (budget2>=budgetMax){budgetMax = budget2; maxBudgetType=2;}
            if (mort1>=mortMax){mortMax = mort1; maxMortType=1;}
            if (mort2>=mortMax){mortMax = mort2; maxMortType=2;}
        }
        if (sauv) {
            boolean append = (nbrLancer!=1);
            try {
                LocalDateTime date = LocalDateTime.now();
                FileWriter fw = new FileWriter(file, append);
                if (!append) {
                    fw.write("\nRésultats des simulations du :" + date + ":\n");
                }
                fw.write("\n\nLancer numéro "+nbrLancer+", " + nbrSimulation+ " simulations:\n");
                fw.write("Fusée U1:\n");
                fw.write("Budget:"+Arrays.toString(list_budgetU1)+"\n");
                fw.write("Nombre de morts:"+Arrays.toString(list_nbMortU1)+"\n");
                fw.write("Fusée U2:\n");
                fw.write("Budget:"+Arrays.toString(list_budgetU2)+"\n");
                fw.write("Nombre de morts:"+Arrays.toString(list_nbMortU2)+"\n");
                fw.close();
            }
            catch (IOException e){
                System.out.println("Erreur de sauvegarde.");
            }
        }


        labelResultatBudgetMax.setText("Budget maximum : \n" + budgetMax + "M€ pour U"+maxBudgetType);
        labelResultatMortMax.setText("Nombre maximum de morts : \n" + mortMax+ " avec la fusée U"+maxMortType);

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
