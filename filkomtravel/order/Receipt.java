package order;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * The Receipt class represents a receipt for a rental transaction.
 * It contains information about the rental date, duration, price, and return
 * date.
 */
public class Receipt {

    private String[] tanggalSewa, jamSewa;
    private int jam;
    private double harga;
    private int nomorNota = 0;
    private boolean isMember;

    /**
     * Constructs a Receipt object with the specified rental information.
     * 
     * @param tanggalSewa the rental date in the format [year, month, day]
     * @param jam         the duration of the rental in hours
     * @param harga       the price of the rental
     * @param jamSewa     the rental time in the format [hour, minute]
     */
    public Receipt(String[] tanggalSewa, int jam, double harga, String[] jamSewa, boolean isMember, int nomorNota) {
        this.tanggalSewa = tanggalSewa;
        this.jam = jam;
        this.harga = harga;
        this.jamSewa = jamSewa;
        this.isMember = isMember;
        this.nomorNota = nomorNota;
    }

    /**
     * Prints the details of the rental transaction, including rental date,
     * duration, return date, and price.
     */
    public void print() {

        // Price Formats
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("Rp #,###.00", symbols);
        String formattedNumber = String.format("%08d", nomorNota);

        System.out.println("=".repeat(50));
        System.out.println();
        System.out.println("// Detail Penyewaan");
        System.out.println("Nomor Nota\t\t: " + formattedNumber);
        System.out.println("Tanggal Sewa\t\t: " + tanggalSewa[0] + "/" + tanggalSewa[1] + "/" + tanggalSewa[2]);
        System.out.println("Lama Sewa\t\t: " + jam + " jam");
        System.out.println("Jam Sewa\t\t: " + jamSewa[0] + ":" + jamSewa[1] + " WIB");
        System.out.println("-".repeat(50));

        int waktu = Integer.parseInt(jamSewa[0]) + jam;
        int hari = waktu / 24;
        int jam = waktu % 24;
        int tanggal = Integer.parseInt(tanggalSewa[2]) + hari;
        if (tanggal > 30) {
            tanggal -= 30;
            int bulan = Integer.parseInt(tanggalSewa[1]) + 1;
            if (bulan > 12) {
                bulan -= 12;
                int tahun = Integer.parseInt(tanggalSewa[0]) + 1;
                System.out.println("Tanggal Pengembalian\t: " + tahun + "/" + bulan + "/" + tanggal);
            } else {
                System.out.println("Tanggal Pengembalian\t: " + tanggalSewa[0] + "/" + bulan + "/" + tanggal);
            }
        } else {
            System.out.println("Tanggal Pengembalian\t: " + tanggalSewa[0] + "/" + tanggalSewa[1] + "/" + tanggal);
        }
        System.out.println("Jam Pengembalian\t: " + jam + ":" + jamSewa[1] + " WIB");
        System.out.println("-".repeat(50));

        if (isMember) {
            // Member discount
            System.out.println("Harga\t\t\t: " + df.format(harga));
            System.out.println("Diskon\t\t\t: " + df.format(this.harga * 0.15));
            System.out.println("Total\t\t\t: " + df.format(this.harga * 0.85));

        } else {
            System.out.println("Harga\t\t\t: " + df.format(harga));
        }
        System.out.println();
        // System.out.println("=".repeat(50));
    }
}
