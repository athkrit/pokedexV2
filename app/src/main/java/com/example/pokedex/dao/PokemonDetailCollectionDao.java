package com.example.pokedex.dao;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;

public class PokemonDetailCollectionDao {
    @SerializedName("abilities")                    private List<Array> abilities = null;
    @SerializedName("base_experience")              private Integer baseExperience;
    @SerializedName("forms")                        private List<Array> forms = null;

    public List<Array> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Array> abilities) {
        this.abilities = abilities;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }

    public List<Array> getForms() {
        return forms;
    }

    public void setForms(List<Array> forms) {
        this.forms = forms;
    }

    public List<Array> getGameIndices() {
        return gameIndices;
    }

    public void setGameIndices(List<Array> gameIndices) {
        this.gameIndices = gameIndices;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<Array> getHeldItems() {
        return heldItems;
    }

    public void setHeldItems(List<Array> heldItems) {
        this.heldItems = heldItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getLocationAreaEncounters() {
        return locationAreaEncounters;
    }

    public void setLocationAreaEncounters(String locationAreaEncounters) {
        this.locationAreaEncounters = locationAreaEncounters;
    }

    public List<Array> getMoves() {
        return moves;
    }

    public void setMoves(List<Array> moves) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Object getSpecies() {
        return species;
    }

    public void setSpecies(Object species) {
        this.species = species;
    }

    public PokemonSpriteDao getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSpriteDao sprites) {
        this.sprites = sprites;
    }

    public List<PokemonStatsDao> getStats() {
        return stats;
    }

    public void setStats(List<PokemonStatsDao> stats) {
        this.stats = stats;
    }

    public List<PokemonTypeDao> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonTypeDao> types) {
        this.types = types;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @SerializedName("game_indices")                 private List<Array> gameIndices = null;
    @SerializedName("height")                       private Integer height;
    @SerializedName("held_items")                   private List<Array> heldItems = null;
    @SerializedName("id")                           private Integer id;
    @SerializedName("is_default")                   private Boolean isDefault;
    @SerializedName("location_area_encounters")     private String locationAreaEncounters;
    @SerializedName("moves")                        private List<Array> moves = null;
    @SerializedName("name")                         private String name;
    @SerializedName("order")                        private Integer order;
    @SerializedName("species")                      private Object species;
    @SerializedName("sprites")                      private PokemonSpriteDao sprites;
    @SerializedName("stats")                        private List<PokemonStatsDao> stats = null;
    @SerializedName("types")                        private List<PokemonTypeDao> types = null;
    @SerializedName("weight")                       private Integer weight;
}
