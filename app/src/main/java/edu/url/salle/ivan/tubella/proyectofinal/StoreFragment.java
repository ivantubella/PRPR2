package edu.url.salle.ivan.tubella.proyectofinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;

public class StoreFragment extends Fragment {
    private Trainer trainer;
    private TextView tvMoney;
    private ImageButton btnPokeball;
    private ImageButton btnSuperball;
    private ImageButton btnUltraball;
    private ImageButton btnMasterball;


    public StoreFragment(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_store, container, false);

        this.tvMoney = (TextView) v.findViewById(R.id.textViewMoney);
        this.btnPokeball = (ImageButton) v.findViewById(R.id.buttonPokeball);
        this.btnSuperball = (ImageButton) v.findViewById(R.id.buttonSuperball);
        this.btnUltraball = (ImageButton) v.findViewById(R.id.buttonUltraball);
        this.btnMasterball = (ImageButton) v.findViewById(R.id.buttonMasterball);

        updateMoney();
        buttonsFunctionality();

        return v;
    }

    private void buttonsFunctionality() {
        btnPokeball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trainer.getMoney() >= 200) {
                    trainer.decreaseMoney(200);
                    updateMoney();
                    trainer.addItem("Pokeball");
                    Toast.makeText(getActivity(), "YOU JUST BOUGHT A POKEBALL!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "THERE'S NOT ENOUGH MONEY TO BUY THIS ITEM", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSuperball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trainer.getMoney() >= 500) {
                    trainer.decreaseMoney(500);
                    updateMoney();
                    trainer.addItem("Superball");
                    Toast.makeText(getActivity(), "YOU JUST BOUGHT A SUPERBALL!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "THERE'S NOT ENOUGH MONEY TO BUY THIS ITEM", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUltraball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trainer.getMoney() >= 1500) {
                    trainer.decreaseMoney(1500);
                    updateMoney();
                    trainer.addItem("Ultraball");
                    Toast.makeText(getActivity(), "YOU JUST BOUGHT AN ULTRABALL!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "THERE'S NOT ENOUGH MONEY TO BUY THIS ITEM", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMasterball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (trainer.getMoney() >= 100000) {
                    trainer.decreaseMoney(100000);
                    updateMoney();
                    trainer.addItem("Masterball");
                    Toast.makeText(getActivity(), "YOU JUST BOUGHT A MASTERBALL!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "THERE'S NOT ENOUGH MONEY TO BUY THIS ITEM", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void updateMoney() {
        this.tvMoney.setText("Money: ".concat(Integer.toString(this.trainer.getMoney()).concat(" $")));
    }

}