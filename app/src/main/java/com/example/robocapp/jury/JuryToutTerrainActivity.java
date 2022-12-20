package com.example.robocapp.jury;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class JuryToutTerrainActivity extends AppCompatActivity {
    TextView team_id;
    CheckBox cb0, cb1, cb2, cb3, cb4,cb5,eliminated; //checkboxes
    EditText time,cause;
    RadioButton rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10,rb11,rb12,rb13,rb14,rb15,rb16,rb17,rb18,rb19,rb20;
    RadioGroup rg1,rg2,rg3,rg4,rg5,rg6;
    Button btnReset, btnSubmit;
    DatabaseReference teamsRef = FirebaseDatabase.getInstance().getReference().child("teams");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jury_toutterrain);

        Intent intent = getIntent();
        String data = intent.getStringExtra(QRScannerJury.DATA);

        team_id=findViewById(R.id.team_id);


        System.out.println("old data is "+data);
        team_id.setText(data);

        cb1 = findViewById(R.id.checkBox1);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);
        cb4 = findViewById(R.id.checkBox4);
        cb5 = findViewById(R.id.checkBox5);
        cb0 = findViewById(R.id.checkBox0);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        rb4 = findViewById(R.id.radio4);
        rb5 = findViewById(R.id.bronze1);
        rb6 = findViewById(R.id.bronze2);
        rb7 = findViewById(R.id.bronze3);
        rb8 = findViewById(R.id.bronze4);
        rb9 = findViewById(R.id.gold1);
        rb10 = findViewById(R.id.gold2);
        rb11 = findViewById(R.id.gold3);
        rb12 = findViewById(R.id.diamand1);
        rb13 = findViewById(R.id.cube1);
        rb14 = findViewById(R.id.cube2);
        rb15 = findViewById(R.id.cube3);
        rb16 = findViewById(R.id.cube4);
        rb17 = findViewById(R.id.wcube1);
        rb18 = findViewById(R.id.wcube2);
        rb19 = findViewById(R.id.wcube3);
        rb20 = findViewById(R.id.wcube4);
        rg1 = findViewById(R.id.chemin);
        rg2 = findViewById(R.id.bronze);
        rg3 = findViewById(R.id.gold);
        rg4 = findViewById(R.id.diamand);
        rg5 = findViewById(R.id.cubes);
        rg6 = findViewById(R.id.wcubes);

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
                    if (rb1.isChecked()) {
                        //-----M1-----
                        if (rb5.isChecked()) {
                            score_jury[0] += 10;
                        } else if (rb6.isChecked()) {
                            score_jury[0] += 20;
                        } else if (rb7.isChecked()) {
                            score_jury[0] += 30;
                        } else if (rb8.isChecked()) {
                            score_jury[0] += 40;
                        }
                        if (rb9.isChecked()) {
                            score_jury[0] += 30;
                        } else if (rb10.isChecked()) {
                            score_jury[0] += 60;
                        } else if (rb11.isChecked()) {
                            score_jury[0] += 90;
                        }
                        if (rb12.isChecked()) {
                            score_jury[0] += 50;
                        }
                        //------------
                    } else if (rb2.isChecked()) {
                        //-----M2-----
                        if (cb0.isChecked()) {
                            score_jury[0] += 80;
                        }
                        if (cb1.isChecked()) {
                            score_jury[0] += 100;
                        }
                        //------------
                    } else if (rb3.isChecked()) {
                        //-----M3-----
                        if (rb13.isChecked()) {
                            score_jury[0] += 45;
                        } else if (rb14.isChecked()) {
                            score_jury[0] += 90;
                        } else if (rb15.isChecked()) {
                            score_jury[0] += 135;
                        } else if (rb16.isChecked()) {
                            score_jury[0] += 180;
                        }
                        if (rb17.isChecked()) {
                            score_jury[0] -= 10;
                        } else if (rb18.isChecked()) {
                            score_jury[0] -= 20;
                        } else if (rb19.isChecked()) {
                            score_jury[0] -= 30;
                        } else if (rb20.isChecked()) {
                            score_jury[0] -= 40;
                        }
                        //------------
                    } else if (rb4.isChecked()) {
                        //-----M4-----
                        if (cb2.isChecked()) {
                            score_jury[0] += 60;
                        }
                        if (cb3.isChecked()) {
                            score_jury[0] += 60;
                        }
                        if (cb4.isChecked()) {
                            score_jury[0] += 60;
                        }
                        //------------
                    }

                    float t;
                    if (time.getText().toString().equals(""))
                        t=0;
                    else
                        t = Float.parseFloat(time.getText().toString());
                    if(t>300){
                        t=0;
                    } else {
                        t= (float) ((360-t)*0.8);
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
                        float r = (float)score_jury[0]+finalT;
                        teamsRef.child(id).child("score_jury").setValue(r).addOnSuccessListener(suc -> // set to add or update
                        {
                            Toast.makeText(JuryToutTerrainActivity.this, "The score of "+data+"  is successfully set", Toast.LENGTH_SHORT).show();
                        }).addOnFailureListener(er -> {
                            Toast.makeText(JuryToutTerrainActivity.this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                        // 2nd method : (without using a map)
                        //teamsRef.child(team_id.getText().toString()).setValueAsync(new team(team_name.getText().toString(),score_jury));
                        Intent intent = new Intent(JuryToutTerrainActivity.this, NavbarJury.class);
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
                rg2.clearCheck();
                rg3.clearCheck();
                rg4.clearCheck();
                rg5.clearCheck();
                rg6.clearCheck();
            }
        });
    } // onCreate() ends.

    public void onMissionSelected(View view) {
        LinearLayout m1 = findViewById(R.id.M1);
        LinearLayout m2 = findViewById(R.id.M2);
        LinearLayout m3 = findViewById(R.id.M3);
        LinearLayout m4 = findViewById(R.id.M4);
        m1.setVisibility(View.GONE);
        m2.setVisibility(View.GONE);
        m3.setVisibility(View.GONE);
        m4.setVisibility(View.GONE);
        switch (view.getId()) {
            case R.id.radio1:
                m1.setVisibility(View.VISIBLE);
                break;
            case R.id.radio2:
                m2.setVisibility(View.VISIBLE);
                break;
            case R.id.radio3:
                m3.setVisibility(View.VISIBLE);
                break;
            case R.id.radio4:
                m4.setVisibility(View.VISIBLE);
                break;
        }
    }
}