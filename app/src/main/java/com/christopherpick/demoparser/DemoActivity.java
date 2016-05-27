package com.christopherpick.demoparser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class DemoActivity extends AppCompatActivity {

    private static final String DEFAULT_FRAGMENT = "default_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        if (savedInstanceState != null) {
            return;
        } else {
            CategoryFragment defaultFragment = new CategoryFragment();
            defaultFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, defaultFragment, DEFAULT_FRAGMENT).commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}


