package com.test.warungbelajaradmin.Model;

import com.google.firebase.database.Exclude;

public class JadwalKursusMentor {
    private String jenis_kursus;
    private String paket;
    private String batch;

    @Exclude
    private Jadwal jadwal;

    public JadwalKursusMentor() {
    }

    public JadwalKursusMentor(String jenis_kursus, String paket, String batch, Jadwal jadwal) {
        this.jenis_kursus = jenis_kursus;
        this.paket = paket;
        this.batch = batch;
        this.jadwal = jadwal;
    }

    public String getJenis_kursus() {
        return jenis_kursus;
    }

    public void setJenis_kursus(String jenis_kursus) {
        this.jenis_kursus = jenis_kursus;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }

    public void setJadwal(Jadwal jadwal) {
        this.jadwal = jadwal;
    }
}
