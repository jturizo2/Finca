package domain.fincas.com.fincas;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
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
    ProgressBar circular;
    Button bn1,bn2,bn4,bn5,bn6,bn7;
    LinearLayout cir1;

    String us22="";
    FirebaseAuth.AuthStateListener mAut;
    FirebaseAuth mAut2;
    int ev1=0,ev2=0,ev3=0,ev4=0,ev5=0,ev6=0,ev7=0,ev8=0;
    int ev11=0,ev22=0,ev33=0,ev44=0,ev55=0,ev66=0,ev77=0,ev88=0;
    //----------- Sacamos información de las tablas----------------------------------------
    File dir =crearDirectorioPublico("Datos"); //Metodo para crer la ruta de almacenamiento del  backup
    String NameFile1 = "Medicamentos.csv";
    String NameFile8 = "jo.csv";
    String NameFile11 = "Leche.csv";
    String NameFile2 = "Ventas.csv";
    String NameFile3 = "Animales.csv";
    String NameFile4 = "Finca.csv";
    String NameFile5 = "Propietarios.csv";
    String NameFile6 = "Hierro.csv";
    String filePath = dir.toString() + File.separator +"/"+ NameFile1;
    String filePath8 = dir.toString() + File.separator +"/"+ NameFile8;
    String filePath9 = dir.toString() + File.separator +"/"+ NameFile11;
    String filePath1 = dir.toString() + File.separator +"/"+ NameFile2;
    String filePath2 = dir.toString() + File.separator +"/"+ NameFile3;
    String filePath3 = dir.toString() + File.separator +"/"+ NameFile4;
    String filePath4 = dir.toString() + File.separator +"/"+ NameFile5;
    String filePath5 = dir.toString() + File.separator +"/"+ NameFile6;
//---------------------------------------------------------------------------
     String localFile1="" ; String localFile2="" ; String localFile3="" ; String localFile4="" ;
     String localFile5="" ; String localFile6="" ; String localFile7="" ; String localFile8="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homep);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        añadirVistas();

        mAut = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user1 = firebaseAuth.getCurrentUser();
                String name2 = "";
                UsersSQLiteHelper admine2 = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
                SQLiteDatabase db2 = admine2.getWritableDatabase();
                Cursor fila1 = db2.rawQuery("SELECT NEW FROM NNEW LIMIT 1", null);
                while(fila1.moveToNext()){
                    name2=fila1.getString(0);
                }
                db2.close();
                //   Fin de la Consulta

                if("".equals(name2)) {
                    if ((user1 != null) && user1.isEmailVerified()) {
                        us22 = user1.getEmail();
                        Toast.makeText(getApplicationContext(), "Welcome "+user1.getEmail(), Toast.LENGTH_LONG).show();
                        new Asyn_load().execute();//Carga de tarea asincrona global
                        //Tareas en el inicio de sesion
                        //Descargar base de datos de la nube
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        // Create a storage reference from our app
                        StorageReference storageRef = storage.getReference();
                        //------------------------------------------------------------------------------------------
                        // Create a reference with an initial file path and name
                        String rt = us22 + "/Animales.csv";
                        StorageReference pathReference1 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = new File(dir, "Animales.csv");
                            pathReference1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been created
                                    localFile1=localFile.toString();
                                    ev11=1;
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle any errors
                                    Toast.makeText(getApplicationContext(), e.toString() + "Falla", Toast.LENGTH_LONG).show();

                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString() + "", Toast.LENGTH_LONG).show();
                        }
                        //------------------------------------------------------------------------------------------
                        rt = us22 + "/Medicamentos.csv";
                        StorageReference pathReference2 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = new File(dir, "Medicamentos.csv");
                            pathReference2.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    localFile2=localFile.toString();
                                    ev22=1;

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle any errors
                                    Toast.makeText(getApplicationContext(), e.toString() + "Falla", Toast.LENGTH_LONG).show();

                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString() + "", Toast.LENGTH_LONG).show();
                        }
                        //------------------------------------------------------------------------------------------
                        rt = us22 + "/Finca.csv";
                        StorageReference pathReference3 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = new File(dir, "Finca.csv");
                            pathReference3.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    localFile3=localFile.toString();
                                    ev33=1;

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle any errors
                                    Toast.makeText(getApplicationContext(), e.toString() + "Falla", Toast.LENGTH_LONG).show();

                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString() + "", Toast.LENGTH_LONG).show();
                        }
                        //------------------------------------------------------------------------------------------
                        rt = us22 + "/Leche.csv";
                        StorageReference pathReference4 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = new File(dir, "Leche.csv");
                            pathReference4.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    localFile4=localFile.toString();
                                    ev44=1;
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle any errors
                                    Toast.makeText(getApplicationContext(), e.toString() + "Falla", Toast.LENGTH_LONG).show();

                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString() + "", Toast.LENGTH_LONG).show();
                        }
                        //------------------------------------------------------------------------------------------
                        rt = us22 + "/Ventas.csv";
                        StorageReference pathReference5 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = new File(dir, "Ventas.csv");
                            pathReference5.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    localFile5=localFile.toString();
                                    ev55=1;
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle any errors
                                    Toast.makeText(getApplicationContext(), e.toString() + "Falla", Toast.LENGTH_LONG).show();

                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString() + "", Toast.LENGTH_LONG).show();
                        }

                        //------------------------------------------------------------------------------------------
                        rt = us22 + "/Hierro.csv";
                        StorageReference pathReference6 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = new File(dir, "Hierro.csv");
                            pathReference6.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    localFile6=localFile.toString();
                                    ev66=1;
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle any errors
                                    Toast.makeText(getApplicationContext(), e.toString() + "Falla", Toast.LENGTH_LONG).show();

                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString() + "", Toast.LENGTH_LONG).show();
                        }
                        //------------------------------------------------------------------------------------------
                        rt = us22 + "/Propietarios.csv";
                        StorageReference pathReference7 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = new File(dir, "Propietarios.csv");
                            pathReference7.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    localFile7=localFile.toString();
                                    ev77=1;
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle any errors
                                    Toast.makeText(getApplicationContext(), e.toString() + "Falla", Toast.LENGTH_LONG).show();

                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString() + "", Toast.LENGTH_LONG).show();
                        }
                        //------------------------------------------------------------------------------------------
                        rt = us22 + "/jo.csv";
                        StorageReference pathReference8 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = new File(dir, "jo.csv");
                            pathReference8.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    localFile8=localFile.toString();
                                    ev88=1;
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle any errors
                                    Toast.makeText(getApplicationContext(), e.toString() + "Falla", Toast.LENGTH_LONG).show();

                                }
                            });

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.toString() + "", Toast.LENGTH_LONG).show();
                        }
                        //Marcamos que la la aplicación a fue corrieda por primera vez
                        SQLiteDatabase db1 = admine2.getWritableDatabase();
                        ContentValues registro = new ContentValues();
                        registro.put("NEW", "1");
                        db1.insert("NNEW", null, registro);
                        db1.close();
                    } else {
                        Intent choo2 = new Intent(homep.this, MainActivity.class);
                        startActivity(choo2);
                        finish();
                    }
                }
            }
        };

    }
    private void añadirVistas(){
        bn1=(Button) findViewById(R.id.bn1);
        bn2=(Button) findViewById(R.id.bn2);
        bn4=(Button) findViewById(R.id.bn4);
        bn5=(Button) findViewById(R.id.bn5);
        bn6=(Button) findViewById(R.id.bn6);
        bn7=(Button) findViewById(R.id.bn7);
        cir1=(LinearLayout) findViewById(R.id.cir1);
        circular =(ProgressBar)findViewById(R.id.cir);
        circular.setProgress(0);
        ViewGroup.LayoutParams params = cir1.getLayoutParams();
        params.height = 0;
        cir1.setLayoutParams(params);

    }
    public  class Asyn_load extends AsyncTask <Void,Integer,Void>{
        int prog;

        @Override
        protected void onPreExecute() {
            prog=0;
            circular.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = cir1.getLayoutParams();
            params.height = 200;
            cir1.setLayoutParams(params);

            bn1.setVisibility(View.INVISIBLE);
            bn2.setVisibility(View.INVISIBLE);
            bn4.setVisibility(View.INVISIBLE);
            bn5.setVisibility(View.INVISIBLE);
            bn6.setVisibility(View.INVISIBLE);
            bn7.setVisibility(View.INVISIBLE);


        }

        @Override
        protected Void doInBackground(Void... voids) {

            while (prog<100){
                if(ev11==1 &&ev22==1 &&ev33==1 &&ev44==1 &&ev55==1 &&ev66==1 &&ev77==1&&ev88==1) {
                    prog=110;
                }
                SystemClock.sleep(100);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
           circular.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            circular.setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = cir1.getLayoutParams();
            params.height = 0;
            cir1.setLayoutParams(params);

            bn1.setVisibility(View.VISIBLE);
            bn2.setVisibility(View.VISIBLE);
            bn4.setVisibility(View.VISIBLE);
            bn5.setVisibility(View.VISIBLE);
            bn6.setVisibility(View.VISIBLE);
            bn7.setVisibility(View.VISIBLE);
            bn1.setEnabled(false);
            bn2.setEnabled(false);
            bn4.setEnabled(false);
            bn5.setEnabled(false);
            bn6.setEnabled(false);
            bn7.setEnabled(false);

            //Toast.makeText(getApplicationContext(), "Fin ", Toast.LENGTH_LONG).show();
            prube();
        }
    }

    public  class Asyn_out extends AsyncTask <Void,Integer,Void>{
        int progreso;

        @Override
        protected void onPreExecute() {
            progreso=0;
            circular.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = cir1.getLayoutParams();
            params.height = 200;
            cir1.setLayoutParams(params);

            bn1.setVisibility(View.INVISIBLE);
            bn2.setVisibility(View.INVISIBLE);
            bn4.setVisibility(View.INVISIBLE);
            bn5.setVisibility(View.INVISIBLE);
            bn6.setVisibility(View.INVISIBLE);
            bn7.setVisibility(View.INVISIBLE);

        }

        @Override
        protected Void doInBackground(Void... voids) {

            while (progreso<100){
                if(ev1==1 &&ev2==1 &&ev3==1 &&ev4==1 &&ev5==1 &&ev6==1 &&ev7==1&&ev8==1) {
                progreso=110;
                }
                    SystemClock.sleep(5);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            circular.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            circular.setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = cir1.getLayoutParams();
            params.height = 0;
            cir1.setLayoutParams(params);

            //Toast.makeText(getApplicationContext(), "Fin ", Toast.LENGTH_LONG).show();
            outEnd();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAut);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAut != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAut);
        }
    }

    @Override
    public void onBackPressed() {

    }
    public void SI(){

//Ruta de carga fija - Hacerla dinamica

        String medicamentos  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Medicamentos.csv";
        String animales  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Animales.csv";
        String finca  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Finca.csv";
        String hierro  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Hierro.csv";
        String propietarios  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Propietarios.csv";
        String ventas  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Ventas.csv";
        String leche  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/Leche.csv";
        String jornal  = android.os.Environment.getExternalStorageDirectory().getAbsolutePath()+"/Datos/jo.csv";

        try{
        //------------Carga CSV Leche ----------------------------------------


        //------------Carga CSV Tratamientos ----------------------------------------


        //------------Carga CSV Animales ----------------------------------------


        //------------Carga CSV Ventas ----------------------------------------

        //------------Carga CSV Propietarios ----------------------------------------


        //------------Carga CSV Hierro ----------------------------------------


            //------------Carga CSV jornales ----------------------------------------

        //------------Carga CSV NFINCAS ----------------------------------------

            //Marcamos que la la aplicación a fue corrieda por primera vez
            UsersSQLiteHelper ad88 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db1 = ad88.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("NEW", "1");
            db1.insert("NNEW", null, registro);
            db1.close();
        }catch (Exception e){
            Toast.makeText(this,"La carpeta \"Datos\" de la copia de seguridad no fue encontrada, por favor asegurar que este en la ruta indicada y abra la aplicación de nuevo", Toast.LENGTH_LONG).show();
            Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show();

        }


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
        //finish();
    }
    public void consultas(View view) {
        Intent i = new Intent(homep.this, home_consultas.class);
        startActivity(i);
        //finish();

    }

    public void ingreso_info(View view) {
        Intent i = new Intent(homep.this, botones_ingreso_info.class);
        startActivity(i);
        //finish();

    }

    public void backup(View view) {
        Intent i = new Intent(homep.this, home_datos.class);
        startActivity(i);
        //finish();

    }

    public void cerrar(View view) {
        String user =FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();

        if(ev1==0 && ev2==0 && ev3==0 && ev4==0 && ev5==0 && ev6==0 && ev7==0 && ev8==0) {
        //Tareas cuando cierras sesion:
        //Subir base de datos local a la nube
        //-----------Actualizamos los archivos locales----------------------------------------
        //-----------------Tabla tratamientos------------------------------------
        List<String[]> data = new ArrayList<String[]>();
        UsersSQLiteHelper admine1 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db = admine1.getWritableDatabase();
        Cursor fila = db.rawQuery("SELECT ID,CODIGO, MEDICAMENTO, DETALLE, COSTO,FECHA FROM TRATAMIENTOS", null);
        //Se guardan los elementos de cada fila
        while (fila.moveToNext()) {
            data.add(new String[]{
                    fila.getString(0), fila.getString(1),fila.getString(2),fila.getString(3),
                    fila.getString(4), fila.getString(5)});
        }
        db.close();
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
            Toast.makeText(this, ""+e.toString(), Toast.LENGTH_LONG).show();

        }
        //-----------------Tabla HORNALES------------------------------------
        data = new ArrayList<String[]>();
        db = admine1.getWritableDatabase();
        Cursor fila1 = db.rawQuery("SELECT ID, FECHA, TRABAJO,CANTJORNAL, VALORJORNAL, TOTAL FROM HORNAL", null);
        //Se guardan los elementos de cada fila
        while (fila1.moveToNext()) {
            data.add(new String[]{
                    fila1.getString(0),
                    fila1.getString(1),
                    fila1.getString(2),
                    fila1.getString(3),
                    fila1.getString(4),
                    fila1.getString(5)
            });
        }
        db.close();
        f = new File(filePath8);
        CSVWriter writer5;
        f.delete();
        try {
            // File exist
            if (f.exists() && !f.isDirectory()) {
                FileWriter mFileWriter = new FileWriter(filePath8, true);
                writer5 = new CSVWriter(mFileWriter, ';');
            } else {
                writer5 = new CSVWriter(new FileWriter(filePath8), ';');
            }
            writer5.writeAll(data);
            writer5.close();
        } catch (IOException e) {
        }
        //-----------------Tabla Leche-----------------------------------
        data = new ArrayList<String[]>();
            db = admine1.getWritableDatabase();
            Cursor fila2 = db.rawQuery("SELECT * FROM LECHE", null);
        //Se guardan los elementos de cada fila
        while (fila2.moveToNext()) {
            data.add(new String[]{
                    fila2.getString(0),
                    fila2.getString(1),
                    fila2.getString(2),
                    fila2.getString(3)
            });
        }
        db.close();
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
        //--------------- Tabla Ventas---------------------------------
        data = new ArrayList<String[]>();
        db = admine1.getWritableDatabase();
        Cursor fila3 = db.rawQuery("SELECT * FROM VENTAS", null);
        //Se guardaran los elementos de cada fila
        while (fila3.moveToNext()) {
            data.add(new String[]{
                    fila3.getString(0),
                    fila3.getString(1),
                    fila3.getString(2),
                    fila3.getString(3),
                    fila3.getString(4),
                    fila3.getString(5),
                    fila3.getString(6),
                    fila3.getString(7),
                    fila3.getString(8)
            });
        }
        db.close();
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
            writer1.writeAll(data);
            writer1.close();
        } catch (IOException e) {
        }
        //-----------------Tabla Animales ------------------------------------
        data = new ArrayList<String[]>();
            db = admine1.getWritableDatabase();
        Cursor fila4 = db.rawQuery("SELECT * FROM ANIMALESN", null);
        //Se guardaran los elementos de cada fila
        while (fila4.moveToNext()) {
            data.add(new String[]{
                    fila4.getString(0),
                    fila4.getString(1),
                    fila4.getString(2),
                    fila4.getString(3),
                    fila4.getString(4),
                    fila4.getString(5),
                    fila4.getString(6),
                    fila4.getString(7),
                    fila4.getString(8),
                    fila4.getString(9),
                    fila4.getString(10),
                    fila4.getString(11),
                    fila4.getString(12),
                    fila4.getString(13),
                    fila4.getString(14),
                    fila4.getString(15),
                    fila4.getString(16),
                    fila4.getString(17),
                    fila4.getString(18),
                    fila4.getString(19),
                    fila4.getString(20),
                    fila4.getString(21),
                    fila4.getString(22)
            });
        }
        db.close();
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
            writer2.writeAll(data);
            writer2.close();
        } catch (IOException e) {
        }
        //--------------- Tabla Fincas-------------------------------
        data = new ArrayList<String[]>();
            db = admine1.getWritableDatabase();

            Cursor fila5 = db.rawQuery("SELECT * FROM NFINCAS", null);
        //Se guardaran los elementos de cada fila
        while (fila5.moveToNext()) {
            data.add(new String[]{
                    fila5.getString(0),
                    fila5.getString(1),
                    fila5.getString(2),
                    fila5.getString(3),
                    fila5.getString(4),
                    fila5.getString(5)
            });
        }
        db.close();

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
            writer3.writeAll(data);
            writer3.close();
        } catch (IOException e) {
        }
        //--------------- Tabla Propietarios---------------------------------
        data = new ArrayList<String[]>();
            db = admine1.getWritableDatabase();
            Cursor fila6 = db.rawQuery("SELECT * FROM PROPIETARIOS", null);
        //Se guardaran los elementos de cada fila
        while (fila6.moveToNext()) {
            data.add(new String[]{fila6.getString(0),
                    fila6.getString(1),
                    fila6.getString(2)
            });
        }
        db.close();
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
            writer4.writeAll(data);
            writer4.close();
        } catch (IOException e) {
            Toast.makeText(this, e+"Error: "+e.toString(), Toast.LENGTH_LONG).show();
        }
        //--------------- Tabla Hierros---------------------------------
        data = new ArrayList<String[]>();
            db = admine1.getWritableDatabase();

            Cursor fila7 = db.rawQuery("SELECT * FROM THIERRO", null);
        //Se guardaran los elementos de cada fila
        while (fila7.moveToNext()) {
            data.add(new String[]{
                    fila7.getString(0),
                    fila7.getString(1)
            });
        }
        db.close();

        File f5 = new File(filePath5);
        CSVWriter writ;
        f5.delete();
        try {
            // File exist
            if (f5.exists() && !f5.isDirectory()) {
                FileWriter mFileWriter = new FileWriter(filePath5, true);
                writ = new CSVWriter(mFileWriter, ';');
            } else {
                writ = new CSVWriter(new FileWriter(filePath5), ';');
            }

            writ.writeAll(data);

            writ.close();
            Toast.makeText(this, "Copia  de seguridad guardada en la carpeta Datos.", Toast.LENGTH_LONG).show();


        } catch (IOException e) {
            Toast.makeText(this, "Error: en la configuración de la APP, active los permisos de alamacenamiento.", Toast.LENGTH_LONG).show();

        }

        }
        //------------------------------------------------------------------------------------------------
        new Asyn_out().execute();

        //Guardar en la nube la copia de seguridad
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        //Hierro
        Uri file = Uri.fromFile(new File(filePath));
        StorageReference riversRef =  storageRef.child(user+"/"+file.getLastPathSegment());
        UploadTask uploadTask1 = riversRef.putFile(file);
        //Medicamentos

        file = Uri.fromFile(new File(filePath8));
        riversRef =  storageRef.child(user+"/"+file.getLastPathSegment());
        UploadTask uploadTask2 = riversRef.putFile(file);


        file = Uri.fromFile(new File(filePath9));
        riversRef =  storageRef.child(user+"/"+file.getLastPathSegment());
        UploadTask uploadTask3 = riversRef.putFile(file);

        file = Uri.fromFile(new File(filePath1));
        riversRef =  storageRef.child(user+"/"+file.getLastPathSegment());
        UploadTask  uploadTask4 = riversRef.putFile(file);

        file = Uri.fromFile(new File(filePath2));
        riversRef =  storageRef.child(user+"/"+file.getLastPathSegment());
        UploadTask  uploadTask5 = riversRef.putFile(file);

        file = Uri.fromFile(new File(filePath3));
        riversRef =  storageRef.child(user+"/"+file.getLastPathSegment());
        UploadTask  uploadTask6 = riversRef.putFile(file);

        file = Uri.fromFile(new File(filePath4));
        riversRef =  storageRef.child(user+"/"+file.getLastPathSegment());
        UploadTask  uploadTask7 = riversRef.putFile(file);

        file = Uri.fromFile(new File(filePath5));
        riversRef =  storageRef.child(user+"/"+file.getLastPathSegment());
        UploadTask  uploadTask8 = riversRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
        uploadTask1.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //Toast.makeText(homep.this, "Error!." + exception.toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                //Marcamos que la la aplicación a fue corrieda por primera vez
                ev1 = 1;
                //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

            }
        });

        uploadTask2.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //Toast.makeText(homep.this, "Error!¡?." + exception.toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                ev2 = 1;
                //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

            }
        });
        uploadTask3.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
               // Toast.makeText(homep.this, "Error!." + exception.toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                ev3 = 1;
                //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

            }
        });
        uploadTask4.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
             //   Toast.makeText(homep.this, "Error!." + exception.toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                ev4 = 1;
                /// /Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

            }
        });
        uploadTask5.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
           //     Toast.makeText(homep.this, "Error!." + exception.toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                ev5 = 1;
                //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

            }
        });
        uploadTask6.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //Toast.makeText(homep.this, "Error!." + exception.toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                ev6 = 1;
                //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

            }
        });
        uploadTask7.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
               // Toast.makeText(homep.this, "Error!." + exception.toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                ev7 = 1;
                //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

            }
        });
        uploadTask8.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                //Toast.makeText(homep.this, "Error!." + exception.toString(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                ev8 = 1;
                //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

            }
        });

    }

    public void outEnd() {
            FirebaseAuth.getInstance().signOut();
            UsersSQLiteHelper ad = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
            SQLiteDatabase db = ad.getWritableDatabase();
            db.execSQL("DELETE FROM NFINCAS");
            db.execSQL("DELETE FROM TRATAMIENTOS");
            db.execSQL("DELETE FROM HORNAL");
            db.execSQL("DELETE FROM LECHE");
            db.execSQL("DELETE FROM VENTAS");
            db.execSQL("DELETE FROM ANIMALESN");
            db.execSQL("DELETE FROM PROPIETARIOS");
            db.execSQL("DELETE FROM THIERRO");
            db.execSQL("DELETE FROM NNEW");
            db.close();

            File f4 = new File(filePath);f4.delete();
            f4 = new File(filePath8);f4.delete();
            f4 = new File(filePath9);f4.delete();
            f4 = new File(filePath1);f4.delete();
            f4 = new File(filePath2);f4.delete();
            f4 = new File(filePath3);f4.delete();
            f4 = new File(filePath4);f4.delete();
            f4 = new File(filePath5);f4.delete();
            Intent choo2 = new Intent(homep.this, MainActivity.class);
            startActivity(choo2);

    }
    public void reporte(View view) {

        List<String[]> data = new ArrayList<String[]>();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
        Date date = new Date();


        //-----------------Tabla tratamientos------------------------------------
        UsersSQLiteHelper admine1 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db1 = admine1.getWritableDatabase();
        Cursor fila1 = db1.rawQuery("SELECT * FROM TRATAMIENTOS", null);
        data.add(new String[]{"Tabla de tratamientos medicos registrados "});
        //SE GUARDAN LOS NOMBRE DE LAS COLUMNNAS
        data.add(new String[]{
                "ID",
                "CODIGO",
                "MEDICAMENTO",
                "DETALLE",
                "COSTO",
                "FECHA"
        });

        //Se gusragan los elementos de cada fila
        while (fila1.moveToNext()) {
            data.add(new String[]{
                    fila1.getString(0),
                    fila1.getString(1),
                    fila1.getString(2),
                    fila1.getString(3),
                    fila1.getString(4),
                    fila1.getString(5)
            });
        }
        db1.close();
        //-----------------Tabla Animales ------------------------------------
        UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db2 = admine2.getWritableDatabase();
        Cursor fila2 = db2.rawQuery("SELECT * FROM ANIMALESN", null);
        data.add(new String[]{"Tabla de Animales Registrados "});

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
        data.add(new String[]{"Tabla de Ventas Registradas "});

        //SE GUARDAN LOS NOMBRE DE LAS COLUMNNAS
        data.add(new String[]{
                "ID",
                "CODIGO",
                "PESO",
                "DETALLE",
                "COMPRADOR",
                "VALOR",
                "FECHAV",
                "GENERO",
                "ETAPAP"
        });

        //Se gusragan los elementos de cada fila
        while (fila4.moveToNext()) {
            data.add(new String[]{
                    fila4.getString(0),
                    fila4.getString(1),
                    fila4.getString(2),
                    fila4.getString(3),
                    fila4.getString(4),
                    fila4.getString(5),
                    fila4.getString(6),
                    fila4.getString(7),
                    fila4.getString(8)
            });
        }
        db4.close();

        //--------------Tabla Leche.-----------------------------------------
        UsersSQLiteHelper admine5 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db3 = admine5.getWritableDatabase();
        Cursor fila5 = db3.rawQuery("SELECT * FROM LECHE", null);
        data.add(new String[]{"Registros de leches de Vacas "});

        //SE GUARDAN LOS NOMBRE DE LAS COLUMNNAS
        data.add(new String[]{
                "ID",
                "CODIGO",
                "LITROS",
                "FECHA"

        });

        //Se gusragan los elementos de cada fila
        while (fila5.moveToNext()) {
            data.add(new String[]{
                    fila5.getString(0),
                    fila5.getString(1),
                    fila5.getString(2),
                    fila5.getString(3)

            });
        }
        db3.close();

        //-----------------Tabla HORNALES------------------------------------
        data.add(new String[]{"Trabajos rejistrados "});

        //SE GUARDAN LOS NOMBRE DE LAS COLUMNNAS
        data.add(new String[]{
                "ID",
                "FECHA",
                "TRABAJO",
                "CANTJORNAL",
                "VALORJORNAL",
                "TOTAL"
        });
        admine1 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        db1 = admine1.getWritableDatabase();
        fila1 = db1.rawQuery("SELECT ID, FECHA, TRABAJO,CANTJORNAL, VALORJORNAL, TOTAL FROM HORNAL", null);
        //Se guardan los elementos de cada fila
        while (fila1.moveToNext()) {
            data.add(new String[]{
                    fila1.getString(0),
                    fila1.getString(1),
                    fila1.getString(2),
                    fila1.getString(3),
                    fila1.getString(4),
                    fila1.getString(5)

            });
        }
        db1.close();

        //Guardamos CSV-------------------------
        String NameFile = "Reporte.csv";
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String filePath = baseDir+ "/"+NameFile;
        File f = new File(filePath);
        f.delete();
        CSVWriter writer;

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
            Toast.makeText(this,"Reporte almacenado en la memoria del dispositivo.", Toast.LENGTH_LONG).show();

        }catch (IOException e) {
            Toast.makeText(this,"Error, intente mas tarde."+e.toString(), Toast.LENGTH_LONG).show();

        }


    }
    public File crearDirectorioPublico(String nombreDirectorio) {
        //Crear directorio público en la carpeta Pictures.
        File directorio = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath(), nombreDirectorio);
        //Muestro un mensaje en el logcat si no se creo la carpeta por algun motivo
        if (!directorio.mkdirs()) System.out.println("Error: No se creo el directorio público");
        return directorio;
    }
    public void prube () {
        UsersSQLiteHelper admi = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);

        if(!localFile1.equals("")&&!localFile2.equals("")&&!localFile3.equals("")&&!localFile4.equals("")&& !localFile5.equals("")&&!localFile6.equals("")&&!localFile7.equals("")&&!localFile8.equals("")) {
            //------------Carga CSV Animales ----------------------------------------
            try {
                //Toast.makeText(homep.this, "Entré", Toast.LENGTH_LONG).show();

                SQLiteDatabase d = admi.getWritableDatabase();
                File file = new File(filePath2);
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                fis.getChannel().position(0);
                String str2 = ");";

                BufferedReader bRead = new BufferedReader(new InputStreamReader(fis));
                String line = "";
                String tableName = "ANIMALESN";
                String columns = "CODIGO,NOMBRE,GENERO,PROCEDENCIA,RAZA,FOTO,PROPIETARIO,PROPIETARIOA,HIERRO,PROPOSITO,FECHAINGRE,PESO,PESODESTETE,ETAPAP,VALORC,CODMAMA,CODPAPA,CODPARTO,FECHANACI,NFINCA,TIPOANIMAL,COMPPAR";
                String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
                d.beginTransaction();
                while ((line = bRead.readLine()) != null) {
                    line = line.replace("\"", "");
                    System.out.println(":ANIMALES:  " + line + "  jaja");
                    StringBuilder sb2 = new StringBuilder(str1);
                    String[] str12 = line.split(";");
                    sb2.append("'" + str12[1] + "',");
                    sb2.append("'" + str12[2] + "',");
                    sb2.append("'" + str12[3] + "',");
                    sb2.append("'" + str12[4] + "',");
                    sb2.append("'" + str12[5] + "',");
                    sb2.append("'" + str12[6] + "',");
                    sb2.append("'" + str12[7] + "',");
                    sb2.append("'" + str12[8] + "',");
                    sb2.append("'" + str12[9] + "',");
                    sb2.append("'" + str12[10] + "',");
                    sb2.append("'" + str12[11] + "',");
                    sb2.append("'" + str12[12] + "',");
                    sb2.append("'" + str12[13] + "',");
                    sb2.append("'" + str12[14] + "',");
                    sb2.append("'" + str12[15] + "',");
                    sb2.append("'" + str12[16] + "',");
                    sb2.append("'" + str12[17] + "',");
                    sb2.append("'" + str12[18] + "',");
                    sb2.append("'" + str12[19] + "',");
                    sb2.append("'" + str12[20] + "',");
                    sb2.append("'" + str12[21] + "',");
                    sb2.append("'" + str12[22] + "'");

                    sb2.append(str2);
                    d.execSQL(sb2.toString());

                }
                d.setTransactionSuccessful();
                d.endTransaction();
                d.close();

            } catch (Exception e) {
                Toast.makeText(homep.this, "Entré1", Toast.LENGTH_LONG).show();

            }
            // Local temp file has been created
            //------------Carga CSV Animales ----------------------------------------
            try {
                SQLiteDatabase db5 = admi.getWritableDatabase();
                File file = new File(filePath);
                FileInputStream fis = null;

                fis = new FileInputStream(file);
                fis.getChannel().position(0);
                BufferedReader bRead = new BufferedReader(new InputStreamReader(fis));
                String line = "";
                String tableName = "TRATAMIENTOS";
                String columns = "CODIGO, MEDICAMENTO, DETALLE, COSTO,FECHA";
                String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
                String str2 = ");";
                db5.beginTransaction();
                while ((line = bRead.readLine()) != null) {
                    line = line.replace("\"", "");
                    System.out.println(":TRATA:  " + line + "  jaja");
                    StringBuilder sb = new StringBuilder(str1);
                    String[] str = line.split(";");
                    sb.append("'" + str[1] + "',");
                    sb.append("'" + str[2] + "',");
                    sb.append("'" + str[3] + "',");
                    sb.append("'" + str[4] + "',");
                    sb.append("'" + str[5] + "'");

                    sb.append(str2);
                    db5.execSQL(sb.toString());
                }
                db5.setTransactionSuccessful();
                db5.endTransaction();
                db5.close();
                //Baja los datos a la carpeta datos

            } catch (Exception e) {
                Toast.makeText(homep.this, "Entré2", Toast.LENGTH_LONG).show();

            }
            // Local temp file has been created
            //------------Carga CSV Animales ----------------------------------------
            try {

                SQLiteDatabase d5 = admi.getWritableDatabase();
                File file = new File(filePath3);
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                fis.getChannel().position(0);
                BufferedReader bRead = new BufferedReader(new InputStreamReader(fis));
                String line = "";
                String tableName = "NFINCAS";
                String columns = "CODIGO, NOMBREF,HECTAREAS,DIVICIONES,LOTES";
                String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
                String str2 = ");";
                d5.beginTransaction();
                while ((line = bRead.readLine()) != null) {
                    line = line.replace("\"", "");
                    System.out.println(":finca:  " + line + "  jaja");
                    StringBuilder sb2 = new StringBuilder(str1);
                    String[] str12 = line.split(";");
                    sb2.append("'" + str12[1] + "',");
                    sb2.append("'" + str12[2] + "',");
                    sb2.append("'" + str12[3] + "',");
                    sb2.append("'" + str12[4] + "',");
                    sb2.append("'" + str12[5] + "'");
                    sb2.append(str2);
                    d5.execSQL(sb2.toString());
                }
                d5.setTransactionSuccessful();
                d5.endTransaction();
                d5.close();
//                Toast.makeText(homep.this, "Copia de seguridad cargada!!", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(homep.this, "Entré3", Toast.LENGTH_LONG).show();

            }
            // Local temp file has been created
            //------------Carga CSV Animales ----------------------------------------
            try {
                SQLiteDatabase db55 = admi.getWritableDatabase();
                File file = new File(filePath9);
                FileInputStream fis = null;

                fis = new FileInputStream(file);
                fis.getChannel().position(0);
                BufferedReader bRead = new BufferedReader(new InputStreamReader(fis));
                String line = "";
                String tableName = "LECHE";
                String columns = "COD, LITROS, FECHA";
                String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
                String str2 = ");";
                db55.beginTransaction();
                while ((line = bRead.readLine()) != null) {
                    line = line.replace("\"", "");
                    System.out.println(":LECHE:  " + line + "  jaja");
                    StringBuilder sb = new StringBuilder(str1);
                    String[] str = line.split(";");
                    sb.append("'" + str[1] + "',");
                    sb.append("'" + str[2] + "',");
                    sb.append("'" + str[3] + "'");
                    sb.append(str2);
                    db55.execSQL(sb.toString());
                }
                db55.setTransactionSuccessful();
                db55.endTransaction();
                db55.close();

            } catch (Exception e) {
                Toast.makeText(homep.this, "Entré4", Toast.LENGTH_LONG).show();

            }
            // Local temp file has been created
            //------------Carga CSV Animales ----------------------------------------
            try {
                SQLiteDatabase d2 = admi.getWritableDatabase();
                File file = new File(filePath1);
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                fis.getChannel().position(0);
                BufferedReader bRead = new BufferedReader(new InputStreamReader(fis));
                String line = "";
                String tableName = "VENTAS";
                String columns = "CODIGO, PESO, DETALLE, COMPRADOR, VALOR, FECHAV, GENERO, ETAPAP";
                String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
                String str2 = ");";
                d2.beginTransaction();
                while ((line = bRead.readLine()) != null) {
                    line = line.replace("\"", "");
                    System.out.println(":VENTAS:  " + line + "  jaja");
                    StringBuilder sb2 = new StringBuilder(str1);
                    String[] str12 = line.split(";");
                    sb2.append("'" + str12[1] + "',");
                    sb2.append("'" + str12[2] + "',");
                    sb2.append("'" + str12[3] + "',");
                    sb2.append("'" + str12[4] + "',");
                    sb2.append("'" + str12[5] + "',");
                    sb2.append("'" + str12[6] + "',");
                    sb2.append("'" + str12[7] + "',");
                    sb2.append("'" + str12[8] + "'");
                    sb2.append(str2);
                    d2.execSQL(sb2.toString());

                }
                d2.setTransactionSuccessful();
                d2.endTransaction();
                d2.close();

            } catch (Exception e) {
                Toast.makeText(homep.this, "Entré5", Toast.LENGTH_LONG).show();

            }

            // Local temp file has been created
            try {
                SQLiteDatabase d4 = admi.getWritableDatabase();
                File file = new File(filePath5);
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                fis.getChannel().position(0);
                BufferedReader bRead = new BufferedReader(new InputStreamReader(fis));
                String line = "";
                String tableName = "THIERRO";
                String columns = "HIERRO";
                String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
                String str2 = ");";
                d4.beginTransaction();
                while ((line = bRead.readLine()) != null) {
                    line = line.replace("\"", "");
                    System.out.println(":HIERRO:  " + line + "  jaja");
                    StringBuilder sb2 = new StringBuilder(str1);
                    String[] str12 = line.split(";");
                    sb2.append("'" + str12[1] + "'");
                    sb2.append(str2);
                    System.out.println(sb2.toString() + "  jaja");
                    d4.execSQL(sb2.toString());
                }
                d4.setTransactionSuccessful();
                d4.endTransaction();
                d4.close();

            } catch (Exception e) {
                Toast.makeText(homep.this, "Entré6", Toast.LENGTH_LONG).show();
            }

            // Local temp file has been created
            try {
                SQLiteDatabase d3 = admi.getWritableDatabase();
                File file = new File(filePath4);
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                fis.getChannel().position(0);
                BufferedReader bRead = new BufferedReader(new InputStreamReader(fis));
                String line = "";
                String tableName = "PROPIETARIOS";
                String columns = "NOMBRE, APELLIDO";
                String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
                String str2 = ");";
                d3.beginTransaction();
                while ((line = bRead.readLine()) != null) {
                    line = line.replace("\"", "");
                    System.out.println(":propi:  " + line + "  jaja");
                    StringBuilder sb2 = new StringBuilder(str1);
                    String[] str12 = line.split(";");
                    sb2.append("'" + str12[1] + "',");
                    sb2.append("'" + str12[2] + "'");
                    sb2.append(str2);
                    d3.execSQL(sb2.toString());
                }
                d3.setTransactionSuccessful();
                d3.endTransaction();
                d3.close();

            } catch (Exception e) {
                Toast.makeText(homep.this, "Entré7", Toast.LENGTH_LONG).show();
            }
            // Local temp file has been created
            try {
                SQLiteDatabase d4 = admi.getWritableDatabase();
                File file = new File(filePath8);
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                fis.getChannel().position(0);
                BufferedReader bRead = new BufferedReader(new InputStreamReader(fis));
                String line = "";
                String tableName = "HORNAL";
                String columns = "FECHA, TRABAJO,CANTJORNAL, VALORJORNAL, TOTAL";
                String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
                String str2 = ");";
                d4.beginTransaction();
                while ((line = bRead.readLine()) != null) {
                    line = line.replace("\"", "");
                    System.out.println(":JORNAL:  " + line + "  jaja");
                    StringBuilder sb2 = new StringBuilder(str1);
                    String[] str12 = line.split(";");
                    sb2.append("'" + str12[1] + "',");
                    sb2.append("'" + str12[2] + "',");
                    sb2.append("'" + str12[3] + "',");
                    sb2.append("'" + str12[4] + "',");
                    sb2.append("'" + str12[5] + "'");
                    sb2.append(str2);
                    System.out.println(sb2.toString() + "  jaja");
                    d4.execSQL(sb2.toString());
                }
                d4.setTransactionSuccessful();
                d4.endTransaction();
                d4.close();
            } catch (Exception e) {
                Toast.makeText(homep.this, "Entré81", Toast.LENGTH_LONG).show();
            }

            bn1.setEnabled(true);
            bn2.setEnabled(true);
            bn4.setEnabled(true);
            bn5.setEnabled(true);
            bn6.setEnabled(true);
            bn7.setEnabled(true);

        }
    }
}