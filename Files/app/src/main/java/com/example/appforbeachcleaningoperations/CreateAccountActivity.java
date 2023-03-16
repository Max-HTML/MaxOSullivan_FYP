package com.example.appforbeachcleaningoperations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText editTextUsername, editTextEmail, editTextPassword;
    Button signUp;
    ProgressBar progressBar;
    TextView existingAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_acitivity);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.createAccountEmail);
        editTextPassword = findViewById(R.id.createAccountPassword);
        signUp = findViewById(R.id.signUpBtn);
        progressBar = findViewById(R.id.progressBar);
        existingAccount = findViewById(R.id.existingAccount);

        existingAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateAccountActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());


                //checks if email input is empty
                if(TextUtils.isEmpty(email)){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(CreateAccountActivity.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //checks if password input is empty
                if(TextUtils.isEmpty(password)){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(CreateAccountActivity.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Connecting to Firebase with user's inputed Email and Password
                //The following block of code was taken from https://firebase.google.com/docs/auth/android/password-auth#java_1
                //Start
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // If sign in succeeds, alert will be displayed.
                                    Toast.makeText(CreateAccountActivity.this, "Authentication Successful.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, alert will be displayed.
                                    Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                //End

            }
        });
    }

}