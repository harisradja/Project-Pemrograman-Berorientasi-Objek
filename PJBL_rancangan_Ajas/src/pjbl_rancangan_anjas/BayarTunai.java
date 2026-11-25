/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pjbl_rancangan_anjas;

/**
 * Pembayaran dengan uang tunai. "implements" = menjalankan kontrak Pembayaran.
 * @author AnjasAnjasAnjas
 */
public class BayarTunai implements Pembayaran{
    
    private double uangBayar; // uang yang diberikan pembeli
    private double tagihan;         // total yang harus dibayar (diisi saat proses())

    public BayarTunai(double uangBayar) {
        this.uangBayar = uangBayar;
    }

    /** Berhasil jika uang yang diberikan cukup (>= total tagihan). */
    @Override
    public boolean proses(double jumlah) {
        this.tagihan = jumlah; // simpan tagihan untuk menghitung kembalian
        return uangBayar >= jumlah;
    }

    /** Kembalian = uang bayar - tagihan. Dipanggil setelah proses(). */
    public double hitungKembalian() {
        return uangBayar - tagihan;
    }

    public double getUangBayar() { return uangBayar; }
}
