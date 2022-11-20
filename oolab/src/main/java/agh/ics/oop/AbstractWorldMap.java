package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected MapVisualizer visualizer = new MapVisualizer(this);
    protected Vector2d topRightMap;
    protected Vector2d bottomLeftMap;
    protected MapBoundary mapBorder = new MapBoundary();
    public AbstractWorldMap(int topRightX, int topRightY, int bottomLeftX, int bottomLeftY) {
        this.topRightMap = new Vector2d(topRightX, topRightY);
        this.bottomLeftMap = new Vector2d(bottomLeftX, bottomLeftY);
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
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
            animals.put(animal.getPosition(), animal);
            animal.addObserver(this);
            animal.addObserver(mapBorder);
            mapBorder.addElement(animal.getPosition());
            return true;
        }
        throw new IllegalArgumentException(animal.getPosition() + " position is occuppied by other animal");
    }

    public boolean isOccupied(Vector2d position) {
        return objectAt(position) instanceof Animal;
    }

    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }
        return null;
    }

}
