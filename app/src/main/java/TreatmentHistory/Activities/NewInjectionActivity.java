package TreatmentHistory.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.android.injection.R;
import TreatmentHistory.Converters;
import TreatmentHistory.CustomArrayAdapter;
import TreatmentHistory.DatabaseObjects.Medicine;
import TreatmentHistory.DateSetter;
import TreatmentHistory.MedicineViewModel;

public class NewInjectionActivity extends AppCompatActivity {
    private EditText mDosageValue;
    private EditText mDate;
    private Converters con;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_injection);
        this.mDosageValue = findViewById(R.id.edit_dosage);
        this.mDate = findViewById(R.id.edit_date);

        Spinner medSpinner = findViewById(R.id.edit_medicine);
        MedicineViewModel viewModel = ViewModelProviders.of(this).get(MedicineViewModel.class);
        viewModel.getAllMedicine().observe(this, list ->
                medSpinner.setAdapter(new CustomArrayAdapter(this, R.layout.spinner, list)));

        new DateSetter(mDate, this);
        this.con = new Converters();

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mDosageValue.getText()) ||
                        TextUtils.isEmpty(mDate.getText())) {
                    Snackbar sn = Snackbar.make(view, R.string.form_empty, Snackbar.LENGTH_LONG);
                    sn.show();
                } else {
                    String dosage = mDosageValue.getText().toString();
//                    String medicine = mMedicineName.getText().toString();
                    String medicine = GetSelectedMedicineId();
                    String date = mDate.getText().toString();
                    replyIntent.putExtra("new_injection", con.mergeStrings(medicine,dosage,date));
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    private String GetSelectedMedicineId() {
        Medicine med = (Medicine) ((Spinner) findViewById(R.id.edit_medicine)).getSelectedItem();
        return String.valueOf(med.uid);
    }
}
