package com.test.warungbelajaradmin.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Fragment.KonfirmasiPembayaran;

public class BaseKonfirmasiPembayaran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_konfirmasi_pembayaran);

        goToKonfirmasiPembayaran();
    }

    public void goToKonfirmasiPembayaran(){
        KonfirmasiPembayaran frament = new KonfirmasiPembayaran();
        getSupportFragmentManager().beginTransaction().add(R.id.container_fragment_konfirmasi_pembayaran, frament, "konfirmasi_pembayaran").commit();
    }
}
