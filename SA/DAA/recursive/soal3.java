package DAA.recursive;

import java.util.Scanner;

public class soal3 {
    static int count = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nilai n: ");
        int n = input.nextInt();
        System.out.println("Q(" + n + ") = " + Q(n));
        System.out.println(count + " kali rekursi");
    }

    public static int Q(int n) {
        if (n == 1) {
            return 1;
        } else {
            count++;
            return Q(n - 1) + 2 * n - 1;
        }
    }
}
