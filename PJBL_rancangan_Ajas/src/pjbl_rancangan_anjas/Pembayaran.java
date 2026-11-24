/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pjbl_rancangan_anjas;

/**
 * INTERFACE untuk semua metode pembayaran (tunai, QRIS, dst).
 * Transaksi cukup tahu "bisa di-proses", tanpa peduli bayarnya lewat apa.
 * Ini disebut STRATEGY PATTERN: metode baru bisa ditambah tanpa mengubah Transaksi.
 * @author AnjasAnjasAnjas
 */
public interface Pembayaran {
    /**
     * @param jumlah total yang harus dibayar
     * @return true jika pembayaran berhasil, false jika gagal
     */
    boolean proses(double jumlah);
}
