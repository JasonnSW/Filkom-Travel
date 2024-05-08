import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import vehicle.*;
import java.util.LinkedList;

public class Order {
    private LinkedList<Order> listPesanan = new LinkedList<Order>();
    private LinkedList<Receipt> listNota = new LinkedList<Receipt>();
    private int nomorPesanan = 0;
    private Calendar tanggalPesanan;
    private Vehicle sewaan;

    public Order() {
    }

    public Order(int nomorPesanan, Calendar tanggalPesanan, Vehicle sewaan) {
        this.nomorPesanan = nomorPesanan;
        this.tanggalPesanan = tanggalPesanan;
        this.sewaan = sewaan;
    }

    public void print() {
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
            System.out.println("Price: " + df.format(pesanan.sewaan.getPrice()));
            System.out.println();
        });
    }

    public void printNota(int index) {
        listNota.get(index).print();
    }

    public void checkOut(int nomorPesanan) {
        listPesanan.remove(nomorPesanan);
    }

    public boolean isEmpty() {
        return listPesanan.isEmpty();
    }

    public void tambahPesanan(Vehicle sewaan) {
        Calendar date = Calendar.getInstance();
        int num = listPesanan.size() + 1;
        Order pesanan = new Order(num, date, sewaan);
        listPesanan.add(pesanan);
    }

    public void tambahNota(String[] tanggalSewa, int jam, double harga, String[] jamSewa, boolean isMember) {
        Receipt nota = new Receipt(tanggalSewa, jam, harga, jamSewa, isMember);
        listNota.add(nota);
    }
}

class MainOrder {
    public static void main(String[] args) {
        Vehicle mobil = new Mobil("Toyota Avanza", "Silver", "B 1234 XYZ", 7, 200000);
        Vehicle motor = new Motor("Honda Beat", "Black", "B 1234 XYZ", 2, 50000);
        Vehicle van = new Van("Toyota Hiace", "White", "B 1234 XYZ", 15, 500000);
        Order pesanan1 = new Order();
        pesanan1.tambahPesanan(mobil);
        pesanan1.tambahPesanan(van);
        pesanan1.tambahPesanan(motor);
        pesanan1.print();
    }
}
