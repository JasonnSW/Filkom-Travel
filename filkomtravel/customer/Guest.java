package customer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The Guest class represents a guest user in the system.
 * It extends the Customer class and provides additional functionality for guest
 * users.
 */
public class Guest extends Customer {
    private String id;
    private int saldoAwal;

    public Guest(String id, int saldoAwal) {
        this.id = id;
        this.saldoAwal = saldoAwal;
    }

    public String getId() {
        return id;
    }

    public int getSaldoAwal() {
        return saldoAwal;
    }

    public static boolean idExists(String id, LinkedList<Guest> guests) {
        for (Guest guest : guests) {
            if (guest.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void topUpBalance(int amount) {
        this.saldoAwal += amount;
    }

    public int getBalance() {
        return this.saldoAwal;
    }

    public String getNamaLengkap() {
        return this.id; // Atau implementasi lain yang sesuai untuk Guest
    }
}

class TopUpService {
    public String topUp(Guest customer, int amount) {
        if (customer == null) {
            return "TOPUP FAILED: NON EXISTENT CUSTOMER";
        }

        // Panggil metode topUpBalance dari objek Customer
        customer.topUpBalance(amount);

        // Membuat pesan sukses
        return String.format("TOPUP SUCCESS: %s %d => %d", customer.getNamaLengkap(), amount, customer.getBalance());
    }
}
