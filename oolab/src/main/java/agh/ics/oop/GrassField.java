package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap{
    private final List<Grass> grasses;
    private final int n;
    private final int maxSpawnRange;
    private final int minSpawnRange;
    public GrassField(int n) {
        super(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1);
        this.n = n;
        this.maxSpawnRange = (int) Math.sqrt(n * 10);
        this.minSpawnRange = 0;
        grasses = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            while (flag) {
                int randomX = (int) (Math.random() * maxSpawnRange) + minSpawnRange;
                int randomY = (int) (Math.random() * maxSpawnRange) + minSpawnRange;
                Vector2d randomPos = new Vector2d(randomX, randomY);
                if (objectAt(randomPos) == null) {
                    grasses.add(new Grass(randomPos));
                    flag = false;
                }
            }
        }
    }

    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position))
            return true;
        for (Grass grass : grasses) {
            if (position.equals(grass.getPosition()))
                return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) {
            return super.objectAt(position);
        }
        for (Grass grass : grasses) {
            if (grass.isAt(position))
                return grass;
        }
        return null;
    }

    public boolean canMoveTo(Vector2d position) {
        if (!(objectAt(position) instanceof Animal)) {
            Object checkedPos = objectAt(position);
            if (checkedPos instanceof Grass) {
                grasses.remove(checkedPos);
                boolean flag = true;
                while (flag) {
                    int randomX = (int) (Math.random() * maxSpawnRange) + minSpawnRange;
                    int randomY = (int) (Math.random() * maxSpawnRange) + minSpawnRange;
                    Vector2d randomPos = new Vector2d(randomX, randomY);
                    if (objectAt(randomPos) == null) {
                        grasses.add(new Grass(randomPos));
                        flag = false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public Vector2d getLowerLeft() {
        Vector2d currLowerLeft = topRightMap;
        for (Animal animal : animals) {
            currLowerLeft = currLowerLeft.lowerLeft(animal.getPosition());
        }
        for (Grass grass : grasses) {
            currLowerLeft = currLowerLeft.lowerLeft(grass.getPosition());
        }
        return currLowerLeft;
    }

    public Vector2d getUpperRight() {
        Vector2d currUpperRight = bottomLeftMap;
        for (Animal animal : animals) {
            currUpperRight = currUpperRight.upperRight(animal.getPosition());
        }
        for (Grass grass : grasses) {
            currUpperRight = currUpperRight.upperRight(grass.getPosition());
        }
        return currUpperRight;
    }
}
