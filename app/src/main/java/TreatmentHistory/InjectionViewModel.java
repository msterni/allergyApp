package TreatmentHistory;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.List;
import TreatmentHistory.DatabaseObjects.AllergyRepository;
import TreatmentHistory.DatabaseObjects.Injection;

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