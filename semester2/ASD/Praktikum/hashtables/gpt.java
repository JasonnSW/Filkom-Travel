import java.util.Scanner;

public class nathan_051_12_1 {
    static class HashTable {
        private int capacity;
        private String[] nisArray;
        private String[] nameArray;

        public HashTable(int capacity) {
            this.capacity = capacity;
            nisArray = new String[capacity];
            nameArray = new String[capacity];
        }

        private int hash(String nis) {
            return Integer.parseInt(nis) % capacity;
        }

        public void tambah(String data) {
            String[] parts = data.split(";");
            for (String part : parts) {
                String[] student = part.split(" ");
                String nis = student[0];
                String name = student[1];
                for (int k = 2; k < student.length; k++) {
                    name += " " + student[k];
                }
                int index = hash(nis);
                while (nisArray[index] != null) {
                    index = (index + 1) % capacity;
                }
                nisArray[index] = nis;
                nameArray[index] = name;
            }
            System.out.println("Berhasil memasukkan " + parts.length + " data siswa");
        }

        public void cari(String nis) {
            int index = hash(nis);
            while (nisArray[index] != null && !nisArray[index].equals(nis)) {
                index = (index + 1) % capacity;
            }
            if (nisArray[index] != null && nisArray[index].equals(nis)) {
                System.out.println("NIS " + nis + " adalah " + nameArray[index] + ", ditemukan pada indeks " + index);
            } else {
                System.out.println("Data siswa dengan NIS " + nis + " tidak ditemukan");
            }
        }

        public void hapus(String nis) {
            int index = hash(nis);
            while (nisArray[index] != null && !nisArray[index].equals(nis)) {
                index = (index + 1) % capacity;
            }
            if (nisArray[index] != null && nisArray[index].equals(nis)) {
                nisArray[index] = null;
                nameArray[index] = null;
                System.out.println("Data siswa dengan NIS " + nis + " berhasil dihapus");
            } else {
                System.out.println("Data siswa dengan NIS " + nis + " tidak ditemukan");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        int numCommands = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        HashTable hashTable = new HashTable(capacity);

        for (int i = 0; i < numCommands; i++) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");
            if (parts[0].equals("tambah")) {
                String data = scanner.nextLine();
                hashTable.tambah(data);
            } else if (parts[0].equals("cari")) {
                String data = scanner.nextLine();
                hashTable.cari(data);
            } else if (parts[0].equals("hapus")) {
                String data = scanner.nextLine();
                hashTable.hapus(data);
            }
        }

        scanner.close();
    }
}
