import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class User {

    protected int user_id;
    protected String nama;
    protected String username;
    protected String password;

    public enum Role {
        ADMIN, KASIR
    }

    protected Role role;

    public User(int user_id, String nama, String username,
            String password, Role role) {
        this.user_id = user_id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public abstract void viewProfile();

    // PENGECEKAN USERNAME & PASSWORD (WAJIB)
    public static User login(String username, String password) {

        String sql = """
                    SELECT * FROM user
                    WHERE username = ? AND password = ?
                """;

        try (Connection c = Koneksi.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("user_id");
                String nama = rs.getString("nama");
                String role = rs.getString("role");

                if (role.equalsIgnoreCase("ADMIN")) {
                    return new Admin(id, nama, username, password);
                } else if (role.equalsIgnoreCase("KASIR")) {
                    return new Kasir(id, nama, username, password);
                }
            }

        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return null;
    }

    public String getNama() {
        return nama;
    }

    public Role getRole() {
        return role;
    }
}
