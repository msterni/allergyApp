package TreatmentHistory.DatabaseObjects;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class AllergyRepository {

    private MedicineDao mMedicineDao;
    private InjectionDao mInjectionDao;
    private LiveData<List<Injection>> mAllInjections;
    private LiveData<List<Medicine>> allMedicine;

    public AllergyRepository(Application application) {
        AllergyDatabase db = AllergyDatabase.getDatabase(application);
        mInjectionDao = db.injectionDao();
        mMedicineDao = db.medicineDao();
        mAllInjections = mInjectionDao.getInjections();
        allMedicine = mMedicineDao.getMedicines();
    }
    public LiveData<List<Injection>> getAllInjections() {
        return mAllInjections;
    }

    public void insertInjection(Injection injection) {
        new insertInjAsync(mInjectionDao).execute(injection);
    }

    public void insertMedicine (Medicine medicine){
        new insertMedAsync(mMedicineDao).execute(medicine);
    }

    public LiveData<List<Medicine>> getAllMedicine() {
        return allMedicine;
    }

    private static class insertInjAsync extends AsyncTask<Injection, Void, Void> {
        private InjectionDao mAsyncInjectionDao;
        insertInjAsync(InjectionDao dao) {
            mAsyncInjectionDao = dao;
        }
        @Override
        protected Void doInBackground(final Injection... params) {
            mAsyncInjectionDao.insert(params[0]);
            return null;
        }
    }

    private static class insertMedAsync extends AsyncTask<Medicine,Void,Void>{
        private MedicineDao medDao;
        insertMedAsync(MedicineDao medicine) {
            medDao = medicine;
        }
        @Override
        protected Void doInBackground(Medicine... medicines) {
            medDao.insert(medicines[0]);
            return null;
        }
    }
}
