/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pjbl_rancangan_anjas;

import java.time.LocalDate; // kelas bawaan Java untuk tanggal (tanpa jam)
/**
 * Produk berkemasan yang dijual per buah (mie instan, susu, dll).
 * - extends Produk        : mewarisi id, nama, harga, stok, kurangiStok(), dll.
 * - implements Kadaluarsa : berjanji menyediakan cekKadaluarsa().
 * @author AnjasAnjasAnjas
 */
public class ProdukKemasan extends Produk implements Kadaluarsa {
    
    private final String barcode;
    private final LocalDate kadaluarsa; // tanggal kadaluarsa

    public ProdukKemasan(String id, String nama, double harga, double stok,
                         String barcode, LocalDate kadaluarsa) {
        super(id, nama, harga, stok); // data umum diurus oleh kelas induk
        this.barcode = barcode;
        this.kadaluarsa = kadaluarsa;
    }

    /** Harga = harga satuan x jumlah buah, dengan 2 validasi. */
    @Override // menandakan method ini mengisi method abstract milik Produk
    public double hitungHarga(double jumlah) {
        // Produk kemasan tidak boleh dibeli pecahan (mis. 2,5 bungkus)
        if (jumlah != Math.floor(jumlah)) {
            throw new IllegalArgumentException("Produk kemasan harus dibeli dalam jumlah bulat");
        }
        // Produk kadaluarsa tidak boleh dijual
        if (cekKadaluarsa()) {
            throw new IllegalStateException(nama + " sudah kadaluarsa");
        }
        return harga * jumlah;
    }

    /** Kadaluarsa jika hari ini sudah melewati tanggal kadaluarsa. */
    @Override
    public boolean cekKadaluarsa() {
        return LocalDate.now().isAfter(kadaluarsa);
    }

    public String getBarcode() { return barcode; }
    public LocalDate getKadaluarsa() { return kadaluarsa; }
}
