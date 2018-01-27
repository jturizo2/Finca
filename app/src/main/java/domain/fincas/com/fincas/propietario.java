package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class propietario extends AppCompatActivity {
    private EditText nombre1,apellido1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propietario);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        nombre1=(EditText)findViewById(R.id.nombre1);
        apellido1=(EditText)findViewById(R.id.apellido1);



    }
    @Override
    public void  onBackPressed(){

        String name2 = "";
        UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db2 = admine2.getWritableDatabase();
        Cursor fila1 = db2.rawQuery("SELECT NEW FROM NNEW LIMIT 1", null);
        while(fila1.moveToNext()){
            name2=fila1.getString(0);
        }
        db2.close();
        //   Fin de la Consulta*/

        if("".equals(name2)){

        }else{
            Intent i = new Intent(propietario.this, home_configuracion.class);
            startActivity(i);
            finish();

        }
        db2.close();

    }
    public void guardar(View view) {

        if ("".equals(nombre1.getText().toString())) {
            Toast.makeText(this, "Asigne un nombre!!", Toast.LENGTH_LONG).show();
        } else{
            if ("".equals(apellido1.getText().toString())) {
                Toast.makeText(this, "Asigne un Apellido!!", Toast.LENGTH_LONG).show();
            } else{
                        String name = nombre1.getText().toString();
                        String lname = apellido1.getText().toString();

                        //Base de datos
                        UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                        SQLiteDatabase db = admine.getWritableDatabase();

                        ContentValues registro = new ContentValues();
                        registro.put("NOMBRE", name);
                        registro.put("APELLIDO", lname);
                        db.insert("PROPIETARIOS", null, registro);
                        db.close();
                        Toast.makeText(this, "Propietario Guardado!", Toast.LENGTH_LONG).show();
                        Bundle bundle = getIntent().getExtras();
                        final String road = bundle.getString("road");
                if (("0").equals(road)) {
                    Intent i = new Intent(propietario.this, home_configuracion.class);
                    startActivity(i);
                    finish();
                }
                if (("1").equals(road)) {
                    Intent i = new Intent(propietario.this, hierro.class);
                    i.putExtra ("road", "1");
                    startActivity(i);
                    finish();
                }


                }
            }
    }
}
