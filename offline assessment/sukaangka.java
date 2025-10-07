import java.util.Scanner;

public class sukaangka {

    public static void main(String[] args) {
        Scanner than = new Scanner(System.in);
        double input = than.nextDouble(); // Read input as integer
        String result = (input % 1 == 0 && input % 2 == 0 && input % 3 == 0 && input % 4 == 0 && input % 5 == 0)
                ? "YA" // If true
                : "TIDAK"; // If false
        System.out.println(result);
    }
}