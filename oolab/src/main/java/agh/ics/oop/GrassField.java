package agh.ics.oop;

import java.util.*;

public class GrassField extends AbstractWorldMap{
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private final int n;
    private final int maxSpawnRange;
    private final int minSpawnRange;
    public GrassField(int n) {
        super(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1);
        this.n = n;
        this.maxSpawnRange = (int) Math.sqrt(n * 10);
        this.minSpawnRange = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            while (flag) {
                int randomX = (int) (Math.random() * maxSpawnRange) + minSpawnRange;
                int randomY = (int) (Math.random() * maxSpawnRange) + minSpawnRange;
                Vector2d randomPos = new Vector2d(randomX, randomY);
                if (objectAt(randomPos) == null) {
                    Grass grass = new Grass(randomPos);
                    grasses.put(randomPos, grass);
                    mapBorder.addElement(grass.getPosition());
                    flag = false;
                }
            }
        }
    }

    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position))
            return true;
        return objectAt(position) instanceof Grass;
    }

    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) != null) {
            return super.objectAt(position);
        }
        if (grasses.containsKey(position)) {
            return grasses.get(position);
        }
        return null;
    }

    public boolean canMoveTo(Vector2d position) {
        if (!(objectAt(position) instanceof Animal)) {
            Object checkedPos = objectAt(position);
            if (checkedPos instanceof Grass) {
                Grass removeGrass = (Grass) checkedPos;
                grasses.remove(removeGrass.getPosition(),removeGrass);
                mapBorder.removeElement(removeGrass.getPosition());
                boolean flag = true;
                while (flag) {
                    int randomX = (int) (Math.random() * maxSpawnRange) + minSpawnRange;
                    int randomY = (int) (Math.random() * maxSpawnRange) + minSpawnRange;
                    Vector2d randomPos = new Vector2d(randomX, randomY);
                    if (objectAt(randomPos) == null) {
                        Grass grass = new Grass(randomPos);
                        grasses.put(randomPos, grass);
                        grass.addObserver(this.mapBorder);
                        mapBorder.addElement(grass.getPosition());
                        flag = false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public Vector2d getLowerLeft() {
        return mapBorder.getLowerLeft();
    }

    public Vector2d getUpperRight() {
        return mapBorder.getUpperRight();
    }
}
