package com.example.loginacessapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class juryActivity extends AppCompatActivity {
    private static final int
            score_task1 = 10, score_task2 = 20,
            score_task3 = 30, score_task4 = 40,
            score_task5 = 50, score_task6 = 60,
            score_task7 = 70, score_task8 = 80,
            score_task9 = 90;
    EditText team_name,team_id;
    CheckBox cb1, cb2, cb3, cb4,cb5,cb6,cb7,cb8,cb9; //checkboxes
    ImageButton btnReset, btnSubmit;
    DatabaseReference teamsRef = FirebaseDatabase.getInstance().getReference().child("teams");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jury);

        Intent intent = getIntent();
        String data = intent.getStringExtra(qrscanner1.DATA);

        team_id=findViewById(R.id.team_id);


        System.out.println("old data is "+data);
        team_id.setText(data);

        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);
        cb4 = findViewById(R.id.checkBox4);
        cb5 = findViewById(R.id.checkBox5);
        cb6 = findViewById(R.id.checkBox6);
        cb7 = findViewById(R.id.checkBox7);
        cb8 = findViewById(R.id.checkBox8);
        //cb9 = findViewById(R.id.checkBox9);

        btnReset = findViewById(R.id.imageButton1);
        btnSubmit = findViewById(R.id.imageButton2);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String id = team_id.getText().toString();
                int score_jury = 0;
                if (cb1.isChecked()) {
                    score_jury += score_task1;
                }
                if (cb2.isChecked()) {
                    score_jury += score_task2;
                }
                if (cb3.isChecked()) {
                    score_jury += score_task3;
                }
                if (cb4.isChecked()) {
                    score_jury += score_task4;
                }
                if (cb5.isChecked()) {
                    score_jury += score_task5;
                }
                if (cb6.isChecked()) {
                    score_jury += score_task6;
                }
                if (cb7.isChecked()) {
                    score_jury += score_task7;
                }
                if (cb8.isChecked()) {
                    score_jury += score_task8;
                }

                System.out.println("score jury =" + score_jury);
                System.out.println("team id =" + team_id.getText().toString());
                int s = score_jury;

                teamsRef.child(id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String ch = dataSnapshot.child("concours").getValue().toString();
                        teamsRef.child(id).child("score_jury").setValue(s).addOnSuccessListener(suc -> // set to add or update
                        {
                            Toast.makeText(juryActivity.this, "Le score de ** "+data+" ** est bien ajouté", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(er -> {
                            Toast.makeText(juryActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                        // 2nd method : (without using a map)
                        //teamsRef.child(team_id.getText().toString()).setValueAsync(new team(team_name.getText().toString(),score_jury));
                        Intent intent = new Intent(juryActivity.this, jurynavbar.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (cb1.isChecked())
                    cb1.setChecked(false);
                if (cb2.isChecked())
                    cb2.setChecked(false);
                if (cb3.isChecked())
                    cb3.setChecked(false);
                if (cb4.isChecked())
                    cb4.setChecked(false);
                if (cb5.isChecked())
                    cb5.setChecked(false);
                if (cb6.isChecked())
                    cb6.setChecked(false);
                if (cb7.isChecked())
                    cb7.setChecked(false);
                if (cb8.isChecked())
                    cb8.setChecked(false);
            }
        });
    } // onCreate() ends.

    public void onCheckBoxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkBox1:
                if (checked)
                    Log.d("CheckBox1", "Checked");
                else
                    Log.d("CheckBox1", "Unchecked");
                break;
            case R.id.checkBox2:
                if (checked)
                    Log.d("CheckBox2", "Checked");
                else
                    Log.d("CheckBox2", "Unchecked");
                break;
            case R.id.checkBox3:
                if (checked)
                    Log.d("CheckBox3", "Checked");
                else
                    Log.d("CheckBox3", "Unchecked");
                break;
            case R.id.checkBox4:
                if (checked)
                    Log.d("CheckBox4", "Checked");
                else
                    Log.d("CheckBox4", "Unchecked");
                break;
            case R.id.checkBox5:
                if (checked)
                    Log.d("CheckBox5", "Checked");
                else
                    Log.d("CheckBox5", "Unchecked");
                break;
            case R.id.checkBox6:
                if (checked)
                    Log.d("CheckBox6", "Checked");
                else
                    Log.d("CheckBox6", "Unchecked");
                break;
            case R.id.checkBox7:
                if (checked)
                    Log.d("CheckBox7", "Checked");
                else
                    Log.d("CheckBox7", "Unchecked");
                break;
            case R.id.checkBox8:
                if (checked)
                    Log.d("CheckBox8", "Checked");
                else
                    Log.d("CheckBox8", "Unchecked");
                break;
        }
    }
}