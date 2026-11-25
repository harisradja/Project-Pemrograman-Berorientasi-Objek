/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pjbl_rancangan_anjas;

/**
 * Satu baris belanja dalam nota, misalnya "Mie Instan x 5".
 * Berelasi KOMPOSISI dengan Transaksi: item tidak punya arti tanpa transaksinya.
 * @author AnjasAnjasAnjas
 */
public class ItemTransaksi {
    
    private Produk produk;       // produk yang dibeli
    private double jumlah;       // banyaknya (buah atau kg)
    private double hargaSatuan;  // harga saat transaksi (SNAPSHOT)
    private double subtotal;           // hasil harga x jumlah

    public ItemTransaksi(Produk produk, double jumlah) {
        if (jumlah <= 0) throw new IllegalArgumentException("Jumlah harus > 0");
        this.produk = produk;
        this.jumlah = jumlah;
        // Harga disalin agar nota lama tidak berubah jika harga produk naik kelak
        this.hargaSatuan = produk.getHarga();
        hitungSubTotal(); // langsung hitung subtotal (sekaligus validasi produk)
    }

    /**
     * Subtotal dihitung oleh produknya sendiri (polimorfisme):
     * kemasan dihitung per buah, kiloan dihitung per kg.
     */
    public double hitungSubTotal() {
        subtotal = produk.hitungHarga(jumlah);
        return subtotal;
    }

    public Produk getProduk() { return produk; }
    public double getJumlah() { return jumlah; }
    public double getHargaSatuan() { return hargaSatuan; }
    public double getSubtotal() { return subtotal; }
}
