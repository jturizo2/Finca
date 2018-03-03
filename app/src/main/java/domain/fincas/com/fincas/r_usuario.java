package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static domain.fincas.com.fincas.R.id.button5;

public class r_usuario extends AppCompatActivity {

    EditText nombre, apellido, usuario, contras, rcontras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_usuario);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }

        nombre = (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.apellido);
        usuario = (EditText) findViewById(R.id.usuario);
        contras = (EditText) findViewById(R.id.contras);
        rcontras = (EditText) findViewById(R.id.rcontras);



    }

    public void save(View view) {
        if ("".equals(nombre.getText().toString())) {
            Toast.makeText(this, "Escriba su nombre!!", Toast.LENGTH_LONG).show();
        } else {
            if ("".equals(apellido.getText().toString())) {
                Toast.makeText(this, "Escriba su apellido!!", Toast.LENGTH_LONG).show();
            } else {
                if ("".equals(usuario.getText().toString())) {
                    Toast.makeText(this, "Escriba su usuario!!", Toast.LENGTH_LONG).show();
                } else {
                    if ("".equals(contras.getText().toString())) {
                        Toast.makeText(this, "Escriba su contraseña!!", Toast.LENGTH_LONG).show();
                    } else {
                        if ("".equals(rcontras.getText().toString())) {
                            Toast.makeText(this, "Confirme su contraseña!!", Toast.LENGTH_LONG).show();
                        } else {
                            if (contras.getText().toString().equals(rcontras.getText().toString())) {
                                if(contras.getText().toString().length()>6){
                                    String mail = usuario.getText().toString();
                                    String pass = contras.getText().toString();
                                    registar(mail,pass);
                                    Toast.makeText(this, "Espere el mensaje de confirmación!!", Toast.LENGTH_LONG).show();

                                }else{

                                    Toast.makeText(this, "La contraseña debe tener mas de 6 caracteres!", Toast.LENGTH_LONG).show();

                                }

                            } else {
                                Toast.makeText(this, "No coincide la contraseña!!", Toast.LENGTH_LONG).show();

                            }
                        }
                    }


                }


            }
        }

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(r_usuario.this, MainActivity.class);
        startActivity(i);
        finish();
    }
    public File crearDirectorioPublico(String nombreDirectorio) {
        //Crear directorio público en la carpeta Pictures.
        File directorio = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath(), nombreDirectorio);
        //Muestro un mensaje en el logcat si no se creo la carpeta por algun motivo
        if (!directorio.mkdirs()) System.out.println("Error: No se creo el directorio público");
        return directorio;
    }
private void registar(String email,String pass){
    final String mail=email ;
    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            if(task.isSuccessful()){
                Toast.makeText(r_usuario.this, "Verifique la cuenta con su correo electrónico!.", Toast.LENGTH_LONG).show();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.sendEmailVerification();
                //Crear carpetas nube
                File dir =crearDirectorioPublico("Datos"); //Metodo para crer la ruta de almacenamiento del  backup
                String medicamentos = "Medicamentos.csv";
                String jornales = "jo.csv";
                String leche = "Leche.csv";
                String ventas = "Ventas.csv";
                String animales = "Animales.csv";
                String finca = "Finca.csv";
                String propi = "Propietarios.csv";
                String hierro = "Hierro.csv";
                String medicamentos2 = dir.toString() + File.separator +"/"+ medicamentos;
                String jornales2 = dir.toString() + File.separator +"/"+ jornales;
                String leche2 = dir.toString() + File.separator +"/"+ leche;
                String ventas2 = dir.toString() + File.separator +"/"+ ventas;
                String animales2 = dir.toString() + File.separator +"/"+ animales;
                String finca2 = dir.toString() + File.separator +"/"+ finca;
                String propi2 = dir.toString() + File.separator +"/"+ propi;
                String hierro2 = dir.toString() + File.separator +"/"+ hierro;
                //Idemtificamos usuario iniciado

                //-----------------Tabla tratamientos------------------------------------
                List<String[]> data = new ArrayList<String[]>();
                data.add(new String[]{" "," "," "," "," "," "});
                File f = new File(medicamentos2);
                CSVWriter writer;
                f.delete();
                try {
                    // File exist
                    if (f.exists() && !f.isDirectory()) {
                        FileWriter mFileWriter = new FileWriter(medicamentos2, true);
                        writer = new CSVWriter(mFileWriter, ';');
                    } else {
                        writer = new CSVWriter(new FileWriter(medicamentos2), ';');
                    }

                    writer.writeAll(data);

                    writer.close();

                } catch (IOException e) {

                }

                //-----------------Tabla HORNALES------------------------------------
                data = new ArrayList<String[]>();
                data.add(new String[]{" "," "," "," "," "," "});
                f = new File(jornales2);
                CSVWriter writer5;
                f.delete();
                try {
                    // File exist
                    if (f.exists() && !f.isDirectory()) {
                        FileWriter mFileWriter = new FileWriter(jornales2, true);
                        writer5 = new CSVWriter(mFileWriter, ';');
                    } else {
                        writer5 = new CSVWriter(new FileWriter(jornales2), ';');
                    }

                    writer5.writeAll(data);

                    writer5.close();

                } catch (IOException e) {

                }

                //-----------------Tabla Leche------------------------------------
                data = new ArrayList<String[]>();
                data.add(new String[]{" "," "," "," "});
                File f9 = new File(leche2);
                CSVWriter writer9;
                f9.delete();
                try {
                    // File exist
                    if (f9.exists() && !f9.isDirectory()) {
                        FileWriter mFileWriter = new FileWriter(leche2, true);
                        writer9 = new CSVWriter(mFileWriter, ';');
                    } else {
                        writer9 = new CSVWriter(new FileWriter(leche2), ';');
                    }

                    writer9.writeAll(data);

                    writer9.close();

                } catch (IOException e) {

                }
                //--------------- Tabla Ventas---------------------------------
                data = new ArrayList<String[]>();
                data.add(new String[]{" "," "," "," "," "," "," "," "," "});
                File f1 = new File(ventas2);

                CSVWriter writer1;
                f1.delete();
                try {
                    // File exist
                    if (f1.exists() && !f1.isDirectory()) {
                        FileWriter mFileWriter = new FileWriter(ventas2, true);
                        writer1 = new CSVWriter(mFileWriter, ';');
                    } else {
                        writer1 = new CSVWriter(new FileWriter(ventas2), ';');
                    }

                    writer1.writeAll(data);

                    writer1.close();

                } catch (IOException e) {

                }
                //-----------------Tabla Animales ------------------------------------
                data = new ArrayList<String[]>();
                data.add(new String[]{" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "});
                File f2 = new File(animales2);
                CSVWriter writer2;
                f2.delete();
                try {
                    // File exist
                    if (f2.exists() && !f2.isDirectory()) {
                        FileWriter mFileWriter = new FileWriter(animales2, true);
                        writer2 = new CSVWriter(mFileWriter, ';');
                    } else {
                        writer2 = new CSVWriter(new FileWriter(animales2), ';');
                    }

                    writer2.writeAll(data);

                    writer2.close();

                } catch (IOException e) {

                }

                //--------------- Tabla Fincas---------------------------------
                data = new ArrayList<String[]>();
                data.add(new String[]{" "," "," "," "," "," "});
                File f3 = new File(finca2);
                CSVWriter writer3;
                f3.delete();
                try {
                    // File exist
                    if (f3.exists() && !f3.isDirectory()) {
                        FileWriter mFileWriter = new FileWriter(finca2, true);
                        writer3 = new CSVWriter(mFileWriter, ';');
                    } else {
                        writer3 = new CSVWriter(new FileWriter(finca2), ';');
                    }

                    writer3.writeAll(data);

                    writer3.close();

                } catch (IOException e) {

                }
                //--------------- Tabla Propietarios---------------------------------
                data = new ArrayList<String[]>();
                data.add(new String[]{" "," "," "});
                File f4 = new File(propi2);
                CSVWriter writer4;
                f4.delete();
                try {
                    // File exist
                    if (f4.exists() && !f4.isDirectory()) {
                        FileWriter mFileWriter = new FileWriter(propi2, true);
                        writer4 = new CSVWriter(mFileWriter, ';');
                    } else {
                        writer4 = new CSVWriter(new FileWriter(propi2), ';');
                    }

                    writer4.writeAll(data);

                    writer4.close();

                } catch (IOException e) {

                }
                //--------------- Tabla Hierros---------------------------------
                data = new ArrayList<String[]>();
                data.add(new String[]{" "," "});
                File f5 = new File(hierro2);
                CSVWriter writ;
                f5.delete();
                try {
                    // File exist
                    if (f5.exists() && !f5.isDirectory()) {
                        FileWriter mFileWriter = new FileWriter(hierro2, true);
                        writ = new CSVWriter(mFileWriter, ';');
                    } else {
                        writ = new CSVWriter(new FileWriter(hierro2), ';');
                    }

                    writ.writeAll(data);

                    writ.close();
                    Toast.makeText(r_usuario.this, "Copia  de seguridad guardada en la carpeta Datos.", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    Toast.makeText(r_usuario.this, "Error: en la configuración de la APP, active los permisos de alamacenamiento.", Toast.LENGTH_LONG).show();

                }

                //Guardar en la nube la copia de seguridad
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                //Hierro
                Uri file = Uri.fromFile(new File(hierro2));
                StorageReference riversRef =  storageRef.child(mail+"/"+file.getLastPathSegment());
                UploadTask uploadTask1 = riversRef.putFile(file);
                //Medicamentos

                file = Uri.fromFile(new File(medicamentos2));
                riversRef =  storageRef.child(mail+"/"+file.getLastPathSegment());
                UploadTask uploadTask2 = riversRef.putFile(file);

                file = Uri.fromFile(new File(jornales2));
                riversRef =  storageRef.child(mail+"/"+file.getLastPathSegment());
                UploadTask uploadTask3 = riversRef.putFile(file);

                file = Uri.fromFile(new File(leche2));
                riversRef =  storageRef.child(mail+"/"+file.getLastPathSegment());
                UploadTask  uploadTask4 = riversRef.putFile(file);

                file = Uri.fromFile(new File(ventas2));
                riversRef =  storageRef.child(mail+"/"+file.getLastPathSegment());
                UploadTask  uploadTask5 = riversRef.putFile(file);

                file = Uri.fromFile(new File(animales2));
                riversRef =  storageRef.child(mail+"/"+file.getLastPathSegment());
                UploadTask  uploadTask6 = riversRef.putFile(file);

                file = Uri.fromFile(new File(finca2));
                riversRef =  storageRef.child(mail+"/"+file.getLastPathSegment());
                UploadTask  uploadTask7 = riversRef.putFile(file);

                file = Uri.fromFile(new File(propi2));
                riversRef =  storageRef.child(mail+"/"+file.getLastPathSegment());
                UploadTask  uploadTask8 = riversRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
                uploadTask1.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(r_usuario.this, "Error!."+exception.toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

                    }
                });

                uploadTask2.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(r_usuario.this, "Error!¡?."+exception.toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

                    }
                });
                uploadTask3.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(r_usuario.this, "Error!."+exception.toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

                    }
                });
                uploadTask4.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(r_usuario.this, "Error!."+exception.toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

                    }
                });
                uploadTask5.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(r_usuario.this, "Error!."+exception.toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

                    }
                });
                uploadTask6.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(r_usuario.this, "Error!."+exception.toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

                    }
                });
                uploadTask7.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(r_usuario.this, "Error!."+exception.toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

                    }
                });
                uploadTask8.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(r_usuario.this, "Error!."+exception.toString(), Toast.LENGTH_LONG).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        //Toast.makeText(home_datos.this, "Datos subidos con exito a la nube!.", Toast.LENGTH_LONG).show();

                    }
                });

            }else{
                Toast.makeText(r_usuario.this, "Error: " + task.getException().getMessage()+"", Toast.LENGTH_LONG).show();

            }
        }
    });
}
}