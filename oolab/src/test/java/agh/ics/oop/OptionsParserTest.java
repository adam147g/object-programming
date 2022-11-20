package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
    @Test
    public void parserTest(){
        OptionsParser parser = new OptionsParser();
        String[] test1 = {"f", "backward", "forward", "WRONG"};
        try{
            MoveDirection[] output = parser.parse(test1);
            Assertions.fail("Arguments were invalid");
        }catch(IllegalArgumentException ex){
            Assertions.assertTrue(true);
        }

        String[] test2 = {"WRONG", "left", "l", "WRONG", "right"};
        try{
            MoveDirection[] output = parser.parse(test2);
            Assertions.fail("Arguments were invalid");
        }catch(IllegalArgumentException ex){
            Assertions.assertTrue(true);
        }

        String[] test3 = {"left", "right", "left", "f", "b", "forward", "backward"};
        MoveDirection[] valid_output2 = {MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.LEFT,
                MoveDirection.FORWARD, MoveDirection.BACKWARD,MoveDirection.FORWARD, MoveDirection.BACKWARD};
        Assertions.assertArrayEquals(valid_output2, parser.parse(test3));
    }
}
