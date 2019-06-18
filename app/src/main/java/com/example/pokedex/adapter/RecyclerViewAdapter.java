package com.example.pokedex.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pokedex.R;
import com.example.pokedex.activity.MainActivity;
import com.example.pokedex.activity.PokemonDetailActivity;
import com.example.pokedex.dao.PokemonCollectionDao;
import com.example.pokedex.manager.PokemonNameManager;
import com.example.pokedex.view.RecyclerView;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

import java.util.ArrayList;

import static com.bumptech.glide.load.DecodeFormat.PREFER_ARGB_8888;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    Context context;

    public ArrayList<String> getPokemonPictureNumber() {
        return pokemonPictureNumber;
    }

    public void setPokemonPictureNumber(ArrayList<String> pokemonPictureNumber) {
        this.pokemonPictureNumber = pokemonPictureNumber;
    }

    ArrayList<String> pokemonPictureNumber = new ArrayList<String>();


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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PokemonDetailActivity.class);
                intent.putExtra("pokemonName", PokemonNameManager.getInstance().getDao().getResults().get(i).getName());
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE: {
                                Toast.makeText(Contextor.getInstance().getContext()
                                        , "Delete " + PokemonNameManager.getInstance().getDao().getResults().get(i).getName()
                                        , Toast.LENGTH_SHORT).show();
                                PokemonNameManager.getInstance().getDao().getResults().remove(i);
                                pokemonPictureNumber.remove(i);
                                notifyItemRemoved(i);
                                notifyItemRangeChanged(i, PokemonNameManager.getInstance().getDao().getResults().size());
                            }
                            case DialogInterface.BUTTON_NEGATIVE: {
                                break;
                            }
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Delete this pokemon from this device ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
                return false;
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
        TextView tvPokemonName;
        ImageView imageView;
        ProgressBar progressBar;

        public Holder(View itemView) {
            super(itemView);
            tvPokemonName = (TextView) itemView.findViewById(R.id.tvPokemonName);
            imageView = (ImageView) itemView.findViewById(R.id.imgView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
        }

        public void setItem(int position) {
            tvPokemonName.setText(PokemonNameManager.getInstance().getDao().getResults().get(position).getName());
            Glide.with(Contextor.getInstance().getContext())
                    .applyDefaultRequestOptions(new RequestOptions()
                            .placeholder(R.drawable.pokeball)
                            .error(R.drawable.pokeball))
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemonPictureNumber.get(position + 1) + ".png")
                    .format(PREFER_ARGB_8888)
                    .fitCenter()
                    .into(imageView);
        }
    }
}