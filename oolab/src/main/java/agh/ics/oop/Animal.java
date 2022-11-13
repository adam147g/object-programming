package agh.ics.oop;

public class Animal extends AbstractWorldMapElement {
    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        super(initialPosition);
        this.map = map;
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
    MapDirection getDirection() {
        return this.direction;
    }
    void move(MoveDirection direction) {
        boolean opposite = false;
        switch (direction) {
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case BACKWARD:
                opposite = true;
            case FORWARD:
                Vector2d moveVector = this.direction.toUnitVector();
                if (opposite)
                    moveVector = moveVector.opposite();
                Vector2d newPosition = this.position.add(moveVector);
                if (map.canMoveTo(newPosition))
                    this.position = newPosition;
                break;
        }
    }
}
