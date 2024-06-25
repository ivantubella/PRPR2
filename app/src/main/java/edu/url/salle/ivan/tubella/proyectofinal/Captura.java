package edu.url.salle.ivan.tubella.proyectofinal;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Captura implements Parcelable {
    private String pokemon;
    private String pokeball;
    private int shiny;
    private int id;

    public Captura(String pokemon,String pokeball, int shiny, int id) {
        this.pokemon = pokemon;
        this.pokeball = pokeball;
        this.shiny=shiny;
        this.id=id;
    }

    protected Captura(Parcel in) {
        pokemon = in.readString();
        pokeball = in.readString();

    }

    public static final Creator<Captura> CREATOR = new Creator<Captura>() {
        @Override
        public Captura createFromParcel(Parcel in) {
            return new Captura(in);
        }

        @Override
        public Captura[] newArray(int size) {
            return new Captura[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(pokemon);
        dest.writeString(pokeball);
    }

    public int getShiny() {
        return shiny;
    }

    public void setShiny(int shiny) {
        this.shiny = shiny;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
