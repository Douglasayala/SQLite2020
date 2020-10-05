package com.example.crudsql_lite;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import objetos2.ConexionSQLite;
import objetos2.Dto;

public class consulta_spinner extends AppCompatActivity {
    private Spinner spoptions;
    private TextView tcod,tdec,tpc;

    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto dato = new Dto();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_spinner);

        spoptions = (Spinner)findViewById(R.id.sp_options);
        tcod = (TextView) findViewById(R.id.tv_cod);
        tdec = (TextView) findViewById(R.id.tv_descripcion);
        tpc = (TextView) findViewById(R.id.tv_precio);

        conexion.consultarListaArticulos();

        ArrayAdapter<CharSequence> adap = new ArrayAdapter(this,android.R.layout.simple_spinner_item,conexion.obtenerListaArticulos());
        spoptions.setAdapter(adap);

        spoptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    tcod.setText("Codigo: "+conexion.consultarListaArticulos().get(i-1).getCodigo());
                    tdec.setText("Descripcion: "+conexion.consultarListaArticulos().get(i-1).getDescripcion());
                    tpc.setText("Precio: "+conexion.consultarListaArticulos().get(i-1).getPrecio());

                }else
                {
                    tcod.setText("Codigo: ****");
                    tdec.setText("Descripción: ****");
                    tpc.setText("Precio: ****");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}