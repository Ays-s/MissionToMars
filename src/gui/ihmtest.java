package gui;


        import javafx.application.Application;
        import javafx.beans.value.ChangeListener;
        import javafx.beans.value.ObservableValue;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.scene.Scene;
        import javafx.scene.control.Label;
        import javafx.scene.control.ListView;
        import javafx.scene.control.SelectionMode;
        import javafx.scene.layout.VBox;
        import javafx.stage.Stage;

public class ihmtest extends Application {

    @Override
    public void start(Stage stage) {

        Book book1 = new Book(1L, "J01", "Java IO Tutorial");
        Book book2 = new Book(2L, "J02", "Java Enums Tutorial");
        Book book3 = new Book(2L, "C01", "C# Tutorial for Beginners");

        Label label = new Label();

        // To Creating a Observable List
        ObservableList<Book> books = FXCollections.observableArrayList(book1, book2, book3);

        // Create a ListView
        ListView<Book> listView = new ListView<Book>(books);

        // Only allowed to select single row in the ListView.
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                label.setText("OLD Index: " + oldValue + ",  NEW Index: " + newValue);
            }


        });

        VBox root = new VBox();
        root.getChildren().addAll(listView, label);

        stage.setTitle("ListView & ChangeListener (o7planning.org)");

        Scene scene = new Scene(root, 450, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}