package TreatmentHistory.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.injection.R;

import TreatmentHistory.Converters;
import TreatmentHistory.DatabaseObjects.Medicine;
import TreatmentHistory.MedicineListAdapter;
import TreatmentHistory.MedicineViewModel;
import TreatmentHistory.RequestCodes;

public class ListMedicineActivity extends AppCompatActivity{
    private MedicineViewModel mMedicineViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.rec_view_item);
        final MedicineListAdapter adapter = new MedicineListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMedicineViewModel = ViewModelProviders.of(this).get(MedicineViewModel.class);
        mMedicineViewModel.getAllMedicine().observe(this, adapter::setMedicine);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivityForResult(
                new Intent(ListMedicineActivity.this, NewMedicineActivity.class),
                RequestCodes.NewMedicine)
        );
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 2 && resultCode == RESULT_OK){
            String name = data.getStringExtra("new_medicine");
            Medicine medicine = new Medicine(name);
            mMedicineViewModel.insert(medicine);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
