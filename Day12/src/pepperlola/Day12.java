package pepperlola;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.List;

public class Day12 {
    public static int part1(List<String> input) {
        /*
            EAST = 0
            SOUTH = 1
            WEST = 2
            NORTH = 3
        */
        Direction direction = Direction.EAST;

        Tuple<Integer, Integer> pos = new Tuple<>(0, 0);

        for (String instruction : input) {
            char directionToMove = instruction.charAt(0);
            int amount = Integer.parseInt(instruction.replaceAll("[^0-9]", ""));

            switch (directionToMove) {
                case 'N':
                    moveNorth(pos, amount);
                    break;
                case 'E':
                    moveEast(pos, amount);
                    break;
                case 'S':
                    moveSouth(pos, amount);
                    break;
                case 'W':
                    moveWest(pos, amount);
                    break;
                case 'F':
                    moveForwards(pos, direction, amount);
                    break;
                case 'L':
                    direction = turnLeft(direction, amount);
                    break;
                case 'R':
                    direction = moveRight(direction, amount);
                    break;
            }
        }

        System.out.println(pos);

        return manhattanDistance(0, 0, pos.getX(), pos.getY());
    }

    public static long part2(List<String> input) {
        Tuple<Integer, Integer> waypointPosition = new Tuple<>(10, 1);
        /*
            EAST = 0
            SOUTH = 1
            WEST = 2
            NORTH = 3
        */
        Direction direction = Direction.EAST;

        Tuple<Integer, Integer> pos = new Tuple<>(0, 0);

        for (String instruction : input) {
            char directionToMove = instruction.charAt(0);
            int amount = Integer.parseInt(instruction.replaceAll("[^0-9]", ""));

            switch (directionToMove) {
                case 'N':
                    moveNorth(waypointPosition, amount);
                    break;
                case 'E':
                    moveEast(waypointPosition, amount);
                    break;
                case 'S':
                    moveSouth(waypointPosition, amount);
                    break;
                case 'W':
                    moveWest(waypointPosition, amount);
                    break;
                case 'F':
                    moveShipForward(pos, waypointPosition, amount);
                    break;
                case 'L':
                    direction = turnWaypointLeft(waypointPosition, direction, amount);
                    break;
                case 'R':
                    direction = turnWaypointRight(waypointPosition, direction, amount);
                    break;
            }
        }

        System.out.println(pos);

        return manhattanDistance(0, 0, pos.getX(), pos.getY());
    }

    public static void moveNorth(Tuple<Integer, Integer> pos, int amount) {
        pos.setY(pos.getY() + amount);
    }

    public static void moveSouth(Tuple<Integer, Integer> pos, int amount) {
        pos.setY(pos.getY() - amount);
    }

    public static void moveEast(Tuple<Integer, Integer> pos, int amount) {
        pos.setX(pos.getX() + amount);
    }

    public static void moveWest(Tuple<Integer, Integer> pos, int amount) {
        pos.setX(pos.getX() - amount);
    }

    public static Direction turnLeft(Direction direction, int amount) {
        return getDirectionFromTurn(direction, TurnDirection.LEFT, amount);
    }

    public static Direction moveRight(Direction direction, int amount) {
        return getDirectionFromTurn(direction, TurnDirection.RIGHT, amount);
    }

    public static void moveShipForward(Tuple<Integer, Integer> pos, Tuple<Integer, Integer> waypointPos, int amount) {
        int dX = waypointPos.getX();
        int dY = waypointPos.getY();

        dX *= amount;
        dY *= amount;

        pos.setX(pos.getX() + dX);
        pos.setY(pos.getY() + dY);
    }

    public static Direction turnWaypointLeft(Tuple<Integer, Integer> waypointPos, Direction direction, int amount) {
        Direction newDirection = getDirectionFromTurn(direction, TurnDirection.LEFT, amount);
        setWaypointLocationFromAngle(waypointPos, TurnDirection.LEFT, amount);

        return newDirection;
    }

    public static Direction turnWaypointRight(Tuple<Integer, Integer> waypointPos, Direction direction, int amount) {
        Direction newDirection = getDirectionFromTurn(direction, TurnDirection.RIGHT, amount);
        setWaypointLocationFromAngle(waypointPos, TurnDirection.RIGHT, amount);

        return newDirection;
    }

    public static void setWaypointLocationFromAngle(Tuple<Integer, Integer> waypointPos, TurnDirection direction, int amount) {
        if (direction == TurnDirection.RIGHT) {
            amount = 360 - amount;
        }
        int rotations = amount / 90;

        int rotatedX = waypointPos.getX();
        int rotatedY = waypointPos.getY();

        for (int i = 0; i < rotations; i++) {
            int prevX = rotatedX;
            int prevY = rotatedY;
            rotatedX = -prevY;
            rotatedY = prevX;
        }

        waypointPos.setX(rotatedX);
        waypointPos.setY(rotatedY);
    }

    public static void moveForwards(Tuple<Integer, Integer> pos, Direction direction, int amount) {
        switch (direction) {
            case EAST:
                moveEast(pos, amount);
                break;
            case WEST:
                moveWest(pos, amount);
                break;
            case NORTH:
                moveNorth(pos, amount);
                break;
            case SOUTH:
                moveSouth(pos, amount);
                break;
        }
    }

    public static Direction getDirectionFromTurn(Direction currentDirection, TurnDirection turnDirection, int angle) {
        int directionChange = angle / 90;
        int currentDirectionValue = currentDirection.value;

        if (turnDirection == TurnDirection.RIGHT) {
            currentDirectionValue += directionChange;
        } else {
            currentDirectionValue -= directionChange;
        }

        if (currentDirectionValue > 3)
            currentDirectionValue %= 4;
        if (currentDirectionValue < 0)
            currentDirectionValue = 4 + currentDirectionValue;

        return Direction.getDirection(currentDirectionValue);
    }

    public static int manhattanDistance(int x, int y, int x2, int y2) {
        return Math.abs(x2 - x) + Math.abs(y2 - y);
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println("PART 1: " + part1(input));
        System.out.println("PART 2: " + part2(input));
    }

    enum Direction {
        NORTH(3, 'N'),
        EAST(0, 'E'),
        SOUTH(1, 'S'),
        WEST(2, 'W');

        int value;
        char c;
        Direction(int value, char c) {
            this.value = value;
            this.c = c;
        }

        public static Direction getDirection(char c) {
            for (Direction dir : Direction.values()) {
                if (dir.c == c)
                    return dir;
            }

            return null;
        }

        public static Direction getDirection(int v) {
            for (Direction dir : Direction.values()) {
                if (dir.value == v)
                    return dir;
            }

            return null;
        }
    }

    enum TurnDirection {
        LEFT,
        RIGHT
    }
}
