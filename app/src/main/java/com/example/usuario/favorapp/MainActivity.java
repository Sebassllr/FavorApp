package com.example.usuario.favorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnA);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int vista = view.getId();
        switch (vista) {
            case R.id.btnA: {
                Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
                this.startActivity(new Intent(this, NavigationActivity.class));
                break;
            }
        }
    }
}
