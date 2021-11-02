package com.example.budjet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class LoginActivity extends AppCompatActivity {
    private EditText loginEmail, loginPassword;
    private Button loginButton;
    private TextView loginQn;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        loginQn = findViewById(R.id.loginQn);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


    loginQn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view)  {
            Intent intent = new Intent(LoginActivity.this, SelectRestrationActivity.class);
            startActivity(intent);
        }

    });

    loginButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
        String loginEmailString = loginEmail.getText().toString();
        String loginPasswordString = loginPassword.getText().toString();

        if (TextUtils.isEmpty(loginEmailString)){
            loginEmail.setError("Email is required ");
        }
        if (TextUtils.isEmpty(loginPasswordString)){

            loginPassword.setError("Password is required");
        }
        else{

            progressDialog.setMessage("login in progress");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(loginEmailString, loginPasswordString).addOnCompleteListener(task ->  {
                    if (task.isSuccessful()){
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        progressDialog.dismiss();
                    }
                        else {
                        Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT ).show();
                        progressDialog.dismiss();
                    }



            });
        }
        }
    });








    }
}