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
import TreatmentHistory.DatabaseObjects.Injection;
import TreatmentHistory.InjectionListAdapter;
import TreatmentHistory.InjectionViewModel;
import TreatmentHistory.MedicineViewModel;
import TreatmentHistory.RequestCodes;

public class ListInjectionActivity extends AppCompatActivity {
    private InjectionViewModel injectionViewModel;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_list);

        RecyclerView recyclerView = findViewById(R.id.rec_view_item);
        final InjectionListAdapter adapter = new InjectionListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        injectionViewModel = ViewModelProviders.of(this).get(InjectionViewModel.class);
        MedicineViewModel medicineViewModel = ViewModelProviders.of(this).get(MedicineViewModel.class);
        injectionViewModel.getAllInjections().observe(this, adapter::setInjections);
        medicineViewModel.getAllMedicine().observe(this, adapter::setMedicine);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivityForResult(
                new Intent(ListInjectionActivity.this, NewInjectionActivity.class),
                RequestCodes.NewInjection)
        );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCodes.NewInjection && resultCode == RESULT_OK) {
            String[] unpacked =
                    new Converters().unpackStrings(data.getStringExtra("new_injection"));
            String medicine = unpacked[0];
            Double dosage = Double.parseDouble(unpacked[1]);
            String date = unpacked[2];
            Injection injection = new Injection(Integer.parseInt(medicine),dosage,date);
            injectionViewModel.insert(injection);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
