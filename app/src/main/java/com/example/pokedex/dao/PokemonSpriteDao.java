package com.example.pokedex.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PokemonSpriteDao implements Parcelable {
    @SerializedName("back_default")             private String backDefault;
    @SerializedName("back_female")              private Object backFemale;
    @SerializedName("back_shiny")               private String backShiny;
    @SerializedName("back_shiny_female")        private Object backShinyFemale;
    @SerializedName("front_default")            private String frontDefault;
    @SerializedName("front_female")             private Object frontFemale;
    @SerializedName("front_shiny")              private String frontShiny;
    @SerializedName("front_shiny_female")       private Object frontShinyFemale;

    protected PokemonSpriteDao(Parcel in) {
        backDefault = in.readString();
        backShiny = in.readString();
        frontDefault = in.readString();
        frontShiny = in.readString();
    }

    public static final Creator<PokemonSpriteDao> CREATOR = new Creator<PokemonSpriteDao>() {
        @Override
        public PokemonSpriteDao createFromParcel(Parcel in) {
            return new PokemonSpriteDao(in);
        }

        @Override
        public PokemonSpriteDao[] newArray(int size) {
            return new PokemonSpriteDao[size];
        }
    };

    public String getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }

    public Object getBackFemale() {
        return backFemale;
    }

    public void setBackFemale(Object backFemale) {
        this.backFemale = backFemale;
    }

    public String getBackShiny() {
        return backShiny;
    }

    public void setBackShiny(String backShiny) {
        this.backShiny = backShiny;
    }

    public Object getBackShinyFemale() {
        return backShinyFemale;
    }

    public void setBackShinyFemale(Object backShinyFemale) {
        this.backShinyFemale = backShinyFemale;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public Object getFrontFemale() {
        return frontFemale;
    }

    public void setFrontFemale(Object frontFemale) {
        this.frontFemale = frontFemale;
    }

    public String getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(String frontShiny) {
        this.frontShiny = frontShiny;
    }

    public Object getFrontShinyFemale() {
        return frontShinyFemale;
    }

    public void setFrontShinyFemale(Object frontShinyFemale) {
        this.frontShinyFemale = frontShinyFemale;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backDefault);
        dest.writeString(backShiny);
        dest.writeString(frontDefault);
        dest.writeString(frontShiny);
    }
}
