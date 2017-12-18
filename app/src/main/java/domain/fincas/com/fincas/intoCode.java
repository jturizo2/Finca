package domain.fincas.com.fincas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class intoCode extends AppCompatActivity {
    private EditText cod85;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_into_code);
        cod85 = (EditText) findViewById(R.id.cod85);

    }
    public void onBackPressed() {
        Intent iw = new Intent(intoCode.this, home_listado_animales.class);
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
                Cursor fila5 = db5.rawQuery("SELECT COMPPAR FROM ANIMALESN WHERE  CODIGO='"+COD+"'", null);
                String clas="";
                while (fila5.moveToNext()) {
                    clas= fila5.getString(0);
                }
                db.close();

                if (("0").equals(clas)){// Animal Comprado
                    Intent end7 = new Intent(intoCode.this, listado_animal_codigo.class);
                    end7.putExtra ("COD", COD);
                    startActivity(end7);
                }

                if (("1").equals(clas)){//Animal por parto
                    Intent end1 = new Intent(intoCode.this, listado_animal_codigo2.class);
                    end1.putExtra ("COD", COD);
                    startActivity(end1);
                }
                if (("2").equals(clas)){ //Animal existente
                    Intent end1 = new Intent(intoCode.this, listado_animal_codigo3.class);
                    end1.putExtra ("COD", COD);
                    startActivity(end1);
                }




            }
        }


        //---------------------------------------------------------------

    }

}
