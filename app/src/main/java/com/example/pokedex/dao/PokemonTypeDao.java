package com.example.pokedex.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonTypeDao implements Parcelable {
    @SerializedName("slot")             private Integer slot;

    protected PokemonTypeDao(Parcel in) {
        if (in.readByte() == 0) {
            slot = null;
        } else {
            slot = in.readInt();
        }
        type = in.readParcelable(PokemonTypeDetailDao.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (slot == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(slot);
        }
        parcel.writeParcelable(type, i);
    }
}
