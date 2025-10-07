import java.util.Scanner;

public class cjuraganikan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read grid size
        int N = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Read the grid
        char[][] grid = new char[N][N];
        for (int i = 0; i < N; i++) {
            String row = scanner.nextLine();
            for (int j = 0; j < N; j++) {
                grid[i][j] = row.charAt(j);
            }
        }

        // 1 1 2 2 3
        // 1 1 3 4 6
        // 2 2 4 6 8
        // 3 4 7 9 11
        // 3 4 7 9 12

        // Build prefix sum array
        int[][] prefixSum = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
                if (grid[i - 1][j - 1] == '*') {
                    prefixSum[i][j]++;
                }
            }
        }

        // Read number of queries
        int Q = scanner.nextInt();

        // Process each query
        for (int q = 0; q < Q; q++) {
            int L = scanner.nextInt();

            // Calculate the number of fish in the LxL square starting at (0, 0)
            int fishCount = prefixSum[L][L];
            System.out.println(fishCount);
        }

        scanner.close();
    }
}