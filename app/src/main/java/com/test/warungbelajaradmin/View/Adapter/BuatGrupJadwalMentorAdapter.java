package com.test.warungbelajaradmin.View.Adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.warungbelajaradmin.Model.Mentor;
import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Fragment.BuatGrupJadwal;

import java.util.ArrayList;

public class BuatGrupJadwalMentorAdapter extends RecyclerView.Adapter<BuatGrupJadwalMentorAdapter.BuatGrupJadwalMentorHolder> {
    private ArrayList<Mentor> mentor_list;
    private FragmentActivity activity;

    public BuatGrupJadwalMentorAdapter(ArrayList<Mentor> mentor_list, FragmentActivity activity) {
        this.mentor_list = mentor_list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public BuatGrupJadwalMentorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item_buat_grup_jadwal, viewGroup, false);

        BuatGrupJadwalMentorHolder holder = new BuatGrupJadwalMentorHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BuatGrupJadwalMentorHolder buatGrupJadwalHolder, final int i) {
        buatGrupJadwalHolder.tvNama.setText(mentor_list.get(i).getProfile().getNama());
        buatGrupJadwalHolder.tvSimbol.setText(String.valueOf(mentor_list.get(i).getProfile().getNama().charAt(0)));

        buatGrupJadwalHolder.cvBuatGrupDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuatGrupJadwal buat_grup_jadwal = (BuatGrupJadwal) activity.getSupportFragmentManager().findFragmentByTag("buat_grup_jadwal");
                buat_grup_jadwal.setUIDMentor(mentor_list.get(i).getUid(), mentor_list.get(i).getProfile().getNama());
//                buatGrupJadwalHolder.ll_data.setBackgroundColor(activity.getResources().getColor(R.color.selectedItem));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mentor_list.size();
    }

    public class BuatGrupJadwalMentorHolder extends RecyclerView.ViewHolder{
        LinearLayout ll_data;
        TextView tvNama, tvSimbol;
        CardView cvBuatGrupDaftar;

        public BuatGrupJadwalMentorHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.item_nama_grup);
            tvSimbol = itemView.findViewById(R.id.item_simbol_grup);
            cvBuatGrupDaftar = itemView.findViewById(R.id.cardview_buat_grup_daftar);
            ll_data = itemView.findViewById(R.id.ll_data);
        }
    }
}
