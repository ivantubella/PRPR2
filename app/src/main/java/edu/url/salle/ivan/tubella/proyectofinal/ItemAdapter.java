package edu.url.salle.ivan.tubella.proyectofinal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;

public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
    private ArrayList<String> lItems;
    private Activity activity;
    private boolean isCapturar;
    private Trainer trainer;
    private String pokemon;
    private int tipo;


    public ItemAdapter(ArrayList<String> lItems, Activity activity,Trainer trainer,boolean isCapturar) {
        this.lItems = lItems;
        this.activity = activity;
        this.trainer=trainer;
        this.isCapturar=isCapturar;
    }
    public ItemAdapter(ArrayList<String> lItems, Activity activity,Trainer trainer,boolean isCapturar,String pokemon,int tipo) {
        this.lItems = lItems;
        this.activity = activity;
        this.trainer=trainer;
        this.isCapturar=isCapturar;
        this.pokemon=pokemon;
        this.tipo=tipo;
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

                    int limite=0, limite_menor=0;
                    if (tipo==0){
                    limite = 80;
                    limite_menor=20;
                    } else if (tipo==1) {
                        limite = 200;
                        limite_menor=80;
                    } else if (tipo==2) {
                        limite = 350;
                        limite_menor=200;
                    }else{
                        limite = 200;
                        limite_menor=350;
                    }
                    Random random = new Random();
                    int numeroAleatorio = random.nextInt(limite) + limite_menor;
                    /////////////////////cambiar esto a false
                    boolean capturado = true;

                    if (pokeball.equals("pokeball")){
                        
                    } else if (pokeball.equals("Superball")) {
                        
                    } else if (pokeball.equals("Ultraball")) {
                        
                    }else{
                        capturado = true;
                    }

                    if (capturado){
                        trainer.increaseMoney(400+100*numeroAleatorio);

                        trainer.eraseItem(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, lItems.size());
                        trainer.addPokemon(new Captura(pokemon,pokeball));
                        Toast.makeText(activity, "POKEMON CAPTURADO", Toast.LENGTH_SHORT).show();
                    }else {
                        trainer.eraseItem(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, lItems.size());
                        Toast.makeText(activity, "HAS FALLADO LA CAPTURA", Toast.LENGTH_SHORT).show();

                    }


                }
            }
        }
    }}
