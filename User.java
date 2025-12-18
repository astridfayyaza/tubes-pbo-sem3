public abstract class User implements CRUD {

    protected int user_id;
    protected String nama;
    protected String username;
    protected String password;

    public enum Role { ADMIN, KASIR }
    protected Role role;

    public User(int user_id, String nama, String username, String password, Role role) {
        this.user_id = user_id;
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void tambah(Product newProduct) {
        System.out.println("Data ditambahkan oleh " + username);
    }

    @Override
    public void update(String nama, String ukuran, String warna, float harga, int stok) {
        System.out.println("Data diperbarui oleh " + username);
    }

    @Override
    public void hapus(int id_product) {
        System.out.println("Data dihapus oleh " + username);
    }

    public abstract boolean login(String inputUsername, String inputPassword);
}
