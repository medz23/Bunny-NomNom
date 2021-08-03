package com.example.bunnynomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class Register extends AppCompatActivity {
    CircularProgressButton circularProgressButton;
    ImageView arrowBack;
    EditText bName, name, email, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bName = findViewById(R.id.editTextOName);
        name = findViewById(R.id.editTextBName);
        email = findViewById(R.id.editTextE);
        password = findViewById(R.id.editTextPassword1);
        password2 = findViewById(R.id.editTextPassword2);

        circularProgressButton = findViewById(R.id.circularRegisterBt);
        arrowBack = findViewById(R.id.arrow);

        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final UserEntity userEntity = new UserEntity();
                userEntity.setName(name.getText().toString());
                userEntity.setbName(bName.getText().toString());
                userEntity.setEmail(email.getText().toString());
                userEntity.setPassword(password.getText().toString());
                userEntity.setPassword2(password2.getText().toString());
                if (validateInput(userEntity)) {
                    UserDataBase userDataBase = UserDataBase.getUserDataBase(getApplicationContext());
                    final UserDao userDao = userDataBase.userDao();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                userDao.registerUser(userEntity);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "User Registered!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).start();
                    Intent intent = new Intent(Register.this, ImageAsk.class);// New activity
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                    finish();

                    }

                else {
                    Toast.makeText(Register.this, "huston we have a problem", Toast.LENGTH_SHORT).show();
                }



            }
        });

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);// New activity
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
                finish();
            }
        });
    }

    private Boolean validateInput(UserEntity userEntity) {
        if (userEntity.getName().isEmpty() || userEntity.getbName().isEmpty() || userEntity.getEmail().isEmpty() || userEntity.getPassword().isEmpty() || userEntity.getPassword2().isEmpty()) {
            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!userEntity.getPassword().equals(userEntity.getPassword2())) {
            Toast.makeText(this, "Passwords doesn't match!", Toast.LENGTH_SHORT).show();
            return false;
        } else {

            return true;
        }
    }

}