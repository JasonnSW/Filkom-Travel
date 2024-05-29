package order;

import java.util.*;
import java.util.concurrent.TimeUnit;

import customer.Customer;
import customer.Guest;
import promotion.PercentOffPromo;
import promotion.Promotion;

public class Order {
    private String idPemesan;
    private String idMenu;
    private int qty;
    private Date tanggalAwal;
    private ArrayList<Order> orderList;
    private int totalPrice, subTotalPrice;
    private Promotion promotion;

    public Order(String idPemesan, String idMenu, int qty, Date tanggalAwal, ArrayList<Order> orderList) {
        this.idPemesan = idPemesan;
        this.idMenu = idMenu;
        this.qty = qty;
        this.tanggalAwal = tanggalAwal;
        this.orderList = orderList;
    }

    public String getIdPemesan() {
        return idPemesan;
    }

    public void setIdPemesan(String idPemesan) {
        this.idPemesan = idPemesan;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getTanggalAwal() {
        return tanggalAwal;
    }

    public void setTanggalAwal(Date tanggalAwal) {
        this.tanggalAwal = tanggalAwal;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public static boolean idExists(String id, LinkedList<Guest> guests) {
        for (Guest guest : guests) {
            if (guest.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public int TotalPrice() {
        int subTotal = 0;
        for (int i = 0; i < orderList.size(); i++) {
            subTotal += orderList.get(i).getQty();
        }
        subTotalPrice = subTotal;

        if (promotion instanceof PercentOffPromo) {
            totalPrice = subTotalPrice - (subTotalPrice * promotion.getDiscountPercent() / 100);
        } else {
            totalPrice = subTotalPrice;
        }
        return totalPrice;
    }

    public int getTotalPrice() {
        return TotalPrice();
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

    private Promotion findPromoByCode(String promoCode, ArrayList<Promotion> promoList) {
        for (Promotion promo : promoList) {
            if (promo.getPromoCode().equals(promoCode)) {
                return promo;
            }
        }
        return null;
    }

    public String applyPromo(String promoCode, ArrayList<Promotion> promoList, Date currentDate, int minimumPurchase) {
        Promotion promo = findPromoByCode(promoCode, promoList);

        if (promo == null && getTotalPrice() < minimumPurchase) {
            return "APPLY_PROMO FAILED: " + promoCode;
        }

        if (currentDate.after(promo.getDateEnd()) || currentDate.before(promo.getDateStart())) {
            return "APPLY_PROMO FAILED: " + promoCode + " IS EXPIRED";
        }

        this.promotion = promo;

        if (promotion instanceof PercentOffPromo) {
            totalPrice = subTotalPrice - (subTotalPrice * promotion.getDiscountPercent() / 100);
        }

        return "APPLY_PROMO SUCCESS: " + promoCode;
    }

}
