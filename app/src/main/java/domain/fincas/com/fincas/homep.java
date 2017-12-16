package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class homep extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homep);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
            //Aplicacion istalada por primera vez
            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("BACKUP");
            dialogo.setMessage("¿Cargamos copia de seguridad?");
            dialogo.setCancelable(false);
            dialogo.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo, int id) {
                    SI();
                }
            });
            dialogo.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo, int id) {
                    NO();
                }
            });
            dialogo.show();
        }else{
            //Aplicacion corrida despues de la segunda instalación

        }
    }

    public void SI(){

//Ruta de carga fija - Hacerla dinamica
        String medicamentos  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Medicamentos.csv";
        String animales  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Animales.csv";
        String finca  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Finca.csv";
        String hierro  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Hierro.csv";
        String propietarios  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Propietarios.csv";
        String ventas  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Ventas.csv";
        //------------Carga CSV Tratamientos ----------------------------------------
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();
        File file = new File(medicamentos);
        FileInputStream fis = null;
        try{

        fis = new FileInputStream(file);
        fis.getChannel().position(0);
        BufferedReader bRead = new BufferedReader(new InputStreamReader(fis));
        String line = "";
        String tableName ="TRATAMIENTOS";
        String columns = "CODIGO, MEDICAMENTO, DETALLE, COSTO";
        String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        String str2 = ");";
        db5.beginTransaction();
        while ((line = bRead.readLine()) != null) {
            line = line.replace("\"","");
            System.out.println(":TRATA:  "+line+"  jaja");
            StringBuilder sb = new StringBuilder(str1);
            String[] str = line.split(";");
            sb.append("'" +str[1] + "',");
            sb.append("'" +str[2] + "',");
            sb.append("'" +str[3] + "',");
            sb.append("'" +str[4] + "'");
            sb.append(str2);
            db5.execSQL(sb.toString());
        }
        db5.setTransactionSuccessful();
        db5.endTransaction();

        //------------Carga CSV Animales ----------------------------------------

        UsersSQLiteHelper ad = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase d = ad.getWritableDatabase();
        file = new File(animales);
        fis = null;
        fis = new FileInputStream(file);
        fis.getChannel().position(0);
        bRead = new BufferedReader(new InputStreamReader(fis));
        line = "";
        tableName ="ANIMALESN";
        columns = "CODIGO,NOMBRE,GENERO,PROCEDENCIA,RAZA,FOTO,PROPIETARIO,PROPIETARIOA,HIERRO,PROPOSITO,FECHAINGRE,PESO,PESODESTETE,ETAPAP,VALORC,CODMAMA,CODPAPA,CODPARTO,FECHANACI,NFINCA,TIPOANIMAL,COMPPAR";
        str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        d.beginTransaction();
        while ((line = bRead.readLine()) != null) {
            line = line.replace("\"","");
            System.out.println(":ANIMALES:  "+line+"  jaja");
            StringBuilder sb2 = new StringBuilder(str1);
            String[] str12 = line.split(";");
            sb2.append("'" +str12[1] + "',");
            sb2.append("'" +str12[2] + "',");
            sb2.append("'" +str12[3] + "',");
            sb2.append("'" +str12[4] + "',");
            sb2.append("'" +str12[5] + "',");
            sb2.append("'" +str12[6] + "',");
            sb2.append("'" +str12[7] + "',");
            sb2.append("'" +str12[8] + "',");
            sb2.append("'" +str12[9] + "',");
            sb2.append("'" +str12[10] + "',");
            sb2.append("'" +str12[11] + "',");
            sb2.append("'" +str12[12] + "',");
            sb2.append("'" +str12[13] + "',");
            sb2.append("'" +str12[14] + "',");
            sb2.append("'" +str12[15] + "',");
            sb2.append("'" +str12[16] + "',");
            sb2.append("'" +str12[17] + "',");
            sb2.append("'" +str12[18] + "',");
            sb2.append("'" +str12[19] + "',");
            sb2.append("'" +str12[20] + "',");
            sb2.append("'" +str12[21] + "',");
            sb2.append("'" +str12[22] + "'");
            sb2.append(str2);
            d.execSQL(sb2.toString());

        }
        d.setTransactionSuccessful();
        d.endTransaction();
        //------------Carga CSV Ventas ----------------------------------------
        UsersSQLiteHelper ad2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase d2 = ad2.getWritableDatabase();
        file = new File(ventas);
        fis = null;
        fis = new FileInputStream(file);
        fis.getChannel().position(0);
        bRead = new BufferedReader(new InputStreamReader(fis));
        line = "";
        tableName ="VENTAS";
        columns = "CODIGO, PESO, DETALLE, COMPRADOR, VALOR, FECHAV";
        str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        d2.beginTransaction();
        while ((line = bRead.readLine()) != null) {
            line = line.replace("\"","");
            System.out.println(":VENTAS:  "+line+"  jaja");
            StringBuilder sb2 = new StringBuilder(str1);
            String[] str12 = line.split(";");
            sb2.append("'" +str12[1] + "',");
            sb2.append("'" +str12[2] + "',");
            sb2.append("'" +str12[3] + "',");
            sb2.append("'" +str12[4] + "',");
            sb2.append("'" +str12[5] + "',");
            sb2.append("'" +str12[6] + "'");
            sb2.append(str2);
            d2.execSQL(sb2.toString());

        }
        d2.setTransactionSuccessful();
        d2.endTransaction();
        //------------Carga CSV Propietarios ----------------------------------------
        UsersSQLiteHelper ad3 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase d3 = ad3.getWritableDatabase();
        file = new File(propietarios);
        fis = null;
        fis = new FileInputStream(file);
        fis.getChannel().position(0);
        bRead = new BufferedReader(new InputStreamReader(fis));
        line = "";
        tableName ="PROPIETARIOS";
        columns = "NOMBRE, APELLIDO";
        str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        d3.beginTransaction();
        while ((line = bRead.readLine()) != null) {
            line = line.replace("\"","");
            System.out.println(":propi:  "+line+"  jaja");
            StringBuilder sb2 = new StringBuilder(str1);
            String[] str12 = line.split(";");
            sb2.append("'" +str12[1] + "',");
            sb2.append("'" +str12[2] + "'");
            sb2.append(str2);
            d3.execSQL(sb2.toString());
        }
        d3.setTransactionSuccessful();
        d3.endTransaction();

        //------------Carga CSV Hierro ----------------------------------------
        UsersSQLiteHelper ad4 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase d4 = ad4.getWritableDatabase();
        file = new File(hierro);
        fis = null;
        fis = new FileInputStream(file);
        fis.getChannel().position(0);
        bRead = new BufferedReader(new InputStreamReader(fis));
        line = "";
        tableName ="THIERRO";
        columns = "HIERRO";
        str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        d4.beginTransaction();
        while ((line = bRead.readLine()) != null) {
            line = line.replace("\"","");
            System.out.println(":HIERRO:  "+line+"  jaja");
            StringBuilder sb2 = new StringBuilder(str1);
            String[] str12 = line.split(";");
            sb2.append("'" +str12[1] + "'");
            sb2.append(str2);
            System.out.println(sb2.toString()+"  jaja");
            d4.execSQL(sb2.toString());
        }
        d4.setTransactionSuccessful();
        d4.endTransaction();

        //------------Carga CSV NFINCAS ----------------------------------------
        UsersSQLiteHelper ad5 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase d5 = ad5.getWritableDatabase();
        file = new File(finca);
        fis = null;
        fis = new FileInputStream(file);
        fis.getChannel().position(0);
        bRead = new BufferedReader(new InputStreamReader(fis));
        line = "";
        tableName ="NFINCAS";
        columns = "CODIGO, NOMBREF,HECTAREAS,DIVICIONES,LOTES";
        str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        d5.beginTransaction();
        while ((line = bRead.readLine()) != null) {
            line = line.replace("\"","");
            System.out.println(":finca:  "+line+"  jaja");
            StringBuilder sb2 = new StringBuilder(str1);
            String[] str12 = line.split(";");
            sb2.append("'" +str12[1] + "',");
            sb2.append("'" +str12[2] + "',");
            sb2.append("'" +str12[3] + "',");
            sb2.append("'" +str12[4] + "',");
            sb2.append("'" +str12[5] + "'");
            sb2.append(str2);
            d5.execSQL(sb2.toString());
        }
        d5.setTransactionSuccessful();
        d5.endTransaction();
        Toast.makeText(this,"Copia de seguridad cargada!!", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this,"Error!: "+ e.toString(), Toast.LENGTH_LONG).show();

        }
        //Marcamos que la la aplicación a fue corrieda por primera vez
        UsersSQLiteHelper ad = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db1 = ad.getWritableDatabase();

        ContentValues registro = new ContentValues();
        registro.put("NEW", "1");
        db1.insert("NNEW", null, registro);
        db1.close();

    }

    public void NO(){
        Toast.makeText(this, "Carge los datos principales.", Toast.LENGTH_LONG).show();
        Intent i = new Intent(homep.this, propietario.class);
        String road = "1";
        i.putExtra ("road", road);
        startActivity(i);
        finish();
    }



    public void configuracion(View view) {
        Intent i = new Intent(homep.this, home_configuracion.class);
        startActivity(i);
        finish();

    }

    public void consultas(View view) {
        Intent i = new Intent(homep.this, home_consultas.class);
        startActivity(i);
        finish();

    }

    public void ingreso_info(View view) {
        Intent i = new Intent(homep.this, botones_ingreso_info.class);
        startActivity(i);
        finish();

    }

    public void backup(View view) {
        Intent i = new Intent(homep.this, home_datos.class);
        startActivity(i);
        finish();

    }

    public void cerrar(View view) {
        //Borrar usuario actual
        UsersSQLiteHelper admine3 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db3 = admine3.getWritableDatabase();
        db3.delete("ACTUAL",null,null);
        Intent choo2 = new Intent(homep.this, MainActivity.class);
        startActivity(choo2);
        finish();
    }






}