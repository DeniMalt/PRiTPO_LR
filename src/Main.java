package src;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int M = scanner.nextInt();
            int N = scanner.nextInt();

            if (M == 0 && N == 0) break;

            int[] teamSizes = new int[M];
            for (int i = 0; i < M; i++) {
                teamSizes[i] = scanner.nextInt();
            }

            int[] tableSizes = new int[N];
            for (int i = 0; i < N; i++) {
                tableSizes[i] = scanner.nextInt();
            }

            List<Set<Integer>> tables = new ArrayList<>(N);
            int[] tableCapacity = Arrays.copyOf(tableSizes, N);
            List<List<Integer>> result = new ArrayList<>(M);
            for (int i = 0; i < M; i++) {
                result.add(new ArrayList<>());
            }
            for (int i = 0; i < N; i++) {
                tables.add(new HashSet<>());
            }

            List<Integer> tableIndices = new ArrayList<>();
            for (int i = 0; i < N; i++) tableIndices.add(i);
            tableIndices.sort((a, b) -> Integer.compare(tableSizes[b], tableSizes[a]));

            boolean possible = true;

            for (int teamId = 0; teamId < M; teamId++) {
                for (int memberId = 0; memberId < teamSizes[teamId]; memberId++) {
                    boolean placed = false;

                    for (int tableId : tableIndices) {
                        if (tableCapacity[tableId] > 0 &&
                                !tables.get(tableId).contains(teamId)) {

                            tables.get(tableId).add(teamId);
                            result.get(teamId).add(tableId + 1);
                            tableCapacity[tableId]--;
                            placed = true;
                            break;
                        }
                    }

                    if (!placed) {
                        possible = false;
                        break;
                    }
                }
                if (!possible) break;
            }

            if (possible) {
                System.out.println("\n1");
                for (List<Integer> team : result) {
                    Collections.sort(team);
                    for (int table : team) {
                        System.out.print(table + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("0");
            }
        }
        scanner.close();
    }
}