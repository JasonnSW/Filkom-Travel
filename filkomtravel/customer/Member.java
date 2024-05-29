package customer;

import java.util.Calendar;
import java.util.Scanner;
import java.util.*;

import java.util.Date;

public class Member extends Customer {
    private String nama;
    private Date tanggalDaftar;

    public Member(String id, String nama, Date tanggalDaftar, double saldoAwal) {
        super(id, saldoAwal);
        this.nama = nama;
        this.tanggalDaftar = tanggalDaftar;
    }

    public String getNama() {
        return nama;
    }

    public Date getTanggalDaftar() {
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
}
