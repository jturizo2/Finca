package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static domain.fincas.com.fincas.R.id.button5;

public class r_usuario extends AppCompatActivity {

    EditText nombre, apellido, usuario, contras, rcontras;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_usuario);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


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
                                //----------- Get date----------------
                                //Calendar c = Calendar.getInstance();
                                String gnombre = nombre.getText().toString();
                                String gapellido = apellido.getText().toString();
                                String gusuario = usuario.getText().toString();
                                String gcontra = contras.getText().toString();
                                //Base de datos
                                UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
                                SQLiteDatabase db = admine.getWritableDatabase();

                                ContentValues registro = new ContentValues();
                                registro.put("NOMBRE", gnombre);
                                registro.put("APELLIDO", gapellido);
                                registro.put("USUARIO", gusuario);
                                registro.put("CONTRASEÑA", gcontra);

                                db.insert("USUARIOS", null, registro);
                                db.close();
                                Toast.makeText(this, "Usuario Creado!!", Toast.LENGTH_LONG).show();

                                Intent i = new Intent(r_usuario.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(this, "No coincide la contraseña!!", Toast.LENGTH_LONG).show();

                            }
                        }
                    }


                }


            }
        }

    }

}