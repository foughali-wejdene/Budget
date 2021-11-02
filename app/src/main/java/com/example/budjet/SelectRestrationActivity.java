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

public class SelectRestrationActivity extends AppCompatActivity {
    private EditText loginEmail, loginPassword;
    private Button Registerbtn;
    private TextView RegisterQn;

    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_restration);

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        Registerbtn = findViewById(R.id.Registerbtn);
        RegisterQn = findViewById(R.id.RegisterQn);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        RegisterQn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)  {
                Intent intent = new Intent(SelectRestrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });

        Registerbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String loginEmailString = loginEmail.getText().toString();
                String loginPasswordString = loginPassword.getText().toString();

                if (TextUtils.isEmpty(loginEmailString)){
                    loginEmail.setError("Email is required ");
                }
                if (TextUtils.isEmpty(loginPasswordString)){

                    loginPassword.setError("Password is required");
                }
                else{

                    progressDialog.setMessage("Register in progress");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(loginEmailString, loginPasswordString).addOnCompleteListener((task) ->  {

                            if (task.isSuccessful()){
                                Intent intent = new Intent(SelectRestrationActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                progressDialog.dismiss();
                            }
                            else {
                                Toast.makeText(SelectRestrationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT ).show();
                                progressDialog.dismiss();
                            }



                    });


                }



            }
            });



    }
}