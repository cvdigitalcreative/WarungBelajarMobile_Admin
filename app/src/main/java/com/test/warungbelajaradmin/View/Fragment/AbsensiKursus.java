package com.test.warungbelajaradmin.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Adapter.AbsensiKursusAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsensiKursus extends Fragment {
    private RecyclerView rv_absensi_kursus;
    private ArrayList<String> kursus_list;

    public AbsensiKursus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_absensi_kursus, container, false);

        init(view);
        viewAbsensiKursus();

        return view;
    }

    private void viewAbsensiKursus() {
        kursus_list = new ArrayList<>();

        kursus_list.add("Pengenalan Pemrograman Private");
        kursus_list.add("Pengenalan Pemrograman Public");
        kursus_list.add("Pemrograman Dekstop");
        kursus_list.add("Pemrograman Mobile");
        kursus_list.add("Pemrograman Website");

        rv_absensi_kursus.setAdapter(new AbsensiKursusAdapter(kursus_list, getActivity()));
    }

    public void init(View view){
        rv_absensi_kursus = view.findViewById(R.id.rv_absensi_kursus);
        rv_absensi_kursus.setHasFixedSize(true);
        LinearLayoutManager MyLinearLayoutManager = new LinearLayoutManager(getActivity());
        rv_absensi_kursus.setLayoutManager(MyLinearLayoutManager);
    }
}
