package com.example.robocapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
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

    public void editTeam(View view) {
        startActivity(new Intent(this, QRScannerAdmin.class));
    }

    public void disconnect(View view){
        startActivity(new Intent(this,MainActivity.class));
    }

}