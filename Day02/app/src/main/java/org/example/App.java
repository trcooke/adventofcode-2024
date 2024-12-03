package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

    int part1() throws IOException {
        BufferedReader reader = getInput("input");
        int safeCount = 0;
        for (String line; (line = reader.readLine()) != null;) {
            String[] levels = line.split(" ");
            boolean isSafe = isSafe(levels);
            safeCount += isSafe ? 1 : 0;
            System.out.println(line + " " + isSafe);
        }
        return safeCount;
    }

    private boolean isSafe(String[] levels) {
        int lastLevel = 0;
        boolean isIncreasing = false;
        for (int i = 0; i < levels.length; i++) {
            int thisLevel = Integer.parseInt(levels[i]);
            if (i == 1) {
                isIncreasing = thisLevel > lastLevel;
            }
            if (i > 0) {
                if (Math.abs(thisLevel - lastLevel) > 3 || Math.abs(thisLevel - lastLevel) < 1) {
                    return false;
                }
            }
            if (i > 1) {
                boolean isThisIncreasing = thisLevel > lastLevel;
                if (isThisIncreasing != isIncreasing) {
                    return false;
                }
            }
            lastLevel = thisLevel;
        }
        return true;
    }

    long part2() throws IOException {
        BufferedReader reader = getInput("input");
        int safeCount = 0;
        for (String line; (line = reader.readLine()) != null;) {
            String[] levels = line.split(" ");
            boolean isSafe = isSafe(levels);
            if (!isSafe) {
                List<String[]> levelsVariants = computeLevelsVariants(levels);
                for (String[] levelsVariant : levelsVariants) {
                    if (isSafe(levelsVariant)) {
                        isSafe = true;
                        break;
                    }
                }
            }
            safeCount += isSafe ? 1 : 0;
            System.out.println(line + " " + isSafe);
        }
        System.out.println("Char check: " + "P".compareTo("p"));
        return safeCount;
    }

    private List<String[]> computeLevelsVariants(String[] levels) {
        List<String[]> levelsVariants = new ArrayList<>();
        for (int i = 0; i < levels.length; i++) {
            List<String> newLevels = new ArrayList<>();
            for (int j = 0; j < levels.length; j++) {
                if (i != j) {
                    newLevels.add(levels[j]);
                }
            }
            levelsVariants.add(newLevels.toArray(new String[0]));
        }
        return levelsVariants;
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
}
