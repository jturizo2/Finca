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
        Cursor fila = db5.rawQuery("SELECT CODIGO, NOMBRE, GENERO, RAZA,PROPIETARIO, PROPIETARIOA, HIERRO, PROPOSITO,  PESO, PESODESTETE, ETAPAP, VALORC, FECHANACI, FECHAINGRE  FROM ANIMALESN WHERE COMPPAR='0'", null);
        //Cursor fila = db5.query("ANIMALESN", campos, null, null, null, null, null);
        String ssd ="";
        numero N = new numero();

        while (fila.moveToNext()) {
            ssd +=    "Codigo Animal: "+fila.getString(0)+ "\n"
                    + "Nombre Animal: " + fila.getString(1)+ "\n"
                    + "Genero: " + fila.getString(2)+ "\n"
                    + "Raza: " + fila.getString(3)+ "\n"
                    + "Nuevo Propietario: " + fila.getString(4)+ "\n"
                    + "Vendedor: " + fila.getString(5)+ "\n"
                    + "Hierro: " + fila.getString(6)+ "\n"
                    + "Proposito: " + fila.getString(7)+ "\n"
                    + "Peso al Comprar(kg): " + fila.getString(8)+ "\n"
                    + "Peso Actual (kg): " + fila.getString(9)+ "\n"
                    + "Etapa de Producción: " + fila.getString(10)+ "\n"
                    + "Valor de Compra ($): " + N.douTopes(fila.getString(11))+ "\n"
                    + "Fecha de Nacimiento: " + fila.getString(12)+ "\n"
                    + "Fecha de Compra: " + fila.getString(13)+ "\n \n";

        }
        db5.close();
        text1.setText(ssd);
        text1.setMovementMethod(new ScrollingMovementMethod());

      //   Fin de la Consulta */
    }


    @Override
    public void  onBackPressed(){
        Intent iw = new Intent(listado_animales.this, home_listado_animales.class);
        startActivity(iw);
    }



}
