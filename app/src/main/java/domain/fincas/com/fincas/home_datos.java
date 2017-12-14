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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
                    fila4.getString(4)
            });
        }
        db4.close();

        //-----------------Tabla Animales ------------------------------------
        List<String[]> data2 = new ArrayList<String[]>();
        String NameFile3 = "Animales"+".csv";
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
            Toast.makeText(this,"Información almacenada, consulte en la carpeta Datos.", Toast.LENGTH_LONG).show();

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
            Toast.makeText(this,"Información almacenada, consulte en la carpeta Datos.", Toast.LENGTH_LONG).show();

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
        //------------Carga CSV Tratamientos ----------------------------------------
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();
        String fileName = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Medicamentos.csv";
        FileReader file = new FileReader(fileName);
        BufferedReader buffer = new BufferedReader(file);
        String line = "";
        String tableName ="TRATAMIENTOS";
        String columns = "CODIGO, MEDICAMENTO, DETALLE, COSTO";
        String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        String str2 = ");";
        db5.beginTransaction();
        while ((line = buffer.readLine()) != null) {
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

        //------------Carga CSV Ventas ----------------------------------------
        UsersSQLiteHelper admine5 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db6 = admine5.getWritableDatabase();
        String fileName1 = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Ventas.csv";
        FileReader file1 = new FileReader(fileName1);
        BufferedReader buffer1 = new BufferedReader(file1);
        String line1 = "";
        String tableName1 ="VENTAS";
        String columns1 = "CODIGO, PESO, DETALLE, COMPRADOR, VALOR, FECHAV";
        String str3 = "INSERT INTO " + tableName1 + " (" + columns1 + ") values(";
        String str4 = ");";
        db6.beginTransaction();
        while ((line1 = buffer1.readLine()) != null) {
            StringBuilder sb1 = new StringBuilder(str3);
            String[] str11 = line1.split(";");
            sb1.append("'" +str11[1] + "',");
            sb1.append("'" +str11[2] + "',");
            sb1.append("'" +str11[3] + "',");
            sb1.append("'" +str11[4] + "',");
            sb1.append("'" +str11[5] + "',");
            sb1.append("'" +str11[6] + "'");
            sb1.append(str4);
            db6.execSQL(sb1.toString());
        }
        db6.setTransactionSuccessful();
        db6.endTransaction();

        //------------Carga CSV Animales ----------------------------------------
        UsersSQLiteHelper admine6 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db7 = admine6.getWritableDatabase();
        String fileName2 = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Animales.csv";
        FileReader file2 = new FileReader(fileName2);
        BufferedReader buffer2 = new BufferedReader(file2);
        String line2 = "";
        String tableName2 ="ANIMALESN";
        String columns2 = "ID, CODIGO, NOMBRE, GENERO, PROCEDENCIA, RAZA, FOTO, PROPIETARIO, PROPIETARIOA, HIERRO, PROPOSITO, FECHAINGRE, PESO, PESODESTETE, ETAPAP, VALORC, CODMAMA, CODPAPA, CODPARTO, FECHANACI, NFINCA, TIPOANIMAL, COMPPAR";
        String str5 = "INSERT INTO " + tableName1 + " (" + columns2 + ") values(";
        String str6 = ");";
        db7.beginTransaction();
        while ((line2 = buffer2.readLine()) != null) {
            StringBuilder sb2 = new StringBuilder(str5);
            String[] str12 = line2.split(";");
            sb2.append("'" +str12[0] + "',");
            sb2.append("'" +str12[1] + "',");
            sb2.append("'" +str12[2] + "',");
            sb2.append("'" +str12[3] + "',");
            sb2.append("'" +str12[4] + "',");
            sb2.append("'" +str12[5] + "',");
            sb2.append("'" +str12[6] + "'");
            sb2.append("'" +str12[7] + "'");
            sb2.append("'" +str12[8] + "'");
            sb2.append("'" +str12[9] + "'");
            sb2.append("'" +str12[10] + "'");
            sb2.append("'" +str12[11] + "'");
            sb2.append("'" +str12[12] + "'");
            sb2.append("'" +str12[13] + "'");
            sb2.append("'" +str12[14] + "'");
            sb2.append("'" +str12[15] + "'");
            sb2.append("'" +str12[16] + "'");
            sb2.append("'" +str12[17] + "'");
            sb2.append("'" +str12[18] + "'");
            sb2.append("'" +str12[19] + "'");
            sb2.append("'" +str12[20] + "'");
            sb2.append("'" +str12[21] + "'");
            sb2.append("'" +str12[22] + "'");
            sb2.append(str6);
            db7.execSQL(sb2.toString());
        }
        db7.setTransactionSuccessful();
        db7.endTransaction();

    }// fin cargar datos


}
