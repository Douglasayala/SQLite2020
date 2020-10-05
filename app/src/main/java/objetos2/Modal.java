package objetos2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudsql_lite.MainActivity;
import com.example.crudsql_lite.R;

public class Modal {
    Dialog dialogo;
    AlertDialog.Builder diag;
    boolean validarInput = false;
    String codigo, descr, precio;
    SQLiteDatabase db = null;
    Dto datos = new Dto();

public void  Search(final Context context){
    dialogo = new Dialog(context);
    dialogo.setContentView(R.layout.ventana1);
    dialogo.setCancelable(false);
    final ConexionSQLite conexion = new ConexionSQLite(context);
    final EditText etcodfinal = (EditText)dialogo.findViewById(R.id.et_cod);

    Button btbusc = (Button)dialogo.findViewById(R.id.btn_buscar);
    TextView cancel = (TextView) dialogo.findViewById(R.id.tv_cIose);
    cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialogo.dismiss();
        }
    });
    btbusc.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (etcodfinal.getText().toString().length()== 0){
                validarInput = false;
                etcodfinal.setError("Campo obligatorio");
            }else {
                validarInput = true;
            }

            if (validarInput){
                String cod = etcodfinal.getText().toString();
                datos.setCodigo(Integer.parseInt(cod));
                if (conexion.cosultCod(datos) == true){
                    codigo = String.valueOf(datos.getCodigo());
                    descr = datos.getDescripcion();
                    precio = String.valueOf(datos.getPrecio());

                    Intent ee = new Intent(context, MainActivity.class);
                    ee.putExtra("senale","1");
                    ee.putExtra("codigo",codigo);
                    ee.putExtra("descr","descr");
                    ee.putExtra("pe",precio);
                    context.startActivity(ee);
                    dialogo.dismiss();
                }else {
                    Toast.makeText(context,"No se encuentran resultados",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(context,"No has escrito nada",Toast.LENGTH_SHORT).show();
            }
        }
    });
    dialogo.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialogo.show();
 }

}
