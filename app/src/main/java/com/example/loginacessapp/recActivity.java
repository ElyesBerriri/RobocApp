package com.example.loginacessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class recActivity extends AppCompatActivity {
    TextView team_id;
    Button homebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec);

        Intent intent = getIntent();
        String data = intent.getStringExtra(QRScanRec.DATA);
        homebtn = findViewById(R.id.home_rec);
        team_id=findViewById(R.id.name_rec);
        team_id.setText(data);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recActivity.this, recnavbar.class);
                startActivity(intent);
            }
        });
    }
}
