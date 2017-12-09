package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ingreso_animales extends AppCompatActivity {
    private Spinner Genero2,Raza2,Propietario2,Hierro2,Proposito2,etapap;
    private EditText codig, nomb,peso,pesod,etap_p,valor_c, propietari;
    private Integer c_p ;


    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_animales);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

         codig= (EditText) findViewById(R.id.cod2);
         nomb= (EditText) findViewById(R.id.nombre2);
         peso= (EditText) findViewById(R.id.Peso2);
         pesod= (EditText) findViewById(R.id.Peso_destete2);
         valor_c= (EditText) findViewById(R.id.V_Compra2);
         propietari = (EditText) findViewById(R.id.propieta);

         c_p =0;



        /* // Consultar Campos en la Base de Datos

        String[] campos = new String[] {"CODIGO","NOMBRE", "GENERO","RAZA","PROPIETARIO","HIERRO","PROPOSITO","NFINCA","PESO","PESODESTETE","VALORC","TIPOANIMAL","COMP_PAR"};
        String[] args = new String[] {""};
        String whereClause = "ID=?";
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this,"FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();

        Cursor fila = db5.query("ANIMALESN", campos, null, null, null, null, null, null);
        String ssd ="";
        while (fila.moveToNext()) {
            ssd +=   "<- Datos--> "+ "\n"
                    +  "CODIGO: "+fila.getString(0)+ "\n"
                    + "NOMBRE: " + fila.getString(1)+ "\n"
                    + "GENERO: " + fila.getString(2)+ "\n"
                    + "RAZA: " + fila.getString(3)+ "\n"
                    + "PROPIETARIO: " + fila.getString(4)+ "\n"
                    + "HIERRO: " + fila.getString(5)+ "\n"
                    + "PROPOSITO: " + fila.getString(6)+ "\n"
                    + "NFINCA: " + fila.getString(7)+ "\n"
                    + "PESO: " + fila.getString(8)+ "\n"
                    + "PESODESTETE: " + fila.getString(9)+ "\n"
                    + "VALORC: " + fila.getString(10)+ "\n"
                    + "TIPOANIMAL: " + fila.getString(11)+ "\n"
                    + "COMP_PAR: " + fila.getString(12)+ "\n";
        }
        db5.close();
        Toast.makeText(this,ssd,Toast.LENGTH_LONG).show();

      //   Fin de la Consulta */
        //  Spinners :::::::::::::::::::::::::::::: Etapa :::::::::::::::::
        etapap=(Spinner) findViewById(R.id.etapap);
        String[]tp155 = {""};
        ArrayAdapter<String> adap551 = new ArrayAdapter<String>(ingreso_animales.this, android.R.layout.simple_spinner_item, tp155);
        etapap.setAdapter(adap551);

        //  Spinners :::::::::::::::::::::::::::::: Genero :::::::::::::::::
        Genero2=(Spinner) findViewById(R.id.Genero2);
        String[]tp1 = {"Macho","Hembra"};
        ArrayAdapter<String> adap1 = new ArrayAdapter<String>(ingreso_animales.this, android.R.layout.simple_spinner_item, tp1);
        Genero2.setAdapter(adap1);

        Genero2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                //Toast.makeText(adapterView.getContext(),(String) adapterView.getSelectedItem(),Toast.LENGTH_LONG).show();
                //AC((String) adapterView.getSelectedItem());
                if(adapterView.getSelectedItem().equals("Macho")){

                    String[]tp155 = {"Ternero","Novillo","Toro"};
                    ArrayAdapter<String> adap551 = new ArrayAdapter<String>(ingreso_animales.this, android.R.layout.simple_spinner_item, tp155);
                    etapap.setAdapter(adap551);

                }else{

                    String[]tp155 = {"Ternera","Novilla","Vaca"};
                    ArrayAdapter<String> adap551 = new ArrayAdapter<String>(ingreso_animales.this, android.R.layout.simple_spinner_item, tp155);
                    etapap.setAdapter(adap551);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });

        //  Spinners :::::::::::::::::::::::::::::: Proposito :::::::::::::::::
        Proposito2=(Spinner) findViewById(R.id.Proposito2);
        String[]tp2 = {"Engorde","Lechería","Cria"};
        ArrayAdapter<String> adap2 = new ArrayAdapter<String>(ingreso_animales.this, android.R.layout.simple_spinner_item, tp2);
        Proposito2.setAdapter(adap2);


        //  Spinners :::::::::::::::::::::::::::::: Raza :::::::::::::::::
        Raza2=(Spinner) findViewById(R.id.Raza2);
        String[]tp4 = {"Comercial","Cebú","Normanda","Parda"};
        ArrayAdapter<String> adap4 = new ArrayAdapter<String>(ingreso_animales.this, android.R.layout.simple_spinner_item, tp4);
        Raza2.setAdapter(adap4);
        // consulta data base



        //Consulta hierro
        ArrayList<String> hierro = new ArrayList<String>();
        UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db3 = admine.getWritableDatabase();
        Cursor fila3 = db3.rawQuery("SELECT HIERRO FROM THIERRO", null);
        while(fila3.moveToNext()){
            hierro.add(fila3.getString(0));
        }
        db3.close();

        ArrayList<String> finca = new ArrayList<String>();
        UsersSQLiteHelper adm = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db4 = adm.getWritableDatabase();
        Cursor fila4 = db4.rawQuery("SELECT CODIGO FROM NFINCAS", null);
        while(fila4.moveToNext()){
            finca.add(fila4.getString(0));
        }
        db4.close();

        /*
        //Consulta Propietario
        ArrayList<String> name = new ArrayList<String>();
        UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db2 = admine2.getWritableDatabase();
        Cursor fila1 = db2.rawQuery("SELECT NOMBRE,APELLIDO FROM PROPIETARIOS", null);

        while(fila1.moveToNext()){
            name.add(fila1.getString(0)+" "+fila1.getString(1));
        }
        db2.close();

        Propietario2=(Spinner) findViewById(R.id.Propietario2);
        String[]tp5 = {"Macho","Hembra"};
        ArrayAdapter<String> adap5 = new ArrayAdapter<String>(ingreso_animales.this, android.R.layout.simple_spinner_item, name);
        Propietario2.setAdapter(adap5);

        */

        //hierro consulta database
        Hierro2=(Spinner) findViewById(R.id.Hierro2);
        String[]tp6 = {"Macho","Hembra"};
        ArrayAdapter<String> adap6 = new ArrayAdapter<String>(ingreso_animales.this, android.R.layout.simple_spinner_item, hierro);
        Hierro2.setAdapter(adap6);
    }



    public void guardar(View view) {

        String gcodigo = codig.getText().toString();
        String gnomb = nomb.getText().toString();
        String ggenero = Genero2.getSelectedItem().toString();
        String graza = Raza2.getSelectedItem().toString();
        String gpropietario = propietari.getText().toString();
        String ghierro = Hierro2.getSelectedItem().toString();
        String gpropo = Proposito2.getSelectedItem().toString();
        String gpeso = peso.getText().toString();
        String gpesod = pesod.getText().toString();
        String getapp = etapap.getSelectedItem().toString();
        String gvalorc = valor_c.getText().toString();
        String comp_par = c_p.toString();
        String fecha_i =dateFormat.format(date).toString();
        //------------------------------------------------------------------------------------
        //-----Se busca si el cod existe en los animales
        // Cod actual
        String use=codig.getText().toString();
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
            UsersSQLiteHelper admine9 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db9 = admine9.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("CODIGO", gcodigo);
            registro.put("NOMBRE", gnomb);
            registro.put("GENERO", ggenero);
            registro.put("RAZA", graza);
            registro.put("PROPIETARIOA", gpropietario);
            registro.put("HIERRO", ghierro);
            registro.put("PROPOSITO", gpropo);
            registro.put("PESO", gpeso);
            registro.put("PESODESTETE", gpesod);
            registro.put("ETAPAP", getapp);
            registro.put("VALORC", gvalorc);
            registro.put("COMPPAR", comp_par);
            registro.put("FECHAINGRE", fecha_i);

            db9.insert("ANIMALESN", null, registro);
            db9.close();
            Toast.makeText(this, "Animal Registrado!!", Toast.LENGTH_LONG).show();


            Intent i = new Intent(ingreso_animales.this, ingresoTipoAnimal.class);
            startActivity(i);
            finish();

        }else{
            Toast.makeText(this, "El código ya existe!!!", Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    public void atras(View view) {
        Intent iw = new Intent(ingreso_animales.this, ingresoTipoAnimal.class);
        startActivity(iw);
        finish();
    }

    private void limpiar(){
        codig.setText("");
        codig.requestFocus();
    }


  /* private String getDateTime() {
      /  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
       // Date date = new Date();
       // return dateFormat.format(date);
    } */

} //fin de la clase
