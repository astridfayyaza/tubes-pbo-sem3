
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class RiwayatStok {
    private int riwayatStokId; // PK
    private int produkId; // FK
    private Product product;
    private int jumlah;
    private String tipePerubahan;
    private LocalDate tglPerubahan;

    // konstruktor
    public RiwayatStok(int riwayatStokId, Product product, int jumlah, String tipePerubahan, LocalDate tglPerubahan) {
        this.riwayatStokId = riwayatStokId;
        this.product = product;
        this.produkId = (product != null) ? product.getId_product() : 0;
        this.jumlah = jumlah;
        this.tipePerubahan = tipePerubahan;
        this.tglPerubahan = tglPerubahan;
    }

    public void showRiwayatStok() {
        ArrayList<RiwayatStok> riwayatStok = new ArrayList<>();
        String sql = "SELECT r.riwayat_stok_id, r.produk_id, r.jumlah, r.tipe_perubahan, r.tgl_perubahan, p.nama, p.ukuran, p.warna, p.harga, p.stok FROM riwayat_stok r JOIN produk p ON r.produk_id = p.produk_id ORDER BY r.tgl_perubahan DESC";

        try (Connection c = Koneksi.getConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product(
                    rs.getInt("produk_id"),
                    rs.getString("nama"),
                    rs.getString("ukuran"),
                    rs.getString("warna"),
                    rs.getDouble("harga"),
                    rs.getInt("stok")
                );

                RiwayatStok rist = new RiwayatStok(
                        rs.getInt("riwayat_stok_id"),
                        product,
                        rs.getInt("jumlah"),
                        rs.getString("tipe_perubahan"),
                        rs.getDate("tgl_perubahan").toLocalDate());
                riwayatStok.add(rist);
            }
        } catch (Exception e) {
            System.out.println("Ekspsi: " + e.getMessage());
        }

        System.out.println("=== Riwayat Stok ===");
        System.out.println("ID\tProduct\tJumlah\tPerubahan\tTanggal");

        if (riwayatStok.isEmpty()) {
            System.out.println("Belum ada Riwayat Stok");
            return;
        }

        for (RiwayatStok rist : riwayatStok) {
            System.out.println(rist.produkId + "\t" +
                    rist.product.getNama() + "\t" +
                    rist.jumlah + "\t" +
                    rist.tipePerubahan + "\t" +
                    rist.tglPerubahan);
        }
    }
}