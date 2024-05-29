package customer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Date;
import promotion.Promotion;

/**
 * The Guest class represents a guest user in the system.
 * It extends the Customer class and provides additional functionality for guest
 * users.
 */
public class Guest extends Customer {
    private String id;
    private String nama;
    private double saldoAwal;

    public Guest(String nama, String id, double saldoAwal) {
        super(id, saldoAwal);
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public double getSaldoAwal() {
        return saldoAwal;
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

    @Override
    public void topUpBalance(double amount) {
        this.saldoAwal += amount;
    }

    public double getBalance() {
        return this.saldoAwal;
    }

    public String getNamaLengkap() {
        return this.id;
    }
}
