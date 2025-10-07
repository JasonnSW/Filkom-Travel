package vehicle;

import java.util.*;
import java.time.*;

import order.Order;

public class Menu {
    private String menuKendaraan;
    private String idMenu;
    private String namaMenu;
    private String platNomor;
    private int harga;
    private int qty;
    private String customType;
    private boolean isCheckOut;
    private LocalDate tanggalAwal;

    public Menu(String menuKendaraan, String idMenu, String namaMenu, String platNomor, int harga) {
        this.menuKendaraan = menuKendaraan;
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.platNomor = platNomor;
        this.harga = harga;
        // this.tanggalAwal = tanggalAwal;
        // this.tanggalAkhir = tanggalAkhir;
    }

    public Menu(String menuKendaraan, String idMenu, String namaMenu, String platNomor, int harga, String customType) {
        this.menuKendaraan = menuKendaraan;
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.platNomor = platNomor;
        this.harga = harga;
        this.customType = customType;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuKendaraan='" + menuKendaraan + '\'' +
                ", idMenu='" + idMenu + '\'' +
                ", namaMenu='" + namaMenu + '\'' +
                ", platNomor='" + platNomor + '\'' +
                ", harga=" + harga +
                (customType != null ? ", customType='" + customType + '\'' : "") +
                '}';
    }

    public boolean isCheckOut() {
        return isCheckOut;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public int getHarga() {
        return harga;
    }

    public String getCustomType() {
        return customType;
    }

    public String getMenuKendaraan() {
        return menuKendaraan;
    }

    public static boolean idExists(String id, ArrayList<Menu> menuList) {
        for (Menu menu : menuList) {
            if (menu.getIdMenu().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean platNomorExists(String platNomor, ArrayList<Menu> menuList) {
        for (Menu menu : menuList) {
            if (menu.getPlatNomor().equals(platNomor)) {
                return true;
            }
        }
        return false;
    }

    public LocalDate getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(LocalDate tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }
}
