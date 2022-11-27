package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    Comparator<Vector2d> compX = new CompareClassX();
    Comparator<Vector2d> compY = new CompareClassY();
    SortedSet<Vector2d> sortedX = new TreeSet<>(compX);
    SortedSet<Vector2d> sortedY = new TreeSet<>(compY);
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        addElement(newPosition);
        removeElement(oldPosition);
    }

    public void addElement(Vector2d element){
        sortedX.add(element);
        sortedY.add(element);
    }

    public void removeElement(Vector2d element){
        sortedY.remove(element);
        sortedX.remove(element);
    }

    public Vector2d getLowerLeft(){
        return new Vector2d(sortedX.first().x, sortedY.first().y);
    }

    public Vector2d getUpperRight(){
        return new Vector2d(sortedX.last().x, sortedY.last().y);
    }
}
