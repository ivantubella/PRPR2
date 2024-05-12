package edu.url.salle.ivan.tubella.proyectofinal;

public class Captura {
    private String pokemon;
    private String pokeball;

    public Captura(String pokemon, String pokeball) {
        this.pokemon = pokemon;
        this.pokeball = pokeball;
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }

    public String getPokeball() {
        return pokeball;
    }

    public void setPokeball(String pokeball) {
        this.pokeball = pokeball;
    }
}
