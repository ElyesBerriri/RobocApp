package com.example.loginacessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addTeam extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ProgressBar progressBar;
    private DatabaseReference reference;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    public void addTeam(View view) {

        EditText  editText0 = (EditText) findViewById(R.id.teamid);
        String teamid = editText0.getText().toString().trim();

        EditText  editText1 = (EditText) findViewById(R.id.teamname);
        String teamname = editText1.getText().toString().trim();

        EditText editText2 = (EditText) findViewById(R.id.chef);
        String chef = editText2.getText().toString().trim();

        EditText editText4= (EditText) findViewById(R.id.numtel);
        long numtel = Integer.parseInt(editText4.getText().toString().trim());

        reference = FirebaseDatabase.getInstance().getReference("teams");

        Team team = new Team(teamid,teamname,chef,spinner.getSelectedItem().toString(),numtel);
        System.out.println(team);

        reference.child(teamid.toString()).setValue(team).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(addTeam.this,"User has been registered successfully!",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    //redirect to adminActivity
                    startActivity(new Intent(addTeam.this, AdminActivity.class));
                }else{
                    Toast.makeText(addTeam.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    //===== spinner =====
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
    //==================
}