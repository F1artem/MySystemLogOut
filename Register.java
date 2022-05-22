package com.example.mysystemlogout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    Button butReg;
    EditText username, password;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.writeLog);
        password = (EditText) findViewById(R.id.writePass);
        butReg = (Button) findViewById(R.id.butReg);

        auth = FirebaseAuth.getInstance();

        butReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }
private void createUser(){
    String email = username.getText().toString();
    String passWord = password.getText().toString();
    if (email.isEmpty()) {
        Toast.makeText(Register.this,"Электронная почта не может быть пустая!",Toast.LENGTH_SHORT).show();
    } else if (passWord.isEmpty()) {
        Toast.makeText(Register.this,"Пароль не может быть пустым!",Toast.LENGTH_SHORT).show();
    }else {
        auth.createUserWithEmailAndPassword(email,passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Register.this,"Успешная регистрация", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Login.class));
                }else{
                    Toast.makeText(Register.this,"Ошибка регистрации", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
}