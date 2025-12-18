public class Kasir extends User {

    private int kasirCode;

    public Kasir(int user_id, String nama, String username, String password, int kasirCode) {
        super(user_id, nama, username, password, Role.KASIR);
        this.kasirCode = kasirCode;
    }

    public int getKasirCode() {
        return kasirCode;
    }

    public void viewProfile() {
        System.out.println("--- Profil Kasir ---");
        System.out.println("ID Kasir   : " + user_id);
        System.out.println("Kode Kasir : " + kasirCode);
        System.out.println("Nama       : " + nama);
        System.out.println("Username   : " + username);
        System.out.println("Role       : " + role);
    }

    public void buatTransaksi() {
        System.out.println("Kasir membuat transaksi");
    }
}
