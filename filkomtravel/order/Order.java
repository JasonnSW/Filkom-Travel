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
    private Date tanggalAkhir;
    private ArrayList<Order> orderList;
    private int totalPrice, subTotalPrice;
    private Promotion promotion;
    private String nama;
    private String platNomor;

    public Order(String idPemesan, String idMenu, int qty, Date tanggalAwal, ArrayList<Order> orderList, String nama,
            String platNomor) {
        this.idPemesan = idPemesan;
        this.idMenu = idMenu;
        this.qty = qty;
        this.tanggalAwal = tanggalAwal;
        this.orderList = orderList;
        this.nama = nama;
        this.platNomor = platNomor;
        this.tanggalAkhir = calculateTanggalAkhir();
    }

    public Order(String idPemesan, String idMenu, int qty, Date tanggalAwal, ArrayList<Order> orderList) {
        this.idPemesan = idPemesan;
        this.idMenu = idMenu;
        this.qty = qty;
        this.tanggalAwal = tanggalAwal;
        this.orderList = orderList;
        this.tanggalAkhir = calculateTanggalAkhir();
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setTanggalAkhir(Date tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setSubTotalPrice(int subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSubTotalPrice() {
        return subTotalPrice;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public String getNama() {
        return nama;
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
        this.tanggalAkhir = calculateTanggalAkhir();
    }

    public Date getTanggalAkhir() {
        return tanggalAkhir;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
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

    private Date calculateTanggalAkhir() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.tanggalAwal);
        calendar.add(Calendar.DAY_OF_MONTH, this.qty);
        return calendar.getTime();
    }

    public String getPromoCode() {
        return (promotion != null) ? promotion.getPromoCode() : "NO_PROMO";
    }
}
