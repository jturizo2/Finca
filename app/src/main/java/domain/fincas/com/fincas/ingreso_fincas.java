package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ingreso_fincas extends AppCompatActivity {
    private EditText codf,nomf,hectar,divisi,lotes;
    private String flag="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_fincas);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

       codf =(EditText)findViewById(R.id.editText);
       nomf =(EditText)findViewById(R.id.editText2);
       hectar =(EditText)findViewById(R.id.editText3);
       divisi =(EditText)findViewById(R.id.editText5);
       lotes =(EditText)findViewById(R.id.editText4);



        //Verificar si existe un registro de finca
        UsersSQLiteHelper admine = new UsersSQLiteHelper(this,"FINCAS", null, 1);
        SQLiteDatabase db = admine.getWritableDatabase();
        //Lectura del ultimo valor
        String ss ="";
        Cursor fila = db.rawQuery("SELECT ID FROM NFINCAS ORDER BY ID DESC LIMIT 1", null);
        while(fila.moveToNext()){ss+=fila.getString(0);}
        if(ss.equals("")){
            flag="NO";
        }else{
            flag="SI";
            // Consulta de finca existente
            UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this,"FINCAS", null, 1);
            SQLiteDatabase db2 = admine2.getWritableDatabase();
            //Lectura del ultimo valor
            String cod ="",nom ="",hec ="",div ="",lotes1 ="";
            Cursor fila2 = db2.rawQuery("SELECT CODIGO,NOMBREF,HECTAREAS,DIVICIONES,LOTES FROM NFINCAS ORDER BY ID DESC LIMIT 1", null);
            while (fila2.moveToNext()) {
                cod= fila2.getString(0);
                nom= fila2.getString(1);
                hec= fila2.getString(2);
                div= fila2.getString(3);
                lotes1= fila2.getString(4);
            }
            codf.setText(cod);
            nomf.setText(nom);
            hectar.setText(hec);
            divisi.setText(div);
            lotes.setText(lotes1);
            db2.close();
        }
    }

    @Override
    public void  onBackPressed(){
    }

    public void devolver_ingrefo(View view) {
        Intent i = new Intent(ingreso_fincas.this, home_configuracion.class);
        startActivity(i);
        finish();
    }


    public void guardar_finca(View view) {
        if ("".equals(codf.getText().toString())) {
            Toast.makeText(this, "Digite un codigo para la finca!!", Toast.LENGTH_LONG).show();
        }
            else{
                if ("".equals(nomf.getText().toString())) {
                    Toast.makeText(this, "Digite un nombre para la finca!!", Toast.LENGTH_LONG).show();
                }
                    else{
                    String codigof = codf.getText().toString();
                    String nombref = nomf.getText().toString();
                    String hectareas = hectar.getText().toString();
                    String divisiones = divisi.getText().toString();
                    String lote = lotes.getText().toString();

                    if(flag.equals("SI")) {
                        //Consulta de ultimo ID

                        UsersSQLiteHelper admine3 = new UsersSQLiteHelper(this,"FINCAS", null, 1);
                        SQLiteDatabase db3 = admine3.getWritableDatabase();
                        //Lectura del ultimo valor
                        String ss ="";
                        Cursor fila = db3.rawQuery("SELECT ID FROM NFINCAS ORDER BY ID DESC LIMIT 1", null);
                        while(fila.moveToNext()){ss+=fila.getString(0);}
                        int id = Integer.parseInt(ss);

                        //-------------------------------------------------


                        //Base de datos
                        UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                        SQLiteDatabase db = admine.getWritableDatabase();

                        ContentValues registro = new ContentValues();
                        registro.put("CODIGO", codigof);
                        registro.put("NOMBREF", nombref);
                        registro.put("HECTAREAS", hectareas);
                        registro.put("DIVICIONES", divisiones);
                        registro.put("LOTES", lote);
                        db.update("NFINCAS", registro,"ID="+id,null);
                        db.close();
                        Toast.makeText(this, "Información actualizada!!!", Toast.LENGTH_LONG).show();


                    }else
                    {
                        //Base de datos
                        UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                        SQLiteDatabase db = admine.getWritableDatabase();

                        ContentValues registro = new ContentValues();
                        registro.put("CODIGO", codigof);
                        registro.put("NOMBREF", nombref);
                        registro.put("HECTAREAS", hectareas);
                        registro.put("DIVICIONES", divisiones);
                        registro.put("LOTES", lote);
                        db.insert("NFINCAS",null, registro);
                        db.close();


                        Toast.makeText(this, "Información guardada!!!", Toast.LENGTH_LONG).show();

                    }

                    Intent i = new Intent(ingreso_fincas.this, home_configuracion.class);
                    startActivity(i);
                    finish();

                    }

            }

    } //fin guardar finca



} //fin de la clase
