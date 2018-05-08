package TreatmentHistory.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.android.injection.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));

        final Button medicineButton = findViewById(R.id.medicine_main_button);
        final Button injectionButton = findViewById(R.id.injection_main_button);

        medicineButton.setOnClickListener(view -> startActivity(
                new Intent(this, ListMedicineActivity.class)));
        injectionButton.setOnClickListener(view -> startActivity(
                new Intent(this, ListInjectionActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_injection_list:
                startActivity(
                    new Intent(this, ListInjectionActivity.class));
                return true;
            case R.id.action_medicine_list:
                startActivity(
                        new Intent(this, ListMedicineActivity.class));
                return true;
            default:
                return true;
        }
    }

}
