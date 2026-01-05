
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Product> daftarProduct = new ArrayList<>();
    public static ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
    public static ArrayList<User> daftarUser = new ArrayList<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int pilihan;
        do {
            System.out.println("\n === MENU AWAL ===");
            System.out.println("1. Login");
            System.out.println("2. Keluar");

            System.out.println("Pilih Menu: ");
            pilihan = input.nextInt();
            switch (pilihan) {
                case 1:
                    User user = login();

                    if (user != null) {
                        menuUtama(user);
                    }
                    break;
                case 2:
                    System.out.println("Terima Kasih!");
                    break;
                default:
                    System.out.println("Menu tidak valid!");
                    break;
            }
        } while (pilihan != 2);

    }

    public static void menuUtama(User user) {
        Product p = new Product(0, null, null, null, 0, 0);
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Tampilkan Produk");
            System.out.println("2. Input Transaksi");

            if (user.getRole() == User.Role.ADMIN) {
                System.out.println("3. Input Produk");
                System.out.println("4. Update Produk");
                System.out.println("5. Hapus Produk");
                System.out.println("6. Update Stok");
                System.out.println("7. Cek Detail Transaksi(By User)");
            }
            System.out.println("8. Menampilkan Riwayat Stok");
            System.out.println("20. Tampilkan Profile");

            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    Product.tampilProduk();
                    break;

                case 2:
                    if (user instanceof Kasir) {
                        Transaksi t = new Transaksi(
                                Main.daftarTransaksi.size() + 1,
                                (Kasir) user);
                        t.tambahItem();

                        Main.daftarTransaksi.add(t);
                        t.tampilTransaksi();
                    } else {
                        System.out.println("Menu transaksi hanya untuk kasir!");
                    }
                    break;

                case 3:
                    if (user instanceof Admin) {
                        input = new Scanner(System.in);

                        System.out.println("=== INPUT PRODUCT ===");

                        System.out.print("ID Product : ");
                        int id = input.nextInt();
                        input.nextLine();

                        System.out.print("Nama Pakaian : ");
                        String nama = input.nextLine();

                        System.out.print("Ukuran       : ");
                        String ukuran = input.nextLine();

                        System.out.print("Warna        : ");
                        String warna = input.nextLine();

                        System.out.print("Harga        : ");
                        double harga = input.nextFloat();

                        System.out.print("Stok         : ");
                        int stok = input.nextInt();

                        p = new Product(id, nama, ukuran, warna, harga, stok);
                        ((Admin) user).tambah(p);
                    } else {
                        System.out.println("Akses ditolak!");
                    }
                    break;

                case 4:
                    if (user instanceof Admin) {
                        Scanner in = new Scanner(System.in);

                        System.out.print("ID Produk yang diupdate : ");
                        int id = in.nextInt();
                        in.nextLine();

                        System.out.print("Nama baru   : ");
                        String nama = in.nextLine();

                        System.out.print("Ukuran baru : ");
                        String ukuran = in.nextLine();

                        System.out.print("Warna baru  : ");
                        String warna = in.nextLine();

                        System.out.print("Harga baru  : ");
                        float harga = in.nextFloat();

                        System.out.print("Stok baru   : ");
                        int stok = in.nextInt();

                        ((Admin) user).update(id, nama, ukuran, warna, harga, stok);
                    } else {
                        System.out.println("Akses ditolak! Admin only.");
                    }
                    break;

                case 5:
                    if (user instanceof Admin) {
                        Scanner in = new Scanner(System.in);

                        System.out.print("ID Produk yang dihapus : ");
                        int id = in.nextInt();

                        ((Admin) user).hapus(id);
                    } else {
                        System.out.println("Akses ditolak! Admin only.");
                    }
                    break;
                case 6:
                    if (user instanceof Admin) {
                        Scanner in = new Scanner(System.in);

                        System.out.print("ID Produk yang diupdate : ");
                        int id = in.nextInt();
                        in.nextLine();

                        System.out.print("Stok baru   : ");
                        int stok = in.nextInt();

                        p.updateStok(id, stok);
                    } else {
                        System.out.println("Akses ditolak! Admin only.");
                    }
                    break;
                case 7:
                    if (user instanceof Admin) {
                        Scanner in = new Scanner(System.in);

                        System.out.print("Masukkan Nama Kasir :");
                        String nama = in.nextLine();
                        DetailTransaksi dt = new DetailTransaksi(0, null, null, 0);
                        dt.showDetailTransaksi(nama);
                    } else {
                        System.out.println("Menu ini hanya untuk Admin!");
                    }
                    break;
                case 8:
                    RiwayatStok riwayatStok = new RiwayatStok(0, null, 0, null, null);
                    riwayatStok.showRiwayatStok();
                    break;
                case 20:
                    if (user instanceof Admin) {
                        ((Admin) user).viewProfile();
                    } else 
                        ((Kasir) user).viewProfile();
                    break; 
                case 0:
                    System.out.println("Logout...");
                    break;

                default:
                    System.out.println("Menu tidak valid!");
            }
        } while (pilihan != 0);
    }

    public static User login() {
        Scanner input = new Scanner(System.in);

        System.out.println("=== LOGIN ===");
        System.out.print("Username : ");
        String username = input.nextLine();

        System.out.print("Password : ");
        String password = input.nextLine();

        User user = User.login(username, password);

        if (user != null) {
            System.out.println("Login berhasil sebagai " + user.getRole());
            return user;
        } else {
            System.out.println("Login gagal!");
            return null;
        }
    }

}
