package domain.fincas.com.fincas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class readPartos extends AppCompatActivity {
    TextView read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_partos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        read = (TextView) findViewById(R.id.read);
//------------------------------Consultamos los partos del animal--------------------------------
        String use = getIntent().getExtras().getString("datos");

            UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
            SQLiteDatabase db4 = admine4.getWritableDatabase();

        Cursor fila4 = db4.rawQuery("SELECT CODIGO,NOMBRE,GENERO,FECHANACI FROM ANIMALESN WHERE  COMPPAR='1' AND CODMAMA='"+use+"'", null);

            String ss="<-  Partos registrados--> " + "\n";
            while (fila4.moveToNext()) {
                ss += "<---------------------> " + "\n"
                        + "CODIGO:" + fila4.getString(0) + "\n"
                        + "NOMBRE:" + fila4.getString(1) + "\n"
                        + "GENERO:" + fila4.getString(2) + "\n"
                        + "F.NACIMIENTO:" + fila4.getString(3) + "\n";
            }
            db4.close();
            read.setText(ss);
            read.setMovementMethod(new ScrollingMovementMethod());

        //------------------------------------------------------------------

        }

    @Override
    public void  onBackPressed() {
        finish();
    }

}


