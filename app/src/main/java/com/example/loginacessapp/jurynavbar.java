package com.example.loginacessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.loginacessapp.databinding.ActivityJurynavbarBinding;

import com.google.android.material.bottomnavigation.BottomNavigationView;
public class jurynavbar extends AppCompatActivity {

    ActivityJurynavbarBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJurynavbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefragment(new suiveur());
        binding.navbar.setOnItemSelectedListener(item -> {

            switch(item.getItemId()) {
                case R.id.suiveur:
                    replacefragment(new suiveur());
                    break;
                case R.id.junior:
                    replacefragment(new junior());
                    break;
                case R.id.tterrain:
                    replacefragment(new tterrain());
                    break;
                case R.id.autonome:
                    replacefragment(new autonome());
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
        startActivity(new Intent(this,MainActivity.class));
    }
}



