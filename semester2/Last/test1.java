public class test1 {
    public static void main(String[] args) {
        java.util.Scanner than = new java.util.Scanner(System.in);
        int n = than.nextInt();
        int count = 1;
        while (n > 1) {
            n /= 2;
            count++;

        }
        System.out.println(count);
    }
}