package com.example.robocapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.adapters.TextViewBindingAdapter;

import com.example.robocapp.jury.QRScannerJury;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTeam extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Team globalteam = new Team();
    Spinner spinner;
    private final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("teams");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);

        Intent intent = getIntent();
        String data = intent.getStringExtra(QRScannerAdmin.DATA);
        
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        spinner = (Spinner) findViewById(R.id.editspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        reference.child(data).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Team team = dataSnapshot.getValue(Team.class);
                globalteam = team;
                TextView editText0 = findViewById(R.id.editteamid);
                editText0.setText(team.team_id);

                EditText editText1 = findViewById(R.id.editteamname);
                editText1.setText(team.team_name);

                EditText editText2 = findViewById(R.id.editchef);
                editText2.setText(team.team_chef);

                EditText editText4 = findViewById(R.id.editnumtel);
                editText4.setText(team.num_tel+"");

                switch (team.concours) {
                    case "junior":
                        spinner.setSelection(0);
                        break;
                    case "suiveur":
                        spinner.setSelection(1);
                        break;
                    case "autonome":
                        spinner.setSelection(2);
                        break;
                    default:
                        spinner.setSelection(3);
                        break;
                }

                TextView editText5 = findViewById(R.id.pres);
                if(team.pres) editText5.setText("Oui");
                else editText5.setText("Non");

                TextView editText6 = findViewById(R.id.homologation);
                editText6.setText(team.score_homologation+"");

                TextView editText7 = findViewById(R.id.jury);
                editText7.setText(team.score_jury+"");

                TextView editText8 = findViewById(R.id.edittime);
                editText8.setText(team.time+"");

                TextView editText9 = findViewById(R.id.editeliminated);
                if(team.eliminated) editText9.setText("Oui");
                else editText9.setText("Non");

                TextView editText10 = findViewById(R.id.editcause);
                editText10.setText(team.cause);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    public void onEditTeam(View view) {

        TextView  editText0 = findViewById(R.id.editteamid);
        String teamid = editText0.getText().toString().trim();

        EditText  editText1 = findViewById(R.id.editteamname);
        String teamname = editText1.getText().toString().trim();

        EditText editText2 = findViewById(R.id.editchef);
        String chef = editText2.getText().toString().trim();

        EditText editText4= findViewById(R.id.editnumtel);
        int numtel;
        if(editText4.getText().toString().trim().equals(""))
            numtel = 0;
        else
            numtel = Integer.parseInt(editText4.getText().toString().trim());

        if(teamid.equals("")) {
            Toast.makeText(EditTeam.this, "You have to enter the robot name", Toast.LENGTH_SHORT).show();
        }
        else if(teamname.equals("")) {
            Toast.makeText(EditTeam.this, "You have to enter the team name", Toast.LENGTH_SHORT).show();
        }
        else if(chef.equals("")) {
            Toast.makeText(EditTeam.this, "You have to enter the chef name", Toast.LENGTH_SHORT).show();
        }
        else if(numtel==0) {
            Toast.makeText(EditTeam.this, "You have to enter the phone number", Toast.LENGTH_SHORT).show();
        }
        else{
            globalteam.setTeam_chef(chef);
            globalteam.setTeam_name(teamname);
            globalteam.setConcours(spinner.getSelectedItem().toString());
            globalteam.setNum_tel(numtel);
            reference.child(teamid).setValue(globalteam).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()){
                        Toast.makeText(EditTeam.this,"Team has been registered successfully!",Toast.LENGTH_LONG).show();
                        //progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(EditTeam.this, AdminActivity.class));
                    }else{
                        Toast.makeText(EditTeam.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                        //progressBar.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    //===== spinner =====
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
    //==================

}