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

public class JurySuiveurActivity extends AppCompatActivity {
    TextView team_id;
    CheckBox cb0, cb1, cb2, cb3, cb4,cb5,cb6,cb7,cb8,cb9,eliminated; //checkboxes
    EditText time,cause;
    RadioButton rb1,rb2,rb3,rb4;
    RadioGroup rg;
    Button btnReset, btnSubmit, btnSecondRace;
    DatabaseReference teamsRef = FirebaseDatabase.getInstance().getReference().child("teams");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury_suiveur);

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
        cb9 = findViewById(R.id.checkBox9);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        rb4 = findViewById(R.id.radio4);
        rg = findViewById(R.id.chemin);

        eliminated = findViewById(R.id.eliminated);
        time = findViewById(R.id.temps);
        cause = findViewById(R.id.cause);

        btnSecondRace = findViewById(R.id.secondRace);
        btnReset = findViewById(R.id.imageButton1);
        btnSubmit = findViewById(R.id.imageButton2);

        final float[] score_jury = {0};
        final boolean[] secondRace = {false};
        btnSecondRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = team_id.getText().toString();
                secondRace[0] = true;
                score_jury[0]+=10;
                if (cb0.isChecked()) {
                    score_jury[0] += 20;
                    cb0.setChecked(false);
                }
                if (cb1.isChecked()) {
                    score_jury[0] += 30;
                    cb1.setChecked(false);
                }
                if (cb2.isChecked()) {
                    score_jury[0] += 40;
                    cb2.setChecked(false);
                }
                if (cb3.isChecked()) {
                    score_jury[0] += 50;
                    cb3.setChecked(false);
                }
                if (cb4.isChecked()) {
                    score_jury[0] += 10;
                    cb4.setChecked(false);
                }
                if (cb5.isChecked()) {
                    score_jury[0] += 40;
                    cb5.setChecked(false);
                }
                if (cb6.isChecked()) {
                    score_jury[0] += 50;
                    cb6.setChecked(false);
                }
                if (cb7.isChecked()) {
                    score_jury[0] += 30;
                    cb7.setChecked(false);
                }
                if (cb8.isChecked()) {
                    score_jury[0] += 70;
                    cb8.setChecked(false);
                }
                if (rb1.isChecked()) {
                    score_jury[0] += 20;
                }
                if (rb2.isChecked()) {
                    score_jury[0] += 40;
                }
                if (rb3.isChecked()) {
                    score_jury[0] += 60;
                }
                if (rb4.isChecked()) {
                    score_jury[0] += 80;
                }
                rg.clearCheck();
            }
        });
             btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String id = team_id.getText().toString();
                    if (cb0.isChecked()) {
                        score_jury[0] += 20;
                    }
                    if (cb1.isChecked()) {
                        score_jury[0] += 30;
                    }
                    if (cb2.isChecked()) {
                        score_jury[0] += 40;
                    }
                    if (cb3.isChecked()) {
                        score_jury[0] += 50;
                    }
                    if (cb4.isChecked()) {
                        score_jury[0] += 10;
                    }
                    if (cb5.isChecked()) {
                        score_jury[0] += 40;
                    }
                    if (cb6.isChecked()) {
                        score_jury[0] += 50;
                    }
                    if (cb7.isChecked()) {
                        score_jury[0] += 30;
                    }
                    if (cb8.isChecked()) {
                        score_jury[0] += 70;
                    }
                    if (rb1.isChecked()) {
                        score_jury[0] += 20;
                    }
                    if (rb2.isChecked()) {
                        score_jury[0] += 40;
                    }
                    if (rb3.isChecked()) {
                        score_jury[0] += 60;
                    }
                    if (rb4.isChecked()) {
                        score_jury[0] += 80;
                    }
                    if(!secondRace[0]&&cb9.isChecked()){
                        score_jury[0] += 150;
                    }
                    float t=0;
                    if(cb9.isChecked()){
                        if (time.getText().toString().equals(""))
                            t=0;
                        else
                            t = Float.parseFloat(time.getText().toString());
                        if(secondRace[0]){
                            t= (float) ((480-t)*0.3);
                        } else {
                            t= (float) ((240-t)*0.3);
                        }
                        if(t<0) t=0;
                    }
                    boolean e = eliminated.isChecked();
                    String c = cause.getText().toString();
                    float finalT = t;
                    teamsRef.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //String ch = dataSnapshot.child("concours").getValue().toString();
                        teamsRef.child(id).child("time").setValue(finalT);
                        teamsRef.child(id).child("eliminated").setValue(e);
                        if(e){
                            teamsRef.child(id).child("cause").setValue(c);
                        }
                        Team team = dataSnapshot.getValue(Team.class);
                        assert team != null;
                        int h = team.getScore_homologation();
                        float j = team.getScore_jury();
                        float r = (float)score_jury[0]+h+finalT;
                        if(j<r){
                            teamsRef.child(id).child("score_jury").setValue(r).addOnSuccessListener(suc -> // set to add or update
                            {
                                Toast.makeText(JurySuiveurActivity.this, "The score of "+data+"  is successfully set", Toast.LENGTH_SHORT).show();
                            }).addOnFailureListener(er -> {
                                Toast.makeText(JurySuiveurActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                        } else {
                            Toast.makeText(JurySuiveurActivity.this, "The score of "+data+"  is successfully set", Toast.LENGTH_SHORT).show();
                        }

                        // 2nd method : (without using a map)
                        //teamsRef.child(team_id.getText().toString()).setValueAsync(new team(team_name.getText().toString(),score_jury));
                        Intent intent = new Intent(JurySuiveurActivity.this, NavbarJury.class);
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
                if(cb9.isChecked()){
                    cb9.setChecked(false);
                }
                rg.clearCheck();
            }
        });
    } // onCreate() ends.



    /*
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
    }*/
}