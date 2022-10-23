package agh.ics.oop;

public class OptionsParser {
    public MoveDirection[] parse(String[] args){
        int length = 0;
        for (String arg : args){
            if (arg.equals("f") || arg.equals("forward") || arg.equals("b") || arg.equals("backward") || arg.equals("r") || arg.equals("right") || arg.equals("l") || arg.equals("left")){
                length++;
            }
        }
        MoveDirection[] movesArray = new MoveDirection[length];
        int idx = 0;
        for (String arg : args){
            switch (arg) {
                case "f", "forward" -> {
                    movesArray[idx] = MoveDirection.FORWARD;
                    idx++;
                }
                case "b", "backward" -> {
                    movesArray[idx] = MoveDirection.BACKWARD;
                    idx++;
                }
                case "r", "right" -> {
                    movesArray[idx] = MoveDirection.RIGHT;
                    idx++;
                }
                case "l", "left" -> {
                    movesArray[idx] = MoveDirection.LEFT;
                    idx++;
                }
            }
        }
        return movesArray;
    }
}
