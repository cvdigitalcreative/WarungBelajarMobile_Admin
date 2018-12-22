package com.test.warungbelajaradmin.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Adapter.BuatGrupKursusAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuatGrupKursus extends Fragment {
    private ArrayList<String> kursus_list;
    private RecyclerView rv_buat_grup_kursus;

    public BuatGrupKursus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buat_grup_kursus, container, false);

        init(view);
        viewBuatGrupKursus();

        return view;
    }

    private void viewBuatGrupKursus() {
        kursus_list = new ArrayList<>();

        kursus_list.add("Pengenalan Pemrograman Private");
        kursus_list.add("Pengenalan Pemrograman Public");
        kursus_list.add("Pemrograman Dekstop");
        kursus_list.add("Pemrograman Mobile");
        kursus_list.add("Pemrograman Website");

        rv_buat_grup_kursus.setAdapter(new BuatGrupKursusAdapter(kursus_list, getActivity()));
    }

    public void init(View view){
        rv_buat_grup_kursus = view.findViewById(R.id.rv_buat_grup_kursus);
        rv_buat_grup_kursus.setHasFixedSize(true);
        LinearLayoutManager MyLinearLayoutManager = new LinearLayoutManager(getActivity());
        rv_buat_grup_kursus.setLayoutManager(MyLinearLayoutManager);
    }

}
