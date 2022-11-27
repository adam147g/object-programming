package agh.ics.oop;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    void worldMainTest(){
        String[] testArgs = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(testArgs);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine( map, positions, 200);
        engine.setMoves(directions);
        engine.run();
        assertEquals(new Vector2d(3, 0), engine.animals.get(0).getPosition());
        assertEquals(new Vector2d(2, 4), engine.animals.get(1).getPosition());
    }
}
