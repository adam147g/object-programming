package agh.ics.oop;

import java.util.*;
public class Animal extends AbstractWorldMapElement {
    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        super(initialPosition);
        this.map = map;
        this.observers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return switch (this.direction) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }
    public MapDirection getDirection() {
        return this.direction;
    }
    void move(MoveDirection direction) {
        Vector2d moveVector = this.direction.toUnitVector();
        Vector2d newPosition;
        switch (direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            case BACKWARD -> {
                moveVector = moveVector.opposite();
                newPosition = this.position.add(moveVector);
                if (map.canMoveTo(newPosition))
                    positionChanged(newPosition);
            }
            case FORWARD -> {
                newPosition = this.position.add(moveVector);
                if (map.canMoveTo(newPosition))
                    positionChanged(newPosition);
            }
        }
    }
    void positionChanged(Vector2d newPos) {
        for (IPositionChangeObserver observer : observers)
            observer.positionChanged(this.position, newPos);
        this.position = newPos;
    }

    @Override
    public String getFileName() {
        return switch (this.direction) {
            case NORTH -> "src/main/resources/up.png";
            case EAST -> "src/main/resources/right.png";
            case SOUTH -> "src/main/resources/down.png";
            case WEST -> "src/main/resources/left.png";
        };
    }
}
