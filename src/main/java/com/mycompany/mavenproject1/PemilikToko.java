package com.mycompany.mavenproject1;

public class PemilikToko extends Pengguna {
    public PemilikToko(String id, String nama, String username, String password) {
        super(id, nama, username, password);
    }

    @Override
    public String peran() {
        return "PemilikToko";
    }
}

