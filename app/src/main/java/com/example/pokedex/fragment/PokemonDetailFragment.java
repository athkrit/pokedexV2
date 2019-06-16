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

import com.example.pokedex.R;
import com.example.pokedex.dao.PokemonCollectionDao;
import com.example.pokedex.dao.PokemonDetailCollectionDao;
import com.example.pokedex.manager.HttpManager;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class PokemonDetailFragment extends Fragment {
    TextView tvPokemonName,tvAttack,tvDefence,tvSpeed,tvSpecialAttack,tvSpecialDefence;
    ImageView ivTopRight,ivTopLeft,ivBottomRight,ivBottomLeft;

    public PokemonDetailFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static PokemonDetailFragment newInstance() {
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
        Call<PokemonDetailCollectionDao> call = HttpManager
                .getInstance()
                .getService()
                .loadPokemonDetail("ditto");
        call.enqueue(new Callback<PokemonDetailCollectionDao>() {
            @Override
            public void onResponse(Call<PokemonDetailCollectionDao> call, Response<PokemonDetailCollectionDao> response) {
                if(response.isSuccessful()){
                    PokemonDetailCollectionDao dao = response.body();
                    Log.d("detailDao","success"+dao.toString());
                    Toast.makeText(Contextor.getInstance().getContext(),""+dao.getStats().get(0).getBaseStat(),Toast.LENGTH_SHORT);
                }
                else {
                    Log.d("detailDao","not success");
                }
            }

            @Override
            public void onFailure(Call<PokemonDetailCollectionDao> call, Throwable t) {
                Log.d("detailDao","fail");

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

}
