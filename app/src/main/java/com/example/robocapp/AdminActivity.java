package com.example.robocapp;

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

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        resizeImage(R.drawable.logo_are);
    }

    public void addUser(View view) {
        startActivity(new Intent(this, AddUser.class));
    }

    public void addTeam(View view) {
        startActivity(new Intent(this, AddTeam.class));
    }

    public void disconnect(View view){
        startActivity(new Intent(this,MainActivity.class));
    }

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