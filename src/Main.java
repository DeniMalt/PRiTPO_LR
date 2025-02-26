package src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputData = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                if (!inputData.isEmpty()) {
                    System.out.println(largestEditLadder(inputData));
                    inputData.clear();
                }
                continue;
            }
            inputData.add(line);
        }

        if (!inputData.isEmpty()) {
            System.out.println(largestEditLadder(inputData));
        }
    }

    private static boolean isOneStepAway(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int diff = Math.abs(len1 - len2);

        if (diff > 1) return false;

        if (len1 == len2) {
            int count = 0;
            for (int i = 0; i < len1; i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    count++;
                    if (count > 1) return false;
                }
            }
            return count == 1;
        }

        String longer = len1 > len2 ? word1 : word2;
        String shorter = len1 > len2 ? word2 : word1;

        int i = 0, j = 0;
        int differences = 0;
        while (i < longer.length() && j < shorter.length()) {
            if (longer.charAt(i) != shorter.charAt(j)) {
                if (++differences > 1) return false;
                i++;
            } else {
                i++;
                j++;
            }
        }
        return true;
    }

    private static int largestEditLadder(List<String> words) {
        int n = words.size();
        if (n == 0) return 0;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLength = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (isOneStepAway(words.get(j), words.get(i))) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }

        return maxLength;
    }
}