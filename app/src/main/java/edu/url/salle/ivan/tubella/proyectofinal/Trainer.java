package edu.url.salle.ivan.tubella.proyectofinal;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Trainer implements Parcelable {
    private static Trainer sTrainer;
    private String name;
    private int money;
    private ArrayList<String> items; //Cambiar a ArrayList<Pokeball> items
    private ArrayList<Captura> pokemons; //Cambiar a ArrayList<Pokemon> items o ArrayList<Captura> items

    protected Trainer(Parcel in) {
        name = in.readString();
        money = in.readInt();
        items = in.createStringArrayList();
    }

    public static final Creator<Trainer> CREATOR = new Creator<Trainer>() {
        @Override
        public Trainer createFromParcel(Parcel in) {
            return new Trainer(in);
        }

        @Override
        public Trainer[] newArray(int size) {
            return new Trainer[size];
        }
    };

    public static Trainer getInstance() {
        if (sTrainer == null) {
            sTrainer = new Trainer();
        }
        return sTrainer;
    }
    public Trainer() {
        this.name = "";
        this.money = 2000; //valor inicial per a que es pugui comprar algo al principi
        this.items = new ArrayList<>();
        this.pokemons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getMoney() {
        return money;
    }
    public void increaseMoney(int amount) {
        this.money += amount;
    }
    public void decreaseMoney(int amount) {
        this.money -= amount;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public ArrayList<String> getItems() {
        return items;
    }
    public void setItems(ArrayList<String> items) {
        this.items = items;
    }
    public void addItem(String item) {
        this.items.add(item);
    }
    public void eraseItem() {
        //this.items.remove();
    }
    public ArrayList<Captura> getPokemons() {
        return pokemons;
    }
    public void setPokemons(ArrayList<Captura> pokemons) {
        this.pokemons = pokemons;
    }
    public void addPokemon(Captura pokemon) {this.pokemons.add(pokemon);}
    public void erasePokemon() {
        //this.pokemons.remove();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(money);
        dest.writeStringList(items);
    }
}
