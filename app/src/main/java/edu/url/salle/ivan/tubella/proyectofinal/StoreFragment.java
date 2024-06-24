package edu.url.salle.ivan.tubella.proyectofinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import edu.url.salle.ivan.tubella.proyectofinal.Trainer.Trainer;

public class StoreFragment extends Fragment {
    private Trainer trainer;
    private TextView tvMoney;
    private Button btnPokeball;
    private Button btnSuperball;
    private Button btnUltraball;
    private Button btnMasterball;


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
        this.btnPokeball = (Button) v.findViewById(R.id.buttonPokeball);
        this.btnSuperball = (Button) v.findViewById(R.id.buttonSuperball);
        this.btnUltraball = (Button) v.findViewById(R.id.buttonUltraball);
        this.btnMasterball = (Button) v.findViewById(R.id.buttonMasterball);
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
                    //TODO ADD ITEM AL TRAINER + LLAMAR API PARA OBTENER LA POKEBALL
                    trainer.addItem("Pokeball");
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
                    //TODO ADD ITEM AL TRAINER + LLAMAR API PARA OBTENER LA SUPERBALL
                    trainer.addItem("Superball");
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
                    //TODO ADD ITEM AL TRAINER + LLAMAR API PARA OBTENER LA ULTRABALL
                    trainer.addItem("Ultraball");
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
                    //TODO ADD ITEM AL TRAINER + LLAMAR API PARA OBTENER LA MASTERBALL
                    trainer.addItem("Masterball");
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