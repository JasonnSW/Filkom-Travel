package customer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import promotion.Promotion;
import vehicle.Menu;

public class Member extends Customer {
    private String nama;
    private LocalDate tanggalDaftar;

    public Member(String id, String nama, LocalDate tanggalDaftar, double saldoAwal) {
        super(id, saldoAwal);
        this.nama = nama;
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getNama() {
        return nama;
    }

    public LocalDate getTanggalDaftar() {
        return tanggalDaftar;
    }

    public static boolean idExists(String id, LinkedList<Customer> customers) {
        for (Customer customer : customers) {
            if (customer instanceof Member) {
                Member member = (Member) customer;
                if (member.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public long calculateTanggalAkhir() {
        return ChronoUnit.DAYS.between(tanggalDaftar, LocalDate.now());
    }

    @Override
    public void applyPromo(String idMenu, int qty, LocalDate tanggalAwal, String promoCode,
            ArrayList<Promotion> promoList, int minimumPurchase, Map<String, ArrayList<Menu>> menuOrder) {
        System.out.println("APPLY_PROMO FAILED: " + promoCode);
    }

}
