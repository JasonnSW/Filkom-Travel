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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void topUpBalance(double amount) {
        double initialBalance = this.balance;
        this.balance += amount;
        // System.out.println(String.format("TOPUP SUCCESS: %s %.2f => %.2f", this.name,
        // initialBalance, this.balance));
    }

    public String applyPromo(String idMenu, int qty, Date tanggalAwal, String promoCode, ArrayList<Promotion> promoList,
            int minimumPurchase) {
        ArrayList<Order> orderList = new ArrayList<>();
        Order order = new Order(this.id, idMenu, qty, tanggalAwal, orderList);
        return order.applyPromo(promoCode, promoList, new Date(), minimumPurchase);
    }

    public boolean checkout(ArrayList<Order> orderList, ArrayList<Promotion> promoList) {
        double total = 0;
        for (Order order : orderList) {
            total += order.getTotalPrice();
        }

        if (this.balance < total) {
            System.out.println("CHECK_OUT FAILED: " + this.id + " " + this.name + " INSUFFICIENT_BALANCE");
            return false;
        }

        this.balance -= total;
        orderList.addAll(orders);
        orders.clear();

        return true;
    }

    public void printOrder(ArrayList<Order> orderList) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DecimalFormatSymbols sym = new DecimalFormatSymbols();
        sym.setDecimalSeparator(',');
        sym.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##", sym);
        Order order = orderList.get(orderList.size() - 1);
        System.out.println("\nKode Pemesan: " + getId());
        System.out.println("Nama: " + getName());
        System.out.printf("%3s | %-25s | %3s | %8s \n", "No", "Menu", "Dur.", "Subtotal");
        System.out.println("=================================================");

        int count = 1;
        for (Order orderr : orderList) {
            String menu = orderr.getNama() + " " + orderr.getPlatNomor();
            Date tanggalAwal = orderr.getTanggalAwal();
            Date tanggalAkhir = orderr.getTanggalAkhir();

            System.out.printf("%3d | %-25s | %4d | %8s \n", count, menu, orderr.getQty(),
                    formatter.format(orderr.getTotalPrice()));
            System.out.printf("      %s%s%5s\n", dateFormat.format(tanggalAwal), " - ",
                    dateFormat.format(tanggalAkhir));

            count++;
            System.out.println();
        }
        System.out.println("=================================================");
        String subtotal = formatter.format(order.getTotalPrice());
        String total = formatter.format(order.getTotalPrice());
        String balance = formatter.format(this.getBalance() - order.getTotalPrice());

        System.out.printf("%-32s: %14s \n", "Sub Total", subtotal);
        System.out.println("=================================================");

        System.out.printf("%-32s: %14s \n", "Total", total);
        if (order.getPromoCode() != null) {
            String promo = (order.getPromoCode());
            System.out.printf("%-32s: %14s%n", "PROMO: " + promo, promo);
        }
        System.out.printf("%-32s: %14s \n", "Sisa saldo", balance);
        System.out.println("");
    }

    public void printHistory() {
        System.out.println("Kode Pemesan: " + getId());
        System.out.println("Nama: " + getName());
        System.out.println("Saldo: " + getBalance());
        System.out.printf("%4s | %10s | %5s | %5s | %8s | %-8s%n", "No", "Nomor Pesanan", "Motor", "Mobil", "Subtotal",
                "PROMO");
        System.out.println("=================================================");

        System.out.println("=================================================");
    }

}
