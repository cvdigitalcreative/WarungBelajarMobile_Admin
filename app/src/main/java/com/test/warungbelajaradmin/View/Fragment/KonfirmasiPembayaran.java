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
import com.test.warungbelajaradmin.Model.Kursus;
import com.test.warungbelajaradmin.Model.Nobel;
import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Adapter.KonfirmasiPembayaranAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KonfirmasiPembayaran extends Fragment {
    private RecyclerView rv_konfirmasi_pembayaran;
    private DatabaseReference ref;
    private ArrayList<Nobel> nobel_list;

    public KonfirmasiPembayaran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_konfirmasi_pembayaran, container, false);

        init(view);
        viewDaftarKonfirmasi();

        return view;
    }

    public void viewDaftarKonfirmasi(){
        nobel_list = new ArrayList<>();

        ref.child("nobel").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nobel_list = new ArrayList<>();

                for(DataSnapshot nobelSnapshot : dataSnapshot.getChildren()){
                    for(DataSnapshot kursusSnapshot : nobelSnapshot.child("kursus").getChildren()){
                        String UID = nobelSnapshot.getKey();
                        String nama = nobelSnapshot.child("profile_nobel").child("nama").getValue().toString();

                        int harga = Integer.parseInt(kursusSnapshot.child("informasi_dasar").child("harga").getValue().toString());
                        String foto = kursusSnapshot.child("informasi_dasar").child("foto_bukti_pembayaran").getValue().toString();
                        String paket = kursusSnapshot.child("informasi_dasar").child("paket").getValue().toString();
                        String status  = kursusSnapshot.child("informasi_dasar").child("status").getValue().toString();
                        String jenis_kursus = kursusSnapshot.getKey();

                        if(status.equals("Menunggu Konfirmasi")){
                            Kursus kursus = new Kursus();
                            kursus.setHarga(harga);
                            kursus.setPaket(paket);
                            kursus.setJenis_kursus(jenis_kursus);
                            kursus.setStatus(status);
                            kursus.setFoto_bukti_pembayaran(foto);

                            Nobel nobel = new Nobel();
                            nobel.setUID(UID);
                            nobel.setNama(nama);
                            nobel.setKursus(kursus);

                            nobel_list.add(nobel);
                        }
                    }
                }

                rv_konfirmasi_pembayaran.setAdapter(new KonfirmasiPembayaranAdapter(nobel_list, getActivity()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void init(View view){
        rv_konfirmasi_pembayaran = view.findViewById(R.id.rv_konfirmasi_pembayaran);
        rv_konfirmasi_pembayaran.setHasFixedSize(true);
        LinearLayoutManager MyLinearLayoutManager = new LinearLayoutManager(getActivity());
        rv_konfirmasi_pembayaran.setLayoutManager(MyLinearLayoutManager);

        ref = FirebaseDatabase.getInstance().getReference();
    }

}
