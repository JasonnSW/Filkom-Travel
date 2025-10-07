import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    static public ArrayList<Tanaman> tanamans = new ArrayList<>();
    static public ArrayList<Integer> lokasi = new ArrayList<>();
    static public Scanner in = new Scanner(System.in);

    static public void mulai() {
        System.out.print("Masa Tanam (bulan) : ");
        double masaTanam = in.nextInt();
        in.nextLine();

        masaTanam = (int) masaTanam * 30;
        Data.menanam();
        Data.menanam();
        Data.menanam();
        System.out.println("\n");
        System.out.println("-".repeat(19));
        System.out.println("PROSES PERKEMBANGAN");
        System.out.println("-".repeat(19));
        System.out.println("\n");

        for (int i = 1; i <= masaTanam; i++) {
            if (i % 90 == 0) {
                System.out.println("\n");
                System.out.println("-".repeat(19));
                System.out.println("DILAKUKAN PERAWATAN");
                System.out.println("-".repeat(19));
                System.out.println("\n");

            }
            for (int j = 0; j < tanamans.size(); j++) {
                boolean termasuk = false;
                for (int k = 0; k < lokasi.size(); k++) {
                    if (j == lokasi.get(k))
                        termasuk = true;
                }
                if (termasuk)
                    continue;
                if (tanamans.get(j).status().equals("Hidup")) {
                    if (i % 90 == 0) {
                        if (tanamans.get(j) instanceof Tomat)
                            ((Tomat) tanamans.get(j)).treatment();
                        if (tanamans.get(j) instanceof Stroberi)
                            ((Stroberi) tanamans.get(j)).treatment();
                        if (tanamans.get(j) instanceof Persik)
                            ((Persik) tanamans.get(j)).treatment();
                    }
                    tanamans.get(j).berkembang();
                } else if (tanamans.get(j).status().equals("Mati")) {
                    if (tanamans.get(j) instanceof Tomat)
                        System.out.println("Tanaman " + "Tomat " + "telah mati");
                    if (tanamans.get(j) instanceof Stroberi)
                        System.out.println("Tanaman " + "Stroberi " + "telah mati");
                    if (tanamans.get(j) instanceof Persik)
                        System.out.println("Tanaman " + "Persik " + "telah mati");
                    lokasi.add(j);
                    Data.menanam();
                }
            }
        }

    }

    static public void menanam() {
        System.out.println("Mau Menanam Apa? ");
        System.out.println("1. Tomat");
        System.out.println("2. Stroberi");
        System.out.println("3. Persik");

        int n = in.nextInt();
        in.nextLine();
        switch (n) {
            case 1:
                tanamans.add(new Tomat());
                System.out.println("Tomat berhasil ditanam");
                break;
            case 2:
                tanamans.add(new Stroberi());
                System.out.println("Stroberi berhasil ditanam");
                break;
            case 3:
                tanamans.add(new Persik());
                System.out.println("Persik berhasil ditanam");
                break;
            default:
                System.out.println("Tidak ada dalam pilihan!");
                break;
        }

    }

    static public void info() {
        int count = 1;
        System.out.println("------HASIL MENANANM------\n");
        for (Tanaman e : tanamans) {
            System.out.println("Tanaman buah ke-" + count);
            System.out.println(e.toString());
            if (e instanceof Tomat)
                ((Tomat) e).toString();
            if (e instanceof Stroberi)
                ((Stroberi) e).toString();
            if (e instanceof Persik)
                ((Persik) e).toString();
            count++;
        }
    }

}
