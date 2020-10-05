package com.example.crudsql_lite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import objetos2.ConexionSQLite;
import objetos2.Dto;


public class ListViewArticulos extends AppCompatActivity {

    ListView listViewpersonas;
    ArrayAdapter adaptadar;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;

    String[] version = {"Aestro","Blender","CupCake","Donut","Eclair","Froyo","GindgerBread","HoneyComb","IceCream Sandwich","Jelly Bean","Kitkat","Lollipop","Marshmallow","Nogaut","Oreo"};

    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_articulos);

        listViewpersonas = (ListView) findViewById(R.id.listViewPersonas);
        searchView = (SearchView) findViewById(R.id.searchView);

        adaptadar = new ArrayAdapter(this, android.R.layout.simple_list_item_1,conexion.consultarListaArticulos1());
        listViewpersonas.setAdapter(adaptadar);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String text = s;
                adaptadar.getFilter().filter(text);
                return false;
            }
        });

        listViewpersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String informacion = "Codigo: "+conexion.consultarListaArticulos().get(i).getDescripcion()+"\n";
                informacion+="Precio: "+conexion.consultarListaArticulos().get(i).getPrecio();

                Dto articulos = conexion.consultarListaArticulos().get(i);
                Intent intent = new Intent(ListViewArticulos.this,detalle_articulo.class);
                Bundle bun = new Bundle();
                bun.putSerializable("articulo",articulos);
                intent.putExtras(bun);
                startActivity(intent);

            }
        });
    }

    public  void  vol (View view){
        onBackPressed();
    }
}