package com.test.warungbelajaradmin.View.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.warungbelajaradmin.R;

public class BaseFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPageNo;



    public BaseFragment() {
        // Required empty public constructor
    }

    public static BuatGrupKursus newInstance(int pageNo) {
        // Required empty public constructor
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        BuatGrupKursus fragment = new BuatGrupKursus();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);

        getFragmentManager().beginTransaction().add(R.id.container_fragment_kursus, new BuatGrupKursus(), "buat_grup_kursus").commit();
        return view;
    }


}
