
import java.util.ArrayList;

public class Main {
    public static ArrayList<Product> daftarProduct = new ArrayList<>();
    public static void main(String[] args) {
        daftarProduct.add(
            new Product(001, "Kemeja", "L", "Hitam", 100000, 10)
        );
    }
}
