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
import com.test.warungbelajaradmin.View.Fragment.BuatGrupJadwal;

import java.util.ArrayList;

public class BuatGrupKursusAdapter extends RecyclerView.Adapter<BuatGrupKursusAdapter.BuatGrupKursusHolder> {
    private ArrayList<String> kursus_list;
    private FragmentActivity activity;

    public BuatGrupKursusAdapter(ArrayList<String> kursus_list, FragmentActivity activity) {
        this.kursus_list = kursus_list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BuatGrupKursusAdapter.BuatGrupKursusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item_buat_grup_kursus, viewGroup, false);

        BuatGrupKursusHolder holder = new BuatGrupKursusHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BuatGrupKursusHolder buatGrupKursusHolder, final int i) {
        buatGrupKursusHolder.tv_buat_grup_kursus.setText(kursus_list.get(i));
        buatGrupKursusHolder.cv_buat_grup_kursus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sendedData = new Bundle();

                if(kursus_list.get(i).equals("Pengenalan Pemrograman Private")){
                    sendedData.putString("jenis_kursus", "pengenalan_pemrograman");
                    sendedData.putString("paket_kursus", "private");
                    sendedData.putString("pilihan", "0");
                }
                else if(kursus_list.get(i).equals("Pengenalan Pemrograman Public")){
                    sendedData.putString("jenis_kursus", "pengenalan_pemrograman");
                    sendedData.putString("paket_kursus", "grup");
                    sendedData.putString("pilihan", "0");
                }
                else if(kursus_list.get(i).equals("Pemrograman Dekstop")){
                    sendedData.putString("jenis_kursus", "pemrograman_dekstop");
                    sendedData.putString("paket_kursus", "grup");
                    sendedData.putString("pilihan", "1");
                }
                else if(kursus_list.get(i).equals("Pemrograman Mobile")){
                    sendedData.putString("jenis_kursus", "pemrograman_mobile");
                    sendedData.putString("paket_kursus", "grup");
                    sendedData.putString("pilihan", "2");
                }
                else{
                    sendedData.putString("jenis_kursus", "pemrograman_website");
                    sendedData.putString("paket_kursus", "grup");
                    sendedData.putString("pilihan", "3");
                }

                BuatGrupJadwal fragment = new BuatGrupJadwal();
                fragment.setArguments(sendedData);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment_buat_grup, fragment, "buat_grup_jadwal").commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return kursus_list.size();
    }

    public class BuatGrupKursusHolder extends RecyclerView.ViewHolder{
        TextView tv_buat_grup_kursus;
        CardView cv_buat_grup_kursus;

        public BuatGrupKursusHolder(@NonNull View itemView) {
            super(itemView);

            tv_buat_grup_kursus = itemView.findViewById(R.id.item_buat_grup_kursus);
            cv_buat_grup_kursus = itemView.findViewById(R.id.cardview_buat_grup_kursus);
        }
    }
}
