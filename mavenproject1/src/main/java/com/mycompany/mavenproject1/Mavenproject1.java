package com.mycompany.mavenproject1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mavenproject1 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Pengguna> daftarPengguna = new ArrayList<>();
    private static final List<Produk> daftarProduk = new ArrayList<>();
    private static final List<Transaksi> riwayatTransaksi = new ArrayList<>();
    private static int nomorUrutTransaksi = 1;

    public static void main(String[] args) {
        isiDataAwal();

        while (true) {
            System.out.println("\n=== SISTEM KASIR & STOK TOKO KELONTONG ===");
            System.out.println("1. Login");
            System.out.println("0. Keluar");
            int pilih = bacaInt("Pilih menu: ");

            if (pilih == 0) {
                System.out.println("Terima kasih!");
                break;
            } else if (pilih == 1) {
                Pengguna user = prosesLogin();
                if (user instanceof Kasir kasir) {
                    menuKasir(kasir);
                } else if (user instanceof PemilikToko pemilik) {
                    menuPemilik(pemilik);
                }
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void isiDataAwal() {
        daftarPengguna.add(new Kasir("K01", "Budi (Kasir)", "kasir", "123"));
        daftarPengguna.add(new PemilikToko("P01", "Siti (Pemilik)", "pemilik", "123"));

        LocalDate hariIni = LocalDate.now();
        daftarProduk.add(new ProdukKemasan("BRG01", "Mi Instan", "Makanan", 3500, 50, 10, hariIni.plusMonths(6)));
        daftarProduk.add(new ProdukKemasan("BRG02", "Minyak Goreng 1L", "Sembako", 18000, 8, 10, hariIni.plusDays(5)));
        daftarProduk.add(new ProdukKemasan("BRG03", "Air Mineral 600ml", "Minuman", 4000, 30, 10, hariIni.plusMonths(12)));
        daftarProduk.add(new ProdukKiloan("BRG04", "Beras Premium", "Sembako", 15000, 10000, 2000));
        daftarProduk.add(new ProdukKiloan("BRG05", "Gula Pasir", "Sembako", 17000, 5000, 1000));
    }

    private static Pengguna prosesLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        for (Pengguna p : daftarPengguna) {
            if (p.login(username, password)) {
                System.out.println("Login berhasil! Selamat datang, " + p.getNama() + " [" + p.peran() + "]");
                return p;
            }
        }
        System.out.println("Username atau password salah.");
        return null;
    }

    private static void menuKasir(Kasir kasir) {
        while (true) {
            System.out.println("\n--- MENU KASIR ---");
            System.out.println("1. Lihat Daftar Produk");
            System.out.println("2. Buat Transaksi Penjualan");
            System.out.println("0. Logout");
            int pilih = bacaInt("Pilih menu: ");

            switch (pilih) {
                case 1 -> tampilkanDaftarProduk();
                case 2 -> buatTransaksi(kasir);
                case 0 -> {
                    System.out.println("Logout berhasil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void menuPemilik(PemilikToko pemilik) {
        while (true) {
            System.out.println("\n--- MENU PEMILIK TOKO ---");
            System.out.println("1. Lihat Daftar Produk");
            System.out.println("2. Edit Produk");
            System.out.println("3. Lihat Peringatan Stok & Kedaluwarsa");
            System.out.println("4. Rekap Penjualan Harian");
            System.out.println("0. Logout");
            int pilih = bacaInt("Pilih menu: ");

            switch (pilih) {
                case 1 -> tampilkanDaftarProduk();
                case 2 -> menuEditProduk();
                case 3 -> lihatPeringatan();
                case 4 -> tampilkanRekapHarian();
                case 0 -> {
                    System.out.println("Logout berhasil.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void menuEditProduk() {
        while (true) {
            System.out.println("\n--- EDIT PRODUK ---");
            System.out.println("1. Tambah Produk Baru");
            System.out.println("2. Ubah Harga Produk");
            System.out.println("3. Tambah Stok Masuk");
            System.out.println("4. Hapus Produk");
            System.out.println("0. Kembali");
            int pilih = bacaInt("Pilih menu: ");

            switch (pilih) {
                case 1 -> tambahProdukBaru();
                case 2 -> ubahHargaProduk();
                case 3 -> tambahStokMasuk();
                case 4 -> hapusProduk();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void tampilkanDaftarProduk() {
        System.out.println("\n=== DAFTAR PRODUK ===");
        System.out.printf("%-7s %-20s %-10s %-12s %-12s %-12s%n",
                "Kode", "Nama", "Kategori", "Harga", "Stok", "Info");
        System.out.println("----------------------------------------------------------------------------");
        for (Produk p : daftarProduk) {
            String hargaLabel = p instanceof ProdukKiloan
                    ? String.format("Rp%,d/kg", p.getHarga())
                    : String.format("Rp%,d/pcs", p.getHarga());
            String stokLabel = p.getStok() + " " + p.satuan();
            String info = p instanceof ProdukKemasan pk
                    ? "Exp: " + pk.getTanggalKedaluwarsa()
                    : "-";
            System.out.printf("%-7s %-20s %-10s %-12s %-12s %-12s%n",
                    p.getKodeBarang(), p.getNama(), p.getKategori(), hargaLabel, stokLabel, info);
        }
    }

    private static Produk cariProduk(String kode) {
        for (Produk p : daftarProduk) {
            if (p.getKodeBarang().equalsIgnoreCase(kode)) {
                return p;
            }
        }
        return null;
    }

    private static void buatTransaksi(Kasir kasir) {
        String noTrx = String.format("TRX-%03d", nomorUrutTransaksi++);
        Transaksi trx = new Transaksi(noTrx, kasir);

        tampilkanDaftarProduk();
        while (true) {
            System.out.print("\nMasukkan Kode Barang (atau 'selesai' untuk bayar): ");
            String kode = scanner.nextLine().trim();
            if (kode.equalsIgnoreCase("selesai")) {
                break;
            }
            Produk p = cariProduk(kode);
            if (p == null) {
                System.out.println("Produk tidak ditemukan.");
                continue;
            }
            int jumlah = bacaInt("Jumlah (" + p.satuan() + "): ");
            if (trx.tambahItem(p, jumlah)) {
                System.out.println("Ditambahkan: " + p.getNama() + " (" + jumlah + " " + p.satuan() + ")");
            } else {
                System.out.println("Stok tidak mencukupi! Sisa stok: " + p.getStok() + " " + p.satuan());
            }
        }

        if (trx.getItems().isEmpty()) {
            System.out.println("Transaksi dibatalkan karena tidak ada item.");
            return;
        }

        int total = trx.hitungTotal();
        System.out.printf("\nTotal Belanja: Rp%,d%n", total);
        System.out.println("Pilih Metode Pembayaran: 1. Tunai | 2. QRIS");
        int metode = bacaInt("Pilih: ");

        boolean berhasil = false;
        if (metode == 1) {
            int uang = bacaInt("Masukkan uang tunai: Rp");
            BayarTunai tunai = new BayarTunai(uang);
            berhasil = trx.bayar(tunai);
            if (!berhasil) {
                System.out.println("Pembayaran gagal! Uang tidak cukup.");
            }
        } else if (metode == 2) {
            String ref = "QR-" + System.currentTimeMillis() % 100000;
            BayarQRIS qris = new BayarQRIS(ref);
            berhasil = trx.bayar(qris);
        } else {
            System.out.println("Metode pembayaran tidak valid.");
        }

        if (berhasil) {
            riwayatTransaksi.add(trx);
            System.out.println(trx.cetakNota());
        }
    }

    private static void tambahProdukBaru() {
        System.out.println("\nJenis Produk: 1. Kemasan (pcs) | 2. Kiloan (gram)");
        int jenis = bacaInt("Pilih: ");
        System.out.print("Kode Barang: ");
        String kode = scanner.nextLine().trim();
        System.out.print("Nama Produk: ");
        String nama = scanner.nextLine().trim();
        System.out.print("Kategori: ");
        String kategori = scanner.nextLine().trim();
        int harga = bacaInt(jenis == 2 ? "Harga per kg: Rp" : "Harga per pcs: Rp");
        int stok = bacaInt(jenis == 2 ? "Stok awal (gram): " : "Stok awal (pcs): ");
        int stokMin = bacaInt(jenis == 2 ? "Stok minimum (gram): " : "Stok minimum (pcs): ");

        if (jenis == 1) {
            System.out.print("Tanggal Kedaluwarsa (YYYY-MM-DD): ");
            LocalDate exp = LocalDate.parse(scanner.nextLine().trim());
            daftarProduk.add(new ProdukKemasan(kode, nama, kategori, harga, stok, stokMin, exp));
        } else {
            daftarProduk.add(new ProdukKiloan(kode, nama, kategori, harga, stok, stokMin));
        }
        System.out.println("Produk berhasil ditambahkan.");
    }

    private static void ubahHargaProduk() {
        tampilkanDaftarProduk();
        System.out.print("Masukkan Kode Barang: ");
        Produk p = cariProduk(scanner.nextLine().trim());
        if (p == null) {
            System.out.println("Produk tidak ditemukan.");
            return;
        }
        int hargaBaru = bacaInt("Masukkan harga baru: Rp");
        p.setHarga(hargaBaru);
        System.out.println("Harga " + p.getNama() + " berhasil diubah.");
    }

    private static void tambahStokMasuk() {
        tampilkanDaftarProduk();
        System.out.print("Masukkan Kode Barang: ");
        Produk p = cariProduk(scanner.nextLine().trim());
        if (p == null) {
            System.out.println("Produk tidak ditemukan.");
            return;
        }
        int tambahan = bacaInt("Jumlah stok masuk (" + p.satuan() + "): ");
        p.tambahStok(tambahan);
        System.out.println("Stok " + p.getNama() + " sekarang: " + p.getStok() + " " + p.satuan());
    }

    private static void hapusProduk() {
        tampilkanDaftarProduk();
        System.out.print("Masukkan Kode Barang yang akan dihapus: ");
        Produk p = cariProduk(scanner.nextLine().trim());
        if (p == null) {
            System.out.println("Produk tidak ditemukan.");
            return;
        }
        daftarProduk.remove(p);
        System.out.println("Produk " + p.getNama() + " berhasil dihapus.");
    }

    private static void lihatPeringatan() {
        System.out.println("\n=== PERINGATAN STOK & KEDALUWARSA ===");
        LocalDate hariIni = LocalDate.now();
        boolean ada = false;
        for (Produk p : daftarProduk) {
            if (p.perluPeringatan(hariIni)) {
                ada = true;
                System.out.print("- " + p.getKodeBarang() + " " + p.getNama() + " -> ");
                if (p.getStok() <= p.getStokMinimum()) {
                    System.out.print("[Stok Menipis: " + p.getStok() + " " + p.satuan() + "] ");
                }
                if (p instanceof ProdukKemasan pk && pk.mendekatiKedaluwarsa(hariIni)) {
                    System.out.print("[Mendekati/Lewat Kedaluwarsa: " + pk.getTanggalKedaluwarsa() + "]");
                }
                System.out.println();
            }
        }
        if (!ada) {
            System.out.println("Semua stok dan tanggal kedaluwarsa aman.");
        }
    }

    private static void tampilkanRekapHarian() {
        System.out.println("\n=== REKAP PENJUALAN HARI INI ===");
        LocalDate hariIni = LocalDate.now();
        int totalOmzet = 0;
        int jumlahTransaksi = 0;

        for (Transaksi t : riwayatTransaksi) {
            if (t.getWaktu().toLocalDate().equals(hariIni) && "LUNAS".equals(t.getStatus())) {
                jumlahTransaksi++;
                int totalTrx = t.hitungTotal();
                totalOmzet += totalTrx;
                System.out.printf("- %s | Total: Rp%,d%n", t.getNoTransaksi(), totalTrx);
            }
        }
        System.out.println("----------------------------------");
        System.out.println("Jumlah Transaksi : " + jumlahTransaksi);
        System.out.printf("Total Omzet      : Rp%,d%n", totalOmzet);
    }

    private static int bacaInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }
}
