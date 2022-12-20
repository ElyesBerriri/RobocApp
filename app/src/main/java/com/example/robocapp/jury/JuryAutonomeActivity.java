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

public class JuryAutonomeActivity extends AppCompatActivity {
    TextView team_id;
    CheckBox cb0, cb1, cb2, cb3,eliminated; //checkboxes
    EditText time,cause,mur;
    RadioButton rb1,rb2,rb3,rb4;
    RadioGroup rg1;
    RadioGroup rg2;
    Button btnReset, btnSubmit;
    DatabaseReference teamsRef = FirebaseDatabase.getInstance().getReference().child("teams");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury_autonome);

        Intent intent = getIntent();
        String data = intent.getStringExtra(QRScannerJury.DATA);

        team_id=findViewById(R.id.team_id);
        team_id.setText(data);

        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);
        cb0 = findViewById(R.id.checkBox0);
        rb1 = findViewById(R.id.radioo1);
        rb2 = findViewById(R.id.radioo2);
        rb3 = findViewById(R.id.radiod1);
        rb4 = findViewById(R.id.radiod2);
        rg1 = findViewById(R.id.oxygen);
        rg2 = findViewById(R.id.desinfinction);

        eliminated = findViewById(R.id.eliminated);
        time = findViewById(R.id.temps);
        cause = findViewById(R.id.cause);
        mur = findViewById(R.id.mur);
        btnReset = findViewById(R.id.imageButton1);
        btnSubmit = findViewById(R.id.imageButton2);

        final float[] score_jury = {0};
             btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = team_id.getText().toString();
                    if (cb0.isChecked()) {
                        score_jury[0] += 50;
                    }
                    if (cb1.isChecked()) {
                        score_jury[0] += 40;
                    }
                    if (cb2.isChecked()) {
                        score_jury[0] += 70;
                    }
                    if (cb3.isChecked()) {
                        score_jury[0] += 80;
                    }
                    if (rb1.isChecked()) {
                        score_jury[0] += 100;
                    }
                    if (rb2.isChecked()) {
                        score_jury[0] += 60;
                    }
                    if (rb3.isChecked()) {
                        score_jury[0] += 30;
                    }
                    if (rb4.isChecked()) {
                        score_jury[0] += 70;
                    }
                    float t;
                    if (time.getText().toString().equals(""))
                        t=0;
                    else
                        t = Float.parseFloat(time.getText().toString());
                    t= (float) ((300-t)*0.3);
                    if(t<0) t=0;
                    int touches;
                    if (mur.getText().toString().equals(""))
                        touches=0;
                    else
                        touches = Integer.parseInt(mur.getText().toString());
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
                        assert team != null;
                        int h = team.getScore_homologation();
                        float j = team.getScore_jury();
                        float r = (float)score_jury[0]+h+finalT+touches*(-5);
                        if(j<r){
                            teamsRef.child(id).child("score_jury").setValue(r).addOnSuccessListener(suc -> // set to add or update
                            {
                                Toast.makeText(JuryAutonomeActivity.this, "The score of "+data+"  is successfully set", Toast.LENGTH_SHORT).show();
                            }).addOnFailureListener(er -> {
                                Toast.makeText(JuryAutonomeActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                        } else {
                            Toast.makeText(JuryAutonomeActivity.this, "The score of "+data+"  is successfully set", Toast.LENGTH_SHORT).show();
                        }

                        // 2nd method : (without using a map)
                        //teamsRef.child(team_id.getText().toString()).setValueAsync(new team(team_name.getText().toString(),score_jury));
                        Intent intent = new Intent(JuryAutonomeActivity.this, NavbarJury.class);
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
                rg1.clearCheck();
                rg2.clearCheck();
            }
        });
    }
}