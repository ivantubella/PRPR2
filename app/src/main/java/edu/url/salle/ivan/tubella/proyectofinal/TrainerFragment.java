package edu.url.salle.ivan.tubella.proyectofinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainerFragment extends Fragment {
    private Trainer trainer;
    private Gson gson;
    public RecyclerView itemsRecyclerView;
    private ItemAdapter itemAdapter;
    public RecyclerView pokemonsCapturatsRecyclerView;
    private CapturaAdapter capturaAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    public TrainerFragment(/*Trainer trainer*/) {
        // Required empty public constructor
        //this.trainer = trainer;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrainerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrainerFragment newInstance(String param1, String param2) {
        TrainerFragment fragment = new TrainerFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);


        //TODO CARGAR LOS SHAREDPREFERENCES EN VARIABLE TRAINER LOCAL

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.gson = new Gson();
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trainer, container, false);

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
        Trainer trainer = Trainer.getInstance(getActivity());

        //////ITEMS//////
        ArrayList<String> lItem = trainer.getItems();
        for (int i = 0; i <= 10; i++) {
            lItem.add(Integer.toString(i));
        }
        itemAdapter = new ItemAdapter(lItem, getActivity());
        //li diem a la nostra recyclerview que aquest es el seu adapter
        itemsRecyclerView.setAdapter(itemAdapter);

        //////CAPTURAS//////
        ArrayList<Captura> lPokemons = trainer.getPokemons();
        for (int i = 0; i <= 10; i++) {
            lPokemons.add(new Captura(Integer.toString(i), Integer.toString(i+1)));
        }
        capturaAdapter = new CapturaAdapter(lPokemons, getActivity());
        //li diem a la nostra recyclerview que aquest es el seu adapter
        pokemonsCapturatsRecyclerView.setAdapter(capturaAdapter);
    }

    private void actualizaSharedPreferences() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String json = this.gson.toJson(this.trainer);
        editor.putString("Trainer", json);
        editor.apply();
    }

    private Trainer readSharedPreferences() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        String json = sharedPref.getString("Trainer", "");
        this.trainer = this.gson.fromJson(json, Trainer.class);
        //int defaultValue = getResources().getInteger(R.integer.saved_high_score_default_key);
        //int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);

        return this.trainer;
    }


}