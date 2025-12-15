
import java.time.LocalDate;

public class riwayat_stok{
    private Product product;
    private int riwayat_stok;
    private int jumlah;
    private String tipe_perubahan;
    private LocalDate tgl_perubahan;

    public riwayat_stok(int riwayat_stok, int jumlah, String tipe_perubahan, LocalDate tgl_perubahan) {
        this.riwayat_stok = product.getStok();
        this.jumlah = jumlah;
        this.tipe_perubahan = tipe_perubahan;
        this.tgl_perubahan = tgl_perubahan;
    }

    public void showRiwayatStok() {
        System.out.println("=== Riwayat Perubahan ===");
        System.out.println("    Nama Barang: " + product.getNama());
        System.out.println("    stok awal: " + riwayat_stok);
        System.out.println("    jumlah perubahan: " + jumlah);
        System.out.println("    tipe perubahan: " + tipe_perubahan);
        System.out.println("    tanggal perubahan: " + tgl_perubahan);
    }
}