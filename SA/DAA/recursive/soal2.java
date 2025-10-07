package DAA.recursive;

import java.util.Scanner;

public class soal2 {
    static int count = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nilai n: ");
        int n = input.nextInt();
        System.out.println("2^" + n + " = " + twoToThePowerOf(n) + " atau " + Math.pow(2, n));
        System.out.println(count + " kali rekursi");
    }

    public static int twoToThePowerOf(int n) {
        if (n == 0) {
            return 1;
        } else {
            count++;
            return 2 * twoToThePowerOf(n - 1);
        }
    }
}
