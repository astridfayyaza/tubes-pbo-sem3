
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Transaksi extends DetailTransaksi{
    private int transaksi_id;
    private LocalDate tanggal;
    private LocalTime waktu;
    private DetailTransaksi dTransaksi;

    public Transaksi(int detailId, Product product, Kasir kasir, int jumlah, int transaksi_id) {
        super(detailId, product, kasir, jumlah);
        this.transaksi_id = transaksi_id;
        this.tanggal = LocalDate.now();
        this.waktu = LocalTime.now();
    }

    public int getTransaksi_id() {
        return transaksi_id;
    }

    public void setTransaksi_id(int transaksi_id) {
        this.transaksi_id = transaksi_id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public LocalTime getWaktu() {
        return waktu;
    }

    public void setWaktu(LocalTime waktu) {
        this.waktu = waktu;
    }

    public void cariTransaksi() {

    }

    public void inputTransaksi() {
    Scanner input = new Scanner(System.in);

    System.out.println("=== INPUT TRANSAKSI ===");

    System.out.print("Masukkan ID Produk   : ");
    int idProduk = input.nextInt();

    Product produkDipilih = null;

    // Cari produk
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

    System.out.println("Nama Produk : " + produkDipilih.getNama());
    System.out.println("Harga       : " + produkDipilih.getHarga());
    System.out.println("Stok        : " + produkDipilih.getStok());

    System.out.print("Masukkan Jumlah Beli : ");
    int jumlah = input.nextInt();

    if (jumlah > produkDipilih.getStok()) {
        System.out.println("Stok tidak mencukupi!");
        return;
    }

    // Kurangi stok
    produkDipilih.setStok(produkDipilih.getStok() - jumlah);

    // Buat objek transaksi baru
    Transaksi transaksiBaru = new Transaksi(Main.daftarProduct.size() + 1, produkDipilih, super.getKasir(), jumlah, Main.daftarTransaksi.size() + 1000);

    Main.daftarTransaksi.add(transaksiBaru);

    System.out.println("Transaksi berhasil!");
    transaksiBaru.showDetailTransaksi();
}
}
