package com.test.warungbelajaradmin.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Fragment.BuatGrupKursus;

public class BaseBuatGrupKursus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_buat_grup_kursus);

        goToBuatGrupKursus();
    }

    public void goToBuatGrupKursus(){
        BuatGrupKursus frament = new BuatGrupKursus();
        getSupportFragmentManager().beginTransaction().add(R.id.container_fragment_buat_grup, frament, "buat_grup_kursus").commit();
    }
}
