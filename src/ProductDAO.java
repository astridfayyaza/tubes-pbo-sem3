
import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {

    public static void insert(Product p) {
        String sql = "INSERT INTO product VALUES (?, ?, ?, ?, ?, ?)";

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

        } catch (SQLException e) {
            System.out.println("Eksepsi: " + e.getMessage());
        }
    }

    public static ArrayList<Product> getAll() {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM product";

        try (Connection c = Koneksi.getConnection();
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("id_product"),
                        rs.getString("nama"),
                        rs.getString("ukuran"),
                        rs.getString("warna"),
                        rs.getDouble("harga"),
                        rs.getInt("stok"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Eksepsi: " + e.getMessage());
        }

        return list;
    }

    public static void update(Product p) {
        String sql = "UPDATE product SET nama=?, ukuran=?, warna=?, harga=?, stok=? WHERE id_product=?";

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

        } catch (SQLException e) {
            System.out.println("Eksepsi: " + e.getMessage());
        }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM product WHERE id_product=?";

        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Product dihapus");

        } catch (SQLException e) {
            System.out.println("Eksepsi: " + e.getMessage());
        }
    }
}
