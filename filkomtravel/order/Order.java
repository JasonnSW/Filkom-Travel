package order;

import java.util.ArrayList;

public class Order {
    private String idPemesan;
    private String idMenu;
    private int qty;
    private String tanggalAwal;

    public Order(String idPemesan, String idMenu, int qty, String tanggalAwal) {
        this.idPemesan = idPemesan;
        this.idMenu = idMenu;
        this.qty = qty;
        this.tanggalAwal = tanggalAwal;
    }

    public void setQty(int newQty) {
        this.qty = newQty;
    }

    public String getIdPemesan() {
        return idPemesan;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public int getQty() {
        return qty;
    }

    public String getTanggalAwal() {
        return tanggalAwal;
    }

    public static boolean orderExists(String idPemesan, String idMenu, ArrayList<Order> orderList) {
        for (Order order : orderList) {
            if (order.getIdPemesan().equals(idPemesan) && order.getIdMenu().equals(idMenu)) {
                return true;
            }
        }
        return false;
    }

    public static Order findOrder(String idPemesan, String idMenu, ArrayList<Order> orderList) {
        for (Order order : orderList) {
            if (order.getIdPemesan().equals(idPemesan) && order.getIdMenu().equals(idMenu)) {
                return order;
            }
        }
        return null;
    }
}