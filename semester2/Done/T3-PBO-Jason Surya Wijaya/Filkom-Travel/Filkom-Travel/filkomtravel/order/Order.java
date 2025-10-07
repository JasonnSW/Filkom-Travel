package order;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import customer.Customer;
import promotion.Applicable;
import promotion.PercentOffPromo;
import promotion.Promotion;
import vehicle.Menu;
import vehicle.Vehicle;

public class Order implements Applicable {
    private Map<String, ArrayList<Menu>> menuOrder = new TreeMap<>();
    private int orderNumber;
    Vehicle vehicle;
    private boolean isCheckOut;
    private double subTotalPrice;
    private double totalPrice;
    private Customer customer;
    private Promotion promotion;
    private LocalDate datecheckout;
    private ArrayList<Items> items = new ArrayList<>();

    public Order(Customer customer,
            Map<String, ArrayList<Menu>> menuOrder, Promotion promotion) {
        this.customer = customer;
        this.menuOrder.putAll(menuOrder);
        this.promotion = promotion;
        this.subTotalPrice = calculateSubTotalPrice();
        this.totalPrice = calculateTotalPrice();
        // this.promotion = promo;
    }

    public Order(Customer customer, String idMenu, int qty, LocalDate tanggalAwal,
            Map<String, ArrayList<Menu>> menuOrder) {
        this.totalPrice = calculateTotalPrice();
        this.subTotalPrice = calculateSubTotalPrice();
        // this.promotion = promo;
    }

    public void setDatecheckout(LocalDate datecheckout) {
        this.datecheckout = datecheckout;
    }

    public LocalDate getDatecheckout() {
        return datecheckout;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void addItems(Vehicle vehicle) {
        // items.add(new Items(vehicle));
    }

    public boolean getChekout() {
        return this.isCheckOut;
    }

    public void setCheckOut(boolean checkOut) {
        isCheckOut = checkOut;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public Map<String, ArrayList<Menu>> getOrderList() {
        return menuOrder;
    }

    public void setOrderList(Map<String, ArrayList<Menu>> menuOrder) {
        this.menuOrder = menuOrder;
    }

    // Calculate subtotal price
    private double calculateSubTotalPrice() {
        subTotalPrice = 0;
        ArrayList<Menu> tmp = menuOrder.get(customer.getId());
        for (Menu menu : tmp) {
            subTotalPrice += menu.getHarga() * menu.getQty();
        }
        return subTotalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    private double calculateTotalPrice() {
        double calculatedTotalPrice = getSubTotalPrice();
        if (promotion instanceof PercentOffPromo) {
            calculatedTotalPrice = getSubTotalPrice() - (getSubTotalPrice() * promotion.getDiscountPercent() / 100);
        } else {
            calculatedTotalPrice = getSubTotalPrice();
        }
        return calculatedTotalPrice;
    }

    public double getTotalPrice() {
        return calculateTotalPrice();
    }

    public double getSubTotalPrice() {
        return calculateSubTotalPrice();
    }

    public Map<String, ArrayList<Menu>> getMenuOrder() {
        return menuOrder;
    }

    private Promotion findPromoByCode(String promoCode, ArrayList<Promotion> promoList) {
        for (Promotion promo : promoList) {
            if (promo.getPromoCode().equals(promoCode)) {
                return promo;
            }
        }
        return null;
    }

    public boolean applyPromo(String promoCode, ArrayList<Promotion> promoList, LocalDate currentDate,
            int minimumPurchase, Customer customer) {
        Promotion promo = findPromoByCode(promoCode, promoList);
        if (promo == null) {
            System.out.println("APPLY_PROMO FAILED: " + promoCode);
            return false;
        }

        if (currentDate.isAfter(promo.getDateEnd()) || currentDate.isBefore(promo.getDateStart())) {
            System.out.println("APPLY_PROMO FAILED: " + promoCode + " IS EXPIRED");
            return false;
        }
        // if (getSubTotalPrice() < minimumPurchase) {
        // return "APPLY_PROMO FAILED: " + promoCode;
        // }
        boolean cekPrice = promo.isMinimumPriceEligible(this);
        return cekPrice;
        // boolean cekUser = promo.isCustomerEligible(customer);

        // this.promotion = promo;
        // this.totalPrice = calculateTotalPrice();
        // this.setPromotion(this.promotion);
        // this.getPromotion().setTotalDiscount(this.getPromotion().getDiscountPercent()
        // * this.getTotalPrice() / 100);
        // return "APPLY_PROMO SUCCESS: " + promoCode;
    }

    @Override
    public boolean isCustomerEligible(Object x) {

        return false;
    }

    @Override
    public boolean isMinimumPriceEligible(Object x) {
        if (x instanceof Order) {
            Order order = (Order) x;
            return order.getSubTotalPrice() >= order.getPromotion().getMinimumPurchase();
        }
        return false;
    }

    @Override
    public boolean isShippingFeeEligible(Object x) {
        // Add logic if shipping fee eligibility is applicable
        return true;
    }

    @Override
    public double getPrice(Object x) {
        if (x instanceof Order) {
            Order order = (Order) x;
            return order.getTotalPrice();
        }
        return 0;
    }

    @Override
    public double getCashback(Order order) {
        if (order.getPromotion() instanceof PercentOffPromo) {
            PercentOffPromo promo = (PercentOffPromo) order.getPromotion();
            return order.getTotalPrice() * promo.getDiscount() / 100;
        }
        return 0;
    }

    @Override
    public double getTotalShippingFee() {
        // Add logic if shipping fee is applicable
        return 0;
    }

}
