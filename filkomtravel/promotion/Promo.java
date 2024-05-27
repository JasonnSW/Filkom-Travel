package promotion;

import java.util.ArrayList;

public class Promo {
    private String idPromo;
    private String namaPromo;
    private String tanggalMulai;
    private String tanggalSelesai;
    private int diskon;

    public Promo(String idPromo, String namaPromo, String tanggalMulai, String tanggalSelesai, int diskon) {
        this.idPromo = idPromo;
        this.namaPromo = namaPromo;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.diskon = diskon;
    }

    public String getIdPromo() {
        return idPromo;
    }

    public static boolean idExists(String id, ArrayList<Promo> promoList) {
        for (Promo promo : promoList) {
            if (promo.getIdPromo().equals(id)) {
                return true;
            }
        }
        return false;
    }
}