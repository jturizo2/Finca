package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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
    String us="";
    FirebaseAuth.AuthStateListener mAut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homep);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                        us = user1.getEmail();

                        //Tareas en el inicio de sesion
                        //Descargar base de datos de la nube
                        FirebaseStorage storage = FirebaseStorage.getInstance();
                        // Create a storage reference from our app
                        StorageReference storageRef = storage.getReference();
                        //------------------------------------------------------------------------------------------
                        // Create a reference with an initial file path and name
                        String rt = us + "/Animales.csv";
                        StorageReference pathReference1 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = File.createTempFile("Da1", "cvs");
                            pathReference1.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been created
                                    //------------Carga CSV Animales ----------------------------------------
                                    try {

                                        UsersSQLiteHelper ad = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
                                        SQLiteDatabase d = ad.getWritableDatabase();
                                        File file = new File(localFile.toString());
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
                                    } catch (Exception e) {

                                    }
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
                        rt = us + "/Medicamentos.csv";
                        StorageReference pathReference2 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = File.createTempFile("Da2", "cvs");
                            pathReference2.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been created
                                    //------------Carga CSV Animales ----------------------------------------
                                    try {
                                        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
                                        SQLiteDatabase db5 = admine4.getWritableDatabase();
                                        File file = new File(localFile.toString());
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
                                    } catch (Exception e) {

                                    }
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
                        rt = us + "/Finca.csv";
                        StorageReference pathReference3 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = File.createTempFile("Da3", "cvs");
                            pathReference3.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been created
                                    //------------Carga CSV Animales ----------------------------------------
                                    try {
                                        UsersSQLiteHelper ad5 = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
                                        SQLiteDatabase d5 = ad5.getWritableDatabase();
                                        File file = new File(localFile.toString());
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
                                        Toast.makeText(homep.this, "Copia de seguridad cargada!!", Toast.LENGTH_LONG).show();

                                    } catch (Exception e) {

                                    }
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
                        rt = us + "/Leche.csv";
                        StorageReference pathReference4 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = File.createTempFile("Da4", "cvs");
                            pathReference4.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been created
                                    //------------Carga CSV Animales ----------------------------------------
                                    try {
                                        UsersSQLiteHelper admine44 = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
                                        SQLiteDatabase db55 = admine44.getWritableDatabase();
                                        File file = new File(localFile.toString());
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
                                    } catch (Exception e) {

                                    }
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
                        rt = us + "/Ventas.csv";
                        StorageReference pathReference5 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = File.createTempFile("Da5", "cvs");
                            pathReference5.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been created
                                    //------------Carga CSV Animales ----------------------------------------
                                    try {
                                        UsersSQLiteHelper ad2 = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
                                        SQLiteDatabase d2 = ad2.getWritableDatabase();
                                        File file = new File(localFile.toString());
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
                                    } catch (Exception e) {

                                    }
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
                        rt = us + "/Hierro.csv";
                        StorageReference pathReference6 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = File.createTempFile("Da6", "cvs");
                            pathReference6.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been created
                                    try {
                                        UsersSQLiteHelper ad4 = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
                                        SQLiteDatabase d4 = ad4.getWritableDatabase();
                                        File file = new File(localFile.toString());
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
                                    } catch (Exception e) {

                                    }
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
                        rt = us + "/Propietarios.csv";
                        StorageReference pathReference7 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = File.createTempFile("Da7", "cvs");
                            pathReference7.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been created
                                    try {
                                        UsersSQLiteHelper ad3 = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
                                        SQLiteDatabase d3 = ad3.getWritableDatabase();
                                        File file = new File(localFile.toString());
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
                                    } catch (Exception e) {

                                    }
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
                        rt = us + "/jo.csv";
                        StorageReference pathReference8 = storageRef.child(rt.replace(" ", ""));
                        try {
                            final File localFile = File.createTempFile("Da8", "cvs");
                            pathReference8.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    // Local temp file has been created
                                    try {
                                        UsersSQLiteHelper ad4 = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
                                        SQLiteDatabase d4 = ad4.getWritableDatabase();
                                        File file = new File(localFile.toString());
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

                                    } catch (Exception e) {

                                    }
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
                        UsersSQLiteHelper ad88 = new UsersSQLiteHelper(homep.this, "FINCAS", null, 1);
                        SQLiteDatabase db1 = ad88.getWritableDatabase();

                        ContentValues registro = new ContentValues();
                        registro.put("NEW", "1");
                        db1.insert("NNEW", null, registro);
                        db1.close();
                    } else {

                    }
                }
            }
        };

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
        //Tareas cuando cierras sesion:
        //Subir base de datos local a la nube

        //Elimicar todas las tablas

        //PAsar al Log in

        FirebaseAuth.getInstance().signOut();
        Intent choo2 = new Intent(homep.this, MainActivity.class);
        startActivity(choo2);
        finish();

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


}