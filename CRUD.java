public interface CRUD {
    public void tambah(Product newProduct);
    public void update(String nama, String ukuran, String warna, float harga, int stok);
    public void hapus(int id_product);
}
