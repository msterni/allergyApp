package com.example.android.injection;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.android.injection.DatabaseObjects.Injection;
import com.example.android.injection.DatabaseObjects.AllergyRepository;

import java.util.List;

public class InjectionViewModel extends AndroidViewModel {

    private AllergyRepository mRepository;
    private LiveData<List<Injection>> mAllInjections;

    public InjectionViewModel(Application application) {
        super(application);
        mRepository = new AllergyRepository(application);
        mAllInjections = mRepository.getAllInjections();
    }
    public LiveData<List<Injection>> getAllInjections() {
        return mAllInjections;
    }
    public void insert(Injection injection) {
        mRepository.insertInjection(injection);
    }
}