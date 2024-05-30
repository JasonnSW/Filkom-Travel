package customer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
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

    public String checkout(ArrayList<Order> orderList, ArrayList<Promotion> promoList) {
        double total = 0;
        for (Order order : orders) {
            total += order.getTotalPrice();
        }

        if (this.balance < total) {
            return "CHECK_OUT FAILED: " + this.id + " " + this.name + " INSUFFICIENT_BALANCE";
        }

        this.balance -= total;
        orderList.addAll(orders);
        orders.clear();

        return "CHECK_OUT SUCCESS: " + this.id + " " + this.name;
    }

    public void printOrder() {
        Order lastOrder = orders.get(orders.size() - 1);
        System.out.println("Kode Pemesan: " + getId());
        System.out.println("Nama: " + getName());

        System.out.println("");
    }

    public void printHistory() {
        System.out.println("Kode Pemesan: " + getId());
        System.out.println("Nama: " + getName());
        System.out.println("Saldo: " + getBalance());
        System.out.printf("%4s | %10s | %5s | %5s | %8s | %-8s%n", "No", "Nomor Pesanan", "Motor", "Mobil", "Subtotal",
                "PROMO");
        System.out.println("===============================================================");

        System.out.println("===============================================================");
    }

}
