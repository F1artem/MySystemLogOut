package com.example.mysystemlogout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button butLogin;
    EditText username, password;
    TextView regHere;
    FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.writeLog1);
        password = (EditText) findViewById(R.id.writePass1);
        butLogin = (Button) findViewById(R.id.butLog1);
        regHere = (TextView) findViewById(R.id.regHere1);

        myAuth = FirebaseAuth.getInstance();
        butLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
        regHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        String email = username .getText().toString();
        String passWord = password.getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(Login.this,"Электронная почта не может быть пустая!",Toast.LENGTH_SHORT).show();
//
        } else if (passWord.isEmpty()) {
            Toast.makeText(Login.this,"Пароль не может быть пустым!",Toast.LENGTH_SHORT).show();
//
        }else{
            myAuth.signInWithEmailAndPassword(email,passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this,"Успешный вход!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this,MainActivity.class));
                    }else{
                        Toast.makeText(Login.this,"Ошибка авторизации",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}