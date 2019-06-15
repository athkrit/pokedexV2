package com.example.pokedex.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PokemonResultDao implements Parcelable {
    @SerializedName("name")     private String name;
    @SerializedName("url")      private String url;
    public PokemonResultDao(){

    }

    protected PokemonResultDao(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<PokemonResultDao> CREATOR = new Creator<PokemonResultDao>() {
        @Override
        public PokemonResultDao createFromParcel(Parcel in) {
            return new PokemonResultDao(in);
        }

        @Override
        public PokemonResultDao[] newArray(int size) {
            return new PokemonResultDao[size];
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
