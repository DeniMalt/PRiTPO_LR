package src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputLines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) break;
            inputLines.add(line);
        }
        scanner.close();

        int idx = 0;
        while (idx < inputLines.size()) {
            String[] dimensions = inputLines.get(idx).split("\\s+");
            int m = Integer.parseInt(dimensions[0]);
            int n = Integer.parseInt(dimensions[1]);
            idx++;

            int[][] matrix = new int[m][n];
            for (int i = 0; i < m && idx < inputLines.size(); i++) {
                String[] row = inputLines.get(idx).split("\\s+");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(row[j]);
                }
                idx++;
            }

            Result result = minTravelPath(m, n, matrix);

            for (int num : result.path) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println(result.cost);
        }
    }

    static class Result {
        int[] path;
        int cost;

        Result(int[] path, int cost) {
            this.path = path;
            this.cost = cost;
        }
    }

    private static Result minTravelPath(int m, int n, int[][] matrix) {
        int[][] dp = new int[m][n];
        int[][] parent = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0];
            parent[i][0] = -1;
        }

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int di : new int[]{-1, 0, 1}) {
                    int prevRow = Math.floorMod(i + di, m);
                    if (dp[prevRow][j-1] + matrix[i][j] < dp[i][j] ||
                            (dp[prevRow][j-1] + matrix[i][j] == dp[i][j] && prevRow < parent[i][j])) {

                        dp[i][j] = dp[prevRow][j-1] + matrix[i][j];
                        parent[i][j] = prevRow;
                    }
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        int endRow = -1;
        for (int i = 0; i < m; i++) {
            if (dp[i][n-1] < minCost) {
                minCost = dp[i][n-1];
                endRow = i;
            }
        }

        int[] path = new int[n];
        int currentRow = endRow;
        for (int j = n-1; j >= 0; j--) {
            path[j] = currentRow + 1;
            currentRow = j > 0 ? parent[currentRow][j] : -1;
        }

        return new Result(path, minCost);
    }
}