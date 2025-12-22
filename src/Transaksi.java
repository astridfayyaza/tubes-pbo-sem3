
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
            simpanTransaksi();

        if (this.transaksi_id <= 0) {
            System.out.println("ERROR: Gagal mendapatkan ID Transaksi. Detail tidak bisa disimpan.");
            return;
        }
        
        DetailTransaksi dt = new DetailTransaksi(dTransaksi.size() + 1, null, this.kasir, 0);
        dt.inputBarang(this.transaksi_id);
        
        dTransaksi.add(dt);
    }
    
    public void simpanTransaksi() {
        String sql = "INSERT INTO transaksi (user_id, tanggal, waktu) VALUES (?, CURDATE(), CURTIME())";

        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            c.setAutoCommit(true);

            ps.setInt(1, kasir.getuserId());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Update field class agar bisa dipakai di tambahItem()
                        this.transaksi_id = rs.getInt(1);
                        System.out.println("Transaksi Berhasil Dibuat. ID: " + this.transaksi_id);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Gagal simpan transaksi: " + e.getMessage());
            this.transaksi_id = 0; // Reset jika gagal
        }
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
