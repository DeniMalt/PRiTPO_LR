package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputData = new ArrayList<>();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.equals("0 0")) {
                processInput(inputData);
                break;
            }

            if (line.isEmpty() && !inputData.isEmpty()) {
                processInput(inputData);
                inputData.clear();
            } else if (!line.isEmpty()) {
                inputData.add(line);
            }
        }

        scanner.close();
    }

    private static void processInput(List<String> inputData) {
        if (!inputData.isEmpty()) {
            String[] firstLine = ((String)inputData.get(0)).split("\\s+");
            int n = Integer.parseInt(firstLine[0]);
            int m = Integer.parseInt(firstLine[1]);
            if (n != 0 || m != 0) {
                List<Edge> edges = new ArrayList<>();

                for(int i = 1; i < inputData.size(); ++i) {
                    String[] parts = ((String)inputData.get(i)).split("\\s+");
                    int u = Integer.parseInt(parts[0]);
                    int v = Integer.parseInt(parts[1]);
                    edges.add(new Edge(u, v));
                }

                System.out.println(minDominatingSet(n, edges));
            }
        }
    }

    private static int minDominatingSet(int n, List<Edge> edges) {
        if (n == 0) {
            return 0;
        } else {
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            IntStream.rangeClosed(1, n).forEach((i) -> {
                graph.put(i, new HashSet<>());
            });

            for (Edge edge : edges) {
                if (edge.u != 0 && edge.v != 0) {
                    ((Set) graph.get(edge.u)).add(edge.v);
                    ((Set) graph.get(edge.v)).add(edge.u);
                }
            }

            for(int size = 1; size <= n; ++size) {

                for (Set<Integer> integers : combinations(IntStream.rangeClosed(1, n).boxed().toList(), size)) {
                    Set<Integer> subset = (Set) integers;
                    if (isDominatingSet(graph, subset, n)) {
                        return size;
                    }
                }
            }

            return n;
        }
    }

    private static boolean isDominatingSet(Map<Integer, Set<Integer>> graph, Set<Integer> subset, int n) {
        Set<Integer> covered = new HashSet<>(subset);

        for (int node : subset) {
            covered.addAll(graph.get(node));
        }

        return covered.size() == n;
    }

    private static <T> List<Set<T>> combinations(List<T> elements, int k) {
        List<Set<T>> result = new ArrayList<>();
        combine(elements, k, 0, new HashSet<>(), result);
        return result;
    }

    private static <T> void combine(List<T> elements, int k, int start, Set<T> current, List<Set<T>> result) {
        if (current.size() == k) {
            result.add(new HashSet<>(current));
        } else {
            for(int i = start; i < elements.size(); ++i) {
                current.add(elements.get(i));
                combine(elements, k, i + 1, current, result);
                current.remove(elements.get(i));
            }

        }
    }

    static class Edge {
        int u;
        int v;

        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
}
