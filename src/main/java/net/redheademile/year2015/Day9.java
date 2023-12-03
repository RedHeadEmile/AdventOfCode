package net.redheademile.year2015;

import net.redheademile.Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

public class Day9 {
    private static String[] getInput() {
        return Main.getInput("2015_9.txt").split("\r\n");
    }

    private static int findBestPath(List<String> remainingCities, List<String> currentPath, BiFunction<String, String, Integer> getDistance, boolean shortest) {
        if (remainingCities.isEmpty()) {
            int distance = 0;
            for (int i = 1; i < currentPath.size(); i++)
                distance += getDistance.apply(currentPath.get(i - 1), currentPath.get(i));
            return distance;
        }

        int bestPath = shortest ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        remainingCities = new ArrayList<>(remainingCities);
        for (int i = 0; i < remainingCities.size(); i++) {
            String city = remainingCities.remove(i);
            currentPath.add(city);
            int bestSubPath = findBestPath(remainingCities, currentPath, getDistance, shortest);
            currentPath.remove(city);
            remainingCities.add(city);

            if ((shortest && bestSubPath < bestPath) || (!shortest && bestSubPath > bestPath))
                bestPath = bestSubPath;
        }

        return bestPath;
    }

    private static InitializedData getInitializedData() {
        String[] input = getInput();

        class Edge {
            String city1;
            String city2;
            int distance;
        }

        List<Edge> edges = new ArrayList<>();
        Set<String> citiesSet = new HashSet<>();
        for (String string : input) {
            String[] detail = string.split(" = ");
            String[] cities = detail[0].split(" to ");

            citiesSet.add(cities[0]);
            citiesSet.add(cities[1]);

            edges.add(new Edge() {{
                city1 = cities[0];
                city2 = cities[1];
                distance = Integer.parseInt(detail[1]);
            }});
        }

        BiFunction<String, String, Integer> getDistanceFn = (city1, city2) -> {
            for (Edge edge : edges)
                if ((edge.city1.equals(city1) && edge.city2.equals(city2))
                        || (edge.city2.equals(city1) && edge.city1.equals(city2)))
                    return edge.distance;
            return null;
        };

        return new InitializedData() {{
            cities = new ArrayList<>(citiesSet);
            getDistance = getDistanceFn;
        }};
    }

    public static void partOne() {
        InitializedData initializedData = getInitializedData();
        List<String> cities = initializedData.cities;
        BiFunction<String, String, Integer> getDistance = initializedData.getDistance;

        int bestPath = findBestPath(cities, new ArrayList<>(), getDistance, true);
        System.out.println("[Part 1] Shortest path distance: " + bestPath);
    }

    public static void partTwo() {
        InitializedData initializedData = getInitializedData();
        List<String> cities = initializedData.cities;
        BiFunction<String, String, Integer> getDistance = initializedData.getDistance;

        int bestPath = findBestPath(cities, new ArrayList<>(), getDistance, false);
        System.out.println("[Part 2] Longest path distance: " + bestPath);
    }

    private static class InitializedData {
        List<String> cities;
        BiFunction<String, String, Integer> getDistance;
    }
}
