package domain.fincas.com.fincas;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user, pass;
    private Cursor query;

   // DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        user = (EditText) findViewById(R.id.editText4);
        pass = (EditText) findViewById(R.id.editText5);

        //-----------Datos de la actividad pasada----------------

        String name2 = "";
        String lm2 = "";
        /*
        UsersSQLiteHelper admine2 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db2 = admine2.getWritableDatabase();
        Cursor fila1 = db2.rawQuery("SELECT NOMBRE,APELLIDO FROM ACTUAL LIMIT 1", null);
        while(fila1.moveToNext()){
            name2=fila1.getString(0);
            lm2=fila1.getString(1);
        }
        db2.close();
      //   Fin de la Consulta*

        if("".equals(name2)){


        }else{ */
            Intent iw = new Intent(MainActivity.this, homep.class);
            startActivity(iw);
            finish();
        //}

    }


    /*
    public  void login(View view) {
        UsersSQLiteHelper admin = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String Usuario = user.getText().toString();
        String Contrase = pass.getText().toString();
        // query = db.rawQuery("SELECT  USUARIO, CONTRASEÑA FROM USUARIOS WHERE USUARIO ='"+Usuario+"'AND CONTRASEÑA ='"+pass);

        query = db.rawQuery("SELECT USUARIO, CONTRASEÑA FROM USUARIOS WHERE USUARIO ='" + Usuario + "'AND CONTRASEÑA ='" + Contrase + "'", null);

        while (query.moveToFirst() == true) {
            //preguntamos si el cursor tiene algun valor almacenado
            if (query.moveToFirst() == true) {
                //capturamos los valores del cursos y lo almacenamos en variable
                String usua = query.getString(0);
                String passw = query.getString(1);
                //preguntamos si los datos ingresados son iguales
                if (Usuario.equals(usua) && Contrase.equals(passw)) {
                    //si son iguales entonces vamos a otra ventana
                    //Menu es una nueva actividad empty
                    Intent ven = new Intent(this, homep.class);
                    startActivity(ven);
                    //limpiamos las las cajas de texto
                    user.setText("");
                    pass.setText("");
                } else
                    Toast.makeText(getApplicationContext(), "No coincide la contraseña!!", Toast.LENGTH_LONG).show();

            }


        }
    }
    */
    public void pasar(View view) {
        Intent iw = new Intent(MainActivity.this, r_usuario.class);
        startActivity(iw);
        finish();

       // Toast.makeText(this, "soy jesu!", Toast.LENGTH_LONG).show();
    }


    public void log(View view) {
        String use=user.getText().toString(), pas=pass.getText().toString(),N="",M="";
        //Consultar y suarios registrados
        UsersSQLiteHelper admine = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db = admine.getWritableDatabase();
        Cursor fila = db.rawQuery("SELECT NOMBRE, APELLIDO FROM USUARIOS WHERE (USUARIO='"+use+"' )AND (CONTRASEÑA='"+pas+"') LIMIT 1", null);
        while(fila.moveToNext()){
            N=fila.getString(0);
            M=fila.getString(1);
        }
        db.close();
        if("".equals(M)) {
            Toast.makeText(this, "Usuario o contraseña incorrectos!!", Toast.LENGTH_LONG).show();
        } else {
            //Base de datos
            UsersSQLiteHelper ad = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db1 = ad.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("NOMBRE", N);
            registro.put("APELLIDO",M);
            db1.insert("ACTUAL", null, registro);
            db1.close();
            Toast.makeText(this, "Bienvenido "+N +" "+M+" !", Toast.LENGTH_LONG).show();
            Intent iw = new Intent(MainActivity.this, homep.class);
            startActivity(iw);
            finish();
        }

    }




}
