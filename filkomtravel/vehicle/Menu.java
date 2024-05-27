package vehicle;

import java.util.ArrayList;

public class Menu {
    private String idMenu;
    private String namaMenu;
    private String platNomor;
    private int harga;
    private String customType;

    public Menu(String idMenu, String namaMenu, String platNomor, int harga) {
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.platNomor = platNomor;
        this.harga = harga;
    }

    public Menu(String idMenu, String namaMenu, String platNomor, int harga, String customType) {
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.platNomor = platNomor;
        this.harga = harga;
        this.customType = customType;
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
}
