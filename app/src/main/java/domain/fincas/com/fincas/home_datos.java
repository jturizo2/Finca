package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class home_datos extends AppCompatActivity {

    private TextView ruta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_datos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ruta = (TextView) findViewById(R.id.textVie2);
    }


    @Override
    public void  onBackPressed(){
    }


    public void atras(View view) {
        Intent iw = new Intent(home_datos.this, homep.class);
        startActivity(iw);
        finish();
    }


    public void descargar_datos (View view) {

        List<String[]> data = new ArrayList<String[]>();
        String NameFile1 = "Medicamentos"+".csv";
        //-----------------Tabla tratamientos------------------------------------
        UsersSQLiteHelper admine1 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db1 = admine1.getWritableDatabase();
        Cursor fila1 = db1.rawQuery("SELECT * FROM TRATAMIENTOS", null);
        //Se guardan los elementos de cada fila
        while (fila1.moveToNext()) {
            data.add(new String[]{
                    fila1.getString(0),
                    fila1.getString(1),
                    fila1.getString(2),
                    fila1.getString(3),
                    fila1.getString(4)
            });
        }
        db1.close();

        //--------------- Tabla Ventas---------------------------------
        List<String[]> data1 = new ArrayList<String[]>();
        String NameFile2 = "Ventas"+".csv";
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db4 = admine4.getWritableDatabase();
        Cursor fila4 = db4.rawQuery("SELECT * FROM VENTAS", null);
        //Se guardaran los elementos de cada fila
        while (fila4.moveToNext()) {
            data1.add(new String[]{
                    fila4.getString(0),
                    fila4.getString(1),
                    fila4.getString(2),
                    fila4.getString(3),
                    fila4.getString(4),
                    fila4.getString(5),
                    fila4.getString(6)
            });
        }
        db4.close();

        //-----------------Tabla Animales ------------------------------------
        List<String[]> data2 = new ArrayList<String[]>();
        String NameFile3 = "Animales.csv";
        UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db2 = admine2.getWritableDatabase();
        Cursor fila2 = db2.rawQuery("SELECT * FROM ANIMALESN", null);
        //Se guardaran los elementos de cada fila
        while (fila2.moveToNext()) {
            data2.add(new String[]{
                    fila2.getString(0),
                    fila2.getString(1),
                    fila2.getString(2),
                    fila2.getString(3),
                    fila2.getString(4),
                    fila2.getString(5),
                    fila2.getString(6),
                    fila2.getString(7),
                    fila2.getString(8),
                    fila2.getString(9),
                    fila2.getString(10),
                    fila2.getString(11),
                    fila2.getString(12),
                    fila2.getString(13),
                    fila2.getString(14),
                    fila2.getString(15),
                    fila2.getString(16),
                    fila2.getString(17),
                    fila2.getString(18),
                    fila2.getString(19),
                    fila2.getString(20),
                    fila2.getString(21),
                    fila2.getString(22)
            });
        }
        db2.close();

        //--------------- Tabla Fincas---------------------------------
        List<String[]> data3 = new ArrayList<String[]>();
        String NameFile4 = "Finca.csv";
        UsersSQLiteHelper admine5 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db5 = admine5.getWritableDatabase();
        Cursor fila5 = db5.rawQuery("SELECT * FROM NFINCAS", null);
        //Se guardaran los elementos de cada fila
        while (fila5.moveToNext()) {
            data3.add(new String[]{
                    fila5.getString(0),
                    fila5.getString(1),
                    fila5.getString(2),
                    fila5.getString(3),
                    fila5.getString(4),
                    fila5.getString(5)
            });
        }
        db5.close();

        //--------------- Tabla Propietarios---------------------------------
        List<String[]> data4 = new ArrayList<String[]>();
        String NameFile5 = "Propietarios"+".csv";
        UsersSQLiteHelper admine3 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db3 = admine3.getWritableDatabase();
        Cursor fila3 = db3.rawQuery("SELECT * FROM PROPIETARIOS", null);
        //Se guardaran los elementos de cada fila
        while (fila3.moveToNext()) {
            data4.add(new String[]{
                    fila3.getString(0),
                    fila3.getString(1),
                    fila3.getString(2)

            });
        }
        db3.close();

        //--------------- Tabla Hierros---------------------------------
        List<String[]> data5 = new ArrayList<String[]>();
        String NameFile6 = "Hierro"+".csv";
        UsersSQLiteHelper admine7 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db6 = admine7.getWritableDatabase();
        Cursor fila7 = db6.rawQuery("SELECT * FROM THIERRO", null);
        //Se guardaran los elementos de cada fila
        while (fila7.moveToNext()) {
            data5.add(new String[]{
                    fila7.getString(0),
                    fila7.getString(1)
            });
        }
        db6.close();



        crear_dir(); //Metodo para crer la ruta de almacenamiento del  backup

        // ----------------- Creacion del Csv Medicamentos -------------------------------
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos";
        String filePath = baseDir + File.separator + NameFile1;
        File f = new File(filePath );
        CSVWriter writer;
        f.delete();
        try {
            // File exist
            if (f.exists() && !f.isDirectory()) {
                FileWriter mFileWriter = new FileWriter(filePath, true);
                writer = new CSVWriter(mFileWriter, ';');
            } else {
                writer = new CSVWriter(new FileWriter(filePath), ';');
            }

            writer.writeAll(data);

            writer.close();
            Toast.makeText(this,"Información almacenada, consulte en la carpeta Datos.", Toast.LENGTH_LONG).show();

        }catch (IOException e) {
            Toast.makeText(this,"Error, intente mas tarde.", Toast.LENGTH_LONG).show();

        }


        // ----------------- Creacion del Csv Ventas -------------------------------
        String baseDir1 = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos";
        String filePath1 = baseDir1 + File.separator + NameFile2;
        File f1 = new File(filePath1);
        CSVWriter writer1;
        f1.delete();
        try {
            // File exist
            if (f1.exists() && !f1.isDirectory()) {
                FileWriter mFileWriter = new FileWriter(filePath1, true);
                writer1 = new CSVWriter(mFileWriter, ';');
            } else {
                writer1 = new CSVWriter(new FileWriter(filePath1), ';');
            }

            writer1.writeAll(data1);

            writer1.close();
           // Toast.makeText(this,"Información almacenada, consulte en la carpeta Datos.", Toast.LENGTH_LONG).show();

        }catch (IOException e) {
            Toast.makeText(this,"Error, intente mas tarde.", Toast.LENGTH_LONG).show();

        }


        // ----------------- Creacion del Csv Animales -------------------------------
        String baseDir2 = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos";
        String filePath2 = baseDir2 + File.separator + NameFile3;
        File f2 = new File(filePath2);
        CSVWriter writer2;
        f2.delete();
        try {
            // File exist
            if (f2.exists() && !f2.isDirectory()) {
                FileWriter mFileWriter = new FileWriter(filePath2, true);
                writer2 = new CSVWriter(mFileWriter, ';');
            } else {
                writer2 = new CSVWriter(new FileWriter(filePath2), ';');
            }

            writer2.writeAll(data2);

            writer2.close();
          //  Toast.makeText(this,"Información almacenada, consulte en la carpeta Datos.", Toast.LENGTH_LONG).show();

        }catch (IOException e) {
            Toast.makeText(this,"Error, intente mas tarde.", Toast.LENGTH_LONG).show();

        }


        // ----------------- Creacion del Csv Finca -------------------------------
        String baseDir3 = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos";
        String filePath3 = baseDir3 + File.separator + NameFile4;
        File f3 = new File(filePath3);
        CSVWriter writer3;
        f3.delete();
        try {
            // File exist
            if (f3.exists() && !f3.isDirectory()) {
                FileWriter mFileWriter = new FileWriter(filePath3, true);
                writer3 = new CSVWriter(mFileWriter, ';');
            } else {
                writer3 = new CSVWriter(new FileWriter(filePath3), ';');
            }

            writer3.writeAll(data3);

            writer3.close();
          //  Toast.makeText(this,"Información almacenada, consulte en la carpeta Datos.", Toast.LENGTH_LONG).show();

        }catch (IOException e) {
            Toast.makeText(this,"Error, intente mas tarde.", Toast.LENGTH_LONG).show();

        }

        // ----------------- Creacion del Csv Propietarios -------------------------------
        String baseDir4 = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos";
        String filePath4 = baseDir4 + File.separator + NameFile5;
        File f4 = new File(filePath4);
        CSVWriter writer4;
        f4.delete();
        try {
            // File exist
            if (f4.exists() && !f4.isDirectory()) {
                FileWriter mFileWriter = new FileWriter(filePath4, true);
                writer4 = new CSVWriter(mFileWriter, ';');
            } else {
                writer4 = new CSVWriter(new FileWriter(filePath4), ';');
            }

            writer4.writeAll(data4);

            writer4.close();
          //  Toast.makeText(this,"Información almacenada, consulte en la carpeta Datos.", Toast.LENGTH_LONG).show();

        }catch (IOException e) {
            Toast.makeText(this,"Error, intente mas tarde.", Toast.LENGTH_LONG).show();

        }

        // ----------------- Creacion del Csv Hierros -------------------------------
        String baseDir5 = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos";
        String filePath5 = baseDir5 + File.separator + NameFile6;
        File f5 = new File(filePath5);
        CSVWriter writer5;
        f5.delete();
        try {
            // File exist
            if (f5.exists() && !f5.isDirectory()) {
                FileWriter mFileWriter = new FileWriter(filePath5, true);
                writer5 = new CSVWriter(mFileWriter, ';');
            } else {
                writer5 = new CSVWriter(new FileWriter(filePath5), ';');
            }

            writer5.writeAll(data5);

            writer5.close();
         //   Toast.makeText(this,"Información almacenada, consulte en la carpeta Datos.", Toast.LENGTH_LONG).show();

        }catch (IOException e) {
            Toast.makeText(this,"Error, intente mas tarde.", Toast.LENGTH_LONG).show();

        }
        /*---------------------------------------

      */





    }//fin del metodo crear csv


    public void crear_dir(){
        File f = new File(Environment.getExternalStorageDirectory() + "/Datos");

        // Comprobamos si la carpeta está ya creada

        // Si la carpeta no está creada, la creamos.
        if(!f.isDirectory()) {
            String newFolder = "/Datos"; //cualquierCarpeta es el nombre de la Carpeta que vamos a crear
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File myNewFolder = new File(extStorageDirectory + newFolder);
            myNewFolder.mkdir(); //creamos la carpeta
        }else{
            // Toast.makeText(this,"La carpeta ya estaba creada.", Toast.LENGTH_LONG).show();

        }

    }

    public void carga_datos(View view) throws IOException {
        /*
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
        */
    }// fin cargar datos


}
