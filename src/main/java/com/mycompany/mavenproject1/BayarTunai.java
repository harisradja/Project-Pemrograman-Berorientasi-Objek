package com.mycompany.mavenproject1;

public class BayarTunai implements Pembayaran {
    private int uangDiterima;

    public BayarTunai(int uangDiterima) {
        this.uangDiterima = uangDiterima;
    }

    @Override
    public boolean bayar(int total) {
        return uangDiterima >= total;
    }

    public int hitungKembalian(int total) {
        return uangDiterima - total;
    }

    public int getUangDiterima() {
        return uangDiterima;
    }
}

