package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {
    public Grass(Vector2d pos) {
        super(pos);
    }
    @Override
    public String toString() {
        return "*";
    }
}
