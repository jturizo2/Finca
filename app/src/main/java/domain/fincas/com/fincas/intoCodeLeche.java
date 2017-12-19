package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class intoCodeLeche extends AppCompatActivity {
    private EditText cod85;
    private TextView read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into_code_leche);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        cod85 = (EditText) findViewById(R.id.cod85);
        read = (TextView) findViewById(R.id.read);


    }


    public void onBackPressed() {
        Intent iw = new Intent(intoCodeLeche.this, home_consultas.class);
        startActivity(iw);
        finish();
    }
    public void consul(View view) {

        if ("".equals(cod85.getText().toString())) {
            Toast.makeText(this, "Debe ingresar un codigo de animal!!", Toast.LENGTH_LONG).show();
        } else{

            //-----Se busca si el cod existe en los animales
            // Cod actual
            String use=cod85.getText().toString();

            // Lista de codigos existentes
            UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db = admine.getWritableDatabase();
            Cursor fila = db.rawQuery("SELECT CODIGO FROM ANIMALESN", null);
            String ssd ="";
            String flag="nada";
            while (fila.moveToNext()) {
                String prube= fila.getString(0);
                if(use.equals(prube)){
                    flag="algop";
                    break;
                }

            }
            db.close();
            // Toast.makeText(this, flag+"si", Toast.LENGTH_LONG).show();


            if(flag.equals("nada")){
                Toast.makeText(this, "El código no existe!!!", Toast.LENGTH_LONG).show();
                //limpiar();


            }else{
                //Detectamos la naturaleza del animal.  Cmapo COMPPAR
                String COD = cod85.getText().toString();

                UsersSQLiteHelper admine5 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db5 = admine5.getWritableDatabase();
                Cursor fila5 = db5.rawQuery("SELECT LITROS,FECHA FROM LECHE WHERE  COD='"+COD+"'", null);
                String ssd1="";
                ssd1 +=   "<-  Litros de leche --> "+ "\n";
                Double data = 0.0;
                while (fila5.moveToNext()) {
                    ssd1 +="Fecha: "+fila5.getString(1)+ "  " +  "--  Litros: "+fila5.getString(0)+ "\n";
                    data  += Double.parseDouble(fila5.getString(0));
                }
                ssd1 +="Total litros: "+data.toString()+ "\n";
                db5.close();
                read.setText(ssd1);
                read.setMovementMethod(new ScrollingMovementMethod());

            }
        }


        //---------------------------------------------------------------

    }

}
