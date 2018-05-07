package TreatmentHistory.Activities;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.android.injection.R;
import TreatmentHistory.Converters;
import TreatmentHistory.DatabaseObjects.Injection;
import TreatmentHistory.DatabaseObjects.Medicine;
import TreatmentHistory.InjectionListAdapter;
import TreatmentHistory.InjectionViewModel;
import TreatmentHistory.MedicineViewModel;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_INJECTION_ACTIVITY_REQUEST_CODE = 1;
    public static final int NEW_MEDICINE_ACTIVITY_REQUEST_CODE = 2;

    private InjectionViewModel mInjectionViewModel;
    private MedicineViewModel mMedicineViewModel;
    private Converters con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.con = new Converters();
        mInjectionViewModel = ViewModelProviders.of(this).get(InjectionViewModel.class);
        mMedicineViewModel = ViewModelProviders.of(this).get(MedicineViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final InjectionListAdapter adapter = new InjectionListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mInjectionViewModel.getAllInjections().observe(this, adapter::setInjections);
        mMedicineViewModel.getAllMedicine().observe(this, adapter::setMedicine);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewInjectionActivity.class);
            startActivityForResult(intent, NEW_INJECTION_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.action_medicine_list:
                startActivity(
                        new Intent(this, MedicineListActivity.class));
                return true;
            case R.id.action_add_medicine:
                startActivityForResult(
                        new Intent(this, NewMedicineActivity.class),
                        NEW_MEDICINE_ACTIVITY_REQUEST_CODE);
                return true;
            default:
                return true;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_INJECTION_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String[] unpacked = this.con.unpackStrings(data.getStringExtra("new_injection"));
            String medicine = unpacked[0];
            Double dosage = Double.parseDouble(unpacked[1]);
            String date = unpacked[2];
            Injection injection = new Injection(Integer.parseInt(medicine),dosage,date);
            mInjectionViewModel.insert(injection);
        }
        else if (requestCode == NEW_MEDICINE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
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
