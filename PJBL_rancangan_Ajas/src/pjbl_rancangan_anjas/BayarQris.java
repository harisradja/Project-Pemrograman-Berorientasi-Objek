/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pjbl_rancangan_anjas;

/**
 * Pembayaran dengan QRIS (di sini masih simulasi, belum terhubung ke bank).
 * @author AnjasAnjasAnjas
 */
public class BayarQris implements Pembayaran {
    
    private String kodeQR;
    private String referensi; // nomor referensi dari penyedia pembayaran

    public BayarQris(String kodeQR, String referensi) {
        this.kodeQR = kodeQR;
        this.referensi = referensi;
    }

    /** Berhasil jika tagihan > 0 dan verifikasi QR lolos. */
    @Override
    public boolean proses(double jumlah) {
        return jumlah > 0 && verifikasi();
    }

    /**
     * Simulasi verifikasi: kode QR dan referensi tidak boleh kosong.
     * isBlank() true jika teks kosong atau hanya berisi spasi.
     */
    public boolean verifikasi() {
        return kodeQR != null && !kodeQR.isBlank()
            && referensi != null && !referensi.isBlank();
    }

    public String getKodeQR() { return kodeQR; }
    public String getReferensi() { return referensi; }
    
}
