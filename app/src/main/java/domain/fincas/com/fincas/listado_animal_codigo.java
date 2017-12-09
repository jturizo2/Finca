package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class listado_animal_codigo extends AppCompatActivity {

    private EditText cod_animal,nombre, codmama, codpapa, peso, pesod,propietarioa;
    private TextView raza, genero, propietario,hierro,proposito,etapap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_animal_codigo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        cod_animal = (EditText) findViewById(R.id.editText6);
        nombre =(EditText) findViewById(R.id.nombre2);
        codmama =(EditText) findViewById(R.id.Cod_MAMA2);
        codpapa =(EditText) findViewById(R.id.cod_papa2);
        peso =(EditText) findViewById(R.id.Peso2);
        pesod =(EditText) findViewById(R.id.Peso_destete2);
        etapap =(TextView) findViewById(R.id.produ);
        genero =(TextView) findViewById(R.id.Genero2);
        raza =(TextView) findViewById(R.id.raza2);
        propietario =(TextView) findViewById(R.id.propietar);
        propietarioa =(EditText) findViewById(R.id.vende);
        hierro =(TextView) findViewById(R.id.hierr);
        proposito =(TextView) findViewById(R.id.propo);
       // finca =(TextView) findViewById(R.id.finc);
        //tipoa =(TextView) findViewById(R.id.tipo);
    }

    public void limpiar(){
        nombre.setText("");
        codmama.setText("");
        codpapa.setText("");
        peso.setText("");
        pesod.setText("");
        etapap.setText("");
        genero.setText("");
        raza.setText("");
        propietario.setText("");
        propietarioa.setText("");
        hierro.setText("");
        proposito.setText("");
    }

    @Override
    public void  onBackPressed(){
    }

    public void atras(View view) {
        Intent iw = new Intent(listado_animal_codigo.this, home_listado_animales.class);
        startActivity(iw);
        finish();
    }


    public void consultar(View view){
        if ("".equals(cod_animal.getText().toString())) {
            Toast.makeText(this, "Debe ingresar un codigo de animal!!", Toast.LENGTH_LONG).show();
        } else{
            //-----Se busca si el cod existe en los animales
            // Cod actual
            String use=cod_animal.getText().toString();

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
                UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db2 = admine2.getWritableDatabase();
                Cursor fila2 = db2.rawQuery("SELECT NOMBRE FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                Cursor fila3 = db2.rawQuery("SELECT CODMAMA FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                Cursor fila4 = db2.rawQuery("SELECT CODPAPA FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                Cursor fila5 = db2.rawQuery("SELECT PESO FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                Cursor fila6 = db2.rawQuery("SELECT PESODESTETE FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                Cursor fila7 = db2.rawQuery("SELECT GENERO FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                Cursor fila8 = db2.rawQuery("SELECT ETAPAP FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);

                Cursor fila9 = db2.rawQuery("SELECT RAZA FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                Cursor fila10 = db2.rawQuery("SELECT PROPIETARIO FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                Cursor fila11 = db2.rawQuery("SELECT PROPIETARIOA FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                Cursor fila12 = db2.rawQuery("SELECT HIERRO FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                Cursor fila13 = db2.rawQuery("SELECT PROPOSITO FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
             //   Cursor fila14 = db2.rawQuery("SELECT NFINCA FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
               // Cursor fila15 = db2.rawQuery("SELECT ETAPAP FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);

                String ssd1 ="";
                String ssd2 ="";
                String ssd3 ="";
                String ssd4 ="";
                String ssd5 ="";
                String ssd6 ="";
                String ssd7 ="";

                String ssd8 ="";
                String ssd9 ="";
                String ssd10 ="";
                String ssd11 ="";
                String ssd12 ="";
              //  String ssd13 ="";
               // String ssd14 ="";

                while (fila2.moveToNext()) {ssd1 += fila2.getString(0);}
                while (fila3.moveToNext()) {ssd2 += fila3.getString(0);}
                while (fila4.moveToNext()) {ssd3 += fila4.getString(0);}
                while (fila5.moveToNext()) {ssd4 += fila5.getString(0);}
                while (fila6.moveToNext()) {ssd5 += fila6.getString(0);}
                while (fila7.moveToNext()) {ssd6 += fila7.getString(0);}
                while (fila8.moveToNext()) {ssd7 += fila8.getString(0);}

                while (fila9.moveToNext()) {ssd8 += fila9.getString(0);}
                while (fila10.moveToNext()) {ssd9 += fila10.getString(0);}
                while (fila11.moveToNext()) {ssd10 += fila11.getString(0);}
                while (fila12.moveToNext()) {ssd11 += fila12.getString(0);}
                while (fila13.moveToNext()) {ssd12 += fila13.getString(0);}
              //  while (fila14.moveToNext()) {ssd13 += fila14.getString(0);}
               // while (fila15.moveToNext()) {ssd14 += fila15.getString(0);}


                db2.close();
                nombre.setText(ssd1);
                nombre.setMovementMethod(new ScrollingMovementMethod());

                codmama.setText(ssd2);
                codmama.setMovementMethod(new ScrollingMovementMethod());

                codpapa.setText(ssd3);
                codpapa.setMovementMethod(new ScrollingMovementMethod());

                peso.setText(ssd4);
                peso.setMovementMethod(new ScrollingMovementMethod());

                pesod.setText(ssd5);
                pesod.setMovementMethod(new ScrollingMovementMethod());

                genero.setText(ssd6);
                genero.setMovementMethod(new ScrollingMovementMethod());

                etapap.setText(ssd7);
                etapap.setMovementMethod(new ScrollingMovementMethod());

                raza.setText(ssd8);
                raza.setMovementMethod(new ScrollingMovementMethod());

                propietario.setText(ssd9);
                propietario.setMovementMethod(new ScrollingMovementMethod());

                propietarioa.setText(ssd10);
                propietarioa.setMovementMethod(new ScrollingMovementMethod());

                hierro.setText(ssd11);
                hierro.setMovementMethod(new ScrollingMovementMethod());

                proposito.setText(ssd12);
                proposito.setMovementMethod(new ScrollingMovementMethod());

                //finca.setText(ssd13);


              //  tipoa.setText(ssd14);

            }

        }
    } //fin metodo consultar

    public void eliminar(View view){

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Confirmar Acción");
        dialogo1.setMessage("¿Desea eliminar definitivamente este registro?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        dialogo1.show();

        //------------------------------------------------------------------


    }// fin metodo eliminar

    public void functiondelete(String gcod_a){
        UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db2 = admine2.getWritableDatabase();
        db2.execSQL("DELETE FROM `ANIMALESN` WHERE `CODIGO`='"+gcod_a+"'");
        // Toast.makeText(this, cod+" Entré", Toast.LENGTH_LONG).show();
        // System.out.println("Aqui");
        db2.close();

    }


    public void actualizar(View view){

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("Confirmar Acción");
        dialogo1.setMessage("¿Desea actualizar definitivamente este registro?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar1();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar1();
            }
        });
        dialogo1.show();

    } // fin actualizar

    public void aceptar1(){
        if ("".equals(cod_animal.getText().toString())) {
            Toast.makeText(this, "Digite el codigo a actualizar!!", Toast.LENGTH_LONG).show();
        }
        else{
            String gcodigo = cod_animal.getText().toString();
            String gnombre = nombre.getText().toString();
            String gproa = propietarioa.getText().toString();
            String gcodmama = codmama.getText().toString();
            String gcodpapa = codpapa.getText().toString();
            String gpeso = peso.getText().toString();
            String gpesod = pesod.getText().toString();


            UsersSQLiteHelper admine3 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db3 = admine3.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("NOMBRE", gnombre);
            registro.put("PROPIETARIOA", gproa);
            registro.put("CODMAMA", gcodmama);
            registro.put("CODPAPA", gcodpapa);
            registro.put("PESO", gpeso);
            registro.put("PESODESTETE", gpesod);
            db3.update("ANIMALESN", registro,"CODIGO="+gcodigo,null);
            db3.close();
            Toast.makeText(this, "Información actualizada!!!", Toast.LENGTH_LONG).show();

        }

    }

    public void cancelar1(){
        Toast.makeText(this, "Acción Cancelada", Toast.LENGTH_LONG).show();
    }

    public void aceptar(){

        if ("".equals(cod_animal.getText().toString())) {
            Toast.makeText(this, "Debe ingresar un codigo de animal!!", Toast.LENGTH_LONG).show();
        } else{

            String gcod_a = cod_animal.getText().toString();

            //-------------------------------------------

            //-----Se busca si el cod existe en los animales
            // Cod actual
            String use=cod_animal.getText().toString();
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

            if(flag.equals("nada")){
                //Base de datos
                Toast.makeText(this, "El código del animal no existe!!!", Toast.LENGTH_LONG).show();


            }else{
                functiondelete(gcod_a);
                limpiar();
                Toast.makeText(this, "Animal Eliminado", Toast.LENGTH_LONG).show();
            }



        } //fin else

    }


    public void cancelar(){

        Toast.makeText(this, "Acción Cancelada", Toast.LENGTH_LONG).show();
    }


}



