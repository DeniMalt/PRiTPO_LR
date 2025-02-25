package src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num_cases = Integer.parseInt(scanner.nextLine());
        List<List<String>> case_data = new ArrayList<>();
        List<List<String>> results = new ArrayList<>();

        for (int i = 0; i < num_cases; i++) {
            List<String> block = new ArrayList<>();
            while (true) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                block.add(line);
            }
            case_data.add(block);
        }

        for (List<String> curr_case : case_data) {
            results.add(Decrypt.decrypt(curr_case));
        }

        for (List<String> result : results) {
            for (String s : result) {
                System.out.println(s);
            }
        }
    }
}