package domain.fincas.com.fincas;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class listado_animal_codigo extends AppCompatActivity {

    private EditText cod_animal,nombre, peso, pesod,vende,valorc2,fecha,fecha2,etapap,etPlannedDate,etPlannedDate1;
    private TextView raza, genero,hierro,proposito,partos,npropieta;
    private String id1 ="";
    String genero1 = "";
    String cdmama = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_animal_codigo);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //fecha = (EditText) findViewById(R.id.fecha2);
        //fecha2 = (EditText) findViewById(R.id.fcha2);
        valorc2 = (EditText) findViewById(R.id.valorc2);
        cod_animal = (EditText) findViewById(R.id.editText6);
        nombre =(EditText) findViewById(R.id.nombre2);
        peso =(EditText) findViewById(R.id.Peso2);
        pesod =(EditText) findViewById(R.id.Peso_destete2);
        etapap =(EditText) findViewById(R.id.produ);
        genero =(TextView) findViewById(R.id.Genero2);
        raza =(TextView) findViewById(R.id.raza2);
        vende =(EditText) findViewById(R.id.vende);
        hierro =(TextView) findViewById(R.id.hierr);
        proposito =(TextView) findViewById(R.id.propo);
        npropieta =(TextView) findViewById(R.id.npropietario);

        //--------------- datepicker---------------------------------
        etPlannedDate = (EditText) findViewById(R.id.etPlannedDate);
        etPlannedDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        //etPlannedDate.setText(fc);

        etPlannedDate1 = (EditText) findViewById(R.id.etPlannedDate1);
        etPlannedDate1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDatePickerDialog1();
            }
        });
        //--------------- datepicker---------------------------------

        // ----------Cargamos los datos de la busqueda--------------------
        //Variables de la actividad pasada
        Bundle bundle = getIntent().getExtras();
        final String use = bundle.getString("COD");

                UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                SQLiteDatabase db2 = admine2.getWritableDatabase();
                Cursor fila2 = db2.rawQuery("SELECT ID,NOMBRE,PESO,PESODESTETE,GENERO,ETAPAP,RAZA,PROPIETARIO,PROPIETARIOA,PROPIETARIO," +
                        "HIERRO,PROPOSITO,VALORC,FECHAINGRE, FECHANACI FROM ANIMALESN WHERE  CODIGO='"+use+"'", null);
                String nombre1 = "";
                String peso1 = "";
                String pesodes1 = "";
                String etapap1 = "";
                String raza1= "";
                String propietario1 = "";
                String vendedor1 = "";
                String hierro1 = "";
                String proposito1 = "";
                String valorc1 = "";
                String fecha1 = "";
                String fechan = "";
                String npropietari = "";

                while (fila2.moveToNext()) {
                     id1 = fila2.getString(0);
                     nombre1 = fila2.getString(1);
                     peso1 = fila2.getString(2);
                     pesodes1 = fila2.getString(3);
                     genero1 = fila2.getString(4);
                     etapap1 = fila2.getString(5);
                     raza1= fila2.getString(6);
                     propietario1 = fila2.getString(7);
                     npropietari= fila2.getString(8);
                     vendedor1 = fila2.getString(9);
                     hierro1 = fila2.getString(10);
                     proposito1 = fila2.getString(11);
                     valorc1 = fila2.getString(12);
                    fecha1 = fila2.getString(13);
                    fechan = fila2.getString(14);

                }
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

                vende.setText(vendedor1);
                vende.setMovementMethod(new ScrollingMovementMethod());

                npropieta.setText(npropietari);
                npropieta.setMovementMethod(new ScrollingMovementMethod());

                hierro.setText(hierro1);
                hierro.setMovementMethod(new ScrollingMovementMethod());

                proposito.setText(proposito1);
                proposito.setMovementMethod(new ScrollingMovementMethod());

                valorc2.setText(valorc1);
                valorc2.setMovementMethod(new ScrollingMovementMethod());

                etPlannedDate.setText(fecha1);
                etPlannedDate.setMovementMethod(new ScrollingMovementMethod());

                etPlannedDate1.setText(fechan);
                etPlannedDate1.setMovementMethod(new ScrollingMovementMethod());
            }
    public void consul (View view) {
        //Variables de la actividad pasada
        Bundle bundle = getIntent().getExtras();
        final String use = bundle.getString("COD");
        if (genero1.equals("Hembra")) {
            Intent iw = new Intent(listado_animal_codigo.this, readPartos.class);
            iw.putExtra("datos", use);
            startActivity(iw);
        } else {
            Toast.makeText(this, "Este campo no aplica para machos.", Toast.LENGTH_LONG).show();
        }
    }

    public void limpiar(){

        nombre.setText("");
        peso.setText("");
        pesod.setText("");
        etapap.setText("");
        genero.setText("");
        raza.setText("");
        vende.setText("");
        hierro.setText("");
        proposito.setText("");
        valorc2.setText("");
        etPlannedDate.setText("");
        npropieta.setText("");
        etPlannedDate1.setText("");

    }


    @Override
    public void  onBackPressed(){

        Intent iw = new Intent(listado_animal_codigo.this, home_listado_animales.class);
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

    //metodo mostrar datepicker 1
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
            String gproa = vende.getText().toString();
            String gpeso = peso.getText().toString();
            String gpesod = pesod.getText().toString();
            String vc = valorc2.getText().toString();
            String fec = etPlannedDate.getText().toString();
            String getapp = etapap.getText().toString();
            String gfechana = etPlannedDate1.getText().toString();



            UsersSQLiteHelper admine3 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db3 = admine3.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("CODIGO", gcodigo);
            registro.put("NOMBRE", gnombre);
            registro.put("PROPIETARIOA", gproa);
            registro.put("PESO", gpeso);
            registro.put("PESODESTETE", gpesod);
            registro.put("VALORC", vc);
            registro.put("FECHAINGRE", fec.replace(" ",""));
            registro.put("FECHANACI", gfechana.replace(" ",""));
            registro.put("ETAPAP", getapp);

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



