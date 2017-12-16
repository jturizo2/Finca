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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ingresoAnimalesParto extends AppCompatActivity {
    private Spinner genero, raza, propietario, hierro, proposito,etapap;
    private EditText codigo, nombre ,codmama,codpapa,codparto,peso,pesodeste;
    private Integer c_p ;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_animales_parto);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        codigo= (EditText) findViewById(R.id.cod2);
        nombre= (EditText) findViewById(R.id.nombre2);
        codmama= (EditText) findViewById(R.id.Cod_MAMA2);
        codpapa= (EditText) findViewById(R.id.cod_papa2);
        codparto= (EditText) findViewById(R.id.cod_part2);
        peso= (EditText) findViewById(R.id.Peso2);
        pesodeste= (EditText) findViewById(R.id.Peso_destete2);
        c_p =1;


        etapap=(Spinner) findViewById(R.id.etapap);
        String[]tp155 = {""};
        ArrayAdapter<String> adap551 = new ArrayAdapter<String>(ingresoAnimalesParto.this, android.R.layout.simple_spinner_item, tp155);
        etapap.setAdapter(adap551);

        //  Spinners :::::::::::::::::::::::::::::: Genero :::::::::::::::::
        genero=(Spinner) findViewById(R.id.Genero2);
        String[]tp1 = {"Macho","Hembra"};
        ArrayAdapter<String> adap1 = new ArrayAdapter<String>(ingresoAnimalesParto.this, android.R.layout.simple_spinner_item, tp1);
        genero.setAdapter(adap1);

        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                //Toast.makeText(adapterView.getContext(),(String) adapterView.getSelectedItem(),Toast.LENGTH_LONG).show();
                //AC((String) adapterView.getSelectedItem());
                if(adapterView.getSelectedItem().equals("Macho")){

                    String[]tp155 = {"Ternero","Novillo","Toro"};
                    ArrayAdapter<String> adap551 = new ArrayAdapter<String>(ingresoAnimalesParto.this, android.R.layout.simple_spinner_item, tp155);
                    etapap.setAdapter(adap551);

                }else{

                    String[]tp155 = {"Ternera","Novilla","Vaca"};
                    ArrayAdapter<String> adap551 = new ArrayAdapter<String>(ingresoAnimalesParto.this, android.R.layout.simple_spinner_item, tp155);
                    etapap.setAdapter(adap551);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });




        //  Spinners :::::::::::::::::::::::::::::: Raza :::::::::::::::::
        raza=(Spinner) findViewById(R.id.Raza2);
        String[]tp4 = {"Comercial","Cebú","Normanda","Parda"};
        ArrayAdapter<String> adap4 = new ArrayAdapter<String>(ingresoAnimalesParto.this, android.R.layout.simple_spinner_item, tp4);
        raza.setAdapter(adap4);

        //Consulta Propietario
        ArrayList<String> name = new ArrayList<String>();
        UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db2 = admine2.getWritableDatabase();
        Cursor fila1 = db2.rawQuery("SELECT NOMBRE,APELLIDO FROM PROPIETARIOS", null);

        while(fila1.moveToNext()){
            name.add(fila1.getString(0)+" "+fila1.getString(1));
        }
        db2.close();
        //Spinners Propietario
        propietario=(Spinner) findViewById(R.id.Propietario2);
        String[]tp5 = {"Macho","Hembra"};
        ArrayAdapter<String> adap5 = new ArrayAdapter<String>(ingresoAnimalesParto.this, android.R.layout.simple_spinner_item, name);
        propietario.setAdapter(adap5);

        //Consulta hierro
        ArrayList<String> hierro1 = new ArrayList<String>();
        UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db3 = admine.getWritableDatabase();
        Cursor fila3 = db3.rawQuery("SELECT HIERRO FROM THIERRO", null);
        while(fila3.moveToNext()){
            hierro1.add(fila3.getString(0));
        }
        db3.close();

        //Spinners Hierro
        hierro=(Spinner) findViewById(R.id.Hierro2);
        String[]tp6 = {"Macho","Hembra"};
        ArrayAdapter<String> adap6 = new ArrayAdapter<String>(ingresoAnimalesParto.this, android.R.layout.simple_spinner_item, hierro1);
        hierro.setAdapter(adap6);

        //  Spinners :::::::::::::::::::::::::::::: Proposito :::::::::::::::::
        proposito=(Spinner) findViewById(R.id.Proposito2);
        String[]tp2 = {"Engorde","Lechería","Cria"};
        ArrayAdapter<String> adap2 = new ArrayAdapter<String>(ingresoAnimalesParto.this, android.R.layout.simple_spinner_item, tp2);
        proposito.setAdapter(adap2);

        //Consulta Finca
        ArrayList<String> finca = new ArrayList<String>();
        UsersSQLiteHelper adm = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db4 = adm.getWritableDatabase();
        Cursor fila4 = db4.rawQuery("SELECT CODIGO FROM NFINCAS", null);
        while(fila4.moveToNext()){
            finca.add(fila4.getString(0));
        }
        db4.close();

        //Spinners Finca


    }//fin onCreate


    public void guardar (View view){

        String gcodigo = codigo.getText().toString();
        String gnomb = nombre.getText().toString();
        String ggenero =genero.getSelectedItem().toString();
        String graza = raza.getSelectedItem().toString();
        String gpropietario = propietario.getSelectedItem().toString();
        String ghierro = hierro.getSelectedItem().toString();
        String gpropo = proposito.getSelectedItem().toString();

        String gcodmama = codmama.getText().toString();
        String gcodpapa = codpapa.getText().toString();
        String gcodparto = codparto.getText().toString();
        String gpeso = peso.getText().toString();
        String gpesodeste = pesodeste.getText().toString();
        String getapp = etapap.getSelectedItem().toString();

        String comp_par = c_p.toString();
        String fecha_i =dateFormat.format(date).toString();
        //------------------------------------------------------------
        //-----Se busca si el cod existe en los animales
        // Cod actual
        String use=codigo.getText().toString();
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
            registro.put("PROPIETARIO", gpropietario);
            registro.put("HIERRO", ghierro);
            registro.put("PROPOSITO", gpropo);
            registro.put("CODMAMA", gcodmama);
            registro.put("CODPAPA", gcodpapa);
            registro.put("CODPARTO", gcodparto);
            registro.put("PESO", gpeso);
            registro.put("PESODESTETE", gpesodeste);
            registro.put("ETAPAP", getapp);
            registro.put("COMPPAR", comp_par);
            registro.put("FECHANACI", fecha_i);

            db9.insert("ANIMALESN", null, registro);
            db9.close();
            Toast.makeText(this, "Animal Registrado!!", Toast.LENGTH_LONG).show();


            Intent i = new Intent(ingresoAnimalesParto.this, ingresoTipoAnimal.class);
            startActivity(i);
            finish();

        }else{
            Toast.makeText(this, "El código ya existe!!!", Toast.LENGTH_LONG).show();
            limpiar();

        }

   }
    @Override
    public void  onBackPressed(){
    }

    public void atras(View view) {
        Intent iw = new Intent(ingresoAnimalesParto.this, ingresoTipoAnimal.class);
        startActivity(iw);
        finish();
    }


    private void limpiar(){
        codigo.setText("");
        codigo.requestFocus();
    }



}
