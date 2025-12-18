
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Product implements CRUD {
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

    public int updateStok(int updateStok) {
        this.stok = updateStok;

        return this.stok;
    }

    @Override
    public void tambah(Product newProduct) {
        Main.daftarProduct.add(newProduct);
        System.out.println("Product berhasil di tambahkan");
    }

    @Override
    public void update(int id, String nama, String ukuran, String warna, float harga, int stok) {
        boolean ditemukan = false;

        for (Product p : Main.daftarProduct) {
            if (p.getId_product() == id) {
                p.setNama(nama);
                p.setUkuran(ukuran);
                p.setWarna(warna);
                p.setHarga(harga);
                p.setStok(stok);

                ditemukan = true;
                System.out.println("Product berhasil diupdate");
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Product dengan ID " + id + " tidak ditemukan");
        }
    }

    @Override
    public void hapus(int id_product) {
        boolean ditemukan = false;

        for (int i = 0; i < Main.daftarProduct.size(); i++) {
            if (Main.daftarProduct.get(i).getId_product() == id_product) {
                Main.daftarProduct.remove(i);
                ditemukan = true;
                System.out.println("Product berhasil dihapus");
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Product dengan ID " + id_product + " tidak ditemukan");
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

}