package TreatmentHistory.DatabaseObjects;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface InjectionDao {

    @Query("SELECT * from injection_table order by date DESC")
    LiveData<List<Injection>> getInjections();

    @Insert
    void insert(Injection injection);

    @Query("DELETE FROM injection_table")
    void deleteAll();
}
