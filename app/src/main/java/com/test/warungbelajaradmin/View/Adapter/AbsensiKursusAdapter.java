package com.test.warungbelajaradmin.View.Adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Fragment.AbsensiBatch;

import java.util.ArrayList;

public class AbsensiKursusAdapter extends RecyclerView.Adapter<AbsensiKursusAdapter.AbsensiKursusHolder>{
    private ArrayList<String> kursus_list;
    private FragmentActivity activity;

    public AbsensiKursusAdapter(ArrayList<String> kursus_list, FragmentActivity activity) {
        this.kursus_list = kursus_list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AbsensiKursusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item_absensi_kursus, viewGroup, false);

        AbsensiKursusHolder holder = new AbsensiKursusHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AbsensiKursusHolder absensiKursusHolder, final int i) {
        absensiKursusHolder.tv_kursus.setText(kursus_list.get(i));
        absensiKursusHolder.cv_absensi_kursus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sendedData = new Bundle();

                if(kursus_list.get(i).equals("Pengenalan Pemrograman Private")){
                    sendedData.putString("jenis_kursus", "pengenalan_pemrograman");
                    sendedData.putString("paket_kursus", "private");
                }
                else if(kursus_list.get(i).equals("Pengenalan Pemrograman Public")){
                    sendedData.putString("jenis_kursus", "pengenalan_pemrograman");
                    sendedData.putString("paket_kursus", "grup");
                }
                else if(kursus_list.get(i).equals("Pemrograman Dekstop")){
                    sendedData.putString("jenis_kursus", "pemrograman_dekstop");
                    sendedData.putString("paket_kursus", "grup");
                }
                else if(kursus_list.get(i).equals("Pemrograman Mobile")){
                    sendedData.putString("jenis_kursus", "pemrograman_mobile");
                    sendedData.putString("paket_kursus", "grup");
                }
                else{
                    sendedData.putString("jenis_kursus", "pemrograman_website");
                    sendedData.putString("paket_kursus", "grup");
                }

                AbsensiBatch fragment = new AbsensiBatch();
                fragment.setArguments(sendedData);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment_absensi_nobel, fragment, "absensi_batch").commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return kursus_list.size();
    }

    public class AbsensiKursusHolder extends RecyclerView.ViewHolder{
        TextView tv_kursus;
        CardView cv_absensi_kursus;

        public AbsensiKursusHolder(@NonNull View itemView) {
            super(itemView);

            tv_kursus = itemView.findViewById(R.id.item_kursus);
            cv_absensi_kursus = itemView.findViewById(R.id.cardview_absensi_kursus);
        }
    }
}
