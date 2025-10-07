import java.util.Scanner;

public class nathan_051_12_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tableSize = Integer.parseInt(scanner.nextLine());
        HashTable hashTable = new HashTable(tableSize);
        int inputCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < inputCount; i++) {
            String commandInput = scanner.nextLine();

            if (commandInput.equals("tambah")) {
                String[] studentData = scanner.nextLine().split(";");
                int count = 0;
                for (String data : studentData) {
                    String[] info = data.split(" ", 2);
                    int studentNIS = Integer.parseInt(info[0]);
                    String studentName = info[1];
                    Student student = new Student(studentNIS, studentName);
                    String result = hashTable.addData(student);
                    if (result.equals("Berhasil memasukkan 1 data siswa")) {
                        count++;
                    }
                }
                System.out.println("Berhasil memasukkan " + count + " data siswa");
            } else if (commandInput.equals("cari")) {
                int studentNIS = Integer.parseInt(scanner.nextLine());
                System.out.println(hashTable.searchData(studentNIS));
            } else if (commandInput.equals("hapus")) {
                int studentNIS = Integer.parseInt(scanner.nextLine());
                System.out.println(hashTable.deleteData(studentNIS));
            }
        }

        scanner.close();
    }
}

class dataSiswa {
    String dataNamaMahasiswa;
    int dataNisSiswa;

    dataSiswa(int dataNisSiswa, String dataNamaMahasiswa) {
        this.dataNisSiswa = dataNisSiswa;
        this.dataNamaMahasiswa = dataNamaMahasiswa;
    }
}
class HashTable {
    private int besarUkuranTable;
    private dataSiswa[] table;
    HashTable(int besarUkuranTable) {
        this.besarUkuranTable = besarUkuranTable;
        table = new dataSiswa[besarUkuranTable];
    }
    private int hashFunction(int dataNisSiswa) {
        return dataNisSiswa % besarUkuranTable;
    }

    // private int hash(int NIS) {
    //     return NIS % size;
    // }

    public String dataDicari(int dataNisSiswa) {
        int probeCount = 0;
        int originalIndex = index;
        int index = hashFunction(dataNisSiswa);
        while (table[index] != null) {
            if (table[index].dataNisSiswa == dataNisSiswa) {
                return "NIS " + dataNisSiswa + " adalah " + table[index].dataNamaMahasiswa + ", ditemukan pada indeks " + index;
            }
            index = (index + 1) % besarUkuranTable;
            probeCount++;
            if (index == originalIndex) {
                break;
            }
        }
        return "Data siswa dengan NIS " + dataNisSiswa + " tidak ditemukan";
    }

    // public void insert(int NIS, String data) {
    //     // proses hashing
    //     int index = hash(NIS);
    //     // memasukkan ke dalam tabel
    //     table[index] = data;
    // }

    public String dataDiHapus(int dataNisSiswa) {
        int probeCount = 0;
        int originalIndex = index;
        int index = hashFunction(dataNisSiswa);
        while (table[index] != null) {
            if (table[index].dataNisSiswa == dataNisSiswa) {
                table[index] = new dataSiswa(-1, "");
                return "Data siswa dengan NIS " + dataNisSiswa + " berhasil dihapus";
            }
            index = (index + 1) % besarUkuranTable;
            probeCount++;
            if (index == originalIndex) {
                break;
            }
        }
        return "Data siswa dengan NIS " + dataNisSiswa + " tidak ditemukan";
    }

    
    // public boolean search(int NIS) {
    //     // proses hashing
    //     int index = hash(NIS);
    //     // mencari data
    //     if (table[index] != null) {
    //         System.out.println("NIS " + NIS + " adalah " + table[index] + ", ditemukan pada indeks " + index);
    //         return true;
    //     }
    //     System.out.println("Data siswa dengan NIS " + NIS + " tidak ditemukan");
    //     return false;
    // }

    public String dataDitambah(dataSiswa siswa) {
        int probeCount = 0;
        int originalIndex = index;
        int index = hashFunction(siswa.dataNisSiswa);
        while (table[index] != null && table[index].dataNisSiswa != -1) {
            index = (index + 1) % besarUkuranTable;
            probeCount++;
            if (index == originalIndex) {
                return "Hash table penuh, tidak bisa menambah data siswa.";
            }
        }
        table[index] = siswa;
        return "Berhasil memasukkan 1 data siswa";
 }
}

// public void delete(int NIS) {
//     // proses hashing
//     int index = hash(NIS);
//     // menghapus data
//     if (table[index] != null) {
//         table[index] = null;
//         System.out.println("Data siswa dengan NIS " + NIS + " berhasil dihapus");
//         return;
//     }
//     System.out.println("Data siswa dengan NIS " + NIS + " tidak ditemukan");
//     return;
// }
// }
