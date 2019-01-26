package com.test.warungbelajaradmin.View.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.warungbelajaradmin.R;

public class Login extends AppCompatActivity {
    private EditText et_email, et_password;
    private Button btn_login, btn_register;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        cekloginAdmin();
        login();
    }

    private void cekloginAdmin() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();

                if(user != null){
                    Intent intent = new Intent(Login.this, BaseActivity.class);
                    intent.putExtra("id_user", user.getUid());
                    startActivity(intent);
                }
            }
        };
    }

    public void login(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "email anda belum diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "password anda belum diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Sign in Berhasil", Toast.LENGTH_SHORT).show();
                                    Log.d("Notif", "signInWithEmail:success");

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent intent = new Intent(Login.this, BaseActivity.class);
                                    intent.putExtra("id_user", user.getUid());
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Sign in Gagal", Toast.LENGTH_SHORT).show();
                                    Log.d("Notif", "signInWithEmail:failed");
                                }
                            }
                        });
            }
        });
    }

    public void init(){
        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.kata_sandi);
        btn_login = findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
