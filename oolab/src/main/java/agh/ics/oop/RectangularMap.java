package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements agh.ics.oop.IWorldMap{
    private final Vector2d lowerLeftCorner;
    private final Vector2d upperRightCorner;
    private MapVisualizer visualizer = new MapVisualizer(this);;
    private List<Animal> animals = new ArrayList<>();
    public Vector2d getUpperRight() {
        return this.upperRightCorner;
    }
    public Vector2d getLowerLeft() {
        return this.lowerLeftCorner;
    }
    public RectangularMap(int width, int height) {
        this.lowerLeftCorner = new Vector2d(0, 0);
        this.upperRightCorner = new Vector2d(width - 1, height - 1);
    }
//
    public String toString() {
        return visualizer.draw(lowerLeftCorner, upperRightCorner);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(lowerLeftCorner) && position.follows(upperRightCorner) && !isOccupied(position);
    }
    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal a : animals) {
            if (a.getPosition().equals(position))
                return true;
        }
        return false;
    }
    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }
}
