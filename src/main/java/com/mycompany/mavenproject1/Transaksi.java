package com.mycompany.mavenproject1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    private String noTransaksi;
    private LocalDateTime waktu;
    private String status;
    private Kasir kasir;
    private List<ItemTransaksi> items;
    private Pembayaran metodePembayaran;

    public Transaksi(String noTransaksi, Kasir kasir) {
        this.noTransaksi = noTransaksi;
        this.waktu = LocalDateTime.now();
        this.status = "BARU";
        this.kasir = kasir;
        this.items = new ArrayList<>();
    }

    public boolean tambahItem(Produk produk, int jumlah) {
        if (produk.isTersedia(jumlah)) {
            items.add(new ItemTransaksi(produk, jumlah));
            return true;
        }
        return false;
    }

    public int hitungTotal() {
        int total = 0;
        for (ItemTransaksi item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public boolean bayar(Pembayaran metode) {
        if (items.isEmpty()) {
            return false;
        }
        int total = hitungTotal();
        if (metode.bayar(total)) {
            this.metodePembayaran = metode;
            this.status = "LUNAS";
            for (ItemTransaksi item : items) {
                item.getProduk().kurangiStok(item.getJumlah());
            }
            return true;
        }
        return false;
    }

    public String cetakNota() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== NOTA BELANJA ==========\n");
        sb.append("No. Transaksi : ").append(noTransaksi).append("\n");
        sb.append("Waktu         : ").append(waktu.format(fmt)).append("\n");
        sb.append("Kasir         : ").append(kasir.getNama()).append("\n");
        sb.append("----------------------------------\n");
        for (ItemTransaksi item : items) {
            sb.append(String.format("%-16s %4d %-4s Rp%,d\n",
                    item.getProduk().getNama(),
                    item.getJumlah(),
                    item.getProduk().satuan(),
                    item.getSubtotal()));
        }
        sb.append("----------------------------------\n");
        int total = hitungTotal();
        sb.append(String.format("TOTAL         : Rp%,d\n", total));
        if (metodePembayaran instanceof BayarTunai tunai) {
            sb.append(String.format("Tunai         : Rp%,d\n", tunai.getUangDiterima()));
            sb.append(String.format("Kembalian     : Rp%,d\n", tunai.hitungKembalian(total)));
        } else if (metodePembayaran instanceof BayarQRIS qris) {
            sb.append("Metode        : QRIS (Ref: ").append(qris.getKodeReferensi()).append(")\n");
        }
        sb.append("Status        : ").append(status).append("\n");
        sb.append("==================================\n");
        return sb.toString();
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public String getStatus() {
        return status;
    }

    public List<ItemTransaksi> getItems() {
        return items;
    }
}

