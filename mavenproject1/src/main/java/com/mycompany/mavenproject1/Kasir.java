package com.mycompany.mavenproject1;

public class Kasir extends Pengguna {
    public Kasir(String id, String nama, String username, String password) {
        super(id, nama, username, password);
    }

    @Override
    public String peran() {
        return "Kasir";
    }
}

