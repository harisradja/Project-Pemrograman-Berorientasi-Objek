/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pjbl_rancangan_anjas;

/**
 * KELAS ABSTRAK untuk semua produk.
 * Berisi data dan perilaku yang SAMA untuk semua jenis produk.
 * Hal yang BERBEDA (cara menghitung harga) dibiarkan abstract agar
 * ditentukan oleh kelas turunan.
 * @author AnjasAnjasAnjas
 */
public abstract class Produk {
    // "protected" = boleh diakses kelas ini dan turunannya (ProdukKemasan, ProdukKiloan)
    protected String idProduk;
    protected String nama;
    protected double harga; // harga per satuan (per buah atau per kg)
    protected double stok;  // double supaya produk kiloan boleh pecahan (mis. 12,5 kg)
    
    protected Produk(String idProduk, String nama, double harga, double stok) {
        this.idProduk = idProduk;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }
    
    /**
     * METHOD ABSTRACT: hanya ada "tanda tangan"-nya, tanpa isi.
     * Setiap kelas turunan WAJIB menulis isinya sendiri.
     * Inilah dasar POLIMORFISME: pemanggilannya sama, hasilnya sesuai jenis produk.
     */
    public abstract double hitungHarga(double jumlah);
    
    /** Mengurangi stok saat barang terjual, dengan validasi. */
    public void kurangiStok(double jumlah) {
        if (jumlah <= 0) throw new IllegalArgumentException("Jumlah harus > 0");
        if (jumlah > stok) throw new IllegalStateException("Stok " + nama + " tidak cukup");
        stok -= jumlah; // sama dengan: stok = stok - jumlah
    }
    
    /** Menambah stok saat barang masuk, dengan validasi. */
    public void tambahStok(double jumlah) {
        if (jumlah <= 0) throw new IllegalArgumentException("Jumlah harus > 0");
        stok += jumlah;
    }
    
    // Getter
    public String getIdProduk() { return idProduk; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public double getStok() { return stok; }

    /** toString() menentukan teks yang tampil saat objek di-print. */
    @Override
    public String toString() {
        return String.format("%s - %s (stok: %.2f)", idProduk, nama, stok);
    }
}
