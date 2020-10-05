package com.example.crudsql_lite.objetos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.crudsql_lite.R;
import objetos2.ConexionSQLite;
import objetos2.Dto;

import java.util.ArrayList;
import java.util.List;

public class consulta_recyclerView extends AppCompatActivity {

    private RecyclerView recycler;
    private AdaptadorArticulos adaptadorArticulos;
    ConexionSQLite datos = new ConexionSQLite(consulta_recyclerView.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_recycler_view);
        recycler = (RecyclerView)findViewById(R.id.rview);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adaptadorArticulos = new AdaptadorArticulos(consulta_recyclerView.this,
                datos.mostrarArticulos());
        recycler.setAdapter(adaptadorArticulos);
    }
    public List<Dto> obtenerArticulos() {
        List<Dto> articulos = new ArrayList<>();
        articulos.add(new Dto(1, "Laptop", 200.99));
        articulos.add(new Dto(2, "Impresora HP", 100.78));
        articulos.add(new Dto(3, "Disco Duro 1TB", 100.19));
        return articulos;
    }
}