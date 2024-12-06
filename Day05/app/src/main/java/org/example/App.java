package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class App {

    int part1() throws IOException {
        BufferedReader reader = getInput("input");
        List<String> comparatorRules = new ArrayList<>();
        boolean secondSection = false;
        int sumMiddleVals = 0;
        for (String line; (line = reader.readLine()) != null;) {
            if (line.isBlank()) {
                secondSection = true;
                continue;
            }
            if (!secondSection) {
                comparatorRules.add(line);
            }
            Comparator<Integer> pageComparator = new PageComparator(comparatorRules);
            if (secondSection) {
                String[] vals = line.split(",");
                List<Integer> pages = new ArrayList<>();
                for (String val : vals) {
                    pages.add(Integer.parseInt(val));
                }
                List<Integer> originalPages = new ArrayList<>(pages);
                pages.sort(pageComparator);
                if (originalPages.equals(pages)) {
                    sumMiddleVals += pages.get(pages.size() / 2);
                }
            }
        }
        return sumMiddleVals;
    }

    long part2() throws IOException {
        BufferedReader reader = getInput("input");
        List<String> comparatorRules = new ArrayList<>();
        boolean secondSection = false;
        int sumMiddleVals = 0;
        for (String line; (line = reader.readLine()) != null;) {
            if (line.isBlank()) {
                secondSection = true;
                continue;
            }
            if (!secondSection) {
                comparatorRules.add(line);
            }
            Comparator<Integer> pageComparator = new PageComparator(comparatorRules);
            if (secondSection) {
                String[] vals = line.split(",");
                List<Integer> pages = new ArrayList<>();
                for (String val : vals) {
                    pages.add(Integer.parseInt(val));
                }
                List<Integer> originalPages = new ArrayList<>(pages);
                pages.sort(pageComparator);
                if (!originalPages.equals(pages)) {
                    sumMiddleVals += pages.get(pages.size() / 2);
                }
            }
        }
        return sumMiddleVals;
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

    private static class PageComparator implements Comparator<Integer> {
        private final List<Integer[]> pageRules = new ArrayList<>();

        public PageComparator(List<String> comparatorRules) {
            for (String comparatorRule : comparatorRules) {
                String[] rule = comparatorRule.split("\\|");
                Integer[] pageRule = new Integer[rule.length];
                pageRule[0] = Integer.valueOf(rule[0]);
                pageRule[1] = Integer.valueOf(rule[1]);
                pageRules.add(pageRule);
            }
        }

        @Override
        public int compare(Integer a, Integer b) {
            for (Integer[] pageRule : pageRules) {
                if (a.equals(pageRule[0]) && b.equals(pageRule[1])) {
                    return -1;
                }
                if (a.equals(pageRule[1]) && b.equals(pageRule[0])) {
                    return 1;
                }
            }
            return 0;
        }
    }
}
