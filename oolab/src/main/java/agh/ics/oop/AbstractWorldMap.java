package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals = new ArrayList<>();
    protected MapVisualizer visualizer = new MapVisualizer(this);
    protected Vector2d topRightMap;
    protected Vector2d bottomLeftMap;

    public AbstractWorldMap(int topRightX, int topRightY, int bottomLeftX, int bottomLeftY) {
        this.topRightMap = new Vector2d(topRightX, topRightY);
        this.bottomLeftMap = new Vector2d(bottomLeftX, bottomLeftY);
    }

    abstract Vector2d getLowerLeft();

    abstract Vector2d getUpperRight();

    public String toString() {
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }
    public boolean canMoveTo(Vector2d position) {
        return position.follows(bottomLeftMap) && position.precedes(topRightMap) && !(objectAt(position) instanceof Animal);
    }

    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) instanceof Animal;
    }

    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }

}
