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

    public Medicine(String name) {
        this.mName = name;
    }
    public String getName(){return this.mName;}
}
