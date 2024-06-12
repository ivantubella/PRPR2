package edu.url.salle.ivan.tubella.proyectofinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;
public class TrainerFragment extends Fragment {
    private Trainer trainer;
    public RecyclerView itemsRecyclerView;
    private ItemAdapter itemAdapter;
    public RecyclerView pokemonsCapturatsRecyclerView;
    private CapturaAdapter capturaAdapter;
    private EditText editText;
    private TextView tvMoney;

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

        //////ITEMS//////
        itemsRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewItems);
        v.findViewById(R.id.recyclerViewItems);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

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

        this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) { //user just left it, tapped outside
                    String trainerName = editText.getText().toString();
                    trainer.setName(trainerName);
                    Log.d("TAG NAME", trainerName);
                    //editText.setText(trainerName);
                }
            }
        });

        //////ITEMS//////
        ArrayList<String> lItem = /*new ArrayList<>();//*/ trainer.getItems();
        /*for (int i = 0; i <= 10; i++) {
            lItem.add(Integer.toString(i));
        }*/
        itemAdapter = new ItemAdapter(lItem, getActivity());
        //li diem a la nostra recyclerview que aquest es el seu adapter
        itemsRecyclerView.setAdapter(itemAdapter);

        //////CAPTURAS//////
        ArrayList<Captura> lPokemons = trainer.getPokemons();
        /*for (int i = 0; i <= 1; i++) {
            lPokemons.add(new Captura(Integer.toString(i), "",Integer.toString(i+1)));
        }*/
        capturaAdapter = new CapturaAdapter(lPokemons, getActivity(), trainer);
        //li diem a la nostra recyclerview que aquest es el seu adapter
        pokemonsCapturatsRecyclerView.setAdapter(capturaAdapter);
    }

}