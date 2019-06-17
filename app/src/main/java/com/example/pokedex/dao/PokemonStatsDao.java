package com.example.pokedex.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonStatsDao{

    @SerializedName("base_stat")    private Integer baseStat;
    @SerializedName("effort")       private Integer effort;
    @SerializedName("stat")         private PokemonStatsNameDao stat;

    public Integer getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(Integer baseStat) {
        this.baseStat = baseStat;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public PokemonStatsNameDao getStat() {
        return stat;
    }

    public void setStat(PokemonStatsNameDao stat) {
        this.stat = stat;
    }
}
