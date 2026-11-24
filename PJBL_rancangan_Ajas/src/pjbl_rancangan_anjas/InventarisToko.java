/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pjbl_rancangan_anjas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AnjasAnjasAnjas
 */
public class InventarisToko {
    // List<Produk> = daftar yang hanya boleh berisi objek Produk (atau turunannya)
    private final List<Produk> daftarProduk = new ArrayList<>();

    /** Menambah produk baru; menolak jika ID sudah dipakai. */
    public void tambahProduk(Produk produk) {
        if (ambilById(produk.getIdProduk()) != null) {
            throw new IllegalArgumentException("ID produk sudah ada: " + produk.getIdProduk());
        }
        daftarProduk.add(produk);
    }

    /** Mencari produk yang namanya mengandung kata kunci, atau ID-nya sama. */
    public List<Produk> cariProduk(String kataKunci) {
        List<Produk> hasil = new ArrayList<>();
        String k = kataKunci.toLowerCase(); // huruf kecil semua agar tidak peka huruf besar/kecil
        for (Produk p : daftarProduk) {
            if (p.getNama().toLowerCase().contains(k) || p.getIdProduk().equalsIgnoreCase(k)) {
                hasil.add(p);
            }
        }
        return hasil;
    }

    /** Mengambil satu produk berdasarkan ID; null jika tidak ada. */
    public Produk ambilById(String id) {
        for (Produk p : daftarProduk) {
            if (p.getIdProduk().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    /** Menampilkan produk yang stoknya sudah di bawah atau sama dengan batas. */
    public List<Produk> cekStokMenipis(double batas) {
        List<Produk> hasil = new ArrayList<>();
        for (Produk p : daftarProduk) {
            if (p.getStok() <= batas) hasil.add(p);
        }
        return hasil;
    }

    public List<Produk> getDaftarProduk() { return daftarProduk; }
}
