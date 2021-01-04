package gui;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import spaceship.Item;
import spaceship.Simulation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ihmEdit extends Application {

    String itemFilePath;

    public ihmEdit(String itemFilePath) {
        // Constructeur de la classe
        super();
        this.itemFilePath = itemFilePath;
    }

    @Override
    public void start(Stage stage) {

        //String itemFilePath = "src/ressources/Phase-1.txt";

        ArrayList<Item> listItems = Simulation.loadItems(this.itemFilePath);

        Label labelName = new Label("Name");
        labelName.setMinWidth(150);
        Label labelWeight = new Label("Weight");
        Label labelCost = new Label("Cost");
        Label labelNbrPeople = new Label("Nbr People");

        Button buttonEdit = new Button("Edit");
        Button buttonDel = new Button("Remove");
        Button buttonAdd = new Button("New");
        Button buttonSave = new Button("Save changes");

        final int[] index = new int[1];

        // To Creating a Observable List
        ObservableList<HBox> items = FXCollections.observableArrayList();

        for (Item i: listItems) { //pour tout objet de la liste itemArrayList
            HBox cols = new HBox();
            Label labelNameItem = new Label(i.getName());
            Label labelWeightItem = new Label(Integer.toString(i.getWeight()));
            Label labelCostItem = new Label(Integer.toString(i.getCost()));
            Label labelNbrPeopleItem = new Label(Integer.toString(i.getNbrPeople()));
            labelNameItem.setMinWidth(150);
            labelWeightItem.setMinWidth(40);
            labelCostItem.setMinWidth(28);
            labelNbrPeopleItem.setMinWidth(50);
            cols.getChildren().addAll(labelNameItem, labelWeightItem, labelCostItem, labelNbrPeopleItem);
            cols.setSpacing(25);

            items.addAll(cols);
        }


        // Create a ListView
        ListView<HBox> listView = new ListView<>(items);

        // Only allowed to select single row in the ListView.
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);



        VBox root = new VBox();
        HBox col = new HBox();

        col.setSpacing(25);
        col.setPadding(new Insets(15,20, 10,10));
        col.getChildren().addAll(labelName, labelWeight, labelCost, labelNbrPeople);

        HBox edit = new HBox();

        edit.setSpacing(50);
        edit.setPadding(new Insets(10,10, 10,10));
        edit.setAlignment(Pos.CENTER);
        edit.getChildren().addAll(buttonAdd, buttonEdit, buttonDel, buttonSave);

        listView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> index[0] = (int) newValue);


        buttonAdd.setOnAction(e -> {
            HBox row = new HBox();
            TextField labelNameItem = new TextField("Nom");
            TextField labelWeightItem = new TextField("0");
            TextField labelCostItem = new TextField("0");
            TextField labelNbrPeopleItem = new TextField("0");
            labelSet(row, labelNameItem, labelWeightItem, labelCostItem, labelNbrPeopleItem);

            listView.getItems().add(row);
        });

        buttonDel.setOnAction(e -> {
            listView.getItems().remove(index[0]);
            listItems.remove(index[0]);
        });

        buttonEdit.setOnAction(e -> {
            HBox row = new HBox();
            TextField labelNameItem = new TextField(listItems.get(index[0]).getName());
            TextField labelWeightItem = new TextField(Integer.toString(listItems.get(index[0]).getWeight()));
            TextField labelCostItem = new TextField(Integer.toString(listItems.get(index[0]).getCost()));
            TextField labelNbrPeopleItem = new TextField(Integer.toString(listItems.get(index[0]).getNbrPeople()));
            labelSet(row, labelNameItem, labelWeightItem, labelCostItem, labelNbrPeopleItem);

            listView.getItems().add(index[0], row);
            listView.getItems().remove(index[0]);
        });

        buttonSave.setOnAction(e -> {
            String name = "";
            int weight = 0, cost = 0, nbrPeople = 0;
            ArrayList<String> listSavedItems = new ArrayList<>();
            for (HBox row : listView.getItems()) {
                try {
                    Label labelName1 = (Label) row.getChildren().get(0);
                    Label labelWeight1 = (Label) row.getChildren().get(1);
                    Label labelCost1 = (Label) row.getChildren().get(2);
                    Label labelNbrPeople1 = (Label) row.getChildren().get(3);
                    name = labelName1.getText();
                    weight = Integer.parseInt(labelWeight1.getText());
                    cost = Integer.parseInt(labelCost1.getText());
                    nbrPeople = Integer.parseInt(labelNbrPeople1.getText());

                } catch (Exception exc) {
                    TextField labelName1 = (TextField) row.getChildren().get(0);
                    TextField labelWeight1 = (TextField) row.getChildren().get(1);
                    TextField labelCost1 = (TextField) row.getChildren().get(2);
                    TextField labelNbrPeople1 = (TextField) row.getChildren().get(3);
                    name = labelName1.getText();
                    weight = Integer.parseInt(labelWeight1.getText());
                    cost = Integer.parseInt(labelCost1.getText());
                    nbrPeople = Integer.parseInt(labelNbrPeople1.getText());

                } finally {
                    listSavedItems.add(name + "=" + weight * 1000 + "=" + cost + "=" + nbrPeople);
                }
                Path fileOut = Paths.get(itemFilePath);
                try {
                    Files.write(fileOut, listSavedItems, Charset.defaultCharset());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            ihmMain main = new ihmMain();
            main.start(stage);
        });


        root.getChildren().addAll(col, listView, edit);

        Scene scene = new Scene(root, 450, 400);
        stage.setScene(scene);
        stage.setTitle("Item editing");
        stage.show();
    }

    private void labelSet(HBox row, TextField labelNameItem, TextField labelWeightItem, TextField labelCostItem, TextField labelNbrPeopleItem) {
        labelNameItem.setMinWidth(150);
        labelWeightItem.setMinWidth(25);
        labelWeightItem.setMaxWidth(30);
        labelCostItem.setMinWidth(25);
        labelCostItem.setMaxWidth(30);
        labelNbrPeopleItem.setMinWidth(25);
        labelNbrPeopleItem.setMaxWidth(30);
        row.getChildren().addAll(labelNameItem, labelWeightItem, labelCostItem, labelNbrPeopleItem);
        row.setSpacing(25);
    }


    public static void main(String[] args) {
        launch(args);
    }



}