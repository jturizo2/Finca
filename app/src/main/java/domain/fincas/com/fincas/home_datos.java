package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class home_datos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_datos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        //String NameFile = "Report_Equipo_Fecha: " + dateFormat.format(date).toString()+ ".csv";
        String NameFile = "Reporte_Datos"+".csv";


        //-----------------Tabla tratamientos------------------------------------
        UsersSQLiteHelper admine1 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db1 = admine1.getWritableDatabase();
        Cursor fila1 = db1.rawQuery("SELECT * FROM TRATAMIENTOS", null);
        data.add(new String[]{"Tabla de tratamientos médicos  registrados "});
        //SE GUARDAN LOS NOMBRE DE LAS COLUMNNAS
        data.add(new String[]{
                "ID",
                "CODIGO",
                "MEDICAMENTO",
                "DETALLE",
                "COSTO"
        });

        //Se gusragan los elementos de cada fila
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
        //-----------------Tabla Animales ------------------------------------
        UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db2 = admine2.getWritableDatabase();
        Cursor fila2 = db2.rawQuery("SELECT * FROM ANIMALESN", null);
        data.add(new String[]{"Tabla de animales registrados "});

        //SE GUARDAN LOS NOMBRE DE LAS COLUMNNAS
        data.add(new String[]{
                "ID",
                "CODIGO",
                "NOMBRE",
                "GENERO",
                "PROCEDENCIA",
                "RAZA",
                "FOTO",
                "PROPIETARIO",
                "PROPIETARIOA",
                "HIERRO",
                "PROPOSITO",
                "FECHAINGRE",
                "PESO",
                "PESODESTETE",
                "ETAPAP",
                "VALORC",
                "CODMAMA",
                "CODPAPA",
                "CODPARTO",
                "FECHANACI",
                "NFINCA",
                "TIPOANIMAL",
                "COMPPAR"
        });

        //Se gusragan los elementos de cada fila
        while (fila2.moveToNext()) {
            data.add(new String[]{
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
//--------------Animales vendidos.-----------------------------------------
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db4 = admine4.getWritableDatabase();
        Cursor fila4 = db4.rawQuery("SELECT * FROM VENTAS", null);
        data.add(new String[]{"Tabla de Ventas registradas "});

        //SE GUARDAN LOS NOMBRE DE LAS COLUMNNAS
        data.add(new String[]{
                "ID",
                "CODIGO",
                "PESO",
                "DETALLE",
                "COMPRADOR",
                "VALOR"
        });

        //Se gusragan los elementos de cada fila
        while (fila4.moveToNext()) {
            data.add(new String[]{
                    fila4.getString(0),
                    fila4.getString(1),
                    fila4.getString(2),
                    fila4.getString(3),
                    fila4.getString(4)
            });
        }
        db4.close();


        crear_dir();
        //Guardamos CSV-------------------------


        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/";
        String filePath = baseDir + File.separator + NameFile;
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
        //---------------------------------------
    }


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

    public void carga_datos(View view) {

    }


}
