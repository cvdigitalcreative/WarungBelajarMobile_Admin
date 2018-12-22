package com.test.warungbelajaradmin.View.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.warungbelajaradmin.Model.Kursus;
import com.test.warungbelajaradmin.Model.Nobel;
import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Adapter.AbsensiBatchAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbsensiBatch extends Fragment {
    private RecyclerView rv_absensi_batch;
    private ArrayList<String> batch_list;
    private String jenis_kursus, paket_kursus;

    public AbsensiBatch() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_absensi_batch, container, false);

        init(view);
        receivedData();
        viewAbsensiBatch();

        return view;
    }

    private void viewAbsensiBatch() {
        batch_list = new ArrayList<>();

        if(jenis_kursus.equals("Pemrograman Dekstop") || jenis_kursus.equals("Pemrograman Mobile") || jenis_kursus.equals("Pemrograman Website")){
            batch_list.add("1");
            batch_list.add("2");
            batch_list.add("3");
            batch_list.add("4");
        }
        else{
            batch_list.add("1");
            batch_list.add("2");
            batch_list.add("3");
            batch_list.add("4");
            batch_list.add("5");
            batch_list.add("6");
            batch_list.add("7");
            batch_list.add("8");
            batch_list.add("9");
            batch_list.add("10");
            batch_list.add("11");
            batch_list.add("12");
        }

        Kursus kursus = new Kursus();
        kursus.setPaket(paket_kursus);
        kursus.setJenis_kursus(jenis_kursus);

        rv_absensi_batch.setAdapter(new AbsensiBatchAdapter(batch_list, getActivity(), kursus));
    }

    private void receivedData() {
        jenis_kursus = getArguments().getString("jenis_kursus");
        paket_kursus = getArguments().getString("paket_kursus");
    }

    public void init(View view){
        rv_absensi_batch = view.findViewById(R.id.rv_absensi_batch);
        rv_absensi_batch.setHasFixedSize(true);
        LinearLayoutManager MyLinearLayoutManager = new LinearLayoutManager(getActivity());
        rv_absensi_batch.setLayoutManager(MyLinearLayoutManager);
    }

}
