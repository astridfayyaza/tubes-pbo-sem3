
import java.time.LocalDate;
import java.time.LocalTime;

public class Transaksi {
    private int transaksi_id;
    private LocalDate tanggal;
    private LocalTime waktu;
    private float totalHarga;

    public Transaksi(int transaksi_id, LocalDate tanggal, LocalTime waktu, float totalHarga) {
        this.transaksi_id = transaksi_id;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.totalHarga = totalHarga;
    }
}
