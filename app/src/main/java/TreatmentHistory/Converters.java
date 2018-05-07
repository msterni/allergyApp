package TreatmentHistory;

import android.arch.persistence.room.TypeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converters {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @TypeConverter
    public Date fromTimestamp(String value) throws ParseException {
        if (value == null) return null;
        return dateFormat.parse(value);
    }

    @TypeConverter
    public String dateToTimestamp(Date date) {
        return dateFormat.format(date);
    }

    public String mergeStrings(String a, String b, String c){
        return a+"|"+b+"|"+c;
    }
    public String mergeStrings(String a, String b){
        return a+"|"+b;
    }
    public String[] unpackStrings(String value){
        return value.split("\\|");
    }
}