package hashtables;

public class nathan_051_12_1 {
    public static void main(String[] args) {
        java.util.Scanner than = new java.util.Scanner(System.in);
        HashTable ht = new HashTable(than.nextInt());
        than.nextLine();
        int n = than.nextInt();
        than.nextLine();
        for (int i = 0; i < n; i++) {
            String perintah = than.nextLine();
            switch (perintah) {
                case "tambah":
                    String input[] = than.nextLine().split(";");
                    for (int j = 0; j < input.length; j++) {
                        String data[] = input[j].split(" ");
                        int NIS = Integer.parseInt(data[0]);
                        String nama = data[1];
                        for (int k = 2; k < data.length; k++) {
                            nama += " " + data[k];
                        }
                        ht.insert(NIS, nama);
                    }
                    System.out.println("Berhasil memasukkan " + input.length + " data siswa");
                    break;
                case "cari":
                    int NIS = than.nextInt();
                    if (than.hasNextLine()) {
                        than.nextLine();
                    }
                    ht.search(NIS);
                    break;
                case "hapus":
                    NIS = than.nextInt();
                    if (than.hasNextLine()) {
                        than.nextLine();
                    }
                    ht.delete(NIS);
                    break;

                default:
                    break;
            }
        }
    }
}

class HashTable {
    private String[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        this.table = new String[size];
    }

    private int hash(int NIS) {
        return NIS % size;
    }

    public void insert(int NIS, String data) {
        // proses hashing
        int index = hash(NIS);
        // memasukkan ke dalam tabel
        table[index] = data;
    }

    public boolean search(int NIS) {
        // proses hashing
        int index = hash(NIS);
        // mencari data
        if (table[index] != null) {
            System.out.println("NIS " + NIS + " adalah " + table[index] + ", ditemukan pada indeks " + index);
            return true;
        }
        System.out.println("Data siswa dengan NIS " + NIS + " tidak ditemukan");
        return false;
    }

    public void delete(int NIS) {
        // proses hashing
        int index = hash(NIS);
        // menghapus data
        if (table[index] != null) {
            table[index] = null;
            System.out.println("Data siswa dengan NIS " + NIS + " berhasil dihapus");
            return;
        }
        System.out.println("Data siswa dengan NIS " + NIS + " tidak ditemukan");
        return;
    }
}
