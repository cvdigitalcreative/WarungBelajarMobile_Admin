package com.test.warungbelajaradmin.View.Adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.test.warungbelajaradmin.Model.Nobel;
import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Fragment.AbsensiKehadiran;

import java.util.ArrayList;

public class AbsensiNobelAdapter extends RecyclerView.Adapter<AbsensiNobelAdapter.AbsensiNobelHolder> {
    private ArrayList<Nobel> nobel_list;
    private FragmentActivity activity;

    public AbsensiNobelAdapter(ArrayList<Nobel> nobel_list, FragmentActivity activity) {
        this.nobel_list = nobel_list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AbsensiNobelHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item_absensi_nobel, viewGroup, false);

        AbsensiNobelHolder holder = new AbsensiNobelHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AbsensiNobelHolder absensiNobelHolder, final int i) {
        absensiNobelHolder.tv_nama.setText(nobel_list.get(i).getNama());
        absensiNobelHolder.tv_mentor.setText(nobel_list.get(i).getKursus().getMentor());
        absensiNobelHolder.tv_jadwal1.setText(nobel_list.get(i).getKursus().getCatatan_jadwal().getHari1()+" "+nobel_list.get(i).getKursus().getCatatan_jadwal().getJam_pertama());
        absensiNobelHolder.tv_jadwal2.setText(nobel_list.get(i).getKursus().getCatatan_jadwal().getHari2()+" "+nobel_list.get(i).getKursus().getCatatan_jadwal().getJam_kedua());

        absensiNobelHolder.btn_absensi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sendedData = new Bundle();
                sendedData.putString("UID", nobel_list.get(i).getUID());
                sendedData.putString("jenis_kursus", nobel_list.get(i).getKursus().getJenis_kursus());

                AbsensiKehadiran fragment = new AbsensiKehadiran();
                fragment.setArguments(sendedData);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_absensi_nobel, fragment, "absensi_kehadiran").commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return nobel_list.size();
    }

    public class AbsensiNobelHolder extends RecyclerView.ViewHolder{
        TextView tv_nama, tv_mentor, tv_jadwal1, tv_jadwal2;
        Button btn_absensi;

        public AbsensiNobelHolder(@NonNull View itemView) {
            super(itemView);

            tv_nama = itemView.findViewById(R.id.item_nama);
            tv_mentor = itemView.findViewById(R.id.item_mentor);
            tv_jadwal1 = itemView.findViewById(R.id.item_jadwal1);
            tv_jadwal2 = itemView.findViewById(R.id.item_jadwal2);

            btn_absensi = itemView.findViewById(R.id.item_absensi);
        }
    }
}
