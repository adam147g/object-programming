package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {

    Label elementLabel;
    ImageView elementView;

    public GuiElementBox(IMapElement mapElement) throws FileNotFoundException {
        try {
            Image image = new Image(new FileInputStream(mapElement.getFileName()));
            if (mapElement instanceof Animal) {
                elementView =  new ImageView(image);
                elementView.setFitWidth(20);
                elementView.setFitHeight(20);
                elementLabel = new Label("Z " + mapElement.getPosition());
            } else {
                elementView = new ImageView(image);
                elementView.setFitWidth(20);
                elementView.setFitHeight(20);
                elementLabel = new Label("Grass");
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Couldn't load file " + exception);
        }
    }

    public VBox mapElementView() {
        VBox elementVBox = new VBox();
        elementVBox.getChildren().addAll(elementView, elementLabel);
        elementVBox.setAlignment(Pos.CENTER);
        return elementVBox;
    }
}
