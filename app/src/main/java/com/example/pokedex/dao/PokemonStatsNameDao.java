package com.example.pokedex.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PokemonStatsNameDao implements Parcelable {
    @SerializedName("name")         private String name;
    @SerializedName("url")          private String url;

    protected PokemonStatsNameDao(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<PokemonStatsNameDao> CREATOR = new Creator<PokemonStatsNameDao>() {
        @Override
        public PokemonStatsNameDao createFromParcel(Parcel in) {
            return new PokemonStatsNameDao(in);
        }

        @Override
        public PokemonStatsNameDao[] newArray(int size) {
            return new PokemonStatsNameDao[size];
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
