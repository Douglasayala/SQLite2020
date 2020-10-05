package com.example.crudsql_lite;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Acerca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);
    }

    public void atras(View view) {
        Intent i = new Intent(Acerca.this, MainActivity.class);
        startActivity(i);
        finishAffinity();
    }
}