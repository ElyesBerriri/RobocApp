package com.example.loginacessapp.homologation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.loginacessapp.QRScanner;
import com.example.loginacessapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InputActivity extends AppCompatActivity {

    DatabaseReference db;
    String maquette = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Intent intent = getIntent();
        String data = intent.getStringExtra(QRScanner.DATA);
        Button btn = findViewById(R.id.btnscore);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((TextView)findViewById(R.id.score)).getText().toString().equals("SCORE")){
                    Toast.makeText(InputActivity.this, "Tu dois appuier sur le bouton 'OBTENIR LE SCORE' ", Toast.LENGTH_SHORT).show();
                }
                else{
                    int s = Integer.parseInt(((TextView)findViewById(R.id.score)).getText().toString());
                    db = FirebaseDatabase.getInstance().getReference("teams").child(data);
                    db.child("score_homologation").setValue(s).addOnSuccessListener(suc-> // set to add or update
                    {
                        Toast.makeText(InputActivity.this, "Le score de ** "+data+" ** est bien ajouté", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(er-> {
                        Toast.makeText(InputActivity.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                    startActivity(new Intent(InputActivity.this,AcceuilActivity.class));
/*
                    db.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            maquette = dataSnapshot.child("concours").getValue().toString();
                            if (maquette.equals("junior")) {
                                startActivity(new Intent(InputActivity.this, juniorhomo.class));
                            } else if (maquette.equals("autonome")) {
                                startActivity(new Intent(InputActivity.this, autonomehomo.class));
                            } else if (maquette.equals("suiveur")) {
                                startActivity(new Intent(InputActivity.this, suiveurhomo.class));
                            } else if (maquette.equals("toutterrain")) {
                                startActivity(new Intent(InputActivity.this, tterrainhomo.class));
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}

                    });

 */
                }
            }
        });
    }

    public void sum(View v) {
        int d;
        if (((EditText)findViewById(R.id.note_design)).getText().toString().equals(""))
            d=0;
        else
            d = Integer.parseInt(((EditText)findViewById(R.id.note_design)).getText().toString());

        int m;
        if (((EditText)findViewById(R.id.note_meca)).getText().toString().equals(""))
            m=0;
        else
            m = Integer.parseInt(((EditText)findViewById(R.id.note_meca)).getText().toString());

        int e;
        if (((EditText)findViewById(R.id.note_elec)).getText().toString().equals(""))
            e=0;
        else
            e = Integer.parseInt(((EditText)findViewById(R.id.note_elec)).getText().toString());

        int t = d + m + e;
        CheckBox checkBox = findViewById(R.id.checkbox_respect);
        if(checkBox.isChecked())
            ((TextView)findViewById(R.id.score)).setText(""+t);
        else
            ((TextView)findViewById(R.id.score)).setText(-1+"");
    }
}