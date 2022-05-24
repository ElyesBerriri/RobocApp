package com.example.loginacessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.loginacessapp.databinding.ActivityRecnavbarBinding;

public class recnavbar extends AppCompatActivity {
    ActivityRecnavbarBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecnavbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefragment(new suiveurrec());
        binding.navbarrec.setOnItemSelectedListener(item -> {

            switch(item.getItemId()) {
                case R.id.suiv:
                    replacefragment(new suiveurrec());
                    break;
                case R.id.jun:
                    replacefragment(new juniorrec());
                    break;
                case R.id.tterr:
                    replacefragment(new tterrainrec());
                    break;
                case R.id.auto:
                    replacefragment(new autonomerec());
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
        startActivity(new Intent(this,MainActivity.class));
    }
}

