package customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Date;
import promotion.Promotion;
import vehicle.Menu;

/**
 * The Guest class represents a guest user in the system.
 * It extends the Customer class and provides additional functionality for guest
 * users.
 */
public class Guest extends Customer {

    public Guest(String id, double saldoAwal) {
        super(id, saldoAwal);
    }

    public static boolean idExists(String id, LinkedList<Customer> customers) {
        for (Customer customer : customers) {
            if (customer instanceof Guest) {
                Guest guest = (Guest) customer;
                if (guest.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    // public void topUpBalance(double amount) {
    // this.saldoAwal += amount;
    // }

    // public double getBalance() {
    // return this.saldoAwal;
    // }

    @Override
    public void applyPromo(String idMenu, int qty, LocalDate tanggalAwal, String promoCode,
            ArrayList<Promotion> promoList, int minimumPurchase, Map<String, ArrayList<Menu>> menuOrder) {
        System.out.println("APPLY_PROMO FAILED: " + promoCode);
    }

    // public String applyPromo(String promoCode, ArrayList<Promotion> promoList,
    // LocalDate currentDate,
    // int minimumPurchase) {

    // }
    @Override
    public long calculateTanggalAkhir() {
        return 0;
    }
}
