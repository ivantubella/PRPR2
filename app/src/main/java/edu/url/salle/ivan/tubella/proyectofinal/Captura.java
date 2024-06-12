package edu.url.salle.ivan.tubella.proyectofinal;

public class Captura {
    private String pokemon;
    private String pokemonUrl;
    private String pokeballUrl;

    public Captura(String pokemon,String pokemonUrl, String pokeballUrl) {
        this.pokemon = pokemon;
        this.pokeballUrl = pokeballUrl;
        this.pokemonUrl = pokemonUrl;
    }

    public String getPokemon() {
        return pokemon;
    }
    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }
    public String getPokeballUrl() {
        return pokeballUrl;
    }
    public void setPokeballUrl(String pokeball) {
        this.pokeballUrl = pokeball;
    }
    public String getPokemonUrl() {
        return pokemonUrl;
    }
    public void setPokemonUrl(String pokemonUrl) {
        this.pokemonUrl = pokemonUrl;
    }
}
