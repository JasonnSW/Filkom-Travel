import java.util.Scanner;

public class UhuyKemah {
    public static void main(String[] args) {
        Scanner than = new Scanner(System.in);
        int loop = than.nextInt();
        than.nextLine();
        for (int i = 0; i < loop; i++) {
            String[] input = than.nextLine().split(" ");
            double pemalu = Integer.parseInt(input[0]);
            double pemberani = Integer.parseInt(input[1]);
            double penormal = Integer.parseInt(input[2]);
            if (pemberani % 3 == 1 && penormal < 2 || pemberani % 3 == 2 && penormal == 0) {
                System.out.println(-1);
                continue;
            }
            double tendaPemalu = pemalu;
            double tendaLain = Math.ceil((pemberani + penormal) / 3);
            double total = tendaPemalu + tendaLain;
            System.out.println(((long) total));
        }
    }
}
