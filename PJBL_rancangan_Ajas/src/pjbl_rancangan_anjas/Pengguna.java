/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pjbl_rancangan_anjas;

/**
 * KELAS ABSTRAK: induk dari semua jenis pengguna (Kasir dan PemilikToko).
 * @author AnjasAnjasAnjas
 */
public abstract class Pengguna {
    
     // "private" = hanya bisa diakses dari dalam kelas ini (enkapsulasi).
    private  String idPengguna;
    private  String nama;
    private  String username;
    private  String password;
    
    /**
     * CONSTRUCTOR: dipanggil saat objek dibuat, tugasnya mengisi atribut.
     * "protected" = hanya boleh dipanggil oleh kelas turunan (Kasir/PemilikToko).
     */
    protected Pengguna(String idPengguna, String nama, String username, String password) {
        // "this.x = x" artinya: isi atribut milik objek ini dengan parameter x
        this.idPengguna = idPengguna;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Memeriksa apakah password yang dimasukkan cocok.
     * @return true jika cocok (login berhasil), false jika salah
     */
    public boolean login(String password) {
        return this.password.equals(password);
    }
    
    // ---------- GETTER: cara kelas lain membaca atribut private ----------
    public String getIdPengguna() { return idPengguna; }
    public String getNama() { return nama; }
    public String getUsername() { return username; }
}
