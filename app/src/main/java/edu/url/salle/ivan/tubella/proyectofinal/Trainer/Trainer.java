package edu.url.salle.ivan.tubella.proyectofinal.Trainer;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import edu.url.salle.ivan.tubella.proyectofinal.captura.Captura;

public class Trainer implements Parcelable {
    private static Trainer sTrainer;
    private String name;
    private int money;
    private ArrayList<String> items; //TODO Cambiar a ArrayList<Pokeball> items
    private ArrayList<Captura> pokemons;
    private boolean canCapture;

    protected Trainer(Parcel in) {
        name = in.readString();
        money = in.readInt();
        items = in.createStringArrayList();
        pokemons = in.createTypedArrayList(Captura.CREATOR);



    }

    public static final Creator<Trainer> CREATOR = new Creator<Trainer>() {
        @Override
        public Trainer createFromParcel(Parcel in) {
            return new Trainer(in);
        }
            //return new Trainer(in);

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
        this.canCapture = true;
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
    public void eraseItem(int index) {
            this.items.remove(index);
    }
    public ArrayList<Captura> getPokemons() {
        return pokemons;
    }
    public void setPokemons(ArrayList<Captura> pokemons) {
        this.pokemons = pokemons;
    }
    public int addPokemon(Captura pokemon) {
        if (this.pokemons.size() < 6) {
            this.pokemons.add(pokemon);
            if (this.pokemons.size() == 6) { //s'ha arribat a 6 captures ja
                this.canCapture = false;    //setejem que ja no pot capturar més
            }
            return 1; //se ha podido añadir la captura (habia sitio)
        } else {
            return 0; //NO se ha podido añadir la captura (NO habia sitio)
        }
    }
    public int erasePokemon(int index) {
        if (this.pokemons.size() > 1) {
            if (this.pokemons.size() == 6) { //si abans d'eliminar el pokemon la llista té ja 6 pokemons (MAXIM) al eliminar-lo la llista passarà a 5 captures i ja es podrà tornar a capturar
                this.canCapture = true;
            }
            this.pokemons.remove(index);
            return 1; //se ha podido eliminar la captura (no quedaba una captura)
        } else {
            return 0; //NO se ha podido eliminar la captura (quedaba una captura)
        }
    }
    public int numPokemonsCaptured() {
        return this.pokemons.size();
    }
    public boolean isCanCapture() {
        return canCapture;
    }
    public void updateCanCapture(boolean value) {
        this.canCapture = value;
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
        dest.writeList(pokemons);
    }
}
