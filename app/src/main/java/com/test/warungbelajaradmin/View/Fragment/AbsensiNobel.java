package com.test.warungbelajaradmin.View.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.warungbelajaradmin.Model.Jadwal;
import com.test.warungbelajaradmin.Model.Kursus;
import com.test.warungbelajaradmin.Model.Nobel;
import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Adapter.AbsensiNobelAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsensiNobel extends Fragment {
    private RecyclerView rv_absensi_nobel;
    private ArrayList<Nobel> nobel_list;
    private String jenis_kursus, paket_kursus, batch;
    private DatabaseReference ref;

    public AbsensiNobel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_absensi_nobel, container, false);

        init(view);
        receivedData();
        viewAbsensiNobel();

        return view;
    }

    private void viewAbsensiNobel() {
        nobel_list = new ArrayList<>();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nobel_list = new ArrayList<>();

                for(DataSnapshot nobelSnapshot : dataSnapshot.getChildren()){
                    String nama = nobelSnapshot.child("profile_nobel").child("nama").getValue().toString();
                    String UID = nobelSnapshot.getKey();

                    String batch_fb = nobelSnapshot.child("kursus").child(jenis_kursus).child("informasi_dasar").child("batch").getValue().toString();
                    String status = nobelSnapshot.child("kursus").child(jenis_kursus).child("informasi_dasar").child("status").getValue().toString();
                    String paket = nobelSnapshot.child("kursus").child(jenis_kursus).child("informasi_dasar").child("paket").getValue().toString();
                    String mentor = nobelSnapshot.child("kursus").child(jenis_kursus).child("informasi_dasar").child("mentor").getValue().toString();

                    String hari1 = nobelSnapshot.child("kursus").child(jenis_kursus).child("jadwal").child("hari1").getValue().toString();
                    String hari2 = nobelSnapshot.child("kursus").child(jenis_kursus).child("jadwal").child("hari2").getValue().toString();
                    String jam1 = nobelSnapshot.child("kursus").child(jenis_kursus).child("jadwal").child("jam_pertama").getValue().toString();
                    String jam2 = nobelSnapshot.child("kursus").child(jenis_kursus).child("jadwal").child("jam_kedua").getValue().toString();

                    Jadwal jadwal = new Jadwal();
                    jadwal.setHari1(hari1);
                    jadwal.setHari2(hari2);
                    jadwal.setJam_pertama(jam1);
                    jadwal.setJam_kedua(jam2);

                    Kursus kursus = new Kursus();
                    kursus.setCatatan_jadwal(jadwal);
                    kursus.setPaket(paket);
                    kursus.setMentor(mentor);
                    kursus.setBatch(Integer.parseInt(batch));
                    kursus.setJenis_kursus(jenis_kursus);

                    if(batch_fb.equals(batch) && paket.equals(paket_kursus) && status.equals("Sedang Berlangsung")){
                        Nobel nobel = new Nobel();
                        nobel.setNama(nama);
                        nobel.setUID(UID);
                        nobel.setKursus(kursus);

                        nobel_list.add(nobel);
                    }
                }

                rv_absensi_nobel.setAdapter(new AbsensiNobelAdapter(nobel_list, getActivity()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void receivedData() {
        jenis_kursus = getArguments().getString("jenis_kursus");
        paket_kursus = getArguments().getString("paket_kursus");
        batch = getArguments().getString("batch");

        System.out.println("go batch : "+batch);
        System.out.println("go paket_kursus : "+paket_kursus);
        System.out.println("go jenis_kursus : "+jenis_kursus);
    }

    public void init(View view){
        rv_absensi_nobel = view.findViewById(R.id.rv_absensi_nobel);
        rv_absensi_nobel.setHasFixedSize(true);
        LinearLayoutManager MyLinearLayoutManager = new LinearLayoutManager(getActivity());
        rv_absensi_nobel.setLayoutManager(MyLinearLayoutManager);

        ref = FirebaseDatabase.getInstance().getReference("nobel");
    }

}
