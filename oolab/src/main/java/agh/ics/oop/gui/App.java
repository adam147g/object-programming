package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import java.io.FileNotFoundException;



public class App extends Application implements ISimulationEngineObserver{
    private AbstractWorldMap map;
    GridPane mapGrid = new GridPane();
    SimulationEngine engine;
    private final int width = 40;
    private final int height = 40;

    public void init() {
        try{
            this.map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(3, 5), new Vector2d(4, 8)};
            this.engine = new SimulationEngine(this.map, positions, 500);
            engine.addObserver(this);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
    public void start(Stage primaryStage) throws FileNotFoundException {
        showWorldMap();
        TextField textField = new TextField();
        textField.setText("Animal moves");
//        textField.setPrefWidth(200);
//        textField.setMaxWidth(200);

        Button start = new Button("Start");
        start.setOnAction(e -> {
            MoveDirection[] directions = new OptionsParser().parse(
                    textField.getText().split(" ")
            );
            engine.setMoves(directions);
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });

        VBox vbox = new VBox(mapGrid, textField, start);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void showWorldMap() throws FileNotFoundException {
        mapGrid.setGridLinesVisible(true);
        mapGrid.setAlignment(Pos.CENTER);
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();


        Label yx = new Label("y\\x");
        mapGrid.add(yx, 0, 0);
        mapGrid.getColumnConstraints().add(new ColumnConstraints(width));
        mapGrid.getRowConstraints().add(new RowConstraints(height));
        GridPane.setHalignment(yx, HPos.CENTER);


        // first column
        try{
            for (int rowIdx = 0; rowIdx < map.getUpperRight().y - map.getLowerLeft().y + 1; rowIdx++) {
                Label colValue = new Label(String.valueOf(map.getUpperRight().y - rowIdx));
                mapGrid.add(colValue, 0, rowIdx + 1);
                GridPane.setHalignment(colValue, HPos.CENTER);
                mapGrid.getRowConstraints().add(new RowConstraints(height));
            }

            for (int colIdx = 0; colIdx < map.getUpperRight().x - map.getLowerLeft().x + 1; colIdx++) {
                // first row
                Label rowValue = new Label(String.valueOf(map.getLowerLeft().x + colIdx));
                mapGrid.add(rowValue, colIdx + 1, 0);
                GridPane.setHalignment(rowValue, HPos.CENTER);
                mapGrid.getColumnConstraints().add(new ColumnConstraints(width));

                // fill WorldMap
                for (int rowIdx = 0; rowIdx < map.getUpperRight().y - map.getLowerLeft().y + 1; rowIdx++) {
                    Vector2d checkPos = new Vector2d(map.getLowerLeft().x + colIdx, map.getUpperRight().y - rowIdx);
                    if (map.objectAt(checkPos) != null){
                        GuiElementBox elementCreator = new GuiElementBox((IMapElement) map.objectAt(checkPos));
                        VBox element = elementCreator.mapElementView();
                        mapGrid.add(element, colIdx + 1, rowIdx + 1);
                        GridPane.setHalignment(element, HPos.CENTER);
                    }
                }
            }
            mapGrid.getRowConstraints().add(new RowConstraints(height));
            mapGrid.getColumnConstraints().add(new ColumnConstraints(width));}
        catch (FileNotFoundException ex) {
            System.out.println("Couldnt load files");
        }
    }

    @Override
    public void mapChanged() throws FileNotFoundException {
        Platform.runLater(() -> {
            mapGrid.setGridLinesVisible(false);
            mapGrid.getChildren().clear();
            try {
                showWorldMap();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }
}