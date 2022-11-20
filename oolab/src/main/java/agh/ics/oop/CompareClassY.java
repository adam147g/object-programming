package agh.ics.oop;

import java.util.Comparator;

public class CompareClassY implements Comparator<Vector2d> {
    @Override
    public int compare(Vector2d v1, Vector2d v2){
        // return -1 means that v1 is before v2
        // return  1 means that v2 is before v1
        // return  0 means that v2 == v1
        if (v1.y < v2.y)
            return -1;
        else if (v1.y > v2.y)
            return 1;
            // v1.y==x2.y
        else {
            if (v1.x < v2.x)
                return -1;
            else if (v1.x > v2.x)
                return 1;
        }
        return 0;
    }
}
