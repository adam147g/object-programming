package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    void equalsTest() {
        Vector2d vect1 = new Vector2d(3, 7);
        Vector2d vect2 = new Vector2d(3, 7);
        Vector2d vect3 = new Vector2d(7, 2);
        Object o = new Object();
        assertTrue(vect1.equals(vect2));
        assertFalse(vect2.equals(vect3));
        assertTrue(vect1.equals(vect1));
        assertFalse(vect1.equals(o));
    }

    @Test
    void toStringTest() {
        Vector2d vec1 = new Vector2d(5, 9);
        Vector2d vec2 = new Vector2d(7, -10);
        assertEquals("(5,9)", vec1.toString());
        assertEquals("(7,-10)", vec2.toString());
    }

    @Test
    void precedesTest() {
        Vector2d vect1 = new Vector2d(3, 18);
        Vector2d vect2 = new Vector2d(-2, 10);
        Vector2d vect3 = new Vector2d(-2, 30);
        assertTrue(vect2.precedes(vect1));
        assertTrue(vect2.precedes(vect3));
        assertFalse(vect3.precedes(vect1));

    }

    @Test
    void followsTest() {
        Vector2d vect1 = new Vector2d(3, 18);
        Vector2d vect2 = new Vector2d(-2, 10);
        Vector2d vect3 = new Vector2d(-2, 30);
        assertTrue(vect1.follows(vect2));
        assertTrue(vect3.follows(vect2));
        assertFalse(vect1.follows(vect3));
    }

    @Test
    void upperRightTest() {
        Vector2d vect1 = new Vector2d(3, 18);
        Vector2d vect2 = new Vector2d(-2, 20);
        Vector2d vect3 = new Vector2d(3, 20);
        assertEquals(vect3, vect1.upperRight(vect2));
    }

    @Test
    void lowerLeftTest() {
        Vector2d vect1 = new Vector2d(3, 18);
        Vector2d vect2 = new Vector2d(-2, 20);
        Vector2d vect3 = new Vector2d(-2, 18);
        assertEquals(vect3, vect1.lowerLeft(vect2));
    }

    @Test
    void addTest() {
        Vector2d vect1 = new Vector2d(16, -99);
        Vector2d vect2 = new Vector2d(4, 95);
        Vector2d res = new Vector2d(20, -4);
        assertEquals(res, vect1.add(vect2));
    }

    @Test
    void subtractTest() {
        Vector2d vect1 = new Vector2d(16, -99);
        Vector2d vect2 = new Vector2d(4, 95);
        Vector2d res = new Vector2d(12, -194);
        assertEquals(res, vect1.substract(vect2));
    }

    @Test
    void oppositeTest() {
        Vector2d vect1 = new Vector2d(16, -99);
        Vector2d vect2 = new Vector2d(-99, 16);
        Vector2d vect3 = new Vector2d(0, -95);
        Vector2d vect4 = new Vector2d(-95, 0);
        assertEquals(vect2, vect1.opposite());
        assertEquals(vect4, vect3.opposite());
    }

}
