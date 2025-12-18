
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Transaksi {
    private int transaksi_id;
    private LocalDate tanggal;
    private LocalTime waktu;
    private Kasir kasir;
    private ArrayList<DetailTransaksi> dTransaksi = new ArrayList<>();

    public Transaksi(int transaksi_id, Kasir kasir) {
        this.transaksi_id = transaksi_id;
        this.tanggal = LocalDate.now();
        this.waktu = LocalTime.now();
        this.kasir = kasir;
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

    public void tambahItem() {
        DetailTransaksi dt = new DetailTransaksi(
                dTransaksi.size() + 1, null, kasir, 0);
        dt.inputBarang();
        dTransaksi.add(dt);
    }

    public void tampilTransaksi() {
        System.out.println("=== TRANSAKSI #" + transaksi_id + " ===");
        double grandTotal = 0;

        for (DetailTransaksi dt : dTransaksi) {
            dt.showDetailTransaksi();
            grandTotal += dt.getTotal();
        }

        System.out.println("Grand Total : " + grandTotal);
    }
}
