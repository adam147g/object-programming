package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;
public class World {
    public static void main(String[] args) {
        Application.launch(App.class, args);
        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(3, 4), new Vector2d(5, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
        }
        catch (IllegalArgumentException exception){
            System.out.println("Catched exceptation!:\n"+exception);
        }
    }
}
