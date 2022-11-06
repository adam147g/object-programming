package agh.ics.oop;

public class Animal extends AbstractWorldMapElement {
    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        super(initialPosition);
        this.map = map;
    }

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
    public void move(MoveDirection direction) {
        Vector2d moveVector = new Vector2d(0, 0);
        switch (direction) {
            case RIGHT -> this.direction = switch (this.direction) {
                case NORTH -> MapDirection.EAST;
                case EAST -> MapDirection.SOUTH;
                case SOUTH -> MapDirection.WEST;
                case WEST -> MapDirection.NORTH;
            };
            case LEFT -> this.direction = switch (this.direction) {
                case NORTH -> MapDirection.WEST;
                case WEST -> MapDirection.SOUTH;
                case SOUTH -> MapDirection.EAST;
                case EAST -> MapDirection.NORTH;
            };
            case FORWARD -> moveVector = switch (this.direction) {
                case NORTH -> new Vector2d(0, 1);
                case EAST -> new Vector2d(1, 0);
                case SOUTH -> new Vector2d(0, -1);
                case WEST -> new Vector2d(-1, 0);
            };
            case BACKWARD -> moveVector = switch (this.direction) {
                case NORTH -> new Vector2d(0, -1);
                case EAST -> new Vector2d(-1, 0);
                case SOUTH -> new Vector2d(0, 1);
                case WEST -> new Vector2d(1, 0);
            };
        }
        if (!moveVector.equals(new Vector2d(0, 0))) {
            if (this.position.x + moveVector.x >= 0 && this.position.x + moveVector.x <= 4 &&   this.position.y + moveVector.y >= 0 && this.position.y + moveVector.y <= 4) {
                this.position = new Vector2d(this.position.x + moveVector.x, this.position.y + moveVector.y);
            }
        }
    }

}
