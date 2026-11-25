package com.mycompany.mavenproject1;

public class ProdukKiloan extends Produk {
    public ProdukKiloan(String kodeBarang, String nama, String kategori, int hargaPerKg, int stokGram, int stokMinimumGram) {
        super(kodeBarang, nama, kategori, hargaPerKg, stokGram, stokMinimumGram);
    }

    @Override
    public int hitungHarga(int gram) {
        // Harga disimpan per kg (1000 gram)
        return (int) Math.round((getHarga() * (double) gram) / 1000.0);
    }

    @Override
    public String satuan() {
        return "gram";
    }
}

