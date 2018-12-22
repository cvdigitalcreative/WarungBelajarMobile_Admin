package com.test.warungbelajaradmin.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.warungbelajaradmin.Model.Absensi;
import com.test.warungbelajaradmin.R;

import java.util.ArrayList;

public class AbsensiKehadiranAdapter extends RecyclerView.Adapter<AbsensiKehadiranAdapter.AbsensiKehadiranHolder> {
    private ArrayList<Absensi> absensi_list;
    private String UID, jenis_kursus;
    private DatabaseReference ref;

    public AbsensiKehadiranAdapter(ArrayList<Absensi> absensi_list, String UID, String jenis_kursus) {
        this.absensi_list = absensi_list;
        this.UID = UID;
        this.jenis_kursus = jenis_kursus;
    }

    @NonNull
    @Override
    public AbsensiKehadiranAdapter.AbsensiKehadiranHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item_absensi_kehadiran, viewGroup, false);

        AbsensiKehadiranHolder holder = new AbsensiKehadiranHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AbsensiKehadiranHolder absensiKehadiranHolder, final int i) {
        absensiKehadiranHolder.tv_pertemuan.setText("Pertemuan "+String.valueOf(absensi_list.get(i).getPertemuan()));
        absensiKehadiranHolder.tv_status.setText(absensi_list.get(i).getStatus());

        if(absensi_list.get(i).getStatus().equals("belum berjalan")){
            ref = FirebaseDatabase.getInstance().getReference("nobel");

            absensiKehadiranHolder.btn_hadir.setVisibility(View.VISIBLE);
            absensiKehadiranHolder.btn_tidak_hadir.setVisibility(View.VISIBLE);

            absensiKehadiranHolder.btn_hadir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pertemuan = String.valueOf(absensi_list.get(i).getPertemuan());
                    ref.child(UID).child("kursus").child(jenis_kursus).child("absensi").child(pertemuan).child("status").setValue("Hadir");
                    ref.child(UID).child("kursus").child(jenis_kursus).child("materi").child(pertemuan).child("status").setValue("active");

                    absensiKehadiranHolder.tv_status.setText("Hadir");
                    absensiKehadiranHolder.btn_hadir.setVisibility(View.GONE);
                    absensiKehadiranHolder.btn_tidak_hadir.setVisibility(View.GONE);
                }
            });

            absensiKehadiranHolder.btn_tidak_hadir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pertemuan = String.valueOf(absensi_list.get(i).getPertemuan());
                    ref.child(UID).child("kursus").child(jenis_kursus).child("absensi").child(pertemuan).child("status").setValue("Tidak Hadir");
                    ref.child(UID).child("kursus").child(jenis_kursus).child("materi").child(pertemuan).child("status").setValue("active");

                    absensiKehadiranHolder.tv_status.setText("Tidak Hadir");
                    absensiKehadiranHolder.btn_hadir.setVisibility(View.GONE);
                    absensiKehadiranHolder.btn_tidak_hadir.setVisibility(View.GONE);
                }
            });
        }
        else{
            absensiKehadiranHolder.btn_hadir.setVisibility(View.GONE);
            absensiKehadiranHolder.btn_tidak_hadir.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return absensi_list.size();
    }

    public class AbsensiKehadiranHolder extends RecyclerView.ViewHolder{
        TextView tv_pertemuan, tv_status;
        Button btn_hadir, btn_tidak_hadir;

        public AbsensiKehadiranHolder(@NonNull View itemView) {
            super(itemView);

            tv_pertemuan = itemView.findViewById(R.id.item_pertemuan);
            tv_status = itemView.findViewById(R.id.item_status);

            btn_hadir = itemView.findViewById(R.id.item_hadir);
            btn_tidak_hadir = itemView.findViewById(R.id.item_tidak_hadir);

        }
    }
}
