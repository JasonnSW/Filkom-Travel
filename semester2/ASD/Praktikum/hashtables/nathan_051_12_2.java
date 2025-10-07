package hashtables;

public class nathan_051_12_2 {
    public static void main(String[] args) {
        java.util.Scanner than = new java.util.Scanner(System.in);
        int size = than.nextInt();
        int n = than.nextInt();
        than.nextLine();
        HashTableOpenHashing ht = new HashTableOpenHashing(size);
        String kota[] = than.nextLine().split(" ");
        for (int j = 0; j < kota.length; j++) {
            byte tmp[] = kota[j].getBytes(java.nio.charset.StandardCharsets.US_ASCII);
            String temp = "";
            for (int k = 0; k < tmp.length; k++) {
                temp += tmp[k];
            }
            String a = String.valueOf(temp.charAt(1));
            String b = String.valueOf(temp.charAt(3));
            String logicKakakAsprak = a.concat(b);
            int index = Integer.parseInt(logicKakakAsprak) % size;

            System.out.println("Kode ASCII dari " + kota[j] + " adalah " + temp);
            System.out.println("Index dari " + kota[j] + " adalah " + index);

            ht.insert(kota[j], index);
        }

        ht.printTable();
        System.out.println();

        for (int i = 0; i < n; i++) {
            String command[] = than.nextLine().split(" ");
            switch (command[0]) {
                case "Cari":
                    ht.cari(command[1]);
                    break;
                case "Hapus":
                    ht.hapus(command[1]);
                    break;
                case "Tampilkan":
                    ht.printTable();
                    break;
            
                default:
                    break;
            }



        }
    }
}

class HashTableOpenHashing {
    private LL[] table;
    private int size;

    public HashTableOpenHashing(int size) {
        this.size = size;
        this.table = new LL[size];
    }

    public void insert(String data, int index) {
        // memasukkan ke dalam tabel
        if (table[index] == null) {
            table[index] = new LL();
        }
        if (table[index].isExists(data)) {
            return;
        }
        table[index].add(new NodeLL(data));
    }

    public boolean search(String data) {
        int index = hash(data);
        if (table[index] == null) {
            return false;
        }
        return table[index].isExists(data);
    }

    public int hash(String data) {
        byte tmp[] = data.getBytes(java.nio.charset.StandardCharsets.US_ASCII);
            String temp = "";
            for (int k = 0; k < tmp.length; k++) {
                temp += tmp[k];
            }
            String a = String.valueOf(temp.charAt(1));
            String b = String.valueOf(temp.charAt(3));
            String logicKakakAsprak = a.concat(b);
            int index = Integer.parseInt(logicKakakAsprak) % size;
            return index;
    }

    public void cari(String data) {
        if (search(data) == false) {
            System.out.println("Kota tidak ada di dalam tabel");
        } else {
            System.out.println("Kota ada di dalam tabel");
        }
    }

    public void hapus(String data) {
        int index = hash(data);
        if (table[index] == null) {
            return;
        }
        System.out.println(data + " berhasil dihapus");
        table[index].remove(data);
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print(i + " -> ");
            if (table[i] == null) {
                System.out.println("[]");
            } else {
                table[i].print();
            }
        }
    }
}

class NodeLL {
    String data;
    NodeLL next;

    public NodeLL(String data) {
        this.data = data;
        this.next = null;
    }
}

class LL {
    NodeLL head, tail;
    int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(NodeLL input) {
        if (isEmpty()) {
            head = tail = input;
        } else {
            tail.next = input;
            tail = input;
        }
        size++;
    }

    public void remove(String data) {
        NodeLL pointer = head;
        NodeLL prev = null;
        while (pointer != null) {
            if (data.equals(pointer.data)) {
                if (prev == null) {
                    head = pointer.next;
                } else {
                    prev.next = pointer.next;
                }
                size--;
                return;
            }
            prev = pointer;
            pointer = pointer.next;
        }
    }

    public boolean isExists(String data) {
        NodeLL pointer = head;
        while (pointer != null) {
            if (data.equals(pointer.data)) {
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    public void print() {
        NodeLL pointer = head;
        System.out.print("[");
        while (pointer != null) {
            System.out.print(pointer.data);
            if (pointer.next != null) {
                System.out.print(", ");
            }
            pointer = pointer.next;
        }
        System.out.println("]");
    }
}