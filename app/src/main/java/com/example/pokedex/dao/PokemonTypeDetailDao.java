package com.example.pokedex.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PokemonTypeDetailDao implements Parcelable {
    @SerializedName("name")             private String name;
    @SerializedName("url")              private String url;

    protected PokemonTypeDetailDao(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<PokemonTypeDetailDao> CREATOR = new Creator<PokemonTypeDetailDao>() {
        @Override
        public PokemonTypeDetailDao createFromParcel(Parcel in) {
            return new PokemonTypeDetailDao(in);
        }

        @Override
        public PokemonTypeDetailDao[] newArray(int size) {
            return new PokemonTypeDetailDao[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }
}
