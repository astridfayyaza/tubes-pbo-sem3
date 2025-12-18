import java.time.LocalDate;

public class RiwayatStok {
    private int riwayatStokId; // Primary Key
    private int produkId;      // Foreign Key
    private Product product;   // Referensi objek Product
    private int jumlah;
    private String tipePerubahan;
    private LocalDate tglPerubahan;

    // Konstruktor
    public RiwayatStok(int riwayatStokId, Product product, int jumlah, String tipePerubahan, LocalDate tglPerubahan) {
        this.riwayatStokId = riwayatStokId;
        this.product = product;
        this.produkId = (product != null) ? product.getId_product() : 0;
        this.jumlah = jumlah;
        this.tipePerubahan = tipePerubahan;
        this.tglPerubahan = tglPerubahan;
    }

    public void showRiwayatStok() {
        System.out.println("=== Riwayat Perubahan ===");
        if (product != null) {
            System.out.println("Nama Barang      : " + product.getNama());
            System.out.println("Stok Saat Ini    : " + product.getStok());
        }
        System.out.println("ID Riwayat       : " + riwayatStokId);
        System.out.println("Jumlah Perubahan : " + jumlah);
        System.out.println("Tipe Perubahan   : " + tipePerubahan);
        System.out.println("Tanggal          : " + tglPerubahan);
    }

    // Getter dan Setter bisa ditambahkan di bawah sini
}