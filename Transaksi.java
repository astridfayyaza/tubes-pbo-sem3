
import java.time.LocalDate;
import java.time.LocalTime;

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
}
