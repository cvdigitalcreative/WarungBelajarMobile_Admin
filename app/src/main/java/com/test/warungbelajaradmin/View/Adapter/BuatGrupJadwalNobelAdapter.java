package com.test.warungbelajaradmin.View.Adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.warungbelajaradmin.Model.Nobel;
import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Fragment.BuatGrupJadwal;

import java.util.ArrayList;

public class BuatGrupJadwalNobelAdapter extends RecyclerView.Adapter<BuatGrupJadwalNobelAdapter.BuatGrupJadwalHolder> {
    private ArrayList<Nobel> nobel_list;
    private FragmentActivity activity;

    public BuatGrupJadwalNobelAdapter(ArrayList<Nobel> nobel_list, FragmentActivity activity) {
        this.nobel_list = nobel_list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BuatGrupJadwalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item_buat_grup_jadwal, viewGroup, false);

        BuatGrupJadwalHolder holder = new BuatGrupJadwalHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BuatGrupJadwalHolder buatGrupJadwalHolder, final int i) {
        buatGrupJadwalHolder.tvNama.setText(nobel_list.get(i).getNama());
        buatGrupJadwalHolder.tvSimbol.setText(String.valueOf(nobel_list.get(i).getNama().charAt(0)));
        buatGrupJadwalHolder.cvBuatGrupDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuatGrupJadwal buat_grup_jadwal = (BuatGrupJadwal) activity.getSupportFragmentManager().findFragmentByTag("buat_grup_jadwal");

                buat_grup_jadwal.setUIDNobel(nobel_list.get(i).getUID(), nobel_list.get(i).getKursus().getBatch(), nobel_list.get(i).getKursus().getCatatan_jadwal());
//                buatGrupJadwalHolder.ll_data.setBackgroundColor(activity.getResources().getColor(R.color.selectedItem));
            }
        });

    }

    @Override
    public int getItemCount() {
        return nobel_list.size();
    }

    public class BuatGrupJadwalHolder extends RecyclerView.ViewHolder{
        LinearLayout ll_data;
        TextView tvNama, tvSimbol;
        CardView cvBuatGrupDaftar;

        public BuatGrupJadwalHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.item_nama_grup);
            tvSimbol = itemView.findViewById(R.id.item_simbol_grup);
            cvBuatGrupDaftar = itemView.findViewById(R.id.cardview_buat_grup_daftar);
            ll_data = itemView.findViewById(R.id.ll_data);
        }
    }
}
