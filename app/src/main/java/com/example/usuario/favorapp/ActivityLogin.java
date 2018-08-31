package com.example.usuario.favorapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.usuario.favorapp.Clases.Transacciones;
import com.example.usuario.favorapp.Util.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener{

    /**
     * Botón de registerar
     */
    private Button btnRegister, btnAdd, btnAddRegister;
    private EditText tvMail, tvPassword, tvCompleteName;
    private LinearLayout llShowRegister,llCompleteName;
    private ProgressDialog progressDialog;

    public static boolean calledAlready = false;
    private Transacciones tr = new Transacciones();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tr.inicializatedFireBase(this);
        initializer();
        verificaSignIn();
    }

    /**
     * Inicializa los componentes de la vista
     */
    private void initializer(){
        tvMail = findViewById(R.id.tvMail);
        tvPassword = findViewById(R.id.tvPassword);
        tvCompleteName = findViewById(R.id.tvCompleteName);
        btnAdd = findViewById(R.id.btnA);
        btnAdd.setOnClickListener(this);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnAddRegister = findViewById(R.id.btnRegistrar);
        btnAddRegister.setOnClickListener(this);
        llShowRegister = findViewById(R.id.llConfirmPassword);
        llCompleteName = findViewById(R.id.llCompleteName);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View view) {
        int vista = view.getId();
        switch (vista) {
            case R.id.btnA: {
                String mail = Util.getTxt(tvMail);
                String pass = Util.getTxt(tvPassword);
                if(!Util.emptyCampMSG(tvMail,"Correo vacío") && !Util.emptyCampMSG(tvPassword, "Contraseña vacía")){
                    loginUser(mail, pass);
                }
                break;
            }
            case R.id.btnRegister: {
                addRegister();
                break;
            }
            case R.id.btnRegistrar:{
                /**
                 * Acá va el evento de click para registrar y cambiar de activity
                 */
                registro();
            }
        }
    }

    /**
     * Evento del botón del registro
     */
    private void addRegister(){

        if(btnAdd.isEnabled()){
            btnAdd.setEnabled(Boolean.FALSE);
            btnRegister.setBackgroundColor(Color.parseColor("#E34941"));
            llShowRegister.setVisibility(View.VISIBLE);
            btnAddRegister.setVisibility(View.VISIBLE);
            llCompleteName.setVisibility(View.VISIBLE);

        }else{
            btnAdd.setEnabled(Boolean.TRUE);
            btnRegister.setBackgroundColor(Color.parseColor("#C1C1C1"));
            llShowRegister.setVisibility(View.GONE);
            btnAddRegister.setVisibility(View.GONE);
            llCompleteName.setVisibility(View.GONE);
        }
    }

    private void verificaSignIn(){
        if(tr.firebaseAuth.getCurrentUser() != null){
            tr.account(ActivityLogin.this);
            finish();
        }
    }

    /**andres
     *
     * @param mail
     * @param pass
     */
    private void loginUser(String mail, String pass){
        progressDialog.setMessage("Ingresando, por favor espera");
        progressDialog.show();

       tr.firebaseAuth.signInWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            tr.account(ActivityLogin.this);
                            finish();
                        }else{
                            Toast.makeText(ActivityLogin.this,"Datos errados",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void registro(){
        String name = Util.getTxt(tvCompleteName);
        String mail = Util.getTxt(tvMail);
        String pass = Util.getTxt(tvPassword);
        tr.registrarUser(mail,pass,this,name,100);
    }
}
