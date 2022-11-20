package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


public class App extends Application {
    private GridPane mapGrid;

    public void start(Stage primaryStage) {
        Label label = new Label("Zwierzak");
        Scene scene = new Scene(label, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
