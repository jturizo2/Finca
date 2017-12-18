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

public class listado_animal_codigo3 extends AppCompatActivity {
    private EditText cod_animal,nombre, peso, pesod,fecha;
    private TextView raza, genero,hierro,proposito,etapap,propietario;
    private String id1 ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_animal_codigo3);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        fecha = (EditText) findViewById(R.id.fecha2);
        cod_animal = (EditText) findViewById(R.id.editText6);
        nombre =(EditText) findViewById(R.id.nombre2);
        peso =(EditText) findViewById(R.id.Peso2);
        pesod =(EditText) findViewById(R.id.Peso_destete2);
        cod_animal = (EditText) findViewById(R.id.editText6);


        etapap =(TextView) findViewById(R.id.produ);
        genero =(TextView) findViewById(R.id.Genero2);
        raza =(TextView) findViewById(R.id.raza2);
        hierro =(TextView) findViewById(R.id.hierr);
        proposito =(TextView) findViewById(R.id.propo);
        propietario =(TextView) findViewById(R.id.propi2);

        // ----------Cargamos los datos de la busqueda--------------------
        //Variables de la actividad pasada
        Bundle bundle = getIntent().getExtras();
        final String use = bundle.getString("COD");

        UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db2 = admine2.getWritableDatabase();
        Cursor fila2 = db2.rawQuery("SELECT ID,NOMBRE,PESO,PESODESTETE,GENERO,ETAPAP,RAZA,PROPIETARIO," +
                "HIERRO,PROPOSITO,FECHAINGRE FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
        String nombre1 = "";
        String peso1 = "";
        String pesodes1 = "";
        String genero1 = "";
        String etapap1 = "";
        String raza1= "";
        String propietario1 = "";
        String hierro1 = "";
        String proposito1 = "";
        String fecha1 = "";

        while (fila2.moveToNext()) {
            id1 = fila2.getString(0);
            nombre1 = fila2.getString(1);
            peso1 = fila2.getString(2);
            pesodes1 = fila2.getString(3);
            genero1 = fila2.getString(4);
            etapap1 = fila2.getString(5);
            raza1= fila2.getString(6);
            propietario1 = fila2.getString(7);
            hierro1 = fila2.getString(8);
            proposito1 = fila2.getString(9);
            fecha1 = fila2.getString(10);
        }

        // while (fila15.moveToNext()) {ssd14 += fila15.getString(0);}


        db2.close();

        cod_animal.setText(use);
        cod_animal.setMovementMethod(new ScrollingMovementMethod());

        nombre.setText(nombre1);
        nombre.setMovementMethod(new ScrollingMovementMethod());

        peso.setText(peso1);
        peso.setMovementMethod(new ScrollingMovementMethod());

        pesod.setText(pesodes1);
        pesod.setMovementMethod(new ScrollingMovementMethod());

        genero.setText(genero1);
        genero.setMovementMethod(new ScrollingMovementMethod());

        etapap.setText(etapap1);
        etapap.setMovementMethod(new ScrollingMovementMethod());

        raza.setText(raza1);
        raza.setMovementMethod(new ScrollingMovementMethod());

        hierro.setText(hierro1);
        hierro.setMovementMethod(new ScrollingMovementMethod());

        proposito.setText(proposito1);
        proposito.setMovementMethod(new ScrollingMovementMethod());

        propietario.setText(propietario1);
        propietario.setMovementMethod(new ScrollingMovementMethod());

        fecha.setText(fecha1);
        fecha.setMovementMethod(new ScrollingMovementMethod());

    }




    public void limpiar(){

        nombre.setText("");
        peso.setText("");
        pesod.setText("");
        etapap.setText("");
        genero.setText("");
        raza.setText("");
        hierro.setText("");
        proposito.setText("");
        propietario.setText("");
        fecha.setText("");
    }

    @Override
    public void  onBackPressed(){
    }

    public void atras(View view) {
        Intent iw = new Intent(listado_animal_codigo3.this, home_listado_animales.class);
        startActivity(iw);
        finish();
    }

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
            String gpeso = peso.getText().toString();
            String gpesod = pesod.getText().toString();
            String fec = fecha.getText().toString();



            UsersSQLiteHelper admine3 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db3 = admine3.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("CODIGO", gcodigo);
            registro.put("NOMBRE", gnombre);
            registro.put("PESO", gpeso);
            registro.put("PESODESTETE", gpesod);
            registro.put("FECHAINGRE", fec);
            db3.update("ANIMALESN", registro,"ID="+id1,null);
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




