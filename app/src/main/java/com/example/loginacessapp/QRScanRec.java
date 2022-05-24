package com.example.loginacessapp;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginacessapp.homologation.AutonomeActivity;
import com.example.loginacessapp.homologation.InputActivity;
import com.example.loginacessapp.homologation.JuniorActivity;
import com.example.loginacessapp.homologation.SuiveurActivity;
import com.example.loginacessapp.homologation.ToutTerrainActivity;
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

public class QRScanRec extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    DatabaseReference teamsRef = FirebaseDatabase.getInstance().getReference().child("teams");
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
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {}
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    public void handleResult(Result rawResult) {
        String data = rawResult.getText().toString();

        teamsRef.child(data).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ch = dataSnapshot.child("concours").getValue().toString();
                teamsRef.child(data).child("pres").setValue(true).addOnSuccessListener(suc-> // set to add or update
                {
                    Toast.makeText(QRScanRec.this, "Le Robot ** "+data+" ** est bien ajouté", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er-> {
                    Toast.makeText(QRScanRec.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });
                startActivity(new Intent(QRScanRec.this, recnavbar.class));
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
