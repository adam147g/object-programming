package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int width, int height) {
        super(width - 1, height - 1, 0, 0);
    }
    public Vector2d getLowerLeft() {
        return bottomLeftMap;
    }
    public void setLowerLeft(Vector2d bottomLowerLeft) {
        this.bottomLeftMap = bottomLowerLeft;
    }
    public Vector2d getUpperRight() {
        return topRightMap;
    }
    public void setUpperRight(Vector2d topRightMap) {
        this.topRightMap = topRightMap;
    }
}
