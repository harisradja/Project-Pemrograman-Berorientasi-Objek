/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pjbl_rancangan_anjas;

/**
 * INTERFACE = kontrak. Kelas yang "implements" interface ini WAJIB
 * menulis isi method cekKadaluarsa().
 * Hanya produk yang punya tanggal kadaluarsa yang memakainya
 * (beras curah tidak).
 * @author AnjasAnjasAnjas
 */
public interface Kadaluarsa {
     /** @return true jika produk sudah kadaluarsa. */
    boolean cekKadaluarsa();
}
