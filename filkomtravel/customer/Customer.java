package customer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Date;
import order.Order;
import promotion.Promotion;

public abstract class Customer {

    private String name;
    private String id;
    private double balance;

    public ArrayList<Order> orders = new ArrayList<>();

    protected Customer(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void topUpBalance(double amount) {
        double initialBalance = this.balance;
        this.balance += amount;
        System.out.println(String.format("TOPUP SUCCESS: %s %.2f => %.2f", this.name, initialBalance, this.balance));
    }

    public String applyPromo(String idMenu, int qty, Date tanggalAwal, String promoCode, ArrayList<Promotion> promoList,
            int minimumPurchase) {
        ArrayList<Order> orderList = new ArrayList<>();
        Order order = new Order(this.id, idMenu, qty, tanggalAwal, orderList);
        return order.applyPromo(promoCode, promoList, new Date(), minimumPurchase);
    }

}

