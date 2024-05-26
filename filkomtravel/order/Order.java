package order;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import vehicle.*;
import java.util.LinkedList;

/**
 * The Order class represents an order for renting a vehicle.
 */
public class Order {
    private LinkedList<Order> listPesanan = new LinkedList<Order>();
    private LinkedList<Receipt> listNota = new LinkedList<Receipt>();
    private int nomorPesanan = 0, nomorNota = 0;
    private Calendar tanggalPesanan;
    private Vehicle sewaan;
    private Status status;

    /**
     * Enum representing the status of an order.
     */
    enum Status {
        UNPAID, SUCCESS, CANCELLED
    }

    /**
     * Default constructor for the Order class.
     */
    public Order() {
    }

    /**
     * Constructor for the Order class.
     * 
     * @param nomorPesanan   The order number.
     * @param tanggalPesanan The order date.
     * @param sewaan         The rented vehicle.
     */
    public Order(int nomorPesanan, Calendar tanggalPesanan, Vehicle sewaan) {
        this.nomorPesanan = nomorPesanan;
        this.tanggalPesanan = tanggalPesanan;
        this.sewaan = sewaan;
        this.status = status.UNPAID;
    }

    /**
     * Prints the details of all orders.
     */
    public void printDetails() {
        // Price Formats
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("Rp #,###.00", symbols);

        listPesanan.forEach(pesanan -> {
            System.out.println("Nomor Pesanan: " + pesanan.nomorPesanan);
            System.out.println("Tanggal Pesanan: " + pesanan.tanggalPesanan.getTime());
            System.out.println("Model: " + pesanan.sewaan.getModel());
            System.out.println("Color: " + pesanan.sewaan.getColor());
            System.out.println("Status: " + pesanan.status);
            System.out.println();
        });
    }

    /**
     * Prints the receipt of a specific order.
     * 
     * @param index The index of the order.
     */
    public void printNota(int index) {
        listNota.get(index).print();
    }

    /**
     * Prints the receipt of the latest order.
     */
    public void printNota() {
        listNota.getLast().print();
    }

    /**
     * Marks an order as paid.
     * 
     * @param nomorPesanan The order number.
     */
    public void pay(int nomorPesanan) {
        listPesanan.get(nomorPesanan).status = status.SUCCESS;
    }

    /**
     * Checks if the order list is empty.
     * 
     * @return true if the order list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return listPesanan.isEmpty();
    }

    /**
     * Adds a new order to the order list.
     * 
     * @param sewaan The rented vehicle.
     */
    public void checkOut(Vehicle sewaan) {
        Calendar date = Calendar.getInstance();
        int num = listPesanan.size() + 1;
        Order pesanan = new Order(num, date, sewaan);
        listPesanan.add(pesanan);
    }

    /**
     * Adds a new receipt to the receipt list.
     * 
     * @param tanggalSewa The rental date.
     * @param jam         The rental duration in hours.
     * @param harga       The rental price.
     * @param jamSewa     The rental time range.
     * @param isMember    Indicates if the customer is a member.
     */
    public void tambahNota(String[] tanggalSewa, int jam, double harga, String[] jamSewa, boolean isMember) {
        nomorNota++;
        Receipt nota = new Receipt(tanggalSewa, jam, harga, jamSewa, isMember, nomorNota);
        listNota.add(nota);
    }

    /**
     * Applies a promotional discount to the order.
     * 
     * @return true if the promo is successfully applied, false otherwise.
     */
    public boolean applyPromo() {
        return true;
    }
}