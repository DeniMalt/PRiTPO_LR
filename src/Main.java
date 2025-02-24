import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int scenario = 1;

        while (T > 0) {
            T -= 1;
            ArrayList<String> articles = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();

            int P = scanner.nextInt();
            int N = scanner.nextInt();

            for (int i = 0; i <= P; i++) {
                String input = scanner.nextLine();
                if (!input.isEmpty()) {
                    articles.add(input.trim());
                }
            }

            for (int i = 0; i < N; i++) {
                String input = scanner.nextLine();
                if (!input.isEmpty()) {
                    names.add(input.trim());
                }
            }

            HashMap<String, Integer> Erdos_numbers = ErdosNumber.calc_erdos_numbers(articles);

            System.out.println("\nScenario " + scenario);
            for (String name : names) {
                ErdosNumber.print_erdos_numbers(Erdos_numbers, name);
            }

            scenario += 1;
        }
    }
}