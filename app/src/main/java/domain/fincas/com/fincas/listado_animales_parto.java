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

public class listado_animales_parto extends AppCompatActivity {

    private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_animales_parto);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        text1 = (TextView) findViewById(R.id.textView21);

        String[] args = new String[] {""};
        String whereClause = "ID=?";
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this,"FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();
        Cursor fila = db5.rawQuery("SELECT CODIGO, NOMBRE, GENERO, RAZA, PROPIETARIO, HIERRO, PROPOSITO, CODMAMA, CODPAPA, CODPARTO, PESO, PESODESTETE, ETAPAP, FECHANACI FROM ANIMALESN WHERE COMPPAR='1'", null);
        //Cursor fila = db5.query("ANIMALESN", campos, null, null, null, null, null);
        String ssd ="";
        while (fila.moveToNext()) {
            ssd +=    "Codigo: "+fila.getString(0)+ "\n"
                    + "Nombre: " + fila.getString(1)+ "\n"
                    + "Genero: " + fila.getString(2)+ "\n"
                    + "Raza: " + fila.getString(3)+ "\n"
                    + "Propietario: " + fila.getString(4)+ "\n"
                    + "Hierro: " + fila.getString(5)+ "\n"
                    + "Proposito: " + fila.getString(6)+ "\n"
                    + "Codigo Madre: " + fila.getString(7)+ "\n"
                    + "Codigo Padre: " + fila.getString(8)+ "\n"
                    + "Codigo Parto: " + fila.getString(9)+ "\n"
                    + "Peso(kg): " + fila.getString(10)+ "\n"
                    + "Peso Destete(kg): " + fila.getString(11)+ "\n"
                    + "Etapa de Producción: " + fila.getString(12)+ "\n"
                    + "Fecha de Nacimiento: " + fila.getString(13)+ "\n \n";

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
        Intent iw = new Intent(listado_animales_parto.this, home_listado_animales.class);
        startActivity(iw);
        finish();
    }
}
