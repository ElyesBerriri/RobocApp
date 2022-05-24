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

public class addUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ProgressBar progressBar;
    private DatabaseReference reference;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        spinner = (Spinner) findViewById(R.id.spinner_user);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_user_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void addTeam(View view) {

        EditText editText1 = (EditText) findViewById(R.id.EtNom);
        String username = editText1.getText().toString().trim();

        EditText editText2 = (EditText) findViewById(R.id.EtPass);
        String password = editText2.getText().toString().trim();

        reference = FirebaseDatabase.getInstance().getReference("login");

        User user = new User(username, spinner.getSelectedItem().toString(), password);
        System.out.println(user);

        reference.child(username).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(addUser.this,"User has been registered successfully!",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                    //redirect to adminActivity
                    startActivity(new Intent(addUser.this, AdminActivity.class));
                }else{
                    Toast.makeText(addUser.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
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
//===================
}