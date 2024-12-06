package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class App {

    int part1() throws IOException {
        BufferedReader reader = getInput("input");
        List<String> input = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            input.add(line);
        }
        int xmasCount = 0;
        String xmasString = "XMAS";
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(row).length(); col++) {
                if (input.get(row).charAt(col) == 'X') {
                    Position positionOfX = new Position(row, col);
                    for (Direction direction : Direction.values()) {
                        List<Position> positionsInDirectionFrom = getPositionsInDirectionFrom(positionOfX, direction, 3);
                        String stringToCheck = getTextAtPositions(positionsInDirectionFrom, input);
                        xmasCount += xmasString.equals(stringToCheck) ? 1 : 0;
                    }
                }
            }
        }
        return xmasCount;
    }

    private String getTextAtPositions(List<Position> positions, List<String> input) {
        String text = "";
        for (Position position : positions) {
            if (position.row < 0 || position.row > input.size() - 1 || position.col < 0 || position.col > input.get(position.row).length() - 1) {
                return text;
            }
            text += input.get(position.row).charAt(position.col);
        }
        return text;
    }

    private List<Position> getPositionsInDirectionFrom(Position positionOfX, Direction direction, int maxPositions) {
        List<Position> positions = new ArrayList<>();
        positions.add(positionOfX);
        return getPositionsInDirectionFromIter(positionOfX, direction, maxPositions, positions);
    }

    private List<Position> getPositionsInDirectionFromIter(Position positionOfX, Direction direction, int maxPositions, List<Position> acc) {
        if (maxPositions == 0) {
            return acc;
        }
        Position nextPosition = direction.nextPosition(positionOfX);
        acc.add(nextPosition);
        return getPositionsInDirectionFromIter(nextPosition, direction, maxPositions - 1, acc);
    }

    public enum Direction {
        UP(-1, 0),
        UPRIGHT(-1, 1),
        UPLEFT(-1, -1),
        DOWN(1, 0),
        DOWNRIGHT(1, 1),
        DOWNLEFT(1, -1),
        LEFT(0, -1),
        RIGHT(0, 1);

        private final int rowIncrement;
        private final int colIncrement;

        Direction(int rowIncrement, int colIncrement) {
            this.rowIncrement = rowIncrement;
            this.colIncrement = colIncrement;
        }

        public Position nextPosition(Position currentPosition) {
            return new Position(currentPosition.row + rowIncrement, currentPosition.col + colIncrement);
        }
    }

    long part2() throws IOException {
        BufferedReader reader = getInput("input");
        List<String> input = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            input.add(line);
        }
        int xmasCount = 0;
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(row).length(); col++) {
                if (input.get(row).charAt(col) == 'A') {
                    Position positionOfA = new Position(row, col);
                    String text1 = getTextAtPositions(getPositionsInDirectionFrom(Direction.UPLEFT.nextPosition(positionOfA), Direction.DOWNRIGHT, 2), input);
                    String text2 = getTextAtPositions(getPositionsInDirectionFrom(Direction.UPRIGHT.nextPosition(positionOfA), Direction.DOWNLEFT, 2), input);
                    System.out.println(text1 + " " + text2);
                    xmasCount += ("MAS".equals(text1) || "SAM".equals(text1)) && ("MAS".equals(text2) || "SAM".equals(text2)) ? 1 : 0;
                }
            }
        }
        return xmasCount;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        App solution = new App();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private static class Position {
        private final int row;
        private final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "[" + row + "," + col + ']';
        }
    }
}
