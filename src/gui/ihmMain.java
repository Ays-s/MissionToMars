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
import javafx.scene.image.Image;
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

public class ihmMain extends Application {

    @Override
    public void start(Stage stage) {


        VBox root = new VBox();

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

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menuEdit);

        root.getChildren().addAll(menuBar);

        Scene scene = new Scene(root, 450, 400);
        stage.setTitle("MissionToMars");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
