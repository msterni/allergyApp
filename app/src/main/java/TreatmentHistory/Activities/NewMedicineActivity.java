package TreatmentHistory.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.android.injection.R;
import TreatmentHistory.Converters;

public class NewMedicineActivity extends AppCompatActivity{
    private EditText mName;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_new_medicine);
        this.mName = findViewById(R.id.edit_medicine_name);

        final Button save_button = findViewById(R.id.button_save);
        save_button.setOnClickListener(v -> {
            Intent reply_intent = new Intent();
            if (TextUtils.isEmpty(mName.getText())){
                Snackbar.make(v, R.string.form_empty, Snackbar.LENGTH_LONG).show();
            }else{
                String name = mName.getText().toString();
                reply_intent.putExtra("new_medicine", name);
                setResult(RESULT_OK, reply_intent);
                finish();
            }
        }
        );
    }
}
