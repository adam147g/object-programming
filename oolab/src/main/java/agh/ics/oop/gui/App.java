package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;



public class App extends Application {
    private AbstractWorldMap map;
    private GridPane mapGrid = new GridPane();

    private final int width = 40;
    private final int height = 40;

    public void init() throws Exception {

        String[] args = getParameters().getRaw().toArray(new String[0]);
        MoveDirection[] directions = new OptionsParser().parse(args);
        this.map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(3, 5), new Vector2d(4, 8)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);
    }

    private void showWorldMap() throws FileNotFoundException {
        mapGrid.setGridLinesVisible(true);
        // first value
        Label yx = new Label("y\\x");
        // add first value
        mapGrid.add(yx, 0, 0);
        // center first value
        GridPane.setHalignment(yx, HPos.CENTER);

        GuiElementBox elementCreator;
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
                        elementCreator = new GuiElementBox((IMapElement) map.objectAt(checkPos));
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

    public void start(Stage primaryStage) throws FileNotFoundException {
        showWorldMap();
        Scene scene = new Scene(mapGrid, 600, 550);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}