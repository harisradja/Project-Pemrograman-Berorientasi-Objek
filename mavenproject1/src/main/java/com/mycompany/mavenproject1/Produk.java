package com.mycompany.mavenproject1;

import java.time.LocalDate;

public abstract class Produk {
    private String kodeBarang;
    private String nama;
    private String kategori;
    private int harga;
    private int stok;
    private int stokMinimum;

    public Produk(String kodeBarang, String nama, String kategori, int harga, int stok, int stokMinimum) {
        this.kodeBarang = kodeBarang;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
        this.stokMinimum = stokMinimum;
    }

    public boolean isTersedia(int jumlah) {
        return jumlah > 0 && stok >= jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (jumlah > 0 && stok >= jumlah) {
            stok -= jumlah;
        }
    }

    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            stok += jumlah;
        }
    }

    public boolean perluPeringatan(LocalDate hariIni) {
        return stok <= stokMinimum;
    }

    public abstract int hitungHarga(int jumlah);

    public abstract String satuan();

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        if (harga > 0) {
            this.harga = harga;
        }
    }

    public int getStok() {
        return stok;
    }

    public int getStokMinimum() {
        return stokMinimum;
    }
}

