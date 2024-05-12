package edu.url.salle.ivan.tubella.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import edu.url.salle.ivan.tubella.proyectofinal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private Trainer trainer;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.trainer = new Trainer();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new PokeBallFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.pokeball) {
                replaceFragment(new PokeBallFragment());
            } else if (item.getItemId() == R.id.trainer) {
                replaceFragment(new TrainerFragment(/*this.trainer*/));
            } else if (item.getItemId() == R.id.store) {
                replaceFragment(new StoreFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, fragment);
        ft.commit();
    }
}