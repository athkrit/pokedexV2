package com.example.pokedex.manager.http;

import com.example.pokedex.dao.PokemonCollectionDao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("pokemon")
    Call<PokemonCollectionDao> loadPokemonName(@Query("offset")int offset,@Query("limit") int limit);

}
