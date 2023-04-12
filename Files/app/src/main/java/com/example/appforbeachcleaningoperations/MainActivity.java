package com.example.appforbeachcleaningoperations;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    Button login;
    TextView forgotPassword;
    TextView createAccount;
    ProgressBar progressBar;

    //Checks if user is already logged in
    ////The following block of code was taken from https://firebase.google.com/docs/auth/android/password-auth#java_1
    //Start
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i);
            finish();
        }
    }
    //End

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.loginEmail);
        editTextPassword = findViewById(R.id.loginPassword);
        login = findViewById(R.id.loginBtn);
        progressBar = findViewById(R.id.loginProgressBar);
        createAccount = findViewById(R.id.mainActivityCreateAccount);
        forgotPassword = findViewById(R.id.forgotPassword);

        //if user clicks Existing Member?
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(i);
                finish();
            }
        });


        //if user forgets their password
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checks email inputted
                String forgotPasswordEmail;
                forgotPasswordEmail = String.valueOf(editTextEmail.getText());
                if (TextUtils.isEmpty(forgotPasswordEmail) || !Patterns.EMAIL_ADDRESS.matcher(forgotPasswordEmail).matches()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                mAuth.sendPasswordResetEmail(forgotPasswordEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                       public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Successfully sent password reset to inputed email!", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Failed to send password reset to email!", Toast.LENGTH_LONG).show();
                            }
                       }
                       });
                }
            }
        });

        //if user clicks login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());


                //checks if email input is empty
                if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //checks if password input is empty
                if (TextUtils.isEmpty(password)) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //Connecting to Firebase with user's inputed Email and Password
                //The following block of code was taken from https://firebase.google.com/docs/auth/android/password-auth#java_1
                //Start
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {

                                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                //End

            }
        });

    }
}