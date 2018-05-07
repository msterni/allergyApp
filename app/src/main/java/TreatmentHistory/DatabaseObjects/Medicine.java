package TreatmentHistory.DatabaseObjects;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "medicine_table")
public class Medicine {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @NonNull
    private String mName;
    private String mConcentration;

    public Medicine(String name, String concentration) {
        this.mName = name;
        this.mConcentration = concentration;
    }
    public String getName(){return this.mName;}
    public String getConcentration() {return mConcentration;}
}
