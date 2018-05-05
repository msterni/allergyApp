package com.example.android.injection.DatabaseObjects;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(
        tableName = "injection_table",
        foreignKeys = @ForeignKey(
                entity = Medicine.class,
                parentColumns = "uid",
                childColumns = "medicine",
                onDelete = CASCADE))
public class Injection {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @NonNull
    @ColumnInfo(name = "medicine")
    private int mMedicine;
    @ColumnInfo(name = "dosage")
    private Double mDosage;
    @ColumnInfo(name = "date")
    private String mDate;

    public Injection(@NonNull int medicine, Double dosage, String date) {
        this.mMedicine = medicine;
        this.mDosage = dosage;
        this.mDate = date;
    }
    public int getMedicine(){return this.mMedicine;}
    public Double getDosage() {return mDosage;}
    public String getDate() {return mDate;}
}

