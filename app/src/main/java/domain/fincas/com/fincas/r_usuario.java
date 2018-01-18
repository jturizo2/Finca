package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    public void atra(View view) {
        Intent i = new Intent(r_usuario.this, MainActivity.class);
        startActivity(i);
        finish();
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
        Intent iw = new Intent(r_usuario.this, home_listado_animales.class);
        startActivity(iw);
        finish();
    }

private void registar(String email,String pass){

    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            if(task.isSuccessful()){
                Toast.makeText(r_usuario.this, "Verifique la cuenta con su correo electrónico!.", Toast.LENGTH_LONG).show();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.sendEmailVerification();
            }else{
                Toast.makeText(r_usuario.this, "Error: " + task.getException().getMessage()+"", Toast.LENGTH_LONG).show();

            }
        }
    });
}
}