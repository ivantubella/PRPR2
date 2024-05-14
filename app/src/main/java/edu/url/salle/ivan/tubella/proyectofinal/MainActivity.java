package edu.url.salle.ivan.tubella.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import edu.url.salle.ivan.tubella.proyectofinal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private Trainer trainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new PokeBallFragment(trainer));

        //CARREGAR SHARED PREFERENCES, COMPROBAR SI NULL
        trainer = Trainer.getInstance();
        String jsonTrainer = readSharedPreferences();
        if (jsonTrainer != null && !jsonTrainer.isEmpty()) {
            Gson gson = new Gson();
            trainer = gson.fromJson(jsonTrainer, Trainer.class);
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.pokeball) {
                replaceFragment(new PokeBallFragment(trainer));
            } else if (item.getItemId() == R.id.trainer) {
                replaceFragment(new TrainerFragment(trainer));
            } else if (item.getItemId() == R.id.store) {
                replaceFragment(new StoreFragment(trainer));
            }
            return true;
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG NAME ON STOP", this.trainer.getName());
        actualizaSharedPreferences();
        //Log.d(TAG, "onStop() called");
    }

    private void actualizaSharedPreferences() {
        Log.d("TAG ACTUALIZA SHARED", "shared");
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Gson gson = new Gson();
        String json = gson.toJson(this.trainer);
        Log.d("TAG JSON SHARED", json);
        editor.putString("Trainer", json);
        editor.apply(); //ASYNC
        //editor.commit();//SYNC --> puede llegar a parar la ejecución de la UI
    }

    private String readSharedPreferences() {
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString("Trainer", "");
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }
}