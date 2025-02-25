package src;

import java.util.*;

public class Main {
    public static String add(String a, String b) {
        String s = "";
        a = String.valueOf(new StringBuilder(a).reverse());
        b = String.valueOf(new StringBuilder(b).reverse());
        int i = 0;
        int k = 0;

        while (i < a.length() && i < b.length()) {
            int m = Integer.parseInt(String.valueOf(a.charAt(i))) + Integer.parseInt(String.valueOf(b.charAt(i))) + k;
            k = m / 10;
            s += String.valueOf(m % 10);
            i += 1;
        }

        while (i < a.length()) {
            int m = k + Integer.parseInt(String.valueOf(a.charAt(i)));
            k = m / 10;
            s += String.valueOf(m % 10);
            i += 1;
        }

        while (i < b.length()) {
            int m = k + Integer.parseInt(String.valueOf(b.charAt(i)));
            k = m / 10;
            s += String.valueOf(m % 10);
            i += 1;
        }

        if (k != 0) {
            s += String.valueOf(k);
        }

        return String.valueOf(new StringBuilder(s).reverse());
    }


    public static List<String> solve() {
        List<String> v = new ArrayList<>(List.of(new String[]{"0", "2", "5", "13"}));
        String s;

        for (int i = 4; i < 1001; i++) {
            s = add(v.get(i - 1), v.get(i - 1));
            s = add(v.get(i - 2), s);
            s = add(v.get(i - 3), s);
            v.add(s);
        }

        return v;
    }

    public static void main(String[] args) {
        List<Integer> input_numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        List<String> solution = solve();

        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                input_numbers.add(Integer.parseInt(line));
            }
            catch (NumberFormatException e) {
                break;
            }
        }

        for (Integer i : input_numbers) {
            System.out.println(solution.get(i));
        }
    }
}
