public class Kasir extends User {

    private String kasirCode;

    public Kasir(int user_id, String nama, String username, String password, String kasirCode) {
        super(user_id, nama, username, password, Role.KASIR);
        this.kasirCode = kasirCode;
    }

    public void viewProfile() {
        System.out.println("--- Profil Kasir ---");
        System.out.println("ID: " + user_id);
        System.out.println("Kode Kasir: " + kasirCode);
        System.out.println("Nama: " + getNama());
        System.out.println("Username: " + getUsername());
    }

    @Override
    public boolean login(String inputUsername, String inputPassword) {
        if (this.getUsername().equals(inputUsername) && this.password.equals(inputPassword)) {
            System.out.println("Kasir " + getNama() + " berhasil login.");
            return true;
        } else {
            System.out.println("Login gagal. Username atau password salah.");
            return false;
        }
    }

    public int getKasirCode() {
        System.out.println("Kode Kasir: " + kasirCode);
        return user_id;
    } 
}
