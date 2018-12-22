package com.test.warungbelajaradmin.Model;

import com.google.firebase.database.Exclude;

public class Nobel {
    private String nama;
    private String email;
    private String no_hp;

    @Exclude
    private Kursus kursus;
    private String UID;

    public Nobel() {
    }

    public Nobel(String nama, String email, String no_hp) {
        this.nama = nama;
        this.email = email;
        this.no_hp = no_hp;
    }

    public Nobel(String nama, String email, String no_hp, Kursus kursus, String UID) {
        this.nama = nama;
        this.email = email;
        this.no_hp = no_hp;
        this.kursus = kursus;
        this.UID = UID;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public Kursus getKursus() {
        return kursus;
    }

    public void setKursus(Kursus kursus) {
        this.kursus = kursus;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
