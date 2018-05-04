package com.example.android.roomwordssample.Activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.roomwordssample.Converters;
import com.example.android.roomwordssample.DateSetter;
import com.example.android.roomwordssample.R;

import java.util.Calendar;
import java.util.Locale;

public class NewInjectionActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mDosageValue;
    private EditText mMedicineName;
    private EditText mDate;
    private Converters con;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        this.mDosageValue = findViewById(R.id.edit_dosage);
        this.mMedicineName = findViewById(R.id.edit_medicine);
        this.mDate = findViewById(R.id.edit_date);
        DateSetter fromDate = new DateSetter(mDate, this);
        this.con = new Converters();

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mDosageValue.getText()) ||
                        TextUtils.isEmpty(mMedicineName.getText()) ||
                        TextUtils.isEmpty(mDate.getText())) {
                    Snackbar sn = Snackbar.make(view, R.string.form_empty, Snackbar.LENGTH_LONG);
                    sn.show();

//                    Toast.makeText(
//                            getApplicationContext(),
//                            R.string.empty_not_saved,
//                            Toast.LENGTH_LONG).show();
                } else {
                    String dosage = mDosageValue.getText().toString();
                    String medicine = mMedicineName.getText().toString();
                    String date = mDate.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, con.mergeStrings(medicine,dosage,date));
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
