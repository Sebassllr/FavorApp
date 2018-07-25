package com.example.usuario.favorapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * Botón de registerar
     */
    private Button btnRegister;

    /**
     * Botón de log-in
     */
    private Button btnAdd;

    private Button btnAddRegister;

    private LinearLayout llShowRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
    }

    /**
     * Inicializa los componentes de la vista
     */
    private void initializer(){
        btnAdd = findViewById(R.id.btnA);
        btnAdd.setOnClickListener(this);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnAddRegister = findViewById(R.id.btnRegistrar);
        llShowRegister = findViewById(R.id.llConfirmPassword);
    }

    @Override
    public void onClick(View view) {
        int vista = view.getId();
        switch (vista) {
            case R.id.btnA: {
                this.startActivity(new Intent(this, NavigationActivity.class));
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
        }else{
            btnAdd.setEnabled(Boolean.TRUE);
            btnRegister.setBackgroundColor(Color.parseColor("#C1C1C1"));
            llShowRegister.setVisibility(View.GONE);
            btnAddRegister.setVisibility(View.GONE);
        }
    }
}
