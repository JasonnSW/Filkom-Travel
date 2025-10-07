import java.util.*;

public class test {
    public static void main(String[] args) {
        Scanner than = new Scanner(System.in);
        int loop = than.nextInt();
        than.nextLine();
        for (int i = 0; i < loop; i++) {
            String[] input = than.nextLine().split(" ");
            int size = Integer.parseInt(input[0]);
            int speed = Integer.parseInt(input[1]);
            boolean isUploading = true;
            int minute = 0;
            int tmp = size;
            while (isUploading) {
                minute++;
                if (minute == 1 || minute % speed == 0) {
                    tmp -= 1;
                }
                if (tmp == 0) {
                    isUploading = false;
                }

            }
            if (speed == 1 || size == 1) {
                System.out.println(minute);

            } else
                System.out.println(minute + 1);
        }
    }
}
