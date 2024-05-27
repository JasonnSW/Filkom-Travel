import java.util.ArrayList;
import java.util.List;

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

class KodePromo extends Promo {
    public KodePromo(String kodePromo, String tanggalAwal, String tanggalAkhir, int persenPotongan, int maksPotongan, int minPembelian) {
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
            promo = new KodePromo(kodePromo, tanggalAwal, tanggalAkhir, persenPotongan, maksPotongan, minPembelian);
        } else {
            return "CREATE PROMO " + jenisPromo + " FAILED: KodePromo IS EXISTS";
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
