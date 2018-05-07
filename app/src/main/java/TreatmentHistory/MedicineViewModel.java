package com.example.android.injection;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.android.injection.DatabaseObjects.AllergyRepository;
import com.example.android.injection.DatabaseObjects.Medicine;

import java.util.List;

public class MedicineViewModel extends AndroidViewModel {

    private AllergyRepository mRepository;
    private LiveData<List<Medicine>> mAllMedicine;

    public MedicineViewModel(Application application) {
        super(application);
        mRepository = new AllergyRepository(application);
        mAllMedicine = mRepository.getAllMedicine();
    }
    public LiveData<List<Medicine>> getAllMedicine() {
        return mAllMedicine;
    }
    public void insert(Medicine medicine) {
        mRepository.insertMedicine(medicine);
    }
}