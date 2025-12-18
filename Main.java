import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Product> daftarProduct = new ArrayList<>();
    public static ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();

    public static void main(String[] args) {
        // daftarProduct.add(new Product(101, "Kemeja", "L", "Hitam", 100000, 10));

        // Kasir kasir1 = new Kasir(1, "Budi", "Bud", "123", 101);

        // Product p1 = new Product(1, "Kaos", "M", "Hitam", 50000, 10);
        // Main.daftarProduct.add(p1);

        // Transaksi transaksi = new Transaksi(0, null, kasir1, 0, 0);
        // transaksi.inputTransaksi();
        menuUtama();
    }

    public static void menuUtama() {
        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Tampilkan Produk");
            System.out.println("2. Input Transaksi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    Product.tampilProduk();
                    break;

                case 2:
                    Kasir kasirAktif = new Kasir(1, "Budi", "Bud", "123", 101);
                    Transaksi transaksi = new Transaksi(0, null, kasirAktif, 0, 0);
                    transaksi.inputTransaksi();
                    break;

                case 0:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Menu tidak valid!");
            }
        } while (pilihan != 0);
    }
}
