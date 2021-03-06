package domain.fincas.com.fincas;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ingreso_animales_existentes extends AppCompatActivity {

    private Spinner genero, raza, propietario, hierro, proposito,etapap;
    private EditText codigo, nombre,peso,pesodeste,fecha,fecha2, etPlannedDate,etPlannedDate1;
    private Integer c_p ;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_animales_existentes);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //fecha= (EditText) findViewById(R.id.fecha22);
        //fecha2= (EditText) findViewById(R.id.fech22);
        codigo= (EditText) findViewById(R.id.cod2);
        nombre= (EditText) findViewById(R.id.nombre2);
        peso= (EditText) findViewById(R.id.Peso2);
        pesodeste= (EditText) findViewById(R.id.Peso_destete2);
        c_p =2;
        String fc = dateFormat.format(date.getTime());
        //fecha.setText(fc);
        //fecha2.setText(fc);

        //--------------- datepicker---------------------------------
        etPlannedDate = (EditText) findViewById(R.id.etPlannedDate);
        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        etPlannedDate.setText(fc);

        etPlannedDate1 = (EditText) findViewById(R.id.etPlannedDate1);
        etPlannedDate1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog1();
            }
        });
        etPlannedDate1.setText(fc);

        //--------------- datepicker---------------------------------



        etapap=(Spinner) findViewById(R.id.etapap);
        String[]tp155 = {""};
        ArrayAdapter<String> adap551 = new ArrayAdapter<String>(ingreso_animales_existentes.this, android.R.layout.simple_spinner_item, tp155);
        etapap.setAdapter(adap551);

        //  Spinners :::::::::::::::::::::::::::::: Genero :::::::::::::::::
        genero=(Spinner) findViewById(R.id.Genero2);
        String[]tp1 = {"Macho","Hembra"};
        ArrayAdapter<String> adap1 = new ArrayAdapter<String>(ingreso_animales_existentes.this, android.R.layout.simple_spinner_item, tp1);
        genero.setAdapter(adap1);

        genero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                //Toast.makeText(adapterView.getContext(),(String) adapterView.getSelectedItem(),Toast.LENGTH_LONG).show();
                //AC((String) adapterView.getSelectedItem());
                if(adapterView.getSelectedItem().equals("Macho")){

                    String[]tp155 = {"Ternero","Novillo","Toro"};
                    ArrayAdapter<String> adap551 = new ArrayAdapter<String>(ingreso_animales_existentes.this, android.R.layout.simple_spinner_item, tp155);
                    etapap.setAdapter(adap551);

                }else{

                    String[]tp155 = {"Ternera","Novilla","Vaca"};
                    ArrayAdapter<String> adap551 = new ArrayAdapter<String>(ingreso_animales_existentes.this, android.R.layout.simple_spinner_item, tp155);
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
        ArrayAdapter<String> adap4 = new ArrayAdapter<String>(ingreso_animales_existentes.this, android.R.layout.simple_spinner_item, tp4);
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
        ArrayAdapter<String> adap5 = new ArrayAdapter<String>(ingreso_animales_existentes.this, android.R.layout.simple_spinner_item, name);
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
        ArrayAdapter<String> adap6 = new ArrayAdapter<String>(ingreso_animales_existentes.this, android.R.layout.simple_spinner_item, hierro1);
        hierro.setAdapter(adap6);

        //  Spinners :::::::::::::::::::::::::::::: Proposito :::::::::::::::::
        proposito=(Spinner) findViewById(R.id.Proposito2);
        String[]tp2 = {"Engorde","Lechería","Cria","Doble Proposito","Triple Proposito"};
        ArrayAdapter<String> adap2 = new ArrayAdapter<String>(ingreso_animales_existentes.this, android.R.layout.simple_spinner_item, tp2);
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



    }// fin oncreate

    @Override
    public void  onBackPressed(){
        Intent iw = new Intent(ingreso_animales_existentes.this, ingresoTipoAnimal.class);
        startActivity(iw);
        finish();
    }

    //metodo mostrar datepicker
    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etPlannedDate.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");


    } // fin metodo mostrar datepicker


    //metodo mostrar datepicker1
    private void showDatePickerDialog1() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                etPlannedDate1.setText(selectedDate);
            }
        });
        newFragment.show(getFragmentManager(), "datePicker");


    } // fin metodo mostrar datepicker 1

    public void guardar (View view){

        if ("".equals(codigo.getText().toString())) {
            Toast.makeText(this, "Ingrese el codido del animal!!", Toast.LENGTH_LONG).show();
        } else {

            String gcodigo = codigo.getText().toString();
            String gnomb = nombre.getText().toString();
            String ggenero = genero.getSelectedItem().toString();
            String graza = raza.getSelectedItem().toString();
            String gpropietario = propietario.getSelectedItem().toString();
            String ghierro = hierro.getSelectedItem().toString();
            String gpropo = proposito.getSelectedItem().toString();
            String gpeso = peso.getText().toString();
            String gpesodeste = pesodeste.getText().toString();
            String getapp = etapap.getSelectedItem().toString();
            String comp_par = c_p.toString();
            String fecha_i = etPlannedDate.getText().toString().replace(" ", "");
            String fecha_n = etPlannedDate1.getText().toString().replace(" ", "");

            //------------------------------------------------------------
            //-----Se busca si el cod existe en los animales
            // Cod actual
            String use = codigo.getText().toString();
            // Lista de codigos existentes
            UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db = admine.getWritableDatabase();
            Cursor fila = db.rawQuery("SELECT CODIGO FROM ANIMALESN", null);
            String ssd = "";
            String flag = "nada";
            while (fila.moveToNext()) {
                String prube = fila.getString(0);
                if (use.equals(prube)) {
                    flag = "algop";
                    break;
                }

            }
            db.close();

            if (flag.equals("nada")) {
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
                registro.put("PESO", gpeso);
                registro.put("PESODESTETE", gpesodeste);
                registro.put("ETAPAP", getapp);
                registro.put("COMPPAR", comp_par);
                registro.put("FECHAINGRE", fecha_i);
                registro.put("FECHANACI", fecha_n);

                db9.insert("ANIMALESN", null, registro);
                db9.close();
                Toast.makeText(this, "Animal Registrado!!", Toast.LENGTH_LONG).show();


                Intent i = new Intent(ingreso_animales_existentes.this, ingresoTipoAnimal.class);
                startActivity(i);
                finish();

            } else {
                Toast.makeText(this, "El código ya existe!!!", Toast.LENGTH_LONG).show();
                limpiar();

            }


        }
    }// fin del metodo guardar



    private void limpiar(){
        codigo.setText("");
        codigo.requestFocus();
    }


}
