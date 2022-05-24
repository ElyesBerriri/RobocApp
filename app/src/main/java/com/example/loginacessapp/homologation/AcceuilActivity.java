package com.example.loginacessapp.homologation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.loginacessapp.MainActivity;
import com.example.loginacessapp.R;
import com.example.loginacessapp.databinding.ActivityAcceuilBinding;

public class AcceuilActivity extends AppCompatActivity {

    ActivityAcceuilBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAcceuilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefragment(new suiveurhomo());
        binding.BottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()) {
                case R.id.btnsuiveur:
                    replacefragment(new suiveurhomo());
                    break;
                case R.id.btnjunior:
                    replacefragment(new juniorhomo());
                    break;
                case R.id.btntoutterrian:
                    replacefragment(new tterrainhomo());
                    break;
                case R.id.btnautonome:
                    replacefragment(new autonomehomo());
                    break;

            }
            return true;
        });
    }
    private void replacefragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bc1,fragment);
        fragmentTransaction.commit();
    }

    public void disconnect(View view){
        startActivity(new Intent(this, MainActivity.class));
    }

}



