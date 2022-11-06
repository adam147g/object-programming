package agh.ics.oop;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] moves;
    public final List<Animal> animals;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions) {
        this.moves = moves;
        this.animals = new ArrayList<>();
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal))
                animals.add(animal);
        }
    }
    public Animal getAnimal(int idx) {
        return animals.get(idx);
    }
    @Override
    public void run() {
        int i = 0;
        for (MoveDirection move : moves) {
            animals.get(i).move(move);
            i++;
            i %= animals.size();
        }
    }
}
