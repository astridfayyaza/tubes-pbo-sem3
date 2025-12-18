
import java.time.LocalDate;
import java.time.LocalTime;

public class Transaksi {
    private int transaksi_id;
    private LocalDate tanggal;
    private LocalTime waktu;
    private double total;
    private DetailTransaksi dTransaksi;

    public Transaksi(int transaksi_id, LocalDate tanggal, LocalTime waktu, double total) {
        this.transaksi_id = transaksi_id;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.total = total;
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

    public double gettotal() {
        return total;
    }

    public void settotal(double total) {
        this.total = total;
    }

    public void cariTransaksi() {
        
    }
}
