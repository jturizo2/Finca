package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class hierro extends AppCompatActivity {
    private EditText hie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hierro);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        hie = (EditText) findViewById(R.id.hierro);
    }

    @Override
    public void  onBackPressed(){
        //----
    }



    public void atras(View view) {
        Intent iw = new Intent(hierro.this, home_configuracion.class);
        startActivity(iw);
        finish();
    }
    public void guardar_hierro(View view) {
        if ("".equals(hie.getText().toString())) {
            Toast.makeText(this, "Digite las letras del Hierro!!", Toast.LENGTH_LONG).show();
        }   else{
                String hierro = hie.getText().toString();

                //Base de datos
                UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db = admine.getWritableDatabase();

                ContentValues registro = new ContentValues();
                registro.put("HIERRO", hierro);
                db.insert("THIERRO", null, registro);
                db.close();
                Toast.makeText(this, "IHierro Ingresado Correctamente!", Toast.LENGTH_LONG).show();


            Bundle bundle = getIntent().getExtras();
            final String road = bundle.getString("road");
            if(road.equals("1")){
                Intent i = new Intent(hierro.this, ingreso_fincas.class);
                i.putExtra ("road", "1");
                startActivity(i);
                finish();
            }
            if(road.equals("0")){
                Intent i = new Intent(hierro.this, home_configuracion.class);
                startActivity(i);
                finish();

            }
    }
    }

}
