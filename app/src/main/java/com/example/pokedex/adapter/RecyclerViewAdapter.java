package com.example.pokedex.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pokedex.R;
import com.example.pokedex.dao.PokemonCollectionDao;
import com.example.pokedex.manager.PokemonNameManager;
import com.example.pokedex.view.RecyclerView;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import static com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {
    public PokemonCollectionDao getDao() {
        return dao;
    }

    public void setDao(PokemonCollectionDao dao) {
        this.dao = dao;
    }

    PokemonCollectionDao dao;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_name, viewGroup, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        holder.setItem(i);
        holder.tvPokemonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Contextor.getInstance().getContext(),"Toast"+i,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (PokemonNameManager.getInstance().getDao() == null)
            return 0;
        if (PokemonNameManager.getInstance().getDao().getResults() == null)
            return 0;
        return PokemonNameManager.getInstance().getDao().getResults().size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tvPokemonName, tvPokemonUrl;
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            tvPokemonName = (TextView) itemView.findViewById(R.id.tvPokemonName);
            imageView = (ImageView) itemView.findViewById(R.id.imgView);
        }

        public void setItem(int position) {
            tvPokemonName.setText(PokemonNameManager.getInstance().getDao().getResults().get(position).getName());

            Glide.with(Contextor.getInstance().getContext())
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
                    .format(PREFER_ARGB_8888)
                    .into(imageView);

        }
    }

}
