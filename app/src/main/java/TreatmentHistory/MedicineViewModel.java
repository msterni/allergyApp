package TreatmentHistory;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.List;
import TreatmentHistory.DatabaseObjects.AllergyRepository;
import TreatmentHistory.DatabaseObjects.Medicine;

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