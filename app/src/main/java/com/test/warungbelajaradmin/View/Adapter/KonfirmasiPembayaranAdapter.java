package com.test.warungbelajaradmin.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.warungbelajaradmin.Model.Nobel;
import com.test.warungbelajaradmin.R;

import java.util.ArrayList;

public class KonfirmasiPembayaranAdapter extends RecyclerView.Adapter<KonfirmasiPembayaranAdapter.KonfirmasiPembayaranHolder>{
    private ArrayList<Nobel> nobel_list;
    private FragmentActivity activity;
    private DatabaseReference ref;

    public KonfirmasiPembayaranAdapter(ArrayList<Nobel> nobel_list, FragmentActivity activity) {
        this.nobel_list = nobel_list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public KonfirmasiPembayaranHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item_konfirmasi_pembayaran, viewGroup, false);

        KonfirmasiPembayaranHolder holder = new KonfirmasiPembayaranHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final KonfirmasiPembayaranHolder konfirmasiPembayaranHolder, final int i) {
        konfirmasiPembayaranHolder.tv_nama_konfirmasi.setText(nobel_list.get(i).getNama());
        konfirmasiPembayaranHolder.tv_kursus_konfirmasi.setText(nobel_list.get(i).getKursus().getJenis_kursus());
        konfirmasiPembayaranHolder.tv_paket_konfirmasi.setText(nobel_list.get(i).getKursus().getPaket());
        konfirmasiPembayaranHolder.tv_harga_konfirmasi.setText(String.valueOf(nobel_list.get(i).getKursus().getHarga()));

        konfirmasiPembayaranHolder.btn_lihat_bukti_konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);
                LayoutInflater layoutInflater = activity.getLayoutInflater();
                View mView = layoutInflater.inflate(R.layout.modal_konfirmasi_lihat_bukti, null);

                ImageView ivBuktiPembayaran = mView.findViewById(R.id.konfirmasi_bukti);
                Button btnKembali = mView.findViewById(R.id.konfirmasi_kembali);

                Glide.with(activity).load(nobel_list.get(i).getKursus().getFoto_bukti_pembayaran()).into(ivBuktiPembayaran);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                dialog.show();
                dialog.setCanceledOnTouchOutside(false);

                btnKembali.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        konfirmasiPembayaranHolder.btn_pembayaran_konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(activity);
                LayoutInflater layoutInflater = activity.getLayoutInflater();
                View mView = layoutInflater.inflate(R.layout.modal_konfirmasi_pembayaran, null);

                TextView etJumlahDuit = mView.findViewById(R.id.konfirmasi_jumlah_duit);
                Button btnBatal = mView.findViewById(R.id.konfirmasi_batal);
                Button btnOk = mView.findViewById(R.id.konfirmasi_ok);

                final int harga_dibayar = Integer.parseInt(etJumlahDuit.getText().toString());

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();

                dialog.show();
                dialog.setCanceledOnTouchOutside(false);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(harga_dibayar != 0){
                            Toast.makeText(activity.getApplicationContext(), "Harga harus diisi!", Toast.LENGTH_SHORT);
                            return;
                        }

                        ref = FirebaseDatabase.getInstance().getReference();
                        ref.child("nobel").child(nobel_list.get(i).getUID()).child("kursus").child(nobel_list.get(i).getKursus().getJenis_kursus()).child("informasi_dasar").child("harga_dibayar").setValue(harga_dibayar);
                        ref.child("nobel").child(nobel_list.get(i).getUID()).child("kursus").child(nobel_list.get(i).getKursus().getJenis_kursus()).child("informasi_dasar").child("status").setValue("Menunggu Penjadwalan");

                        konfirmasiPembayaranHolder.cv_konfirmasi_pembayaran.setVisibility(View.GONE);

                        dialog.dismiss();
                    }
                });

                btnBatal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return nobel_list.size();
    }

    public class KonfirmasiPembayaranHolder extends RecyclerView.ViewHolder{
        TextView tv_nama_konfirmasi, tv_kursus_konfirmasi, tv_paket_konfirmasi, tv_harga_konfirmasi;
        Button btn_lihat_bukti_konfirmasi, btn_pembayaran_konfirmasi;
        CardView cv_konfirmasi_pembayaran;

        public KonfirmasiPembayaranHolder(@NonNull View itemView) {
            super(itemView);

            tv_nama_konfirmasi = itemView.findViewById(R.id.item_konfirmasi_nama);
            tv_kursus_konfirmasi = itemView.findViewById(R.id.item_konfirmasi_kursus);
            tv_paket_konfirmasi = itemView.findViewById(R.id.item_konfirmasi_paket);
            tv_harga_konfirmasi = itemView.findViewById(R.id.item_konfirmasi_harga);

            btn_lihat_bukti_konfirmasi = itemView.findViewById(R.id.item_konfirmasi_lihat_bukti);
            btn_pembayaran_konfirmasi = itemView.findViewById(R.id.item_konfirmasi_pembayaran);

            cv_konfirmasi_pembayaran = itemView.findViewById(R.id.cardview_konfirmasi_pembayaran);
        }
    }
}
