public class Admin extends User {

    private String adminCode;

    public Admin(int user_id, String nama, String username, String password, String adminCode) {
        super(user_id, nama, username, password, Role.ADMIN);
        this.adminCode = adminCode;
    }

    public void viewProfile() {
        System.out.println("--- Profil Admin ---");
        System.out.println("ID Admin   : " + user_id);
        System.out.println("Kode Admin : " + adminCode);
        System.out.println("Nama       : " + getNama());
        System.out.println("Username   : " + getUsername());
        System.out.println("Role       : " + role);
    }

    public void viewReport() {
        System.out.println("Admin " + getNama() + " sedang melihat laporan sistem...");
    }

    @Override
    public boolean login(String inputUsername, String inputPassword) {
        if (this.getUsername().equals(inputUsername) && this.password.equals(inputPassword)) {
            System.out.println("Admin " + getNama() + " berhasil login.");
            return true;
        } else {
            System.out.println("Login gagal. Username atau password salah.");
            return false;
        }
    }

    @Override
    public void tambah(Product newProduct) {
        System.out.println("Admin menambahkan produk: " + nama);
    }

    @Override
    public void update(String nama, String ukuran, String warna, float harga, int stok) {
        System.out.println("Admin memperbarui produk: " + nama);
    }

    @Override
    public void hapus(int id_product) {
        System.out.println("Admin menghapus produk dengan ID: " + id_product);
    }
}
