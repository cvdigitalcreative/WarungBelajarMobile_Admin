package com.test.warungbelajaradmin.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.test.warungbelajaradmin.R;

public class MenuUtama extends AppCompatActivity {
    private Button btn_konfirmasi_pembayaran, btn_buat_grup_pembelajaran, btn_absensi_nobel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        init();
        goToSubMenu();
    }

    private void goToSubMenu() {
        btn_absensi_nobel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, BaseAbsensiNobel.class);
                startActivity(intent);
            }
        });

        btn_buat_grup_pembelajaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, BaseBuatGrupKursus.class);
                startActivity(intent);
            }
        });

        btn_konfirmasi_pembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, BaseKonfirmasiPembayaran.class);
                startActivity(intent);
            }
        });
    }

    public void init(){
        btn_konfirmasi_pembayaran = findViewById(R.id.btn_konfirmasi_pembayaran);
        btn_buat_grup_pembelajaran = findViewById(R.id.btn_buat_grup);
        btn_absensi_nobel = findViewById(R.id.btn_absensi);
    }
}
