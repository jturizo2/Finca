package domain.fincas.com.fincas;

import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by Ing Jorge Silva C on 07/02/2018.
 */

public class numero {
    String dou;
    String end;
    public numero() {
    }
    public numero(String dou) {
        this.dou = dou;
    }

    public String getDou() {
        return dou;
    }

    public String douTopes(String d){
        d=d.replace(" ","");
        DecimalFormat formateador = new DecimalFormat("###,###.##");
        this.dou=formateador.format(Double.parseDouble(d));
     return this.dou;
    }
    public String deleteEnd(String d){

        this.end=d;
        return this.end;
    }
}
