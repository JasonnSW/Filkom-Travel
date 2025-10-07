import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Sbd1 {
    static String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=TestDB;encrypt=true;trustServerCertificate=true";
    static String namaPengguna = "Peni";
    static String sandi = "123";
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionUrl, namaPengguna, sandi);
            hapusDataLama(conn);
            masukkanDataDummy(conn);
            sbd1(conn);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void hapusDataLama(Connection conn) {
        String deleteMahasiswa = "DELETE FROM Nilai WHERE NIM = ?";
        String deleteNilai = "DELETE FROM Mahasiswa WHERE NIM = ?";
        try {
            PreparedStatement psNilai = conn.prepareStatement(deleteMahasiswa);
            psNilai.setString(1, "161616");
            psNilai.executeUpdate();
            psNilai.setString(1, "181818");
            psNilai.executeUpdate();
            PreparedStatement psMahasiswa = conn.prepareStatement(deleteNilai);
            psMahasiswa.setString(1, "161616");
            psMahasiswa.executeUpdate();
            psMahasiswa.setString(1, "181818");
            psMahasiswa.executeUpdate();
            System.out.println("Old data deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void masukkanDataDummy(Connection conn) {
        String insertMahasiswa = "INSERT INTO Mahasiswa (NIM, Nama) VALUES (?, ?)";
        String insertNilai = "INSERT INTO Nilai (NIM, Nilai) VALUES (?, ?)";
        try {
            PreparedStatement psMahasiswa = conn.prepareStatement(insertMahasiswa);
            psMahasiswa.setString(1, "12345");
            psMahasiswa.setString(2, "John Doe");
            psMahasiswa.setString(1, "67890");
            psMahasiswa.setString(2, "Jane Smith");
            psMahasiswa.setString(1, "161616");
            psMahasiswa.setString(2, "Syifani");
            psMahasiswa.executeUpdate();
            psMahasiswa.setString(1, "181818");
            psMahasiswa.setString(2, "Kadek");
            psMahasiswa.executeUpdate();
            PreparedStatement psNilai = conn.prepareStatement(insertNilai);
            psNilai.setString(1, "12345");
            psNilai.setFloat(2, 85.5f);
            psNilai.setString(1, "67890");
            psNilai.setFloat(2, 90.0f);
            psNilai.setString(1, "161616");
            psNilai.setFloat(2, 100.0f);
            psNilai.executeUpdate();
            psNilai.setString(1, "181818");
            psNilai.setFloat(2, 100.0f);
            psNilai.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sbd1(Connection conn) {
        String query = """
                SELECT Mahasiswa.NIM, Mahasiswa.Nama, Nilai.Nilai
                FROM Mahasiswa
                INNER JOIN Nilai ON Mahasiswa.NIM = Nilai.NIM;
                """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            System.out.println("===================================");
            System.out.println("| NIM       | Nama         | Nilai |");
            System.out.println("===================================");
            while (rs.next()) {
                String nim = rs.getString("NIM");
                String nama = rs.getString("Nama");
                float nilai = rs.getFloat("Nilai");
                System.out.printf("| %-9s | %-12s | %-5.2f |\n", nim, nama, nilai);
            }
            System.out.println("===================================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
