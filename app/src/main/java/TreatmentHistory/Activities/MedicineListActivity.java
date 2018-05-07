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

public class MedicineListActivity extends AppCompatActivity{
    private MedicineViewModel mMedicineViewModel;
    private Converters con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMedicineViewModel = ViewModelProviders.of(this).get(MedicineViewModel.class);
        this.con = new Converters();

        setSupportActionBar(findViewById(R.id.toolbar));
        RecyclerView recyclerView =findViewById(R.id.recyclerview);
        final MedicineListAdapter adapter = new MedicineListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMedicineViewModel.getAllMedicine().observe(this, adapter::setMedicine);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivityForResult(
                new Intent(MedicineListActivity.this,
                        NewMedicineActivity.class),
                2));
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 2 && resultCode == RESULT_OK){
            String[] unpacked = this.con.unpackStrings(data.getStringExtra("new_medicine"));
            String name = unpacked[0];
            String conc = unpacked[1];
            Medicine medicine = new Medicine(name, conc);
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
