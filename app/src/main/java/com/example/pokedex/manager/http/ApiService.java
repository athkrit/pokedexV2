package com.example.pokedex.manager.http;

import com.example.pokedex.dao.PokemonCollectionDao;
import com.example.pokedex.dao.PokemonDetailCollectionDao;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("pokemon")
    Call<PokemonCollectionDao> loadPokemonName(@Query("offset")int offset,@Query("limit") int limit);

    @GET("pokemon/{name}")
    Call<PokemonDetailCollectionDao> loadPokemonDetail(@Path("name") String name);
}
