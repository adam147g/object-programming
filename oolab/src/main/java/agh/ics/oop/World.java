package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }


    public static boolean animalTest(String[] args){
        Animal zwierze = new Animal();
        System.out.println(zwierze);
        OptionsParser movesParser = new OptionsParser();
        MoveDirection[] moves = movesParser.parse(args);
        for (MoveDirection moveToDo: moves) {
            zwierze.move(moveToDo);
            System.out.println(zwierze);
        }
        return (zwierze.isAt(new Vector2d(3, 4)) && zwierze.isOriented(MapDirection.WEST));
    }


    public static Direction[] toEnum(String[] args) {
        Direction[] directions = new Direction[args.length];
        for (int i = 0; i < args.length; i++) {
            directions[i] = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> Direction.UNKNOWN;
            };
        }
        return directions;
    }

    public static void run(Direction[] args) {
        for (int i = 0; i < args.length; i++) {
            if (i != args.length - 1) {
                System.out.print(args[i] + ", ");
            } else {
                System.out.println(args[i]);
            }
        }
        System.out.println("Start");
        for (Direction argument : args) {
            String message = switch (argument) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case RIGHT -> "Zwierzak idzie w prawo";
                case LEFT -> "Zwierzak idzie w lewo";
                default -> "Nieznane";
            };
            if (!message.equals("Nieznane")) {
                System.out.println(message);
            }
        }
        System.out.println("Stop");
    }
}
