public class DetailTransaksi {
    private int detailId;
    private int transaksiId;
    private int produkId;
    private int userId;
    
    private String namaBarang;
    private double harga; // Menggunakan double untuk decimal
    private int jumlah;
    private double total;

    // Relasi Objek sesuai diagram
    private Product product;
    private Kasir kasir;

    public DetailTransaksi(int detailId, int transaksiId, Product product, Kasir kasir, int jumlah) {
        this.detailId = detailId;
        this.transaksiId = transaksiId;
        this.product = product;
        this.kasir = kasir;
        this.jumlah = jumlah;
        
        // Otomatis mengambil data dari objek Product
        if (product != null) {
            this.produkId = product.getId_product();
            this.namaBarang = product.getNama();
            this.harga = product.getHarga();
            this.total = this.harga * jumlah;
        }
        
        if (kasir != null) {
            this.userId = kasir.getKasirCode(); 
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
        // Logika untuk menambah barang ke detail transaksi
        System.out.println("Barang " + namaBarang + " berhasil diinput.");
    }
}