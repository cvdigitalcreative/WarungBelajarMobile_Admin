package com.test.warungbelajaradmin.View.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.warungbelajaradmin.Model.Jadwal;
import com.test.warungbelajaradmin.Model.JadwalKursusMentor;
import com.test.warungbelajaradmin.Model.Kursus;
import com.test.warungbelajaradmin.Model.Mentor;
import com.test.warungbelajaradmin.Model.Nobel;
import com.test.warungbelajaradmin.Model.ProfileMentor;
import com.test.warungbelajaradmin.R;
import com.test.warungbelajaradmin.View.Adapter.BuatGrupJadwalMentorAdapter;
import com.test.warungbelajaradmin.View.Adapter.BuatGrupJadwalNobelAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuatGrupJadwal extends Fragment {
    private ArrayList<Nobel> nobel_list;
    private ArrayList<Mentor> mentor_list;
    private LinearLayout ll_nobel, ll_mentor;
    private RecyclerView rv_nobel, rv_mentor;
    private Button btn_cari, btn_simpan;
    private Spinner s_hari1, s_hari2;
    private RadioGroup rg_pilihan1, rg_pilihan2;
    private RadioButton rb_pil1_sesi1, rb_pil1_sesi2, rb_pil1_sesi3, rb_pil1_sesi4, rb_pil2_sesi1, rb_pil2_sesi2, rb_pil2_sesi3, rb_pil2_sesi4;
    private RadioButton rb_pilihan1, rb_pilihan2;
    private String uid_nobel, batch_nobel, uid_mentor, nama_mentor, paket_kursus, jenis_kursus, pilihan, jam1, jam2, hari1, hari2;
    private ArrayList<String> hari_list_private, hari_list_grup_pp, hari_list_grup, hari_pertama_list, hari_kedua_list;
    private Jadwal jadwal_nobel;
    private DatabaseReference ref;

    public BuatGrupJadwal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buat_grup_jadwal, container, false);

        init(view);
        receivedData();
        init_sesi_jadwal();
        do_cari(view);
        do_simpan();

        return view;
    }

    private void do_simpan() {
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uid_nobel.isEmpty()){
                    Toast.makeText(getActivity(), "Harap pilih nobel terlebih dahulu!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(uid_mentor.isEmpty()){
                    Toast.makeText(getActivity(), "Harap pilih mentor terlebih dahulu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String key = ref.child("mentor").child(uid_mentor).push().getKey();
                String sesi_pertama, sesi_kedua;

                if(jam1.equals("10.00-12.00")){
                    sesi_pertama = "sesi1";
                }
                else if(jam1.equals("13.00-15.00")){
                    sesi_pertama = "sesi2";
                }
                else if(jam1.equals("16.00-18.00")){
                    sesi_pertama = "sesi3";
                }
                else{
                    sesi_pertama = "sesi4";
                }

                if(jam2.equals("10.00-12.00")){
                    sesi_kedua = "sesi1";
                }
                else if(jam2.equals("13.00-15.00")){
                    sesi_kedua = "sesi2";
                }
                else if(jam2.equals("16.00-18.00")){
                    sesi_kedua = "sesi3";
                }
                else{
                    sesi_kedua = "sesi4";
                }

                Jadwal jadwal = new Jadwal();
                jadwal.setHari1(jadwal_nobel.getHari1());
                jadwal.setHari2(jadwal_nobel.getHari2());
                jadwal.setJam_pertama(jadwal_nobel.getJam_pertama());
                jadwal.setJam_kedua(jadwal_nobel.getJam_kedua());
                jadwal.setSesi_pertama(sesi_pertama);
                jadwal.setSesi_kedua(sesi_kedua);

                JadwalKursusMentor jadwalKursusMentor = new JadwalKursusMentor();
                jadwalKursusMentor.setBatch(batch_nobel);
                jadwalKursusMentor.setPaket(paket_kursus);
                jadwalKursusMentor.setJenis_kursus(jenis_kursus);

                ref.child("nobel").child(uid_nobel).child("kursus").child(jenis_kursus).child("informasi_dasar").child("mentor").setValue(nama_mentor);
                ref.child("nobel").child(uid_nobel).child("kursus").child(jenis_kursus).child("informasi_dasar").child("status").setValue("Sedang Berlangsung");

                ref.child("mentor").child(uid_mentor).child("jadwal_kursus_mentor").child(key).setValue(jadwalKursusMentor);
                ref.child("mentor").child(uid_mentor).child("jadwal_kursus_mentor").child(key).child("jadwal").setValue(jadwal);

                ll_nobel.setVisibility(View.GONE);
                ll_mentor.setVisibility(View.GONE);
                btn_simpan.setVisibility(View.GONE);

                Toast.makeText(getActivity(), "Pengaturan Jadwal Mentor Berhasil!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void do_cari(final View view) {
        btn_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_mentor.setVisibility(View.VISIBLE);
                ll_nobel.setVisibility(View.VISIBLE);
                btn_simpan.setVisibility(View.VISIBLE);

                int selectedId1 = rg_pilihan1.getCheckedRadioButtonId();
                int selectedId2 = rg_pilihan2.getCheckedRadioButtonId();

                System.out.println("id 1 :  "+selectedId1);
                System.out.println("id 2 :  "+selectedId2);

                rb_pilihan1 = view.findViewById(selectedId1);
                rb_pilihan2 = view.findViewById(selectedId2);

                jam1 = rb_pilihan1.getText().toString();
                jam2 = rb_pilihan2.getText().toString();

                hari1 = s_hari1.getSelectedItem().toString().toLowerCase();
                hari2 = s_hari2.getSelectedItem().toString().toLowerCase();

                nobel_list = new ArrayList<>();
                mentor_list = new ArrayList<>();

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int count;
                        for(DataSnapshot mentorSnapshot : dataSnapshot.child("mentor").getChildren()){
                            String uid = mentorSnapshot.getKey();
                            String nama = mentorSnapshot.child("profile_mentor").child("nama").getValue().toString();

                            count=0;
                            for(DataSnapshot hariSnapshot : mentorSnapshot.child("jadwal_mentor").getChildren()){
                                String hari = hariSnapshot.getKey();

                                if(hari.equals(hari1)){
                                    for(DataSnapshot sesiSnapshot : hariSnapshot.getChildren()){
                                        String jam = sesiSnapshot.child("jam").getValue().toString();
                                        String status = sesiSnapshot.child("status").getValue().toString();

                                        if(jam.equals(jam1) && status.equals("Aktif")){
                                            count++;
                                        }
                                    }
                                }

                                if(hari.equals(hari2)){
                                    for(DataSnapshot sesiSnapshot : hariSnapshot.getChildren()){
                                        String jam = sesiSnapshot.child("jam").getValue().toString();
                                        String status = sesiSnapshot.child("status").getValue().toString();

                                        if(jam.equals(jam2) && status.equals("Aktif")){
                                            count++;
                                        }
                                    }
                                }
                            }

                            if(count == 2){
                                ProfileMentor profileMentor = new ProfileMentor();
                                profileMentor.setNama(nama);

                                Mentor mentor = new Mentor();
                                mentor.setUid(uid);
                                mentor.setProfile(profileMentor);

                                mentor_list.add(mentor);
                            }
                        }

                        for(DataSnapshot nobelSnapshot : dataSnapshot.child("nobel").getChildren()){
                            String uid = nobelSnapshot.getKey();
                            String nama = nobelSnapshot.child("profile_nobel").child("nama").getValue().toString();

                            String batch = nobelSnapshot.child("kursus").child(jenis_kursus).child("informasi_dasar").child("batch").getValue().toString();
                            String status = nobelSnapshot.child("kursus").child(jenis_kursus).child("informasi_dasar").child("status").getValue().toString();

                            String hari_pertama = nobelSnapshot.child("kursus").child(jenis_kursus).child("jadwal").child("hari1").getValue().toString();
                            String hari_kedua = nobelSnapshot.child("kursus").child(jenis_kursus).child("jadwal").child("hari2").getValue().toString();
                            String jam_pertama = nobelSnapshot.child("kursus").child(jenis_kursus).child("jadwal").child("jam_pertama").getValue().toString();
                            String jam_kedua = nobelSnapshot.child("kursus").child(jenis_kursus).child("jadwal").child("jam_kedua").getValue().toString();

                            count=0;

                            if(hari_pertama.equals(hari1) && jam_pertama.equals(jam1) && status.equals("Menunggu Penjadwalan")){
                                count += 1;
                            }

                            if(hari_kedua.equals(hari2) && jam_kedua.equals(jam2) && status.equals("Menunggu Penjadwalan")){
                                count += 1;
                            }

                            if(count == 2){
                                Jadwal jadwal = new Jadwal();
                                jadwal.setHari1(hari1);
                                jadwal.setHari2(hari2);
                                jadwal.setJam_pertama(jam1);
                                jadwal.setJam_pertama(jam2);

                                Kursus kursus = new Kursus();
                                kursus.setCatatan_jadwal(jadwal);
                                kursus.setBatch(Integer.parseInt(batch));

                                Nobel nobel = new Nobel();
                                nobel.setNama(nama);
                                nobel.setUID(uid);
                                nobel.setKursus(kursus);

                                nobel_list.add(nobel);
                            }
                        }

                        rv_mentor.setAdapter(new BuatGrupJadwalMentorAdapter(mentor_list, getActivity()));
                        rv_nobel.setAdapter(new BuatGrupJadwalNobelAdapter(nobel_list, getActivity()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void receivedData() {
        jenis_kursus = getArguments().getString("jenis_kursus");
        paket_kursus = getArguments().getString("paket_kursus");
        pilihan = getArguments().getString("pilihan");

        ref = FirebaseDatabase.getInstance().getReference();
    }

    public void init(View view){
        ll_nobel = view.findViewById(R.id.ll_nobel);
        ll_mentor = view.findViewById(R.id.ll_mentor);

        rg_pilihan1 = view.findViewById(R.id.pilihan1);
        rg_pilihan2 = view.findViewById(R.id.pilihan2);

        s_hari1 = view.findViewById(R.id.item_hari1);
        s_hari2 = view.findViewById(R.id.item_hari2);

        rb_pil1_sesi1 = view.findViewById(R.id.pilihan1_sesi1);
        rb_pil1_sesi2 = view.findViewById(R.id.pilihan1_sesi2);
        rb_pil1_sesi3 = view.findViewById(R.id.pilihan1_sesi3);
        rb_pil1_sesi4 = view.findViewById(R.id.pilihan1_sesi4);

        rb_pil2_sesi1 = view.findViewById(R.id.pilihan2_sesi1);
        rb_pil2_sesi2 = view.findViewById(R.id.pilihan2_sesi2);
        rb_pil2_sesi3 = view.findViewById(R.id.pilihan2_sesi3);
        rb_pil2_sesi4 = view.findViewById(R.id.pilihan2_sesi4);

        btn_cari = view.findViewById(R.id.cari_data);
        btn_simpan = view.findViewById(R.id.simpan_data);

        rv_nobel = view.findViewById(R.id.item_nobel_view);
        rv_mentor = view.findViewById(R.id.item_mentor_view);

        rv_nobel.setHasFixedSize(true);
        GridLayoutManager MyGridNobelLayoutManager = new GridLayoutManager(getActivity(), 3);
        rv_nobel.setLayoutManager(MyGridNobelLayoutManager);

        rv_mentor.setHasFixedSize(true);
        GridLayoutManager MyGridMentorLayoutManager = new GridLayoutManager(getActivity(), 3);
        rv_mentor.setLayoutManager(MyGridMentorLayoutManager);

        uid_mentor = "";
        uid_nobel = "";
    }

    public void setUIDNobel(String UID, int batch, Jadwal jadwal){
        uid_nobel = UID;
        batch_nobel = String.valueOf(batch);
        jadwal_nobel = jadwal;
    }

    public void setUIDMentor(String UID, String nama){
        uid_mentor = UID;
        nama_mentor = nama;
    }

    private void init_hari_list() {
        hari_list_private = new ArrayList<>();
        hari_list_grup_pp = new ArrayList<>();
        hari_list_grup = new ArrayList<>();

        hari_list_private.add("Selasa");
        hari_list_private.add("Rabu");
        hari_list_private.add("Kamis");
        hari_list_private.add("Jumat");

        hari_list_grup.add("Selasa");
        hari_list_grup.add("Rabu");
        hari_list_grup.add("Kamis");
        hari_list_grup.add("Jumat");

        hari_list_grup_pp.add("Sabtu");
        hari_list_grup_pp.add("Minggu");
    }

    private void init_spinner_hari(){
        hari_pertama_list = new ArrayList<>();
        hari_kedua_list = new ArrayList<>();

        if(paket_kursus.equals("grup")){
            if(pilihan.equals("0")){
                String[] entries1, entries2;

                hari_pertama_list.addAll(hari_list_grup_pp);
                entries1 = new String[hari_pertama_list.size()];
                entries2 = new String[hari_pertama_list.size()-1];

                String hari_terpilih = hari_pertama_list.get(0);

                int count = 0;
                for(int i=0; i<hari_pertama_list.size(); i++){
                    entries1[i] = hari_pertama_list.get(i);

                    if(!hari_terpilih.equals(hari_pertama_list.get(i))){
                        hari_kedua_list.add(hari_pertama_list.get(i));
                        entries2[count++] = hari_pertama_list.get(i);
                    }
                }

                ArrayAdapter<String> spinnerGrup1Adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, entries1);
                s_hari1.setAdapter(spinnerGrup1Adapter);

                ArrayAdapter<String> spinnerGrup2Adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, entries2);
                s_hari2.setAdapter(spinnerGrup2Adapter);
            }
            else{
                String[] entries1, entries2;

                hari_pertama_list.addAll(hari_list_grup);
                entries1 = new String[hari_pertama_list.size()];
                entries2 = new String[hari_pertama_list.size()-1];

                String hari_terpilih = hari_pertama_list.get(0);

                int count = 0;
                for(int i=0; i<hari_pertama_list.size(); i++){
                    entries1[i] = hari_pertama_list.get(i);

                    if(!hari_terpilih.equals(hari_pertama_list.get(i))){
                        hari_kedua_list.add(hari_pertama_list.get(i));
                        entries2[count++] = hari_pertama_list.get(i);
                    }
                }

                ArrayAdapter<String> spinnerGrup1Adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, entries1);
                s_hari1.setAdapter(spinnerGrup1Adapter);

                ArrayAdapter<String> spinnerGrup2Adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, entries2);
                s_hari2.setAdapter(spinnerGrup2Adapter);
            }
        }
        else{
            String[] entries1, entries2;

            hari_pertama_list.addAll(hari_list_private);
            entries1 = new String[hari_pertama_list.size()];
            entries2 = new String[hari_pertama_list.size()-1];

            String hari_terpilih = hari_pertama_list.get(0);

            int count = 0;
            for(int i=0; i<hari_pertama_list.size(); i++){
                entries1[i] = hari_pertama_list.get(i);

                if(!hari_terpilih.equals(hari_pertama_list.get(i))){
                    hari_kedua_list.add(hari_pertama_list.get(i));
                    entries2[count++] = hari_pertama_list.get(i);
                }
            }

            ArrayAdapter<String> spinnerGrup1Adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, entries1);
            s_hari1.setAdapter(spinnerGrup1Adapter);

            ArrayAdapter<String> spinnerGrup2Adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, entries2);
            s_hari2.setAdapter(spinnerGrup2Adapter);
        }
    }

    private void init_sesi_jadwal() {
        init_hari_list();
        init_spinner_hari();

        s_hari1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDataBaruSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s_hari2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getDataBaruSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getDataBaruSpinner(){
        String[] entries2;

        entries2 = new String[hari_pertama_list.size()-1];
        String hari_terpilih = s_hari1.getSelectedItem().toString();

        int count = 0;
        for(int i=0; i<hari_pertama_list.size(); i++){
            if(!hari_terpilih.equals(hari_pertama_list.get(i))){
                hari_kedua_list.add(hari_pertama_list.get(i));
                entries2[count++] = hari_pertama_list.get(i);
            }
        }

        ArrayAdapter<String> spinnerGrup2Adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, entries2);
        s_hari2.setAdapter(spinnerGrup2Adapter);
    }
}
