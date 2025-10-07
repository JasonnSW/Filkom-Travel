import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

class test {
    public static void main(String[] args) {
        Scanner than = new Scanner(System.in);
        int n = than.nextInt();
        than.nextLine();
        boolean[] isPrime = new boolean[1000001];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i <= 1000000; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= 1000000; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= 1000000; i++) {
            if (isPrime[i]) {
                primeNumbers.add(i);
            }
        }

        int[] primeArray = new int[primeNumbers.size()];
        for (int i = 0; i < primeNumbers.size(); i++) {
            primeArray[i] = primeNumbers.get(i);
            System.out.println(primeArray[i]);
        }

        for (int i = 0; i < n; i++) {
            int count = 0;
            double input = than.nextDouble();
            than.nextLine();
            for (int j = 0; j < primeArray.length; j++) {
                double tmp = input / primeArray[j];
                if (tmp == (int) tmp) {
                    count++;
                }
            }
            if (count <= 4 && count > 0) {
                System.out.println("YA");
            } else {
                System.out.println("BUKAN");
            }
        }
    }

}
