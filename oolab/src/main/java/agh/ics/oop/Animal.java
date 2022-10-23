package agh.ics.oop;
//zmiany
public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    public String toString() {
        return "Position: " + position + ", orientation: " + orientation;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public boolean isOriented(MapDirection direction){
        return this.orientation.equals(direction);
    }

    public void move(MoveDirection direction) {
        Vector2d moveVector = new Vector2d(0, 0);
        switch (direction) {
            case RIGHT -> this.orientation = switch (this.orientation) {
                case NORTH -> MapDirection.EAST;
                case EAST -> MapDirection.SOUTH;
                case SOUTH -> MapDirection.WEST;
                case WEST -> MapDirection.NORTH;
            };
            case LEFT -> this.orientation = switch (this.orientation) {
                case NORTH -> MapDirection.WEST;
                case WEST -> MapDirection.SOUTH;
                case SOUTH -> MapDirection.EAST;
                case EAST -> MapDirection.NORTH;
            };
            case FORWARD -> moveVector = switch (this.orientation) {
                case NORTH -> new Vector2d(0, 1);
                case EAST -> new Vector2d(1, 0);
                case SOUTH -> new Vector2d(0, -1);
                case WEST -> new Vector2d(-1, 0);
            };
            case BACKWARD -> moveVector = switch (this.orientation) {
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
