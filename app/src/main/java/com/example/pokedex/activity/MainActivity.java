package com.example.pokedex.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.pokedex.R;
import com.example.pokedex.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    FrameLayout contentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
        }
        initInstance();
    }

    private void initInstance() {
        contentContainer = (FrameLayout) findViewById(R.id.contentContainer);
    }


}
