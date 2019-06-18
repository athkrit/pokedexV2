package com.example.pokedex.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonStatsDao implements Parcelable{

    @SerializedName("base_stat")    private Integer baseStat;
    @SerializedName("effort")       private Integer effort;
    @SerializedName("stat")         private PokemonStatsNameDao stat;

    protected PokemonStatsDao(Parcel in) {
        if (in.readByte() == 0) {
            baseStat = null;
        } else {
            baseStat = in.readInt();
        }
        if (in.readByte() == 0) {
            effort = null;
        } else {
            effort = in.readInt();
        }
        stat = in.readParcelable(PokemonStatsNameDao.class.getClassLoader());
    }

    public static final Creator<PokemonStatsDao> CREATOR = new Creator<PokemonStatsDao>() {
        @Override
        public PokemonStatsDao createFromParcel(Parcel in) {
            return new PokemonStatsDao(in);
        }

        @Override
        public PokemonStatsDao[] newArray(int size) {
            return new PokemonStatsDao[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (baseStat == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(baseStat);
        }
        if (effort == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(effort);
        }
        parcel.writeParcelable(stat, i);
    }
}
