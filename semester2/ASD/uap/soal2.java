package uap;

public class soal2 {
    public static void main(String[] args) {
        java.util.Scanner than = new java.util.Scanner(System.in);
        int T = than.nextInt();
        than.nextLine();

        for (int t = 0; t < T; t++) {
            int N = than.nextInt();
            int K = than.nextInt();
            int[] heights = new int[N];

            for (int i = 0; i < N; i++) {
                heights[i] = than.nextInt();
            }

            if (than.hasNextLine()) {
                than.nextLine();
            }

            System.out.println(KakRyoMinecraft.mencariMaxS(heights, N, K));
        }
    }
}

class KakRyoMinecraft {
    static int low, high, max, requiredBlocks;

    public static int getLow() {
        return low;
    }

    public static void setLow(int low) {
        KakRyoMinecraft.low = low;
    }

    public static int getHigh() {
        return high;
    }

    public static void setHigh(int high) {
        KakRyoMinecraft.high = high;
    }

    public static int getMax() {
        return max;
    }

    public static void setMax(int max) {
        KakRyoMinecraft.max = max;
    }

    public static int getRequiredBlocks() {
        return requiredBlocks;
    }

    public static void setRequiredBlocks(int requiredBlocks) {
        KakRyoMinecraft.requiredBlocks = requiredBlocks;
    }

    public static int mencariMaxS(int[] heights, int N, int K) {
        low = 1;
        high = getMaxHeight(heights) + K;

        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (canAchieveHeight(heights, N, K, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    private static int getMaxHeight(int[] heights) {
        max = heights[0];
        for (int height : heights) {
            if (height > max) {
                max = height;
            }
        }
        return max;
    }

    public static boolean canAchieveHeight(int[] heights, int N, int K, int targetHeight) {
        requiredBlocks = 0;

        for (int i = 0; i < N; i++) {
            if (heights[i] < targetHeight) {
                requiredBlocks += (targetHeight - heights[i]);
            }

            if (requiredBlocks > K) {
                return false;
            }
        }

        return requiredBlocks <= K;
    }
}