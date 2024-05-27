package customer;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Represents a member of the system.
 * Extends the Guest class and adds additional member-specific functionality.
 */
/**
 * Represents a member of the system.
 * Extends the Guest class.
 */
public class Member extends Guest {
    private String nama;
    private String tanggalDaftar;

    public Member(String id, String nama, String tanggalDaftar, String saldoAwal) {
        super(id, saldoAwal);
        this.nama = nama;
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getNama() {
        return nama;
    }

    public String getTanggalDaftar() {
        return tanggalDaftar;
    }
}
