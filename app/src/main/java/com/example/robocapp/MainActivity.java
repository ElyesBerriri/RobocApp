package com.example.robocapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.example.robocapp.homologation.NavbarHomologation;
import com.example.robocapp.jury.NavbarJury;
import com.example.robocapp.reception.NavbarReception;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText username,password;
    //private Switch active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        //active = findViewById(R.id.active);

    }


    public void loginClick(View v) {
        System.out.println("111111111111111111111111111111111111111111111");/*
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("login");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            String input1;
            String input2;

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!username.getText().toString().trim().equals(""))
                    input1 = username.getText().toString().trim();
                else
                    input1 = "none";
                if(!password.getText().toString().trim().equals(""))
                    input2 = password.getText().toString().trim();
                else
                    input2 = "none";

                if (dataSnapshot.child(input1).exists()) {
                    if (Objects.requireNonNull(dataSnapshot.child(input1).child("password").getValue()).toString().equals(input2)) {
                                /*if (active.isChecked()) {
                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                        Preferences.setDataLogin(MainActivity.this, true);
                                        Preferences.setDataAs(MainActivity.this, "admin");
                                        startActivity(new Intent(MainActivity.this, AdminActivity.class));
                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("réception")){
                                        Preferences.setDataLogin(MainActivity.this, true);
                                        Preferences.setDataAs(MainActivity.this, "réception");
                                        startActivity(new Intent(MainActivity.this, NavbarReception.class));
                                    }
                                    else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("jury")){
                                        Preferences.setDataLogin(MainActivity.this, true);
                                        Preferences.setDataAs(MainActivity.this, "jury");
                                        startActivity(new Intent(MainActivity.this, NavbarJury.class));
                                    }
                                    else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("homologation")){
                                        Preferences.setDataLogin(MainActivity.this, true);
                                        Preferences.setDataAs(MainActivity.this, "homologation");
                                        startActivity(new Intent(MainActivity.this, NavbarHomologation.class));
                                    }
                                } else {
                        System.out.println("2222222222222222222222222222222222");
                        switch (Objects.requireNonNull(dataSnapshot.child(input1).child("as").getValue(String.class))) {
                            case "admin":
                                //Preferences.setDataLogin(MainActivity.this, false);
                                startActivity(new Intent(MainActivity.this, AdminActivity.class));

                                break;
                            case "réception":
                                //Preferences.setDataLogin(MainActivity.this, false);
                                startActivity(new Intent(MainActivity.this, NavbarReception.class));
                                break;
                            case "jury":
                                //Preferences.setDataLogin(MainActivity.this, false);
                                startActivity(new Intent(MainActivity.this, NavbarJury.class));
                                break;
                            case "homologation":
                                //Preferences.setDataLogin(MainActivity.this, false);
                                startActivity(new Intent(MainActivity.this, NavbarHomologation.class));
                                break;
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Password incorrect!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Username incorrect!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }

    public void fbClick(View view) {
        startActivity(getOpenFacebookIntent());
    }
    public Intent getOpenFacebookIntent() {
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/209793105724365"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/association.robotique.ensi"));
        }
    }

    public void instaClick(View view) {
        Uri uri = Uri.parse("http://instagram.com/_u/association.robotique.ensi");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/association.robotique.ensi")));
        }
    }

    public void linkedinClick(View view) {
        Uri uri = Uri.parse("https://tn.linkedin.com/company/cr-ensi");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://tn.linkedin.com/company/cr-ensi")));
        }
    }

}