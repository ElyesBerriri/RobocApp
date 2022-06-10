package com.example.robocapp.homologation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.robocapp.MainActivity;
import com.example.robocapp.R;
import com.example.robocapp.databinding.NavbarHomologationBinding;

public class NavbarHomologation extends AppCompatActivity {

    NavbarHomologationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("//////////////////////////////////////////");
        super.onCreate(savedInstanceState);
        binding = NavbarHomologationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replacefragment(new SuiveurHomologation());
        binding.BottomNavigationView.setOnItemSelectedListener(item -> {

            switch(item.getItemId()) {
                case R.id.btnsuiveur:
                    replacefragment(new SuiveurHomologation());
                    break;
                case R.id.btnjunior:
                    replacefragment(new JuniorHomologation());
                    break;
                case R.id.btntoutterrian:
                    replacefragment(new ToutTerrainHomologation());
                    break;
                case R.id.btnautonome:
                    replacefragment(new AutonomeHomologation());
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



