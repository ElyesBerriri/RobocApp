package com.example.robocapp.jury;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.robocapp.R;
import com.example.robocapp.Team;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JuryJuniorActivity extends AppCompatActivity {
    TextView team_id;
    CheckBox cb0, cb1, cb2, cb3, cb4,cb5,cb6,cb7,cb8,eliminated; //checkboxes
    EditText time,cause;
    Button btnReset, btnSubmit;
    DatabaseReference teamsRef = FirebaseDatabase.getInstance().getReference().child("teams");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury_junior);

        Intent intent = getIntent();
        String data = intent.getStringExtra(QRScannerJury.DATA);

        team_id=findViewById(R.id.team_id);
        team_id.setText(data);

        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);
        cb4 = findViewById(R.id.checkBox4);
        cb5 = findViewById(R.id.checkBox5);
        cb6 = findViewById(R.id.checkBox6);
        cb7 = findViewById(R.id.checkBox7);
        cb8 = findViewById(R.id.checkBox8);
        cb0 = findViewById(R.id.checkBox0);

        eliminated = findViewById(R.id.eliminated);
        time = findViewById(R.id.temps);
        cause = findViewById(R.id.cause);
        btnReset = findViewById(R.id.imageButton1);
        btnSubmit = findViewById(R.id.imageButton2);

        final float[] score_jury = {0};
             btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = team_id.getText().toString();
                    if (cb0.isChecked()) {
                        score_jury[0] += 10;
                    }
                    if (cb1.isChecked()) {
                        score_jury[0] += 20;
                    }
                    if (cb2.isChecked()) {
                        score_jury[0] += 30;
                    }
                    if (cb3.isChecked()) {
                        score_jury[0] += 30;
                    }
                    if (cb4.isChecked()) {
                        score_jury[0] += 50;
                    }
                    if (cb5.isChecked()) {
                        score_jury[0] += 30;
                    }
                    if (cb6.isChecked()) {
                        score_jury[0] += 20;
                    }
                    if (cb7.isChecked()) {
                        score_jury[0] += 40;
                    }
                    if (cb8.isChecked()) {
                        score_jury[0] += 30;
                    }
                    float t;
                    if (time.getText().toString().equals(""))
                        t=0;
                    else
                        t = Float.parseFloat(time.getText().toString());

                    if(t<=300) t= (float) ((360-t)*0.3);
                    else t=0;
                    boolean e = eliminated.isChecked();
                    String c = cause.getText().toString();
                    float finalT = t;
                    teamsRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        teamsRef.child(id).child("time").setValue(finalT);
                        teamsRef.child(id).child("eliminated").setValue(e);
                        if(e){
                            teamsRef.child(id).child("cause").setValue(c);
                        }
                        Team team = dataSnapshot.getValue(Team.class);
                        int h = team.getScore_homologation();
                        float r = (float)score_jury[0]+h+finalT;
                        teamsRef.child(id).child("score_jury").setValue(r).addOnSuccessListener(suc -> // set to add or update
                        {
                            Toast.makeText(JuryJuniorActivity.this, "The score of "+data+"  is successfully set", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(er -> {
                            Toast.makeText(JuryJuniorActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        // 2nd method : (without using a map)
                        //teamsRef.child(team_id.getText().toString()).setValueAsync(new team(team_name.getText().toString(),score_jury));
                        Intent intent = new Intent(JuryJuniorActivity.this, NavbarJury.class);
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
                if (cb0.isChecked())
                    cb0.setChecked(false);
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
    }
}