import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Promo {
    protected String kodePromo;
    protected String tanggalAwal;
    protected String tanggalAkhir;
    protected int persenPotongan;
    protected int maksPotongan;
    protected int minPembelian;

    public Promo(String kodePromo, String tanggalAwal, String tanggalAkhir, int persenPotongan, int maksPotongan, int minPembelian) {
        this.kodePromo = kodePromo;
        this.tanggalAwal = tanggalAwal;
        this.tanggalAkhir = tanggalAkhir;
        this.persenPotongan = persenPotongan;
        this.maksPotongan = maksPotongan;
        this.minPembelian = minPembelian;
    }

    public String getKodePromo() {
        return kodePromo;
    }

    @Override
    public abstract String toString();
}

class DiscountPromo extends Promo {
    public DiscountPromo(String kodePromo, String tanggalAwal, String tanggalAkhir, int persenPotongan, int maksPotongan, int minPembelian) {
        super(kodePromo, tanggalAwal, tanggalAkhir, persenPotongan, maksPotongan, minPembelian);
    }

    @Override
    public String toString() {
        return "Discount Promo: " + kodePromo + ", Berlaku: " + tanggalAwal + " sampai " + tanggalAkhir + ", Potongan: " + persenPotongan + "%, Maksimum Potongan: " + maksPotongan + ", Minimum Pembelian: " + minPembelian;
    }
}

class CashbackPromo extends Promo {
    public CashbackPromo(String kodePromo, String tanggalAwal, String tanggalAkhir, int persenPotongan, int maksPotongan, int minPembelian) {
        super(kodePromo, tanggalAwal, tanggalAkhir, persenPotongan, maksPotongan, minPembelian);
    }

    @Override
    public String toString() {
        return "Cashback Promo: " + kodePromo + ", Berlaku: " + tanggalAwal + " sampai " + tanggalAkhir + ", Cashback: " + persenPotongan + "%, Maksimum Cashback: " + maksPotongan + ", Minimum Pembelian: " + minPembelian;
    }
}

class SistemPromo {
    private List<Promo> promoList = new ArrayList<>();

    public String createPromo(String jenisPromo, String kodePromo, String tanggalAwal, String tanggalAkhir, int persenPotongan, int maksPotongan, int minPembelian) {
        for (Promo promo : promoList) {
            if (promo.getKodePromo().equals(kodePromo)) {
                return "CREATE PROMO " + jenisPromo + " FAILED: " + kodePromo + " IS EXISTS";
            }
        }
        Promo promo;
        if (jenisPromo.equalsIgnoreCase("DISCOUNT")) {
            promo = new DiscountPromo(kodePromo, tanggalAwal, tanggalAkhir, persenPotongan, maksPotongan, minPembelian);
        } else if (jenisPromo.equalsIgnoreCase("CASHBACK")) {
            promo = new CashbackPromo(kodePromo, tanggalAwal, tanggalAkhir, persenPotongan, maksPotongan, minPembelian);
        } else {
            return "CREATE PROMO " + jenisPromo + " FAILED: Invalid Promo Type";
        }
        promoList.add(promo);
        return "CREATE PROMO " + jenisPromo + " SUCCESS: " + kodePromo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Promo promo : promoList) {
            sb.append(promo).append("\n");
        }
        return sb.toString();
    }
}

public class KodePromo {
    public static void main(String[] args) {
        SistemPromo sistem = new SistemPromo();
        Scanner scanner = new Scanner(System.in);
        List<String> outputResults = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("EXIT")) {
                for (String result : outputResults) {
                    System.out.println(result);
                }
                break;
            }

            if (input.startsWith("CREATE PROMO")) {
                String jenisPromo = input.split(" ")[2];
                String detailPromo = scanner.nextLine();
                String[] details = detailPromo.split("\\|");

                if (details.length == 6) {
                    String kodePromo = details[0];
                    String tanggalAwal = details[1];
                    String tanggalAkhir = details[2];
                    int persenPotongan = Integer.parseInt(details[3].replace("%", ""));
                    int maksPotongan = Integer.parseInt(details[4]);
                    int minPembelian = Integer.parseInt(details[5]);

                    String result = sistem.createPromo(jenisPromo, kodePromo, tanggalAwal, tanggalAkhir, persenPotongan, maksPotongan, minPembelian);
                    outputResults.add(result);
                } else {
                    outputResults.add("Format detail promo tidak valid.");
                }
            } else {
                outputResults.add("Perintah tidak valid.");
            }
        }

        scanner.close();
    }
}
