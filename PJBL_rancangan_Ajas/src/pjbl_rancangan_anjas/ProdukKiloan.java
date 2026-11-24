/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pjbl_rancangan_anjas;

/**
 * Produk yang dijual per kilogram (beras, gula curah, dll).
 * Tidak implements Kadaluarsa karena tidak punya tanggal kadaluarsa tercetak.
 * @author AnjasAnjasAnjas
 */
public class ProdukKiloan extends Produk {
    
    private final double hargaPerKg;

    public ProdukKiloan(String id, String nama, double hargaPerKg, double stokKg) {
        // Harga satuan produk kiloan = harga per kg
        super(id, nama, hargaPerKg, stokKg);
        this.hargaPerKg = hargaPerKg;
    }

    /**
     * Harga = harga per kg x berat. Berat boleh pecahan (2,5 kg).
     * @param berat berat dalam kilogram
     */
    @Override
    public double hitungHarga(double berat) {
        return hargaPerKg * berat;
    }

    public double getHargaPerKg() { return hargaPerKg; }
}
