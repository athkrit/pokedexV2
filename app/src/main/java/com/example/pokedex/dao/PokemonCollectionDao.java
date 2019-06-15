package com.example.pokedex.dao;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonCollectionDao implements Parcelable {
    @SerializedName("count")        private Integer count;
    @SerializedName("next")         private String next;
    @SerializedName("previous")     private Object previous;
    @SerializedName("results")      private List<PokemonResultDao> results = null;

    public PokemonCollectionDao(){

    }

    protected PokemonCollectionDao(Parcel in) {
        if (in.readByte() == 0) {
            count = null;
        } else {
            count = in.readInt();
        }
        next = in.readString();
        results = in.createTypedArrayList(PokemonResultDao.CREATOR);
    }

    public static final Creator<PokemonCollectionDao> CREATOR = new Creator<PokemonCollectionDao>() {
        @Override
        public PokemonCollectionDao createFromParcel(Parcel in) {
            return new PokemonCollectionDao(in);
        }

        @Override
        public PokemonCollectionDao[] newArray(int size) {
            return new PokemonCollectionDao[size];
        }
    };

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<PokemonResultDao> getResults() {
        return results;
    }

    public void setResults(List<PokemonResultDao> results) {
        this.results = results;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(count);
        }
        dest.writeString(next);
        dest.writeTypedList(results);
    }

}
