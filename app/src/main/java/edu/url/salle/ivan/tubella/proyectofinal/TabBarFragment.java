package edu.url.salle.ivan.tubella.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabBarFragment extends Fragment implements View.OnClickListener {
    private static final int ID_test = 1; //codeRequest del startActivityForResult

    private Button btnPokedex;
    private Button btnTrainer;
    private Button btnShop;

    View view;

    public void setIdInitial() {
        btnPokedex = view.findViewById(R.id.buttonPokedex);
        btnPokedex.setOnClickListener(this);
        btnShop = view.findViewById(R.id.buttonShop);
        btnShop.setOnClickListener(this);
        btnTrainer = view.findViewById(R.id.buttonTrainer);
        btnTrainer.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el fragment amb el layout pertinent
        view = inflater.inflate(R.layout.fragment_tabbar, container, false);
        setIdInitial();
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == btnPokedex || view == btnTrainer || view == btnShop) {
            startActivityForTest();
        }
    }

    private void startActivityForTest() {
        //aquí el nostre comportament
        Intent intent = new Intent(getActivity(), actTest.class);
        startActivityForResult(intent, ID_test);
        // The request code is any int value.
        // The request code identifies the return result when the result arrives.
        // (You can call startActivityForResult more than once before you get any results. When results arrive, you use the request code to distinguish one result from another.)
        // Aquest codi s’utilitza per, una vegada tenim la resposta de l’activity, saber de quina activity ha vingut
    }
}
