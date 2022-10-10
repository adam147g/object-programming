package agh.ics.oop;


public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal");
        Direction[] enum_args = toEnum(args);
        run(enum_args);
        System.out.println("system zakonczyl dzialanie");
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
