import java.util.ArrayList;

public class Main {
    public static ArrayList<Product> daftarProduct = new ArrayList<>();
    public static void main(String[] args) {
        daftarProduct.add(
            new Product(001, "Kemeja", "L", "Hitam", 100000, 10)
        );

        Kasir kasir1 = new Kasir(001, "Juned", "Ned", "112", "001");

        Transaksi transaksi = new Transaksi(001, daftarProduct, kasir1, 10, 001);
    }
}
