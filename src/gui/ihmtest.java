package gui;


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
        import javafx.scene.layout.*;
        import javafx.stage.Stage;
        import javafx.geometry.Insets;
        import spaceship.*;

        import java.io.*;
        import java.nio.charset.Charset;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.util.ArrayList;

public class ihmtest extends Application {

    @Override
    public void start(Stage stage) {

        String itemFilePath = "src/ressources/Phase-1.txt";

        ArrayList<Item> listItems = Simulation.loadItems(itemFilePath);

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
        ListView<HBox> listView = new ListView<HBox>(items);

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

        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                index[0] = (int) newValue;
            }
        });


        buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                HBox row = new HBox();
                TextField labelNameItem = new TextField("Nom");
                TextField labelWeightItem = new TextField("0");
                TextField labelCostItem = new TextField("0");
                TextField labelNbrPeopleItem = new TextField("0");
                labelNameItem.setMinWidth(150);
                labelWeightItem.setMinWidth(25);
                labelWeightItem.setMaxWidth(30);
                labelCostItem.setMinWidth(25);
                labelCostItem.setMaxWidth(30);
                labelNbrPeopleItem.setMinWidth(25);
                labelNbrPeopleItem.setMaxWidth(30);
                row.getChildren().addAll(labelNameItem, labelWeightItem, labelCostItem, labelNbrPeopleItem);
                row.setSpacing(25);

                listView.getItems().add(row );
            }
        });

        buttonDel.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                listView.getItems().remove(index[0]);
                listItems.remove(index[0]);
            }
        });

        buttonEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                HBox row = new HBox();
                TextField labelNameItem = new TextField(listItems.get(index[0]).getName());
                TextField labelWeightItem = new TextField(Integer.toString(listItems.get(index[0]).getWeight()));
                TextField labelCostItem = new TextField(Integer.toString(listItems.get(index[0]).getCost()));
                TextField labelNbrPeopleItem = new TextField(Integer.toString(listItems.get(index[0]).getNbrPeople()));
                labelNameItem.setMinWidth(150);
                labelWeightItem.setMinWidth(25);
                labelWeightItem.setMaxWidth(30);
                labelCostItem.setMinWidth(25);
                labelCostItem.setMaxWidth(30);
                labelNbrPeopleItem.setMinWidth(25);
                labelNbrPeopleItem.setMaxWidth(30);
                row.getChildren().addAll(labelNameItem, labelWeightItem, labelCostItem, labelNbrPeopleItem);
                row.setSpacing(25);

                listView.getItems().add(index[0], row );
                listView.getItems().remove(index[0]);
            }
        });

        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String name = "";
                int weight = 0 , cost = 0, nbrPeople = 0;
                ArrayList<String> listSavedItems = new ArrayList<String>();
                for (HBox row : listView.getItems()) {
                    try {
                        Label labelName = (Label) row.getChildren().get(0);
                        Label labelWeight = (Label) row.getChildren().get(1);
                        Label labelCost = (Label) row.getChildren().get(2);
                        Label labelNbrPeople = (Label) row.getChildren().get(3);
                        name = labelName.getText();
                        weight = Integer.parseInt(labelWeight.getText());
                        cost = Integer.parseInt(labelCost.getText());
                        nbrPeople = Integer.parseInt(labelNbrPeople.getText());

                    } catch (Exception exc) {
                        TextField  labelName = (TextField ) row.getChildren().get(0);
                        TextField  labelWeight = (TextField ) row.getChildren().get(1);
                        TextField  labelCost = (TextField ) row.getChildren().get(2);
                        TextField  labelNbrPeople = (TextField ) row.getChildren().get(3);
                        name = labelName.getText();
                        weight = Integer.parseInt(labelWeight.getText());
                        cost = Integer.parseInt(labelCost.getText());
                        nbrPeople = Integer.parseInt(labelNbrPeople.getText());

                    } finally {
                        listSavedItems.add(name + "=" + weight*1000+ "=" + cost+ "=" + nbrPeople);
                        System.out.println(name + "=" + weight*1000+ "=" + cost+ "=" + nbrPeople);
                    } ;
                    Path fileOut = Paths.get(itemFilePath);
                    try {
                        Files.write(fileOut, listSavedItems, Charset.defaultCharset());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
        }});


        root.getChildren().addAll(col, listView, edit);

        Scene scene = new Scene(root, 450, 400);
        stage.setScene(scene);
        stage.setTitle("Item editing");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }



}