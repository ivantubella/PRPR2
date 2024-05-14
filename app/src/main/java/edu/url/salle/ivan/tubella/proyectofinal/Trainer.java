package edu.url.salle.ivan.tubella.proyectofinal;

import android.content.Context;

import java.util.ArrayList;

public class Trainer {
    private static Trainer sTrainer;
    private String name;
    private int money;
    private ArrayList<String> items; //Cambiar a ArrayList<Pokeball> items
    private ArrayList<Captura> pokemons; //Cambiar a ArrayList<Pokemon> items o ArrayList<Captura> items

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
    public void addPokemon(Captura pokemon) {
        this.pokemons.add(pokemon);
    }
    public void erasePokemon() {
        //this.pokemons.remove();
    }
}
