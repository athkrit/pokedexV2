package com.example.pokedex.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonTypeDao  {
    @SerializedName("slot")             private Integer slot;

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public PokemonTypeDetailDao getType() {
        return type;
    }

    public void setType(PokemonTypeDetailDao type) {
        this.type = type;
    }

    @SerializedName("type")             private PokemonTypeDetailDao type;

}
