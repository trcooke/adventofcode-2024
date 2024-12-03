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
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            String[] locationIds = line.split(" {3}");
            list1.add(Integer.parseInt(locationIds[0]));
            list2.add(Integer.parseInt(locationIds[1]));
        }
        list1.sort(Integer::compareTo);
        list2.sort(Integer::compareTo);
        int totalDistance = 0;
        for (int i = 0; i < list1.size(); i++) {
            totalDistance += Math.abs(list1.get(i) - list2.get(i));
        }
        return totalDistance;
    }

    long part2() throws IOException {
        BufferedReader reader = getInput("input");
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            String[] locationIds = line.split(" {3}");
            list1.add(Integer.parseInt(locationIds[0]));
            list2.add(Integer.parseInt(locationIds[1]));
        }
        long similarityScore = 0;
        for (Integer id1 : list1) {
            similarityScore += id1 * list2.stream().filter(item -> item.equals(id1)).count();
        }
        return similarityScore;
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
