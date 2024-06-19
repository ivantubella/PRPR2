package edu.url.salle.ivan.tubella.proyectofinal;

import java.util.ArrayList;

public class PokemonRespuestaEspecifico {

    private ArrayList<Abilidades> abilities;
    private int height;
    private int weight;
    private ArrayList<Stats> stats;
    private ArrayList<Types> types;
    private Sprites sprites;


    public ArrayList<Abilidades> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Abilidades> abilities) {
        this.abilities = abilities;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList<Stats> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Stats> stats) {
        this.stats = stats;
    }

    public ArrayList<Types> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Types> types) {
        this.types = types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites Sprites) {
        this.sprites = sprites;
    }
}
