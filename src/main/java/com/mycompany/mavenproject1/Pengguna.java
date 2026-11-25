package com.mycompany.mavenproject1;

public abstract class Pengguna {
    protected String id;
    protected String nama;
    protected String username;
    private String password;

    public Pengguna(String id, String nama, String username, String password) {
        this.id = id;
        this.nama = nama;
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getUsername() {
        return username;
    }

    public abstract String peran();
}

