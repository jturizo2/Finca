package domain.fincas.com.fincas;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReadDataBase extends AppCompatActivity {
    private TextView read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data_base);
        read=(TextView)findViewById(R.id.read);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Tratamientos
        //Leemos la ultima inspeccion registrada
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();
        //Lectura del ultimo valor
        String ss ="";
        Cursor fila2 = db5.rawQuery("SELECT * FROM PROPIETARIOS", null);
        while(fila2.moveToNext()){
            ss+=fila2.getString(0)+"- "+fila2.getString(1)+" -"+fila2.getString(2)+"- \n"
            ;}
        db5.close();
        read.setText(ss);
        read.setMovementMethod(new ScrollingMovementMethod());

        /*


        //Leemos la ultima inspeccion registrada
        UsersSQLiteHelper admine4 = new UsersSQLiteHelper(this, "FINCAS", null, 1);
        SQLiteDatabase db5 = admine4.getWritableDatabase();
        //Lectura del ultimo valor
        String ss ="";
        Cursor fila2 = db5.rawQuery("SELECT * FROM ANIMALESN", null);
        while(fila2.moveToNext()){
            ss+=fila2.getString(0)+"- "+fila2.getString(1)+" -"+fila2.getString(2)+"- "+fila2.getString(3)+"- "+fila2.getString(4)+"- \n"+
            fila2.getString(5)+"- "+fila2.getString(6)+" -"+fila2.getString(7)+"- "+fila2.getString(8)+"- "+fila2.getString(9)+"- \n"+
            fila2.getString(10)+"- "+fila2.getString(11)+" -"+fila2.getString(12)+"- "+fila2.getString(13)+"- "+fila2.getString(14)+"- \n"+
            fila2.getString(15)+"- "+fila2.getString(16)+" -"+fila2.getString(17)+"- "+fila2.getString(18)+"- "+fila2.getString(19)+"- \n"+
            fila2.getString(20)+"- "+fila2.getString(21)+" -"+fila2.getString(22)+"- \n"
            ;}
        db5.close();
        read.setText(ss);
        read.setMovementMethod(new ScrollingMovementMethod());
    */

    }
}
