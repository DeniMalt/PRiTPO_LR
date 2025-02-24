import java.util.*;

public class ErdosNumber {
    public static HashMap<String, Integer> calc_erdos_numbers(ArrayList<String> articles) {
        HashMap<String, Integer> ErdosNumbers = new HashMap<>();
        HashMap<String, List<String>> graph = new HashMap<>();
        Deque<String> queue = new LinkedList<>();

        for (int i = 0; i < articles.toArray().length; i++) {
            List<String> authors = List.of(articles.get(i).split(":")[0].split(", "));
            for (int j = 0; j < authors.size(); j += 2) {
                String author = authors.get(j) + ", " + authors.get(j + 1);
                for (int k = j + 2; k < authors.size(); k += 2) {
                    String coauthor = authors.get(k) + ", " + authors.get(k + 1);
                    graph.computeIfAbsent(author, key -> new ArrayList<>()).add(coauthor);
                    graph.computeIfAbsent(coauthor, key -> new ArrayList<>()).add(author);
                }
            }
        }

        queue.add("Erdos, P.");
        ErdosNumbers.put("Erdos, P.", 0);

        while (!queue.isEmpty()) {
            String author = queue.pop();
            for (int i = 0; i < graph.get(author).size(); i++) {
                if (!ErdosNumbers.containsKey(graph.get(author).get(i))) {
                    ErdosNumbers.put(graph.get(author).get(i), ErdosNumbers.get(author) + 1);
                    queue.add(graph.get(author).get(i));
                }
            }
        }

        return ErdosNumbers;
    }

    public static void print_erdos_numbers(HashMap<String, Integer> ErdosNumbers, String name) {
        if (!ErdosNumbers.containsKey(name)) {
            System.out.println(name + " infinity");
        }
        else {
            System.out.println(name + " " + ErdosNumbers.get(name));
        }
    }
}
