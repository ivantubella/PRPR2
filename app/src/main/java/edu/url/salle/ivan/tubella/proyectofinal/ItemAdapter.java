package edu.url.salle.ivan.tubella.proyectofinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    private ArrayList<String> lItems;
    private Activity activity;
    private boolean isCapturar;
    private Trainer trainer;
    private String pokemon;


    public ItemAdapter(ArrayList<String> lItems, Activity activity,Trainer trainer,boolean isCapturar) {
        this.lItems = lItems;
        this.activity = activity;
        this.trainer=trainer;
        this.isCapturar=isCapturar;
    }
    public ItemAdapter(ArrayList<String> lItems, Activity activity,Trainer trainer,boolean isCapturar,String pokemon) {
        this.lItems = lItems;
        this.activity = activity;
        this.trainer=trainer;
        this.isCapturar=isCapturar;
        this.pokemon=pokemon;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        return new ItemHolder(layoutInflater, parent, this);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        String item = lItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return lItems.size();
    }

    public void removeItem(int position,String pokeball) {
        if (isCapturar){
            if (position >= 0 && position < lItems.size()) {

                ArrayList<Captura> capturas= trainer.getPokemons();

                boolean denegado=false;
                if (!capturas.isEmpty()){
                    for (Captura c : capturas ) {
                        String a = c.getPokemon();
                        if (a.equals(pokemon)){
                            denegado=true;
                        }
                    }
                }else{
                    denegado=false;
                }

                if (denegado){
                    Toast.makeText(activity, "NO SE PUEDE CAPTURAR UN POKEMON YA CAPTURADO", Toast.LENGTH_SHORT).show();

                }else{
                    trainer.eraseItem(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, lItems.size());
                    trainer.addPokemon(new Captura(pokemon,pokeball));
                    Toast.makeText(activity, "POKEMON CAPTURADO", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }}
