import java.util.Scanner;

public class MisiRahasia {
    public static void main(String[] args) {
        Scanner than = new Scanner(System.in);
        while (true) {
            double jarak = than.nextDouble();
            boolean belumCrot = true;
            int[] movement = { 8, 7, 5, 3 };
            int count = 0;
            int answer1 = 0;
            int answer2 = 0;
            int finalanswer1 = 0;

            if (jarak % 8 == 0) {
                finalanswer1 = ((int) jarak / 8);
            }
            if (jarak % 7 == 0) {
                finalanswer1 = ((int) jarak / 7);
            }
            if (jarak % 5 == 0) {
                finalanswer1 = ((int) jarak / 5);
            }
            if (jarak % 3 == 0) {
                finalanswer1 = ((int) jarak / 3);
            }

            for (int i : movement) {

                if (jarak == 0) {
                    answer2 = count;
                    belumCrot = false;
                    break;
                }

                jarak -= i;
                count++;
                if (jarak < 0 || jarak < 3) {
                    jarak += i;
                    count--;
                }
                if (jarak % 8 == 0) {
                    answer1 = ((int) jarak / 8);
                    jarak = 0;
                    count += answer1;
                }
                if (jarak % 7 == 0) {
                    answer1 = ((int) jarak / 7);
                    jarak = 0;
                    count += answer1;
                }
                if (jarak % 5 == 0) {
                    answer1 = ((int) jarak / 5);
                    jarak = 0;
                    count += answer1;
                }
                if (jarak % 3 == 0) {
                    answer1 = ((int) jarak / 3);
                    jarak = 0;
                    count += answer1;

                }

            }
            if (finalanswer1 == 0) {
                finalanswer1 = Integer.MAX_VALUE;
            } else if (answer2 == 0) {
                answer2 = Integer.MAX_VALUE;
            }
            if (finalanswer1 == Integer.MAX_VALUE && answer2 == Integer.MAX_VALUE) {
                System.out.println(0);
            } else
                System.out.println(Math.min(finalanswer1, answer2));

        }

    }
}