public class Product implements CRUD{
    private int id_product;
    private riwayat_stok riwayatStok;
    private detail_transaksi dTransaksi;
    private String nama;
    private String ukuran;
    private String warna;
    private float harga;
    private int stok;

    public Product(int id_product, String nama, String ukuran, String warna, float harga, int stok) {
        this.id_product = id_product;
        this.nama = nama;
        this.ukuran = ukuran;
        this.warna = warna;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public float getHarga() {
        return harga;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int updateStok(int updateStok) {
        this.stok = updateStok;

        return this.stok;
    }

    @Override
    public void tambah(Product newProduct) {

        System.out.println("Product berhasil di tambahkan");
    }

    @Override
    public void update(String nama, String ukuran, String warna, float harga, int stok) {
        if (nama.equals("N/A")) {
            nama = this.nama;
        } if (ukuran.equals("N/A")) {
            ukuran = this.ukuran;
        } if (warna.equals("N/A")) {
            warna = this.warna;
        } if (harga == 0.0) {
            harga = this.harga;
        } if (stok == 0) {
            stok = this.stok;
        }
        setId_product(id_product);
        setNama(nama);
        setUkuran(ukuran);
        setWarna(warna);
        setHarga(harga);
        setStok(stok);

        System.out.println("Product berhasil di ubah");
    }

    @Override
    public void hapus(int id_product) {
        boolean ditemukan = false;

    for (int i = 0; i < Main.daftarProduct.size(); i++) {
        if (Main.daftarProduct.get(i).getId_product() == id_product) {
            Main.daftarProduct.remove(i);
            ditemukan = true;
            System.out.println("Product berhasil dihapus");
            break;
        }
    }

        if (!ditemukan) {
            System.out.println("Product dengan ID " + id_product + " tidak ditemukan");
        }
    }

    
}