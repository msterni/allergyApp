package com.example.android.injection.DatabaseObjects;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MedicineDao {

    @Query("SELECT * from medicine_table ORDER BY mName ASC")
    LiveData<List<Medicine>> getMedicines();

    @Query("SELECT * from medicine_table")
    List<Medicine> getMedicineList();

    @Insert
    void insert(Medicine medicine);

    @Query("DELETE FROM medicine_table")
    void deleteAll();
}
