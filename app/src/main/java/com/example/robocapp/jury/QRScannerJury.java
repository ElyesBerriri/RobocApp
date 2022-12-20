package com.example.robocapp.jury;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.robocapp.Team;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScannerJury extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    public static final String DATA = "com.example.qrcodetest2.EXTRA_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        scannerView.startCamera();
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void handleResult(Result rawResult) {

        String data = rawResult.getText().trim();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("teams");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(data).exists()) {
                    Team team = dataSnapshot.child(data).getValue(Team.class);
                    switch (team.concours) {
                        case "suiveur": {
                            Intent intent = new Intent(QRScannerJury.this, JurySuiveurActivity.class);
                            intent.putExtra(DATA, data);
                            startActivity(intent);
                            break;
                        }
                        case "autonome": {
                            Intent intent = new Intent(QRScannerJury.this, JuryAutonomeActivity.class);
                            intent.putExtra(DATA, data);
                            startActivity(intent);
                            break;
                        }
                        case "junior": {
                            Intent intent = new Intent(QRScannerJury.this, JuryJuniorActivity.class);
                            intent.putExtra(DATA, data);
                            startActivity(intent);
                            break;
                        }
                        case "toutterrain": {
                            Intent intent = new Intent(QRScannerJury.this, JuryToutTerrainActivity.class);
                            intent.putExtra(DATA, data);
                            startActivity(intent);
                            break;
                        }
                    }
                }
                else{
                    Toast.makeText(QRScannerJury.this, "There is no registred robot who has the name : "+data+" !!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(QRScannerJury.this, NavbarJury.class);
                    startActivity(intent);
                }
                onBackPressed();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}