package pepperlola;

import java.util.ArrayList;
import java.util.List;

public class Day11 {
    public static int part1(List<String> input) {
        List<List<Integer>> previousBoard;
        List<List<Integer>> board = getBoard(input);

        int generations = 0;
        while (true) {
            previousBoard = board;
            board = generation(1, 4, previousBoard);

            if (compareBoards(board, previousBoard)) {
                break;
            }

            generations ++;
        }

        return countOccupiedSeats(board);
    }

    public static long part2(List<String> input) {
        List<List<Integer>> previousBoard;
        List<List<Integer>> board = getBoard(input);

        int generations = 0;
        while (true) {
            previousBoard = board;
            board = generation(2, 5, previousBoard);

            if (compareBoards(board, previousBoard)) {
                break;
            }

            generations ++;
        }

        return countOccupiedSeats(board);
    }

    public static int countOccupiedSeats(List<List<Integer>> list) {
        int total = 0;
        for (List<Integer> line : list) {
            for (int i : line) {
                if (i == 1)
                    total ++;
            }
        }

        return total;
    }

    public static List<List<Integer>> getBoard(List<String> input) {
        int width = input.get(0).length();
        int height = input.size();

        List<List<Integer>> board = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Integer> line = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                line.add(getState(x, y, input));
            }

            board.add(line);
        }
        return board;
    }

    public static List<List<Integer>> generation(int part, int numToUnoccupy, List<List<Integer>> input) {
        int width = input.get(0).size();
        int height = input.size();

        List<List<Integer>> currentBoard = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Integer> currentLine = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                currentLine.add(input.get(y).get(x));
            }
            currentBoard.add(currentLine);
        }

        List<List<Integer>> newBoard = new ArrayList<>(currentBoard);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newBoard.get(y).set(x, getNewState(part, x, y, currentBoard.get(y).get(x), numToUnoccupy, input));
            }
        }

        return newBoard;
    }

    public static int getNewState(int part, int x, int y, int currentState, int numToUnoccupy, List<List<Integer>> input) {
        int width = input.get(0).size();
        int height = input.size();

        List<Integer> surroundingStates = part == 1 ? getNeighbors(x, y, width, height, input) : getNeighbors2(x, y, width, height, input);
        int occupiedNeighbors = 0;

        for (int i : surroundingStates) {
            if (i == 1)
                occupiedNeighbors ++;
        }

        if (currentState == 0 && occupiedNeighbors == 0) {
            return 1;
        } else if (currentState == 1 && occupiedNeighbors >= numToUnoccupy) {
            return 0;
        }

        return currentState;
    }

    public static int getState(int x, int y, List<String> input) {
        char c = input.get(y).charAt(x);

        switch (c) {
            case '.':
                return -1;
            case 'L':
                return 0;
            case '#':
                return 1;
        }

        return -1;
    }

    public static char getStateString(int state) {
        switch (state) {
            case -1:
                return '.';
            case 0:
                return 'L';
            case 1:
                return '#';
        }

        return '.';
    }

    public static List<Integer> getNeighbors(int x, int y, int width, int height, List<List<Integer>> input) {
        List<Integer> surroundingStates = new ArrayList<>();
        if (y > 0) {
            if (x > 0) {
                surroundingStates.add(input.get(y - 1).get(x - 1));
            }
            surroundingStates.add(input.get(y - 1).get(x));
            if (x < width - 1) {
                surroundingStates.add(input.get(y - 1).get(x + 1));
            }
        }
        if (x > 0) {
            surroundingStates.add(input.get(y).get(x - 1));
        }
        if (x < width - 1) {
            surroundingStates.add(input.get(y).get(x + 1));
        }

        if (y < height - 1) {
            if (x > 0) {
                surroundingStates.add(input.get(y + 1).get(x - 1));
            }
            surroundingStates.add(input.get(y + 1).get(x));
            if (x < width - 1) {
                surroundingStates.add(input.get(y + 1).get(x + 1));
            }
        }
        return surroundingStates;
    }

    public static List<Integer> getNeighbors2(int x, int y, int width, int height, List<List<Integer>> input) {
        List<Integer> surroundingStates = new ArrayList<>();
        int dX = -1;
        int dY = -1;

        while (x + dX >= 0 && y + dY >= 0 && input.get(y + dY).get(x + dX) == -1) {
            dX --;
            dY --;
        }
        if (x + dX >= 0 && y + dY >= 0)
            surroundingStates.add(input.get(y + dY).get(x + dX));
        dY = -1;

        while (y + dY >= 0 && input.get(y + dY).get(x) == -1) {
            dY --;
        }
        if (y + dY >= 0)
            surroundingStates.add(input.get(y + dY).get(x));

        dX = 1;
        dY = -1;

        while (x + dX < width && y + dY >= 0 && input.get(y + dY).get(x + dX) == -1) {
            dX ++;
            dY --;
        }
        if (y + dY >= 0 && x + dX < width)
            surroundingStates.add(input.get(y + dY).get(x + dX));

        dX = -1;

        while (x + dX >= 0 && input.get(y).get(x + dX) == -1) {
            dX --;
        }

        if (x + dX >= 0) {
            surroundingStates.add(input.get(y).get(x + dX));
        }

        dX = 1;

        while (x + dX < width && input.get(y).get(x + dX) == -1) {
            dX ++;
        }

        if (x + dX < width) {
            surroundingStates.add(input.get(y).get(x + dX));
        }

        dY = 1;
        dX = -1;

        while (x + dX >= 0 && y + dY < height && input.get(y + dY).get(x + dX) == -1) {
            dX --;
            dY ++;
        }

        if (x + dX >= 0 && y + dY < height) {
            surroundingStates.add(input.get(y + dY).get(x + dX));
        }

        dY = 1;

        while (y + dY < height && input.get(y + dY).get(x) == -1) {
            dY ++;
        }

        if (y + dY < height)
            surroundingStates.add(input.get(y + dY).get(x));

        dX = 1;
        dY = 1;

        while (x + dX < width && y + dY < height && input.get(y + dY).get(x + dX) == -1) {
            dX ++;
            dY ++;
        }

        if (x + dX < width && y + dY < height) {
            surroundingStates.add(input.get(y + dY).get(x + dX));
        }
        return surroundingStates;
    }

    public static boolean compareBoards(List<List<Integer>> board1, List<List<Integer>> board2) {
        if (board1.size() != board2.size()) return false;
        if (board1.size() <= 0) return false;
        if (board1.get(0).size() != board2.get(0).size()) return false;
        for (int y = 0; y < board1.size(); y ++) {
            for (int x = 0; x < board1.get(0).size(); x++) {
                if (board1.get(y).get(x) != board2.get(y).get(x)) return false;
            }
        }

        return true;
    }

    public static int sanitizeValue(int val, int min, int max) {
        if (val > max) {
            return val % max;
        } else if (val < min) {
            return max - val;
        }

        return -1;
    }

    public static void printBoard(List<List<Integer>> board) {
        StringBuilder builder = new StringBuilder();
        for (List<Integer> line : board) {
            for (int i : line) {
                builder.append(getStateString(i));
            }
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }

    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        List<String> input = FileUtil.loadFile(directory + "/src/pepperlola/input.txt");
        System.out.println("PART 1: " + part1(input));
        System.out.println("PART 2: " + part2(input));
    }
}
