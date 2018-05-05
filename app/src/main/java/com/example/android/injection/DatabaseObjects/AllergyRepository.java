package com.example.android.injection.DatabaseObjects;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class AllergyRepository {

    private InjectionDao mInjectionDao;
    private LiveData<List<Injection>> mAllInjections;

    public AllergyRepository(Application application) {
        AllergyDatabase db = AllergyDatabase.getDatabase(application);
        mInjectionDao = db.injectionDao();
        mAllInjections = mInjectionDao.getInjections();
    }
    public LiveData<List<Injection>> getAllInjections() {
        return mAllInjections;
    }

    public void insert (Injection injection) {
        new insertAsyncTask(mInjectionDao).execute(injection);
    }

    private static class insertAsyncTask extends AsyncTask<Injection, Void, Void> {

        private InjectionDao mAsyncTaskDao;

        insertAsyncTask(InjectionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Injection... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
