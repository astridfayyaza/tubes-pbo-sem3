import java.util.Scanner;

public class DetailTransaksi {
    private int detailId;
    private int produkId;
    private String userId;

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
            this.userId = kasir.getKasirCode(); // mendapatkan userId dari objek Kasir
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

    public void inputBarang() {
        Scanner input = new Scanner(System.in);

        System.out.println("=== INPUT BARANG TRANSAKSI ===");

        Product.tampilProduk();

        System.out.print("Pilih ID Produk : ");
        int idProduk = input.nextInt();

        Product produkDipilih = null;
        for (Product p : Main.daftarProduct) {
            if (p.getId_product() == idProduk) {
                produkDipilih = p;
                break;
            }
        }

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
        this.product = produkDipilih;
        this.jumlah = jumlah;
        this.produkId = produkDipilih.getId_product();
        this.namaBarang = produkDipilih.getNama();
        this.harga = produkDipilih.getHarga();
        this.total = harga * jumlah;

        // update stok
        produkDipilih.setStok(produkDipilih.getStok() - jumlah);

        System.out.println("Barang berhasil ditambahkan ke transaksi!");
    }

}