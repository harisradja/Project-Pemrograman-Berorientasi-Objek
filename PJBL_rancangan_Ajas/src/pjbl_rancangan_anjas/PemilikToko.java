/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pjbl_rancangan_anjas;

/**
 * PemilikToko = pengguna yang mengelola produk, 
 * Juga turunan dari Pengguna (extends), jadi punya login().
 * @author AnjasAnjasAnjas
 */
public class PemilikToko extends Pengguna {
    // Pemilik "mengenal" inventaris toko agar bisa mengelola produknya
    private InventarisToko inventaris;

    public PemilikToko(String id, String nama, String username, String password,
                       InventarisToko inventaris) {
        super(id, nama, username, password); // isi data milik induk
        this.inventaris = inventaris;
    }
    
    /** Mendaftarkan produk baru ke inventaris. */
    public void kelolaProduk(Produk produk) {
        inventaris.tambahProduk(produk);
    }

    /** Mencatat barang masuk: menambah stok produk berdasarkan ID-nya. */
    public void inputStokMasuk(String idProduk, double jumlah) {
        Produk p = inventaris.ambilById(idProduk);
        if (p == null) {
            throw new IllegalArgumentException("Produk tidak ditemukan: " + idProduk);
        }
        p.tambahStok(jumlah);
    }
    
    /** Menampilkan laporan harian ke layar (memakai toString() milik LaporanHarian). */
    public void lihatLaporan(LaporanHarian laporan) {
        System.out.println(laporan);
    }
}
