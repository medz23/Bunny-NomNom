package com.example.bunnynomnom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.stream.Stream;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class Login extends AppCompatActivity {
    CircularProgressButton circularProgressButton;
    TextView signIn;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        circularProgressButton = findViewById(R.id.circularLoginBt);
        signIn = findViewById(R.id.signInClick);

        email = findViewById(R.id.editTextInp);
        password = findViewById(R.id.editTextPass);

        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if(emailText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(Login.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    UserDataBase userDataBase = UserDataBase.getUserDataBase(getApplicationContext());
                    UserDao userDao = userDataBase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                                UserEntity userEntity = userDao.login(emailText, passwordText);
                                if(userEntity == null){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(Login.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    
                                }
                                else{
                                    Intent intent = new Intent(Login.this, MainActivity.class);// New activity
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                                    finish();
                                }
                        }
                    }).start();
                }

            }


        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);// New activity
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                finish();
            }
        });
    }
}