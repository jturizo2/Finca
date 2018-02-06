package domain.fincas.com.fincas;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class out extends AppCompatActivity {
    FirebaseAuth.AuthStateListener mAut;
    FirebaseAuth mAut2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mAut = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user1 = firebaseAuth.getCurrentUser();
                if((user1 != null) && user1.isEmailVerified() ){
                    Toast.makeText(getApplicationContext(), "Welcome."+user1.getEmail().toString(), Toast.LENGTH_LONG).show();

                    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(out.this);
                    dialogo1.setTitle("Confirmar Acción");
                    dialogo1.setMessage("¿Desea cerrar sesión?");
                    dialogo1.setCancelable(false);
                    dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            aceptar();
                        }
                    });
                    dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo1, int id) {
                            cancelar();
                        }
                    });
                    dialogo1.show();
                }else{

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

    @Override
    public void onBackPressed() {

    }
    public void aceptar(){
        mAut2.signOut();
    }
    public void cancelar(){
        Intent choo2 = new Intent(out.this, out.class);
        startActivity(choo2);
        finish();
    }
}
