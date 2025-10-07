import java.util.Scanner;

public class UmayumAchoo {

    public static void main(String[] args) {
        Scanner than = new Scanner(System.in);
        int loop = than.nextInt();
        than.nextLine();
        for (int i = 0; i < loop; i++) {
            int baris = than.nextInt();
            than.nextLine();
            String barisan = than.nextLine();
            boolean pandemiDimulai = false;
            int count = 0;
            int max = 0;
            for (int j = 0; j < baris; j++) {
                if (barisan.charAt(j) == 'P') {
                    pandemiDimulai = true;
                    count = 0;
                    continue;
                }
                if (pandemiDimulai) {
                    count++;
                    if (count > max) {
                        max = count;
                    }
                }

            }
            System.out.println(max);
        }
    }
}