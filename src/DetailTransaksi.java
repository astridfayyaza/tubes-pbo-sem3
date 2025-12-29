import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DetailTransaksi {
    private int detailId;
    private int transaksiId;
    private int produkId;
    private int userId;

    private String namaBarang;
    private double harga; // double untuk decimal
    private int jumlah;
    private double total;

    // fungsi turunan dari Product dan Kasir
    private Product product;
    private Kasir kasir;

    public DetailTransaksi(int detailId, Product product, Kasir kasir, int jumlah) {
        this.detailId = detailId;
        this.product = product;
        this.kasir = kasir;
        this.jumlah = jumlah;

        // otomatis mengisi fields lain berdasarkan objek terkait
        if (product != null) {
            this.produkId = product.getId_product();
            this.namaBarang = product.getNama();
            this.harga = product.getHarga();
            this.total = this.harga * jumlah;
        }

        if (kasir != null) {
            this.userId = kasir.getuserId(); // mendapatkan userId dari objek Kasir
        }
    }

    public double getTotal() {
        return total;
    }

    public void showDetailTransaksi() {
        System.out.println("--- Detail Item ---");
        System.out.println("Barang : " + namaBarang);
        System.out.println("Harga  : " + harga);
        System.out.println("Jumlah : " + jumlah);
        System.out.println("Total  : " + total);
        if (kasir != null) {
            System.out.println("Kasir  : " + kasir.getNama());
        }
    }

    public void inputBarang(int transaksiId) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== INPUT BARANG TRANSAKSI ===");
        Product.tampilProduk();

        System.out.print("Pilih ID Produk : ");
        int idProduk = input.nextInt();

        Product produkDipilih = Product.getById(idProduk);
        if (produkDipilih == null) {
            System.out.println("Produk tidak ditemukan!");
            return;
        }

        System.out.print("Jumlah beli : ");
        int jumlah = input.nextInt();

        if (jumlah > produkDipilih.getStok()) {
            System.out.println("Stok tidak mencukupi!");
            return;
        }

        // set data detail transaksi
        this.transaksiId = transaksiId;
        this.produkId = produkDipilih.getId_product();
        this.userId = kasir.getuserId(); // INT
        this.namaBarang = produkDipilih.getNama();
        this.harga = produkDipilih.getHarga();
        this.jumlah = jumlah;
        this.total = harga * jumlah;

        // SIMPAN KE DATABASE
        String sql = "INSERT INTO detail_transaksi "
                + "(transaksi_id, produk_id, user_id, nama_barang, harga, jumlah, total) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {   

            ps.setInt(1, this.transaksiId);
            ps.setInt(2, this.produkId);
            ps.setInt(3, this.userId);
            ps.setString(4, this.namaBarang);
            ps.setDouble(5, this.harga);
            ps.setInt(6, this.jumlah);
            ps.setDouble(7, this.total);

            ps.executeUpdate();
            System.out.println("Detail transaksi berhasil disimpan ke database");

        } catch (Exception e) {
            System.out.println("Gagal simpan detail transaksi: " + e.getMessage());
        }

        // UPDATE STOK DATABASE
        String sqlRS = "UPDATE produk SET stok = stok - ? WHERE produk_id = ? AND stok >= ?";

        // Bagian Update Stok yang benar:
        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sqlRS)) {

            ps.setInt(1, jumlah);
            ps.setInt(2, produkId);
            ps.setInt(3, jumlah);

            int barisTerupdate = ps.executeUpdate(); // Simpan ke variabel, JANGAN panggil lagi di IF

            if (barisTerupdate == 0) {
                System.out.println("Gagal update stok (stok tidak cukup)");
            }
        } catch (Exception e) {
            System.out.println("Gagal update stok: " + e.getMessage());
        }

    }
}