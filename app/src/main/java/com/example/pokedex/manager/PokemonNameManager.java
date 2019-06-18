package com.example.pokedex.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pokedex.dao.PokemonCollectionDao;
import com.example.pokedex.dao.PokemonResultDao;
import com.google.gson.Gson;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.util.ArrayList;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PokemonNameManager {

    private static PokemonNameManager instance;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    int count = 0;

    public static PokemonNameManager getInstance() {
        if (instance == null)
            instance = new PokemonNameManager();
        return instance;
    }

    private Context mContext;
    private PokemonCollectionDao dao;


    public PokemonCollectionDao getDao() {
        return dao;
    }

    public void setDao(PokemonCollectionDao dao) {
        this.dao = dao;
        saveCache();
    }

    private PokemonNameManager() {
        mContext = Contextor.getInstance().getContext();
        loadCache();
    }

    public void appendDaoAtTopPosition(PokemonCollectionDao newDao) {
        if (dao == null)
            dao = new PokemonCollectionDao();
        if (dao.getResults() == null)
            dao.setResults(new ArrayList<PokemonResultDao>());
        dao.getResults().addAll(dao.getResults().size(), newDao.getResults());
    }

    private void saveCache() {
        PokemonCollectionDao cacheDao = new PokemonCollectionDao();
        if (dao != null && dao.getResults() != null)
            cacheDao.setResults(dao.getResults().subList(0, Math.min(20, dao.getResults().size())));
        String json = new Gson().toJson(cacheDao);
        SharedPreferences prefs = mContext.getSharedPreferences("" +
                        "photos",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        //add delete
        editor.putString("json", json);
        editor.apply();
    }

    private void loadCache() {
        SharedPreferences prefs = mContext.getSharedPreferences("" +
                        "photos",
                Context.MODE_PRIVATE);
        String json = prefs.getString("json", null);
        if (json == null)
            return;
        dao = new Gson().fromJson(json, PokemonCollectionDao.class);
    }
}
