package com.example.bunnynomnom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;


public class ImageAsk extends AppCompatActivity {
    ImageView profile;
    FloatingActionButton fab;
    CircularProgressButton circularProgressButton;
    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
    int REQUEST_CODE = 12345;
    boolean isPermissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_ask);

        profile = findViewById(R.id.imageViewProfile);
        fab = findViewById(R.id.floatingActionBtProfile);
        circularProgressButton = findViewById(R.id.circularBtProfile);

        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageAsk.this, Login.class);// New activity
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();

            }
        });
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Complete Action Using"), REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(ImageAsk.this, permissions, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                profile.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            Toast.makeText(this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    }
}
