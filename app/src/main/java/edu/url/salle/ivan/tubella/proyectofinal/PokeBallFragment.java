package edu.url.salle.ivan.tubella.proyectofinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;

public class PokeBallFragment extends Fragment {

    private Trainer trainer;

    public PokeBallFragment(Trainer trainer) {
        this.trainer = trainer;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_poke_ball, container, false);
    }
}