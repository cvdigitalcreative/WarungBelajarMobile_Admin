package com.test.warungbelajaradmin.Model;

import com.google.firebase.database.Exclude;

public class Mentor {
    private ProfileMentor profile;
    private JadwalKursusMentor jadwalKursus;

    @Exclude
    private String uid;

    public Mentor() {
    }

    public Mentor(ProfileMentor profile, JadwalKursusMentor jadwalKursus, String uid) {
        this.profile = profile;
        this.jadwalKursus = jadwalKursus;
        this.uid = uid;
    }

    public ProfileMentor getProfile() {
        return profile;
    }

    public void setProfile(ProfileMentor profile) {
        this.profile = profile;
    }

    public JadwalKursusMentor getJadwalKursus() {
        return jadwalKursus;
    }

    public void setJadwalKursus(JadwalKursusMentor jadwalKursus) {
        this.jadwalKursus = jadwalKursus;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
