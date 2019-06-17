package com.example.pokedex.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedex.R;
import com.example.pokedex.activity.MainActivity;
import com.example.pokedex.adapter.RecyclerViewAdapter;
import com.example.pokedex.dao.PokemonCollectionDao;
import com.example.pokedex.manager.HttpManager;
import com.example.pokedex.manager.PokemonNameManager;
import com.example.pokedex.manager.http.ApiService;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class MainFragment extends Fragment {
    android.support.v7.widget.RecyclerView recyclerView;
    TextView textView;
    int pokemonCount;
    Boolean loading = false;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> pokemonPictureNumber;


    public MainFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerViewAdapter.setDao(PokemonNameManager.getInstance().getDao());

        pokemonCount = PokemonNameManager.getInstance().getCount();

        Call<PokemonCollectionDao> call = HttpManager
                .getInstance()
                .getService()
                .loadPokemonName(pokemonCount, 20);

        call.enqueue(new PokemonNameLoadCallBack(PokemonNameLoadCallBack.MODE_RELOAD));
        pokemonPictureNumber = new ArrayList<>();

        for(Integer count =0 ; count<=964 ; count++){
            pokemonPictureNumber.add(count.toString());
        }
        recyclerViewAdapter.setContext(getContext());
        recyclerViewAdapter.setPokemonPictureNumber(pokemonPictureNumber);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addOnScrollListener(onScrollAdd);
    }

    private void loadMoreData() {
        if (loading)
            return;
        loading = true;
        if (pokemonCount <= 940) {
            PokemonNameManager.getInstance().setCount(pokemonCount + 20);
            Log.d("loading", "loaddddddd" + String.valueOf(pokemonCount));
            Call<PokemonCollectionDao> call = HttpManager
                    .getInstance()
                    .getService()
                    .loadPokemonName(PokemonNameManager.getInstance().getCount(), 20);
            call.enqueue(new PokemonNameLoadCallBack(PokemonNameLoadCallBack.MODE_LOAD_MORE));
        }
    }
    RecyclerView.OnScrollListener onScrollAdd = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 0) {
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int pastVisibleItem = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
                if (visibleItemCount + pastVisibleItem >= totalItemCount) {
                    pokemonCount = PokemonNameManager.getInstance().getCount();
                    loadMoreData();
                }
            }
        }

    };

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

    class PokemonNameLoadCallBack implements Callback<PokemonCollectionDao> {
        public static final int MODE_RELOAD = 1;
        public static final int MODE_LOAD_MORE = 2;

        int mode;

        public PokemonNameLoadCallBack(int mode) {
            this.mode = mode;
        }

        @Override
        public void onResponse(Call<PokemonCollectionDao> call,
                               Response<PokemonCollectionDao> response) {
            if (response.isSuccessful()) {
                PokemonCollectionDao dao = response.body();
                if (mode == MODE_LOAD_MORE) {
                    PokemonNameManager.getInstance().appendDaoAtTopPosition(dao);
                    loading = false;
                } else {

                    PokemonNameManager.getInstance().setDao(dao);
                }
                recyclerViewAdapter.notifyDataSetChanged();

//                    recyclerViewAdapter.setDao(dao);
//                recyclerView.notifyAll();

            } else {
                loading = false;
            }
        }

        @Override
        public void onFailure(Call<PokemonCollectionDao> call,
                              Throwable t) {
            loading = false;
        }
    }

}
