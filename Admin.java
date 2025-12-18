public class Admin extends User implements CRUD {

    private String adminCode;

    public Admin(int user_id, String nama, String username,
                 String password, String adminCode) {
        super(user_id, nama, username, password, Role.ADMIN);
        this.adminCode = adminCode;
    }

    public void viewProfile() {
        System.out.println("--- Profil Admin ---");
        System.out.println("ID Admin   : " + user_id);
        System.out.println("Kode Admin : " + adminCode);
        System.out.println("Nama       : " + nama);
        System.out.println("Username   : " + username);
        System.out.println("Role       : " + role);
    }

    @Override
    public void tambah(Product newProduct) {
        System.out.println("Admin menambahkan produk: "
                + newProduct.getNama());
    }

    @Override
    public void update(int id, String nama, String ukuran,
                       String warna, float harga, int stok) {
        System.out.println("Admin memperbarui produk ID: " + id);
    }

    @Override
    public void hapus(int id_product) {
        System.out.println("Admin menghapus produk ID: " + id_product);
    }

    public void viewReport() {
        System.out.println("Admin melihat laporan sistem");
    }
}
