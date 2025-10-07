package customer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.sound.midi.Soundbank;

import order.Items;
import order.Order;
import order.print;
import promotion.CashbackPromo;
import promotion.PercentOffPromo;
import promotion.Promotion;
import vehicle.Vehicle;
import vehicle.Menu;

public abstract class Customer {
    private String id;
    private double balance;
    private Promotion promotion;
    Vehicle vehicle;

    // menyimpan order
    public static ArrayList<Order> orders = new ArrayList<>();
    public ArrayList<Menu> menuOrder = new ArrayList<>();

    protected Customer(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
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
        this.balance += amount;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void applyPromo(String idMenu, int qty, LocalDate tanggalAwal, String promoCode,
            ArrayList<Promotion> promoList, int minimumPurchase, Map<String, ArrayList<Menu>> menuOrder) {
        Order order = new Order(this, idMenu, qty, tanggalAwal, menuOrder);

        Promotion cek = null;
        LocalDate currentDate = LocalDate.now();
        for (Promotion promo : promoList) {
            if (promo.getPromoCode().equals(promoCode)) {
                cek = promo;
            }
        }
        boolean cekUser = cek.isCustomerEligible(this);
        boolean cekPrice = order.applyPromo(promoCode, promoList, currentDate, minimumPurchase, this);
        if (cekUser && cekPrice) {
            this.promotion = cek;
            System.out.println("APPLY_PROMO SUCCESS: " + promoCode);
        }
    }

    public boolean checkout(Map<String, ArrayList<Menu>> map, ArrayList<Promotion> promoList) {
        double total = 0;
        ArrayList<Menu> tmp = map.get(this.id);
        for (Menu menu : tmp) {
            total += menu.getQty() * menu.getHarga();
        }

        if (this.balance < total) {
            System.out.println("CHECK_OUT FAILED: " + this.id + " "
                    + (this instanceof Member ? ((Member) this).getNama() : "NON_MEMBER" + " INSUFFICIENT_BALANCE"));
            return false;
        }
        Order order = new Order(this, map, promotion);
        this.balance -= total;
        order.setDatecheckout(LocalDate.now());
        order.setCheckOut(true);
        orders.add(order);
        order.setOrderNumber(lastIndex(this));
        // this.orders.addAll(orderList); // Add orders to customer's orders list
        map.clear();

        return true;
    }

    // public void printOrder(ArrayList<Menu> orderr) {
    // if (orderr.isEmpty()) {
    // return;
    // }

    // SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    // DecimalFormatSymbols sym = new DecimalFormatSymbols();
    // sym.setDecimalSeparator(',');
    // sym.setGroupingSeparator('.');
    // DecimalFormat df = new DecimalFormat("###,###.##", sym);
    // Menu order = orderr.get(orderr.size() - 1);

    // System.out.println("\nKode Pemesan: " + getId());
    // System.out.println("Nama: " + getName());
    // if (order.isCheckOut()) {
    // System.out.println("Nomor Pesanan: ");
    // SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd MMMM yyyy");
    // System.out.println("Tanggal Pesanan: " +
    // dateFormat2.format(order.getTanggalAwal()));
    // // cnt += 1;
    // }
    // System.out.printf("%3s | %-25s | %3s | %8s \n", "No", "Menu", "Dur.",
    // "Subtotal");
    // System.out.println("=================================================");

    // int count = 1;
    // for (meunOrder orderr : orderr) {
    // String menu = orderr.getNama() + " " + orderr.getPlatNomor();
    // Date tanggalAwal = orderr.getTanggalAwal();
    // Date tanggalAkhir = orderr.getTanggalAkhir();

    // System.out.printf("%3d | %-25s | %4d | %8s \n", count, menu, orderr.getQty(),
    // df.format(order.getHarga() * order.getQty()));
    // System.out.printf(" %s%s%5s\n", dateFormat.format(tanggalAwal), " - ",
    // dateFormat.format(tanggalAkhir));

    // count++;
    // }
    // System.out.println("=================================================");
    // String subtotal = df.format(order.getSubTotalPrice());
    // String total = df.format(order.getTotalPrice());
    // String balance = df.format(this.balance);
    // if (!order.isCheckOut()) {
    // balance = df.format(this.balance - order.getTotalPrice());
    // }

    // System.out.printf("%-32s: %14s \n", "Sub Total", subtotal);
    // System.out.println("=================================================");

    // System.out.printf("%-32s: %14s \n", "Total", total);

    // Promotion promo = (order.getPromotion());
    // if (promo != null) {
    // if (this instanceof Member) {
    // if (promo instanceof CashbackPromo) {
    // String dsc = df.format(order.getPromotion().getCashback(order));
    // System.out.printf("%-32s: %15s\n", "PROMO: " +
    // order.getPromotion().getPromoCode(), dsc);
    // } else if (promo instanceof PercentOffPromo) {
    // String dsc = df.format(order.getPromotion().getPrice(order));
    // System.out.printf("%-32s: %15s\n", "PROMO: " +
    // order.getPromotion().getPromoCode(), "-" + dsc);
    // }
    // }
    // }
    // System.out.printf("%-32s: %14s \n", "Saldo", balance);
    // System.out.println("");
    // // cnt++;
    // }
    private Order lastOrder(Customer customer) {
        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                return order;
            }
        }
        return null;
    }

    private int lastIndex(Customer customer) {
        for (int i = orders.size() - 1; i >= 0; i--) {
            if (orders.get(i).getCustomer().equals(customer)) {
                return i + 1;
            }
        }
        return -1;
    }

    public void printOrder(ArrayList<Menu> menuOrder) {
        ArrayList<Menu> toPrint = new ArrayList<>();
        Order order = lastOrder(this);
        if (order == null) {
            return;
        }
        if (menuOrder == null) {
            order.setOrderNumber(lastIndex(this));
            toPrint.addAll(order.getMenuOrder().get(this.id));
        } else {
            toPrint.addAll(menuOrder);
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("dd MMM YYYY");
        DecimalFormatSymbols sym = new DecimalFormatSymbols();
        sym.setDecimalSeparator(',');
        sym.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("###,###.##", sym);

        System.out.println("\nKode Pemesan: " + getId());
        System.out.println("Nama: " + (this instanceof Member ? ((Member) this).getNama() : "NON_MEMBER"));
        if (order != null) {
            if (order.getChekout()) {
                System.out.println("Nomor Pesanan:" + order.getOrderNumber());
                System.out.println("Tanggal Pesanan: " + df1.format(order.getDatecheckout()));
            }
        }
        System.out.printf("%3s | %-25s | %3s | %8s \n", "No", "Menu", "Dur.", "Subtotal");
        System.out.println("==================================================");

        int count = 1;
        double sub = 0;
        for (Menu menu : toPrint) {
            String menuu = menu.getNamaMenu() + " " + menu.getPlatNomor();
            LocalDate tanggalAwal = menu.getTanggalAwal();
            LocalDate tanggalAkhir = tanggalAwal.plusDays(menu.getQty() - 1);
            sub += menu.getHarga() * menu.getQty();
            System.out.printf("%3d | %-25s | %4d | %8s \n", count, menuu, menu.getQty(),
                    df.format(menu.getHarga() * menu.getQty()));
            System.out.printf("      %s%s%5s\n", dateFormat.format(tanggalAwal), " - ",
                    dateFormat.format(tanggalAkhir));

            count++;
        }
        System.out.printf("%-32s: %15s \n", "Sub Total", df.format(sub));
        Promotion promo = order.getPromotion();
        if (promo != null) {
            System.out.printf("PROMO: %-25s: %15s \n", order.getPromotion().getPromoCode(),
                    ((order.getPromotion() instanceof PercentOffPromo) ? "-" + order.getTotalPrice()
                            : order.getTotalPrice()));

        }
        System.out.println("==================================================");
        System.out.printf("%-32s: %15s \n", "Total", df.format(order.getTotalPrice()));
        System.out.printf("%-32s: %15s \n", "Saldo", df.format(this.balance));
    }

    public String getNama() {
        return "";
    }

    public void printHistory() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DecimalFormatSymbols sym = new DecimalFormatSymbols();
        sym.setDecimalSeparator(',');
        sym.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("###,###.##", sym);

        System.out.println("Kode Pemesan: " + this.getId());
        System.out.println("Nama: " + (this instanceof Member ? ((Member) this).getNama() : "NON_MEMBER"));
        System.out.println("Saldo: " + df.format(this.getBalance()));
        System.out.printf("%4s| %10s | %5s | %5s | %8s | %-8s%n", "No", "Nomor Pesanan", "Motor", "Mobil", "Subtotal",
                "PROMO");
        System.out.println("==========================================================");

        int i = 1;
        for (Order order : this.orders) {
            int mobil = 0;
            int motor = 0;
            for (Map.Entry<String, ArrayList<Menu>> tmp : order.getOrderList().entrySet()) {
                ArrayList<Menu> tmp2 = tmp.getValue();
                for (Menu menu : tmp2) {
                    if (menu.getMenuKendaraan().equalsIgnoreCase("MOTOR")) {
                        motor++;
                    } else if (menu.getMenuKendaraan().equalsIgnoreCase("MOBIL")) {
                        mobil++;
                    }
                }
            }
            System.out.printf("%4d| %13d | %5d | %5d | %8s | ", i,
                    i, motor, mobil,
                    df.format(order.getSubTotalPrice()));

            if (order.getPromotion() != null) {
                System.out.printf("%-8s%n", order.getPromotion().getPromoCode());
            } else {
                System.out.printf("%-8s%n", "-");
            }
            i++;

        }
        System.out.println("==========================================================");
    }

    public void getOrderListForUser(String PemesanPromo, Map<String, ArrayList<Menu>> menuOrder) {

    }

    abstract public long calculateTanggalAkhir();
    // Calendar calendar = Calendar.getInstance();
    // calendar.setTime(this.tanggalAwal);
    // calendar.add(Calendar.DAY_OF_MONTH, this.qty);
    // return calendar.get();

}
