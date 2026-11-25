package com.mycompany.mavenproject1;

public class ItemTransaksi {
    private Produk produk;
    private int jumlah;
    private int subtotal;

    public ItemTransaksi(Produk produk, int jumlah) {
        this.produk = produk;
        this.jumlah = jumlah;
        this.subtotal = produk.hitungHarga(jumlah);
    }

    public Produk getProduk() {
        return produk;
    }

    public int getJumlah() {
        return jumlah;
    }

    public int getSubtotal() {
        return subtotal;
    }
}

