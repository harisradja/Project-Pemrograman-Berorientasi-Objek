package com.mycompany.mavenproject1;

import java.time.LocalDate;

public class ProdukKemasan extends Produk {
    private LocalDate tanggalKedaluwarsa;

    public ProdukKemasan(String kodeBarang, String nama, String kategori, int harga, int stok, int stokMinimum, LocalDate tanggalKedaluwarsa) {
        super(kodeBarang, nama, kategori, harga, stok, stokMinimum);
        this.tanggalKedaluwarsa = tanggalKedaluwarsa;
    }

    @Override
    public int hitungHarga(int jumlah) {
        return getHarga() * jumlah;
    }

    @Override
    public String satuan() {
        return "pcs";
    }

    public boolean mendekatiKedaluwarsa(LocalDate hariIni) {
        // Peringatan jika sudah kedaluwarsa atau kurang dari/sama dengan 7 hari lagi
        return !tanggalKedaluwarsa.isAfter(hariIni.plusDays(7));
    }

    @Override
    public boolean perluPeringatan(LocalDate hariIni) {
        return super.perluPeringatan(hariIni) || mendekatiKedaluwarsa(hariIni);
    }

    public LocalDate getTanggalKedaluwarsa() {
        return tanggalKedaluwarsa;
    }
}

