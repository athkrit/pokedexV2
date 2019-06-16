package com.example.pokedex.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonTypeDao implements Parcelable {
    @SerializedName("slot")
    private Integer slot;
    @SerializedName("type")
    private List<PokemonTypeDetailDao> type;

    protected PokemonTypeDao(Parcel in) {
        if (in.readByte() == 0) {
            slot = null;
        } else {
            slot = in.readInt();
        }
        type = in.createTypedArrayList(PokemonTypeDetailDao.CREATOR);
    }

    public static final Creator<PokemonTypeDao> CREATOR = new Creator<PokemonTypeDao>() {
        @Override
        public PokemonTypeDao createFromParcel(Parcel in) {
            return new PokemonTypeDao(in);
        }

        @Override
        public PokemonTypeDao[] newArray(int size) {
            return new PokemonTypeDao[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (slot == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(slot);
        }
        dest.writeTypedList(type);
    }
}
