package agh.ics.proj1;

public class Grass implements IMapElement{
    public Vector2d position;
    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "*";
    }
}
