package com.example.robocapp.jury;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.robocapp.MainActivity;
import com.example.robocapp.R;
import com.example.robocapp.databinding.NavbarJuryBinding;
import com.example.robocapp.jury.AutonomeJury;
import com.example.robocapp.jury.JuniorJury;
import com.example.robocapp.jury.SuiveurJury;
import com.example.robocapp.jury.ToutTerrainJury;

public class NavbarJury extends AppCompatActivity {

    NavbarJuryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NavbarJuryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefragment(new SuiveurJury());
        binding.navbar.setOnItemSelectedListener(item -> {

            switch(item.getItemId()) {
                case R.id.suiveur:
                    replacefragment(new SuiveurJury());
                    break;
                case R.id.junior:
                    replacefragment(new JuniorJury());
                    break;
                case R.id.tterrain:
                    replacefragment(new ToutTerrainJury());
                    break;
                case R.id.autonome:
                    replacefragment(new AutonomeJury());
                    break;

            }
            return true;
        });
    }
    private void replacefragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bc,fragment);
        fragmentTransaction.commit();
    }

    public void disconnect(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}



