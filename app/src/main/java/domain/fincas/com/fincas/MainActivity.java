package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import domain.fincas.com.fincas.objetos.FireBaseReferences;

public class MainActivity extends AppCompatActivity {
    EditText user, pass;
    private Cursor query;
    FirebaseAuth.AuthStateListener mAut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        user = (EditText) findViewById(R.id.editText4);
        pass = (EditText) findViewById(R.id.editText5);
        mAut = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user1 = firebaseAuth.getCurrentUser();
                if((user1 != null) && user1.isEmailVerified() ){
                    Toast.makeText(getApplicationContext(), "Sesión iniciada.", Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Welcome "+user1.getEmail(), Toast.LENGTH_LONG).show();
                    //Si ya esta iniciada la sesion salto a home p
                    Intent iw = new Intent(MainActivity.this, homep.class);
                    startActivity(iw);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Sesión cerrada.", Toast.LENGTH_LONG).show();
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

    public void pasar(View view) {
        Intent iw = new Intent(MainActivity.this, r_usuario.class);
        startActivity(iw);
        finish();
    }


    public void log(View view) {
        String Usuario = user.getText().toString();
        String Contrase = pass.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(Usuario,Contrase).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser user1 =  FirebaseAuth.getInstance().getCurrentUser();
                if(task.isSuccessful()){
                    if(user1.isEmailVerified()) {
                        Toast.makeText(MainActivity.this, "Sesión iniciada correctamente.", Toast.LENGTH_LONG).show();
                        //Si ya esta iniciada la sesion salto a home p
                        Intent iw = new Intent(MainActivity.this, homep.class);
                        startActivity(iw);
                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Correo no verificado.", Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(MainActivity.this, "Error: " + task.getException().getMessage()+"", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
