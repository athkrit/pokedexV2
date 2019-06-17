package com.example.pokedex.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.pokedex.R;
import com.example.pokedex.fragment.MainFragment;
import com.example.pokedex.fragment.PokemonDetailFragment;

public class PokemonDetailActivity extends AppCompatActivity {
    FrameLayout contentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        String pokemonName = getIntent().getStringExtra("pokemonName");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, PokemonDetailFragment.newInstance(pokemonName))
                    .commit();
        }
        initInstance();
    }

    private void initInstance() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contentContainer = (FrameLayout) findViewById(R.id.contentContainer);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
//            case R.id.setting:

        }
        return super.onOptionsItemSelected(item);
    }
}
