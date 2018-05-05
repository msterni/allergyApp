package com.example.android.injection.DatabaseObjects;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//@Entity(tableName = "injection_table")
//public class Injection{
//    @PrimaryKey
//    @NonNull
//    @ColumnInfo(name="injection")
//    private Date
//}

@Entity(tableName = "injection_table")
public class Injection {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @NonNull
    @ColumnInfo(name = "medicine")
    private String mMedicine;
    @ColumnInfo(name = "dosage")
    private Double mDosage;
    @ColumnInfo(name = "date")
    private String mDate;

    public Injection(@NonNull String medicine, Double dosage, String date) {
        this.mMedicine = medicine;
        this.mDosage = dosage;
        this.mDate = date;
    }
    public String getMedicine(){return this.mMedicine;}
    public Double getDosage() {return mDosage;}
    public String getDate() {return mDate;}
}

