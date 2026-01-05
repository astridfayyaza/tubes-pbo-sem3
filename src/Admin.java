import java.sql.Connection;
import java.sql.PreparedStatement;

public class Admin extends User implements CRUD {

    private String adminCode;

    public Admin(int user_id, String nama, String username,
            String password) {
        super(user_id, nama, username, password, Role.ADMIN);
    }

    @Override
    public void viewProfile() {
        System.out.println("--- Profil Admin ---");
        System.out.println("ID Admin   : " + user_id);
        System.out.println("Kode Admin : " + adminCode);
        System.out.println("Nama       : " + nama);
        System.out.println("Username   : " + username);
        System.out.println("Role       : " + role);
    }

    @Override
    public void tambah(Product p) {
        String sql = "INSERT INTO produk VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, p.getId_product());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getUkuran());
            ps.setString(4, p.getWarna());
            ps.setDouble(5, p.getHarga());
            ps.setInt(6, p.getStok());

            ps.executeUpdate();
            System.out.println("Product tersimpan di database");

        } catch (Exception e) {
            System.out.println("Eksepsi: " + e.getMessage());
        }
    }

    @Override
    public void update(int produk_id, String nama, String ukuran, String warna, float harga, int stok) {
        Product p = new Product(produk_id, nama, ukuran, warna, harga, stok);
        String sql = "UPDATE produk SET nama=?, ukuran=?, warna=?, harga=?, stok=? WHERE produk_id=?";
        
        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getNama());
            ps.setString(2, p.getUkuran());
            ps.setString(3, p.getWarna());
            ps.setDouble(4, p.getHarga());
            ps.setInt(5, p.getStok());
            ps.setInt(6, p.getId_product());

            ps.executeUpdate();
            System.out.println("Product diupdate");

        } catch (Exception e) {
            System.out.println("Eksepsi: " + e.getMessage());
        }
    }

    @Override
    public void hapus(int produk_id) {
        String sql = "DELETE FROM produk WHERE produk_id=?";

        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, produk_id);
            ps.executeUpdate();
            System.out.println("Product dihapus");

        } catch (Exception e) {
            System.out.println("Eksepsi: " + e.getMessage());
        }
    }

    public void viewReport() {
        System.out.println("Admin melihat laporan sistem");
    }
}
