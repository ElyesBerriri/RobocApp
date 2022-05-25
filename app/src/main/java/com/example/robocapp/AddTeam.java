package com.example.robocapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
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

public class AddTeam extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //private ProgressBar progressBar;
    private DatabaseReference reference;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        resizeImage(R.drawable.lo);
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

        reference.child(teamid).setValue(team).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(AddTeam.this,"User has been registered successfully!",Toast.LENGTH_LONG).show();
                    //progressBar.setVisibility(View.GONE);
                    //redirect to adminActivity
                    startActivity(new Intent(AddTeam.this, AdminActivity.class));
                }else{
                    Toast.makeText(AddTeam.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                    //progressBar.setVisibility(View.GONE);
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


    public Drawable resizeImage(int imageResource) {
        Display display = getWindowManager().getDefaultDisplay();
        double deviceWidth = display.getWidth();
        BitmapDrawable bd = (BitmapDrawable) this.getResources().getDrawable(imageResource);
        double imageHeight = bd.getBitmap().getHeight();
        double imageWidth = bd.getBitmap().getWidth();
        double ratio = deviceWidth / imageWidth;
        int newImageHeight = (int) (imageHeight * ratio);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), imageResource);
        return new BitmapDrawable(this.getResources(),
                getResizedBitmap(bMap, newImageHeight, (int) deviceWidth));
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);
    }

}