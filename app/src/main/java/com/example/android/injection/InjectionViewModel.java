package com.example.android.injection;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.android.injection.DatabaseObjects.Injection;
import com.example.android.injection.DatabaseObjects.InjectionRepository;

import java.util.List;

public class InjectionViewModel extends AndroidViewModel {

    private InjectionRepository mRepository;
    private LiveData<List<Injection>> mAllInjections;

    public InjectionViewModel(Application application) {
        super(application);
        mRepository = new InjectionRepository(application);
        mAllInjections = mRepository.getAllInjections();
    }
    public LiveData<List<Injection>> getAllInjections() {
        return mAllInjections;
    }
    public void insert(Injection injection) {
        mRepository.insert(injection);
    }
}