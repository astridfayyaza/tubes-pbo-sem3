
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class Product {
    private int id_product;
    private RiwayatStok riwayatStok;
    private DetailTransaksi dTransaksi;
    private String nama;
    private String ukuran;
    private String warna;
    private double harga;
    private int stok;

    public Product(int id_product, String nama, String ukuran, String warna, double harga, int stok) {
        this.id_product = id_product;
        this.nama = nama;
        this.ukuran = ukuran;
        this.warna = warna;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void updateStok(int produk_id, int stok) {

        Product p = new Product(produk_id, "", "", "", 0, stok);

        if (Product.getById(produk_id) == null) {
            System.out.println("Produk tidak ditemukan!");
            return;
        }

        String sql = "UPDATE produk SET stok=stok+? WHERE produk_id=?";
        String sqlRiwayat = "INSERT INTO riwayat_stok (produk_id, jumlah, tipe_perubahan, tgl_perubahan) VALUES (?, ?, ?, ?)";

        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                PreparedStatement ps2 = c.prepareStatement(sqlRiwayat)) {

            ps.setInt(1, p.getStok());
            ps.setInt(2, p.getId_product());

            ps2.setInt(1, p.getId_product());
            ps2.setInt(2, p.getStok());
            ps2.setString(3, "Masuk");
            ps2.setDate(4, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();
            ps2.executeUpdate();
            System.out.println("stok diupdate");

        } catch (Exception e) {
            System.out.println("Eksepsi: " + e.getMessage());
        }

    }

    public static void tampilProduk() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM `produk`";

        try (Connection c = Koneksi.getConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("produk_id"),
                        rs.getString("nama"),
                        rs.getString("ukuran"),
                        rs.getString("warna"),
                        rs.getDouble("harga"),
                        rs.getInt("stok"));
                list.add(p);
            }
        } catch (Exception e) {
            System.out.println("Eksepsi: " + e.getMessage());
        }

        System.out.println("=== DAFTAR PRODUK ===");
        System.out.println("ID\tNama\tUkuran\tWarna\tHarga\tStok");

        if (list.isEmpty()) {
            System.out.println("Belum ada produk.");
            return;
        }

        for (Product p : list) {
            System.out.println(
                    p.getId_product() + "\t" +
                            p.getNama() + "\t" +
                            p.getUkuran() + "\t" +
                            p.getWarna() + "\t" +
                            p.getHarga() + "\t" +
                            p.getStok());
        }
    }

    public static Product getById(int idProduk) {
        String sql = "SELECT * FROM produk WHERE produk_id = ?";

        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idProduk);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Product(
                        rs.getInt("produk_id"),
                        rs.getString("nama"),
                        rs.getString("ukuran"),
                        rs.getString("warna"),
                        rs.getDouble("harga"),
                        rs.getInt("stok"));
            }

        } catch (Exception e) {
            System.out.println("Gagal ambil produk: " + e.getMessage());
        }

        return null;
    }

}