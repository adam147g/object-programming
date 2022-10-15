package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");
        Direction[] enum_args = toEnum(args);
        run(enum_args);
        System.out.println("system zakonczyl dzialanie");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println(MapDirection.NORTH);
        System.out.println(MapDirection.SOUTH);
        System.out.println(MapDirection.WEST);
        System.out.println(MapDirection.EAST);

        System.out.println(MapDirection.NORTH + ", next: " + MapDirection.NORTH.next());
        System.out.println(MapDirection.SOUTH + ", next: " + MapDirection.SOUTH.next());
        System.out.println(MapDirection.WEST + ", next: " + MapDirection.WEST.next());
        System.out.println(MapDirection.EAST + ", next: " + MapDirection.EAST.next());

        System.out.println(MapDirection.NORTH + ", previous: " + MapDirection.NORTH.previous());
        System.out.println(MapDirection.SOUTH + ", previous: " + MapDirection.SOUTH.previous());
        System.out.println(MapDirection.WEST + ", previous: " + MapDirection.WEST.previous());
        System.out.println(MapDirection.EAST + ", previous: " + MapDirection.EAST.previous());

        System.out.println(MapDirection.NORTH + ", toUnitVector: " + MapDirection.NORTH.toUnitVector());
        System.out.println(MapDirection.SOUTH + ", toUnitVector: " + MapDirection.SOUTH.toUnitVector());
        System.out.println(MapDirection.WEST + ", toUnitVector: " + MapDirection.WEST.toUnitVector());
        System.out.println(MapDirection.EAST + ", toUnitVector: " + MapDirection.EAST.toUnitVector());


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
