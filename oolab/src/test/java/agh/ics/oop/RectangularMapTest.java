package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {

    @Test
    public void canMoveToTest() {
        IWorldMap map = new RectangularMap(2, 2);
        assertTrue(map.canMoveTo(new Vector2d(0, 0)));
        assertFalse(map.canMoveTo(new Vector2d(0, 2)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    public void placeTest() {
        IWorldMap map = new RectangularMap(3, 3);
        assertTrue(map.place(new Animal(map, new Vector2d(1, 1))));
        assertFalse(map.place(new Animal(map, new Vector2d(1, 1))));
        assertFalse(map.place(new Animal(map, new Vector2d(4, 1))));
    }

    @Test
    public void isOccupiedTest() {
        IWorldMap map = new RectangularMap(5, 5);
        assertFalse(map.isOccupied(new Vector2d(2, 4)));
        map.place(new Animal(map, new Vector2d(2, 4)));
        assertTrue(map.isOccupied(new Vector2d(2, 4)));
        assertFalse(map.isOccupied(new Vector2d(2, 1)));
    }

    @Test
    public void objectAtTest() {
        IWorldMap map = new RectangularMap(5, 5);
        assertEquals(map.objectAt(new Vector2d(2, 4)), null);
        map.place(new Animal(map, new Vector2d(2, 4)));
        assertTrue(map.objectAt(new Vector2d(2, 4)) instanceof Animal);
        assertEquals(map.objectAt(new Vector2d(1, 3)), null);
        map.place(new Animal(map, new Vector2d(1, 3)));
        assertTrue(map.objectAt(new Vector2d(1, 3)) instanceof Animal);
    }
}
