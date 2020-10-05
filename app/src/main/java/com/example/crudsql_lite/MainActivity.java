package com.example.crudsql_lite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.crudsql_lite.objetos.consulta_recyclerView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import objetos2.ConexionSQLite;
import objetos2.Dto;
import objetos2.Modal;

public class MainActivity extends AppCompatActivity {

    private EditText etcod,etdesc,etpz;
    private Button bbuscarc,bbuscard,bbguardar,bmodificar,borrar;
    private TextView result;

    boolean inputET = false;
    boolean inputED = false;
    boolean input1 = false;
    int resultInsert = 0;

    // "Despues se activa"
   Modal ventanas = new Modal();
    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto dato = new Dto();
    AlertDialog.Builder alert;

    public boolean onKeyDown(int KeyCode, KeyEvent event){
        if (KeyCode == KeyEvent.KEYCODE_BACK){
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_close)
                    .setTitle("Advertencia")
                    .setMessage("¿Quieres salir?")
                    .setNegativeButton(android.R.string.cancel,null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finishAffinity();
                        }
                    })
                    .show();

            return true;
        }

        return  super.onKeyDown(KeyCode,event);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBLANCO));
        toolbar.setTitleMargin(0,0,0,0);
        toolbar.setSubtitle("CRUD SQLite");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.colorBLANCO));
        toolbar.setTitle("Douglas Ayala SIS21A");
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comfirmacion();
            }
        });


        etcod = (EditText) findViewById(R.id.et_codigo);
        etdesc = (EditText) findViewById(R.id.et_descripcion);
        etpz = (EditText) findViewById(R.id.et_precio);
        bbuscarc = (Button) findViewById(R.id.btn_guardar);
        bbuscard = (Button) findViewById(R.id.btn_consultar1);
        bbguardar = (Button) findViewById(R.id.btn_consultar2);
        bmodificar = (Button) findViewById(R.id.btn_actualizar);
        borrar = (Button) findViewById(R.id.btn_eliminar);
        final FloatingActionsMenu fab = findViewById(R.id.fab);
        final FloatingActionButton fab1 = findViewById(R.id.item1);
        final FloatingActionButton fab2 = findViewById(R.id.item2);
        final FloatingActionButton fab3 = findViewById(R.id.item3);

        String senal = "";
        String codigo = "";
        String desc = "";
        String prezio = "";

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comfirmacion();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Acerca.class);
                startActivity(i);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventanas.Search(MainActivity.this);
            }
        });

        try {

            Bundle bun = getIntent().getExtras();
            if (bun != null){

                String a = (String) bun.get("codigo");
                String b = (String) bun.get("descr");
                String c = (String) bun.get("codigo");
                senal = bun.getString("pe");
                desc = bun.getString("de");
                prezio = bun.getString("pe");
                etcod.setText(a);
                etdesc.setText(b);
                etpz.setText(c);
            }

            }catch (Exception o){

        }
    }
    private void comfirmacion(){
        String msm = "¿Quieres salir?";
        alert = new AlertDialog.Builder(MainActivity.this);
        alert.setIcon(R.drawable.ic_close);
        alert.setTitle("Advertencia");
        alert.setMessage(msm);
        alert.setCancelable(false);
        alert.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
            }
        });

        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.show();
    }
    public void limpiardat(){

        etcod.setText(null);
        etdesc.setText(null);
        etpz.setText(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, consulta_recyclerView.class);
            startActivity(i);
            return true;
        }else if (id == R.id.lists1){
            Intent spinnerAct2 = new Intent(MainActivity.this,ListViewArticulos.class);
            startActivity(spinnerAct2);
            return true;
        }else if (id == R.id.lists0){
            Intent spinnerAct = new Intent(MainActivity.this,consulta_spinner.class);
            startActivity(spinnerAct);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void guardar (View view){
        if (etcod.getText().toString().length()== 0){
            etcod.setError("Campo obligatorio");
            inputET = false;
        }else {
            inputET = true;
        }

        if (etdesc.getText().toString().length()==0){
            etdesc.setError("Campo obligatorio");
            inputED = false;
        }else {
            inputED = true;
        }

        if (etpz.getText().toString().length()== 0){
            etpz.setError("Campo obligatorio");
            input1 = false;
        }else{
            input1 = true;
        }
        if (inputET && inputED && input1){
            try {
                dato.setCodigo(Integer.parseInt(etcod.getText().toString()));
                dato.setDescripcion(etdesc.getText().toString());
                dato.setPrecio(Double.parseDouble(etpz.getText().toString()));

                if (conexion.InsertTradicional(dato)){
                    Toast.makeText(this,"Registro Agregado!",Toast.LENGTH_SHORT).show();
                    limpiardat();
                }else {
                    Toast.makeText(this,"Ya existe el registro"+etcod.getText().toString(),Toast.LENGTH_LONG).show();
                }
            }catch (Exception o){

                Toast.makeText(this,"Hubo un Error el algo",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public  void mensaje(String msm){
        Toast.makeText(this," "+msm,Toast.LENGTH_SHORT).show();
    }

    public  void consultcod (View view){
        if (etcod.getText().toString().length()== 0){
            etcod.setError("Campo obligatorio");
            inputET = false;
        }else {
            inputET = true;
        }

        if (inputET){
            String codigo = etcod.getText().toString();
            dato.setCodigo(Integer.parseInt(codigo));

            if (conexion.consultArt(dato)){
                etdesc.setText(dato.getDescripcion());
                etpz.setText(""+dato.getPrecio());
            }else{

                Toast.makeText(this,"No existe el articulo ese",Toast.LENGTH_SHORT).show();
                limpiardat();
            }
        }else{
            Toast.makeText(this,"Ingrese el articulo por favor",Toast.LENGTH_SHORT).show();

        }

    }

    public  void consultadescripcion (View view){
        if (etdesc.getText().toString().length()== 0){
            etdesc.setError("Campo obligatorio");
            inputED = false;
        }else {
            inputED = true;
        }

        if (inputED){
            String desc = etdesc.getText().toString();
            dato.setDescripcion(desc);
            if (conexion.cosultDesc(dato)){
                etcod.setText(""+dato.getCodigo());
                etdesc.setText(dato.getDescripcion());
                etpz.setText(""+dato.getPrecio());

            }else {
                Toast.makeText(this,"No existe tal articulo",Toast.LENGTH_SHORT).show();
                limpiardat();

            }
        }else {
            Toast.makeText(this,"Ingrese el articulo por descripcion por favor",Toast.LENGTH_SHORT).show();
        }


    }

    public  void borrarcodigo(View view){
        if (etcod.getText().toString().length()== 0){
            etcod.setError("Campo obligatorio");
            inputET = false;
        }else {
            inputET = true;
        }

        if (inputET){
            String codmw = etcod.getText().toString();
            dato.setCodigo(Integer.parseInt(codmw));
            if (conexion.delCod(MainActivity.this,dato)){
                limpiardat();

            }else {
                Toast.makeText(this,"Ingrese el articulo ",Toast.LENGTH_SHORT).show();
                limpiardat();
            }
        }

    }


    public void modificar (View view){
        if (etcod.getText().toString().length()== 0){
            etcod.setError("Campo obligatorio");
            inputET = false;
        }else {
            inputET = true;
        }

        if (inputET){
            String cod = etcod.getText().toString();
            String desc = etdesc.getText().toString();
            double prezio = Double.parseDouble(etpz.getText().toString());

            dato.setCodigo(Integer.parseInt(cod));
            dato.setDescripcion(desc);
            dato.setPrecio(prezio);

            if (conexion.mod(dato)){
                Toast.makeText(this,"Editado",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"No se encotro el art a modificar el articulo ",Toast.LENGTH_SHORT).show();

            }
        }
    }
}