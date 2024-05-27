package customer;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The Guest class represents a guest user in the system.
 * It extends the Customer class and provides additional functionality for guest
 * users.
 */
public class Guest {
    private String id;
    private String saldoAwal;

    public Guest(String id, String saldoAwal) {
        this.id = id;
        this.saldoAwal = saldoAwal;
    }

    public String getId() {
        return id;
    }

    public String getSaldoAwal() {
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
}
