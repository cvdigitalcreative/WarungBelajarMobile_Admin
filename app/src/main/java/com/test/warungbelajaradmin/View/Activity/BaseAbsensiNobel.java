package com.test.warungbelajaradmin.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Fragment.AbsensiKursus;

public class BaseAbsensiNobel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_absensi_nobel);

        goToAbsensiNobel();
    }

    public void goToAbsensiNobel(){
        AbsensiKursus frament = new AbsensiKursus();
        getSupportFragmentManager().beginTransaction().add(R.id.container_fragment_absensi_nobel, frament, "absensi_kursus").commit();
    }
}
