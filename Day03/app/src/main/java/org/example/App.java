package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    int part1() throws IOException {
        BufferedReader reader = getInput("input");
        int instructionTally = 0;
        for (String line; (line = reader.readLine()) != null;) {
            instructionTally += instructionSum(line);
        }
        return instructionTally;
    }

    private int instructionSum(String input) {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Matcher matcher = pattern.matcher(input);
        int instructionTally = 0;
        while (matcher.find()) {
            String found = input.substring(matcher.start(), matcher.end());
            String nums = found.substring(found.indexOf("(") + 1, found.lastIndexOf(")"));
            String[] args = nums.split(",");
            instructionTally += Integer.parseInt(args[0]) * Integer.parseInt(args[1]);
        }
        return instructionTally;
    }

    long part2() throws IOException {
        BufferedReader reader = getInput("input");
        int instructionTally = 0;
        boolean enabled = true;
        for (String line; (line = reader.readLine()) != null;) {
            String thisLine = line;
            while (thisLine.length() > 0) {
                if (enabled) {
                    int enabledInstructionsEndIndex = thisLine.indexOf("don't()");
                    if (enabledInstructionsEndIndex == -1) {
                        instructionTally += instructionSum(thisLine);
                        thisLine = "";
                    } else {
                        enabled = false;
                        instructionTally += instructionSum(thisLine.substring(0, enabledInstructionsEndIndex));
                        thisLine = thisLine.substring(enabledInstructionsEndIndex + "don't()".length());
                    }
                } else {
                    int disabledInstructionsEndIndex = thisLine.indexOf("do()");
                    if (disabledInstructionsEndIndex == -1) {
                        thisLine = "";
                    } else {
                        enabled = true;
                        thisLine = thisLine.substring(disabledInstructionsEndIndex + "do()".length());
                    }
                }
            }
        }
        return instructionTally;
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
