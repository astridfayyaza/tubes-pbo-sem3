public class DetailTransaksi {
    private int detailId;
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
            this.userId = kasir.getKasirCode(); // mendapatkan userId dari objek Kasir
        }
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
        // logic untuk menambah barang ke file ini
        System.out.println("Barang " + namaBarang + " berhasil diinput.");
    }
}