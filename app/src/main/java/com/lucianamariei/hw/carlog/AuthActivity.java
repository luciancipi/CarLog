package com.lucianamariei.hw.carlog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();

        Button signInButton = findViewById(R.id.auth_sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        Button signUpButton = findViewById(R.id.auth_sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    }

    private void updateUI(FirebaseUser user) {
        if(user != null) {
            onAuthComplete();
        }
    }

    private void onAuthComplete() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private String getEmail() {
        return ((EditText)findViewById(R.id.auth_email)).getText().toString();
    }

    private String getPassword() {
        return ((EditText)findViewById(R.id.auth_password)).getText().toString();
    }


    private void signIn() {
        mAuth.signInWithEmailAndPassword(getEmail(), getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(AuthActivity.this,
                                    R.string.auth_error_sign_in, Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void signUp() {
        mAuth.createUserWithEmailAndPassword(getEmail(), getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(AuthActivity.this,
                                    R.string.auth_error_sign_up, Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }
                    }
                });
    }
}
