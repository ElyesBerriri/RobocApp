package com.example.loginacessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void addUser(View view) {
        Intent intent = new Intent(this,addUser.class);
        startActivity(intent);
    }

    public void addTeam(View view) {
        Intent intent = new Intent(this,addTeam.class);
        startActivity(intent);
    }

    public void disconnect(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}