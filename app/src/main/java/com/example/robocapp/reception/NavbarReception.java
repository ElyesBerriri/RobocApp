package com.example.robocapp.reception;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.robocapp.MainActivity;
import com.example.robocapp.R;
import com.example.robocapp.databinding.NavbarReceptionBinding;
import com.example.robocapp.reception.AutonomeReception;
import com.example.robocapp.reception.JuniorReception;
import com.example.robocapp.reception.SuiveurReception;
import com.example.robocapp.reception.ToutTerrainReception;

public class NavbarReception extends AppCompatActivity {
    NavbarReceptionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = NavbarReceptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefragment(new SuiveurReception());
        binding.navbarrec.setOnItemSelectedListener(item -> {

            switch(item.getItemId()) {
                case R.id.suiv:
                    replacefragment(new SuiveurReception());
                    break;
                case R.id.jun:
                    replacefragment(new JuniorReception());
                    break;
                case R.id.tterr:
                    replacefragment(new ToutTerrainReception());
                    break;
                case R.id.auto:
                    replacefragment(new AutonomeReception());
                    break;
            }
            return true;
        });
    }
    private void replacefragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bcrec,fragment);
        fragmentTransaction.commit();
    }

    public void disconnect(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}

