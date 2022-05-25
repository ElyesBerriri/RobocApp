package com.example.robocapp;

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
        startActivity(new Intent(this, AddUser.class));
    }

    public void addTeam(View view) {
        startActivity(new Intent(this, AddTeam.class));
    }

    public void disconnect(View view){
        startActivity(new Intent(this,MainActivity.class));
    }
}