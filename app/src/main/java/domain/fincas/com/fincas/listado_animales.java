package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class listado_animales extends AppCompatActivity {

    private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_animales);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        text1 = (TextView) findViewById(R.id.textView13);




        // Consultar Campos en la Base de Datos

       // String[] campos = new String[] {"CODIGO","NOMBRE", "GENERO","RAZA","PROPIETARIOA","HIERRO","PROPOSITO","NFINCA","PESO","PESODESTETE","VALORC","TIPOANIMAL","COMPPAR","FECHAINGRE"};
        String[] args = new String[] {""};
        String whereClause = "ID=?";
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this,"FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();
        Cursor fila = db5.rawQuery("SELECT CODIGO, NOMBRE, GENERO, RAZA, PROPIETARIOA, HIERRO, PROPOSITO,  PESO, PESODESTETE, ETAPAP, VALORC, FECHAINGRE  FROM ANIMALESN WHERE COMPPAR='0'", null);
        //Cursor fila = db5.query("ANIMALESN", campos, null, null, null, null, null);
        String ssd ="";
        while (fila.moveToNext()) {
            ssd +=    "Codigo: "+fila.getString(0)+ "\n"
                    + "Nombre: " + fila.getString(1)+ "\n"
                    + "Genero: " + fila.getString(2)+ "\n"
                    + "Raza: " + fila.getString(3)+ "\n"
                    + "Propietario Anterior: " + fila.getString(4)+ "\n"
                    + "Hierro: " + fila.getString(5)+ "\n"
                    + "Proposito: " + fila.getString(6)+ "\n"

                    + "Peso(kg): " + fila.getString(7)+ "\n"
                    + "Peso Destete (kg): " + fila.getString(8)+ "\n"
                    + "Etapa de Producción: " + fila.getString(9)+ "\n"
                    + "Valor de Compra ($): " + fila.getString(10)+ "\n"
                    + "Fecha de Ingreso: " + fila.getString(11)+ "\n \n";

        }
        db5.close();
        text1.setText(ssd);
        text1.setMovementMethod(new ScrollingMovementMethod());

      //   Fin de la Consulta */
    }

    @Override
    public void  onBackPressed(){
    }

    public void atras(View view) {
        Intent iw = new Intent(listado_animales.this, home_listado_animales.class);
        startActivity(iw);
    }



}
