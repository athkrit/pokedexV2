package com.example.pokedex.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.pokedex.R;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

public class RecyclerView extends android.support.v7.widget.RecyclerView {
    TextView tvName;

    public RecyclerView(@NonNull Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initInflate();
        initInstances();
    }
    private void initInflate() {
        inflate(getContext(), R.layout.list_item_name, this);
    }

    private void initInstances() {
        tvName= findViewById(R.id.tvPokemonName);
        // findViewById here
    }
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

}
