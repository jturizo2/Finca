package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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


    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    String myData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_datos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ruta = (TextView) findViewById(R.id.textVie2);
        Button boton = (Button) findViewById(R.id.button20);



        //----------Verificamos----------------

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            boton.setEnabled(false);
        }
    }


    @Override
    public void onBackPressed() {
    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public void atras(View view) {
        Intent iw = new Intent(home_datos.this, homep.class);
        startActivity(iw);
        finish();
    }


    public void descargar_datos(View view) {
        //----------- Sacamos información de las tablas----------------------------------------

        List<String[]> data = new ArrayList<String[]>();
        String NameFile1 = "Medicamentos.csv";
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

        List<String[]> data11 = new ArrayList<String[]>();
         String NameFile11 = "Leche.csv";
        //-----------------Tabla Leche------------------------------------
        UsersSQLiteHelper admine11 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db11 = admine11.getWritableDatabase();
        Cursor fila11 = db11.rawQuery("SELECT * FROM LECHE", null);
        //Se guardan los elementos de cada fila
        while (fila11.moveToNext()) {
            data11.add(new String[]{
                    fila11.getString(0),
                    fila11.getString(1),
                    fila11.getString(2),
                    fila11.getString(3)
            });
        }
        db11.close();

        //--------------- Tabla Ventas---------------------------------
        List<String[]> data1 = new ArrayList<String[]>();
        String NameFile2 = "Ventas.csv";
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
        String NameFile5 = "Propietarios.csv";
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
        String NameFile6 = "Hierro.csv";
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
//-------------Pedir permiso al celular de guardar el directorio --------------------------------------------------------------------------
        File dir =crearDirectorioPublico("Datos"); //Metodo para crer la ruta de almacenamiento del  backup

        // ----------------- Creacion del Csv Leche -------------------------------

        String filePath9 = dir.toString() + File.separator +"/"+ NameFile11;
        File f9 = new File(filePath9);
        CSVWriter writer9;
        f9.delete();
        try {
            // File exist
            if (f9.exists() && !f9.isDirectory()) {
                FileWriter mFileWriter = new FileWriter(filePath9, true);
                writer9 = new CSVWriter(mFileWriter, ';');
            } else {
                writer9 = new CSVWriter(new FileWriter(filePath9), ';');
            }

            writer9.writeAll(data);

            writer9.close();

        } catch (IOException e) {

        }

        // ----------------- Creacion del Csv Medicamentos -------------------------------

        String filePath = dir.toString() + File.separator +"/"+ NameFile1;
        File f = new File(filePath);
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

        } catch (IOException e) {

        }


        // ----------------- Creacion del Csv Ventas -------------------------------
        String filePath1 = dir.toString() + File.separator +"/"+ NameFile2;
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

        } catch (IOException e) {

        }


        // ----------------- Creacion del Csv Animales -------------------------------
        String filePath2 = dir.toString() + File.separator +"/"+ NameFile3;
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

        } catch (IOException e) {

        }


        // ----------------- Creacion del Csv Finca -------------------------------
        String filePath3 = dir.toString() + File.separator +"/"+ NameFile4;
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

        } catch (IOException e) {

        }

        // ----------------- Creacion del Csv Propietarios -------------------------------
        String filePath4 = dir.toString() + File.separator +"/"+ NameFile5;
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

        } catch (IOException e) {

        }

        // ----------------- Creacion del Csv Hierros -------------------------------
        String filePath5 = dir.toString() + File.separator +"/"+ NameFile6;
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

        } catch (IOException e) {
            Toast.makeText(this, "Error: en la configuración de la APP, active los permisos de alamacenamiento.", Toast.LENGTH_LONG).show();

        }


    }//fin del metodo crear csv
    public File crearDirectorioPublico(String nombreDirectorio) {
        //Crear directorio público en la carpeta Pictures.
        File directorio = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath(), nombreDirectorio);
        //Muestro un mensaje en el logcat si no se creo la carpeta por algun motivo
        if (!directorio.mkdirs()) System.out.println("Error: No se creo el directorio público");
        return directorio;
    }

}