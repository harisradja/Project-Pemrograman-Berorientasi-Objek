package com.mycompany.mavenproject1;

public class BayarQRIS implements Pembayaran {
    private String kodeReferensi;
    private String status;

    public BayarQRIS(String kodeReferensi) {
        this.kodeReferensi = kodeReferensi;
        this.status = "MENUNGGU";
    }

    @Override
    public boolean bayar(int total) {
        if (total > 0) {
            this.status = "BERHASIL";
            return true;
        }
        this.status = "GAGAL";
        return false;
    }

    public String getKodeReferensi() {
        return kodeReferensi;
    }

    public String getStatus() {
        return status;
    }
}

