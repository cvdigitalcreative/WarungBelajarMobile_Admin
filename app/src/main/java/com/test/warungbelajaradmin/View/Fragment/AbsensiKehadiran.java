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
import com.test.warungbelajaradmin.Model.Absensi;
import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Adapter.AbsensiKehadiranAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsensiKehadiran extends Fragment {
    private ArrayList<Absensi> absensi_list;
    private RecyclerView rv_absensi_kehadiran;
    private DatabaseReference ref;
    private String jenis_kursus, UID;

    public AbsensiKehadiran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_absensi_kehadiran, container, false);

        init(view);
        receivedData();
        viewAbsensiKehadiran();

        return view;
    }

    private void viewAbsensiKehadiran() {
        absensi_list = new ArrayList<>();

        ref.child(UID).child("kursus").child(jenis_kursus).child("absensi").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                absensi_list = new ArrayList<>();

                for(DataSnapshot kehadiranSnapshot : dataSnapshot.getChildren()){
                    int pertemuan = Integer.parseInt(kehadiranSnapshot.getKey());
                    String status = kehadiranSnapshot.child("status").getValue().toString();

                    Absensi absensi = new Absensi(pertemuan, status);
                    absensi_list.add(absensi);
                }

                rv_absensi_kehadiran.setAdapter(new AbsensiKehadiranAdapter(absensi_list, UID, jenis_kursus));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void receivedData() {
        jenis_kursus = getArguments().getString("jenis_kursus");
        UID = getArguments().getString("UID");
    }

    public void init(View view){
        rv_absensi_kehadiran = view.findViewById(R.id.rv_absensi_kehadiran);
        rv_absensi_kehadiran.setHasFixedSize(true);
        LinearLayoutManager MyLinearLayoutManager = new LinearLayoutManager(getActivity());
        rv_absensi_kehadiran.setLayoutManager(MyLinearLayoutManager);

        ref = FirebaseDatabase.getInstance().getReference("nobel");
    }

}
