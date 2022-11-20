package agh.ics.oop;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {


    @Test
    public void canMoveToTest() {
        GrassField map = new GrassField(10);
        assertTrue(map.canMoveTo(new Vector2d(0, 1)));
        assertTrue(map.canMoveTo(new Vector2d(5, 5)));
        assertTrue(map.canMoveTo(new Vector2d(4, 3)));
        assertTrue(map.canMoveTo(new Vector2d(8, 1)));
        assertTrue(map.canMoveTo(new Vector2d(9, 9)));
        assertTrue(map.canMoveTo(new Vector2d(Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1)));
        map.place(new Animal(map, new Vector2d(4, 66)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(4, 66)));
    }

    @Test
    public void placeTest() {
        GrassField map = new GrassField(10);
        assertTrue(map.place(new Animal(map, new Vector2d(-44, 80))));
        assertTrue(map.place(new Animal(map, new Vector2d(52, 95))));
        assertTrue(map.place(new Animal(map, new Vector2d(Integer.MAX_VALUE-1, Integer.MAX_VALUE-1))));
        try {
            map.place(new Animal(map, new Vector2d(52, 95)));
            Assertions.fail("Can't place at 52, 95");
        }
        catch(IllegalArgumentException exception){
            Assertions.assertTrue(true);
        }
        assertTrue(map.place(new Animal(map, new Vector2d(-414, 80))));
        try {
            map.place(new Animal(map, new Vector2d(-414, 80)));
            Assertions.fail("Can't place at -414, 80");
        }
        catch(IllegalArgumentException exception){
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void isOccupiedTest() {
        GrassField map = new GrassField(10);
        map.place(new Animal(map, new Vector2d(77, 26)));
        assertTrue(map.isOccupied(new Vector2d(77, 26)));
        assertFalse(map.isOccupied(new Vector2d(87, 81)));
        assertFalse(map.isOccupied(new Vector2d(24, 15)));
    }

    @Test
    public void objectAtTest() {
        GrassField map = new GrassField(10);
        assertTrue(map.objectAt(new Vector2d(1, 1)) == null || map.objectAt(new Vector2d(1, 1)) instanceof Grass);
        map.place(new Animal(map, new Vector2d(1, 1)));
        assertTrue(map.objectAt(new Vector2d(1, 1)) instanceof Animal);
    }

}
