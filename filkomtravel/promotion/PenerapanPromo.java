import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

class Pelanggan {
    private String idPemesan;
    private char tipe; // 'A' untuk Anggota, 'G' untuk Tamu
    private LocalDate tanggalDaftar;

    public Pelanggan(String idPemesan, char tipe, LocalDate tanggalDaftar) {
        this.idPemesan = idPemesan;
        this.tipe = tipe;
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getIdPemesan() {
        return idPemesan;
    }

    public char getTipe() {
        return tipe;
    }

    public LocalDate getTanggalDaftar() {
        return tanggalDaftar;
    }
}

class Promo {
    private String kodePromo;
    private double minimalPembelian;
    private LocalDate tanggalMulai;
    private LocalDate tanggalAkhir;

    public Promo(String kodePromo, double minimalPembelian, LocalDate tanggalMulai, LocalDate tanggalAkhir) {
        this.kodePromo = kodePromo;
        this.minimalPembelian = minimalPembelian;
        this.tanggalMulai = tanggalMulai;
        this.tanggalAkhir = tanggalAkhir;
    }

    public String getKodePromo() {
        return kodePromo;
    }

    public double getMinimalPembelian() {
        return minimalPembelian;
    }

    public LocalDate getTanggalMulai() {
        return tanggalMulai;
    }

    public LocalDate getTanggalAkhir() {
        return tanggalAkhir;
    }

    public boolean isExpired(LocalDate tanggalSekarang) {
        return tanggalSekarang.isBefore(tanggalMulai) || tanggalSekarang.isAfter(tanggalAkhir);
    }
}

class PromoService {
    private Map<String, Promo> promoMap = new HashMap<>();

    public void addPromo(Promo promo) {
        promoMap.put(promo.getKodePromo(), promo);
    }

    public String applyPromo(Pelanggan pelanggan, String kodePromo, double totalPembelian) {
        LocalDate sekarang = LocalDate.now();
        Promo promo = promoMap.get(kodePromo);

        if (promo == null) {
            return "APPLY_PROMO FAILED: Invalid KodePromo";
        }

        if (promo.isExpired(sekarang)) {
            return "APPLY_PROMO FAILED: " + kodePromo + " is EXPIRED";
        }

        long umurAkun = java.time.temporal.ChronoUnit.DAYS.between(pelanggan.getTanggalDaftar(), sekarang);

        if (umurAkun <= 30) {
            return "APPLY_PROMO FAILED: " + kodePromo;
        }

        if (totalPembelian < promo.getMinimalPembelian()) {
            return "APPLY_PROMO FAILED: " + kodePromo;
        }

        return "APPLY_PROMO SUCCESS: " + kodePromo;
    }
}
