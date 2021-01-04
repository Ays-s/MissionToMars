package gui;


        import javafx.application.Application;
        import javafx.beans.value.ChangeListener;
        import javafx.beans.value.ObservableValue;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.geometry.Pos;
        import javafx.scene.Scene;
        import javafx.scene.control.Label;
        import javafx.scene.control.Button;
        import javafx.scene.control.ListView;
        import javafx.scene.control.SelectionMode;
        import javafx.scene.layout.*;
        import javafx.stage.Stage;
        import javafx.geometry.Insets;
        import spaceship.*;

        import java.util.ArrayList;

public class ihmtest extends Application {

    @Override
    public void start(Stage stage) {

        ArrayList<Item> listItems = Simulation.loadItems("src/ressources/Phase-1.txt");

        Label labelName = new Label("Name");
        labelName.setMinWidth(150);
        Label labelWeight = new Label("Weight");
        Label labelCost = new Label("Cost");
        Label labelNbrPeople = new Label("Nbr People");

        Button buttonEdit = new Button("Edit");
        Button buttonDel = new Button("Remove");
        Button buttonAdd = new Button("New");

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

        edit.setSpacing(75);
        edit.setPadding(new Insets(10,10, 10,10));
        edit.setAlignment(Pos.CENTER);
        edit.getChildren().addAll(buttonAdd, buttonEdit, buttonDel);


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