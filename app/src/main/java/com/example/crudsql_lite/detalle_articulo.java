package com.example.crudsql_lite;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import objetos2.Dto;

public class detalle_articulo extends AppCompatActivity {

    private TextView tdcod, tddesc, tdpz, tdcod1,tddesc1,tdpz1, tfcha ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_articulo);

        tdcod = (TextView) findViewById(R.id.tv_codigo);
        tddesc = (TextView) findViewById(R.id.tv_descripcion);
        tdpz = (TextView) findViewById(R.id.tv_precio);
        tdcod1 = (TextView) findViewById(R.id.tv_codigo1);
        tddesc1 = (TextView) findViewById(R.id.tv_descripcion1);
        tdpz1 = (TextView) findViewById(R.id.tv_precio1);
        tfcha = (TextView) findViewById(R.id.tv_fecha);

        Bundle objeto = getIntent().getExtras();
        Dto dto = null;
        if (objeto != null){
            dto = (Dto) objeto.getSerializable("articulo");
            tdcod.setText(""+dto.getCodigo());
            tddesc.setText(dto.getDescripcion());
            tdpz.setText(""+dto.getPrecio());

            tdcod1.setText(""+dto.getCodigo());
            tddesc1.setText(dto.getDescripcion());
            tdpz1.setText(String.valueOf(dto.getPrecio()));
            tfcha.setText("Fecha de creacion: "+getDateTime());




        }

    }

    private String getDateTime(){
        SimpleDateFormat dates = new SimpleDateFormat(
                "yyyy-MM-dd-HH:mm:ss a", Locale.getDefault());
        Date day = new Date();
        return dates.format(day);
    }

    public  void  vol (View view){
        onBackPressed();
    }
}