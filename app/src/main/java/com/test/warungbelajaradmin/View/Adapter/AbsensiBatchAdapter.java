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

import com.test.warungbelajaradmin.Model.Kursus;
import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Fragment.AbsensiBatch;
import com.test.warungbelajaradmin.View.Fragment.AbsensiNobel;

import java.util.ArrayList;

public class AbsensiBatchAdapter extends RecyclerView.Adapter<AbsensiBatchAdapter.AbsensiBatchHolder> {
    private ArrayList<String> batch_list;
    private FragmentActivity activity;
    private Kursus kursus;


    public AbsensiBatchAdapter(ArrayList<String> batch_list, FragmentActivity activity, Kursus kursus) {
        this.batch_list = batch_list;
        this.activity = activity;
        this.kursus = kursus;
    }

    @NonNull
    @Override
    public AbsensiBatchHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item_absensi_batch, viewGroup, false);

        AbsensiBatchHolder holder = new AbsensiBatchHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AbsensiBatchHolder absensiBatchHolder, final int i) {
        absensiBatchHolder.tv_batch.setText("Batch "+batch_list.get(i));
        absensiBatchHolder.cv_absensi_batch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle sendedData = new Bundle();
                sendedData.putString("jenis_kursus", kursus.getJenis_kursus());
                sendedData.putString("paket_kursus", kursus.getPaket());
                sendedData.putString("batch", batch_list.get(i));

                AbsensiNobel fragment = new AbsensiNobel();
                fragment.setArguments(sendedData);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment_absensi_nobel, fragment, "absensi_nobel").commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return batch_list.size();
    }

    public class AbsensiBatchHolder extends RecyclerView.ViewHolder{
        TextView tv_batch;
        CardView cv_absensi_batch;

        public AbsensiBatchHolder(@NonNull View itemView) {
            super(itemView);

            tv_batch = itemView.findViewById(R.id.item_batch);
            cv_absensi_batch = itemView.findViewById(R.id.cardview_absensi_batch);
        }
    }
}
