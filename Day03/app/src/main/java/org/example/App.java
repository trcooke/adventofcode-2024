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
            Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String found = line.substring(matcher.start(), matcher.end());
                String nums = found.substring(found.indexOf("(") + 1, found.lastIndexOf(")"));
                String[] args = nums.split(",");
                instructionTally += Integer.parseInt(args[0]) * Integer.parseInt(args[1]);
            }
        }
        return instructionTally;
    }

    long part2() throws IOException {
        BufferedReader reader = getInput("input-test");
        int instructionTally = 0;
        for (String line; (line = reader.readLine()) != null;) {
            Pattern pattern = Pattern.compile("(mul\\([0-9]{1,3},[0-9]{1,3}\\))");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String found = line.substring(matcher.start(), matcher.end());
                System.out.println(found);
//                String nums = found.substring(found.indexOf("(") + 1, found.lastIndexOf(")"));
//                String[] args = nums.split(",");
//                instructionTally += Integer.parseInt(args[0]) * Integer.parseInt(args[1]);
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
