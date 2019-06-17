package com.example.pokedex.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.dao.PokemonCollectionDao;
import com.example.pokedex.dao.PokemonDetailCollectionDao;
import com.example.pokedex.manager.HttpManager;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class PokemonDetailFragment extends Fragment {
    private static String pokemonName;
    TextView tvPokemonName, tvAttack, tvDefence, tvSpeed, tvSpecialAttack, tvSpecialDefence, tvType;
    ImageView ivTopRight, ivTopLeft, ivBottomRight, ivBottomLeft;
    PokemonDetailCollectionDao dao;
    String pokemonTypes;

    public PokemonDetailFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static PokemonDetailFragment newInstance(String Name) {
        pokemonName = Name;
        PokemonDetailFragment fragment = new PokemonDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        tvPokemonName = (TextView) rootView.findViewById(R.id.tvPokemonName);
        tvType = (TextView) rootView.findViewById(R.id.tvType);
        tvAttack = (TextView) rootView.findViewById(R.id.tvAttack);
        tvDefence = (TextView) rootView.findViewById(R.id.tvDefence);
        tvSpeed = (TextView) rootView.findViewById(R.id.tvSpeed);
        tvSpecialAttack = (TextView) rootView.findViewById(R.id.tvSpecialAttack);
        tvSpecialDefence = (TextView) rootView.findViewById(R.id.tvSpecialDefence);

        ivBottomLeft = (ImageView) rootView.findViewById(R.id.ivBottomLeft);
        ivBottomRight = (ImageView) rootView.findViewById(R.id.ivBottomRight);
        ivTopLeft = (ImageView) rootView.findViewById(R.id.ivTopLeft);
        ivTopRight = (ImageView) rootView.findViewById(R.id.ivTopRight);

        tvPokemonName.setText(pokemonName);

        Call<PokemonDetailCollectionDao> call = HttpManager
                .getInstance()
                .getService()
                .loadPokemonDetail(pokemonName);
        call.enqueue(new Callback<PokemonDetailCollectionDao>() {
            @Override
            public void onResponse(Call<PokemonDetailCollectionDao> call, Response<PokemonDetailCollectionDao> response) {
                if (response.isSuccessful()) {
                    dao = response.body();
                    for (int i = 0; i < dao.getStats().size(); i++) {
                        switch (dao.getStats().get(i).getStat().getName()) {
                            case "attack":
                                tvAttack.setText("attack : " + dao.getStats().get(i).getBaseStat());
                            case "defense":
                                tvDefence.setText("defense : " + dao.getStats().get(i).getBaseStat());
                            case "speed":
                                tvSpeed.setText("speed : " + dao.getStats().get(i).getBaseStat());
                            case "special-attack":
                                tvSpecialAttack.setText("special-attack : " + dao.getStats().get(i).getBaseStat());
                            case "special-defense":
                                tvSpecialDefence.setText("special-defense : " + dao.getStats().get(i).getBaseStat());
                        }
                    }
                    for (int i = 0; i < dao.getTypes().size(); i++) {
                        if (pokemonTypes != null) {
                            pokemonTypes = pokemonTypes + " , " + dao.getTypes().get(i).getType().getName();
                        } else {
                            pokemonTypes = dao.getTypes().get(i).getType().getName();
                        }
                    }
                    tvType.setText("Type :" + pokemonTypes);

                    setView(dao.getSprites().getBackDefault(), ivBottomRight);
                    setView(dao.getSprites().getFrontDefault(), ivBottomLeft);
                    setView(dao.getSprites().getBackShiny(), ivTopLeft);
                    setView(dao.getSprites().getFrontShiny(), ivTopRight);

                } else {
                    Log.d("detailDao", "not success");
                }
            }

            @Override
            public void onFailure(Call<PokemonDetailCollectionDao> call, Throwable t) {
                Log.d("detailDao", t.getMessage());

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    private void setView(String url, ImageView ivPosition) {
        Glide.with(Contextor.getInstance().getContext())
                .load(url)
                .format(PREFER_ARGB_8888)
                .fitCenter()
                .into(ivPosition);
        if (ivPosition == null) {
            ivPosition.setImageResource(R.drawable.pokeball);
        }
    }

}
