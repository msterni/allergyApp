package com.example.android.injection.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.injection.Converters;
import com.example.android.injection.R;

public class NewMedicineActivity extends AppCompatActivity{
    private EditText mName;
    private EditText mConcentration;
    private Converters con;

    @Override
    public void onCreate(Bundle savedInstance){
        this.con = new Converters();
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_new_medicine);
        this.mName = findViewById(R.id.edit_medicine_name);
        this.mConcentration = findViewById(R.id.edit_concentration);

        final Button save_button = findViewById(R.id.button_save);
        save_button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent reply_intent = new Intent();
                    if (TextUtils.isEmpty(mName.getText()) ||
                            TextUtils.isEmpty(mConcentration.getText())){
                        Toast.makeText(
                                getApplicationContext(),
                                R.string.empty_not_saved,
                                Toast.LENGTH_LONG).show();
                    }else{
                        String name = mName.getText().toString();
                        String conc = mConcentration.getText().toString();
                        reply_intent.putExtra("new_medicine", con.mergeStrings(name,conc));
                        setResult(RESULT_OK, reply_intent);
                    }
                    finish();
                }
            }
        );
    }
}
