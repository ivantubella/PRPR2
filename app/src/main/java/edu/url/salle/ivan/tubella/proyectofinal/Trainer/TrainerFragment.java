package edu.url.salle.ivan.tubella.proyectofinal.Trainer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import edu.url.salle.ivan.tubella.proyectofinal.items.ItemAdapter;
import edu.url.salle.ivan.tubella.proyectofinal.R;
import edu.url.salle.ivan.tubella.proyectofinal.captura.Captura;
import edu.url.salle.ivan.tubella.proyectofinal.captura.CapturaAdapter;

public class TrainerFragment extends Fragment {
    private Trainer trainer;
    public RecyclerView itemsRecyclerView;
    private ItemAdapter itemAdapter;
    public RecyclerView pokemonsCapturatsRecyclerView;
    private CapturaAdapter capturaAdapter;
    private EditText editText;
    private Button btnSave;
    private TextView tvMoney;
    private TextView tvNoItems;
    private TextView tvNoCapturas;
    private TextView tvPokeball;
    private TextView tvSuperball;
    private TextView tvUltraball;
    private TextView tvMasterball;

    public TrainerFragment(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trainer, container, false);

        this.editText = (EditText) v.findViewById(R.id.tvNameTrainer);
        this.tvMoney = (TextView) v.findViewById(R.id.tvMoney);
        this.btnSave = (Button) v.findViewById(R.id.buttonSave);
        this.tvNoItems = (TextView) v.findViewById(R.id.textViewNoItems);
        this.tvNoCapturas = (TextView) v.findViewById(R.id.textViewNoCapturas);

        this.tvPokeball = (TextView) v.findViewById(R.id.textViewPokeball);
        this.tvSuperball = (TextView) v.findViewById(R.id.textViewSuperball);
        this.tvUltraball = (TextView) v.findViewById(R.id.textViewUltraball);
        this.tvMasterball = (TextView) v.findViewById(R.id.textViewMasterball);

        //////ITEMS//////
        //itemsRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewItems);
        //v.findViewById(R.id.recyclerViewItems);
        //itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //////CAPTURAS//////
        pokemonsCapturatsRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewPokemons);
        v.findViewById(R.id.recyclerViewPokemons);
        pokemonsCapturatsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_trainer, container, false);
    }

    private void updateUI() {
        this.tvMoney.setText(Integer.toString(this.trainer.getMoney()).concat(" $"));
        this.editText.setText(this.trainer.getName());

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.clearFocus();
            }
        });

        this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) { //user just left it, tapped outside
                    String trainerName = editText.getText().toString();
                    trainer.setName(trainerName);
                    Log.d("TAG NAME", trainerName);
                }
            }
        });

        //////ITEMS//////
        ArrayList<String> lItem = trainer.getItems();
        int ctrPokeball = 0, ctrSuperball = 0, ctrUltraball = 0, ctrMasterball = 0;
        for (String s : lItem) {
            switch (s) {
                case "Pokeball":
                    ctrPokeball++;
                    break;
                case "Superball":
                    ctrSuperball++;
                    break;
                case "Ultraball":
                    ctrUltraball++;
                    break;
                case "Masterball":
                    ctrMasterball++;
                    break;
            }
        }

        this.tvPokeball.setText(Integer.toString(ctrPokeball));
        this.tvSuperball.setText(Integer.toString(ctrSuperball));
        this.tvUltraball.setText(Integer.toString(ctrUltraball));
        this.tvMasterball.setText(Integer.toString(ctrMasterball));

        //itemAdapter = new ItemAdapter(lItem, getActivity(),trainer,false);
        //li diem a la nostra recyclerview que aquest es el seu adapter
        //itemsRecyclerView.setAdapter(itemAdapter);

        //////CAPTURAS//////
        ArrayList<Captura> lPokemons = trainer.getPokemons();
        capturaAdapter = new CapturaAdapter(lPokemons, getActivity(), trainer);
        pokemonsCapturatsRecyclerView.setAdapter(capturaAdapter);

        if(lItem.isEmpty()) {
            this.tvNoItems.setText("You don't have items yet. Go to the store to purchase some.");
        } else {
            this.tvNoItems.setText("");
        }

        if(lPokemons.isEmpty()) {
            this.tvNoCapturas.setText("You haven't captured any pokemon yet. Go to the pokedex to catch them all!");
        } else {
            this.tvNoCapturas.setText("");
        }
        if ((lPokemons.size() == 6)) {
            this.tvNoCapturas.setText("You already have captured 6 pokemons. Click on any capture to liberate it.");
        }
    }

}