package agh.ics.oop;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{
    MoveDirection[] moves;
    public final List<Animal> animals;
    private final int delay;
    private final ArrayList<ISimulationEngineObserver> observers = new ArrayList<ISimulationEngineObserver>();

    public SimulationEngine(IWorldMap map, Vector2d[] positions, int delay) {
        this.delay=delay;
        this.animals = new ArrayList<>();
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            if (map.place(animal))
                animals.add(animal);
        }
    }
    public void setMoves(MoveDirection[] moves) {
        this.moves = moves;
    }
    public Animal getAnimal(int idx) {
        return animals.get(idx);
    }
    public void addObserver(ISimulationEngineObserver observer) {
        this.observers.add(observer);
    }
    public void mapChanged() {
        for (ISimulationEngineObserver observer : observers) {
            try {
                observer.mapChanged();
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        if (animals.size()!=0){
            int i = 0;
            for (MoveDirection move : moves) {
                try{
                    Thread.sleep(this.delay);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                animals.get(i).move(move);
                i++;
                i %= animals.size();
                mapChanged();
            }
        }
    }
}







