package com.example.android.roomwordssample.DatabaseObjects;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class InjectionRepository {

    private InjectionDao mInjectionDao;
    private LiveData<List<Injection>> mAllInjections;

    public InjectionRepository(Application application) {
        InjectionDatabase db = InjectionDatabase.getDatabase(application);
        mInjectionDao = db.wordDao();
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
