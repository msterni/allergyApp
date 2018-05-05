package com.example.android.injection.Activities;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.injection.Converters;
import com.example.android.injection.DatabaseObjects.Injection;
import com.example.android.injection.InjectionListAdapter;
import com.example.android.injection.InjectionViewModel;
import com.example.android.injection.R;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private InjectionViewModel mInjectionViewModel;
    private Converters con;
    private LiveData<List<Injection>> injections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.con = new Converters();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final InjectionListAdapter adapter = new InjectionListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mInjectionViewModel = ViewModelProviders.of(this).get(InjectionViewModel.class);
        mInjectionViewModel.getAllInjections().observe(this, new Observer<List<Injection>>() {
            @Override
            public void onChanged(@Nullable final List<Injection> injections) {
                adapter.setInjections(injections);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewInjectionActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String[] unpacked = this.con.unpackStrings(data.getStringExtra(NewInjectionActivity.EXTRA_REPLY));
            String medicine = unpacked[0];
            Double dosage = Double.parseDouble(unpacked[1]);
            String date = unpacked[2];
            Injection injection = new Injection(medicine,dosage,date);
            mInjectionViewModel.insert(injection);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
