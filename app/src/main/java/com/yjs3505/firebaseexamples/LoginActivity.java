package com.yjs3505.firebaseexamples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * @author Bora SAYINER
 * @version 1.0.0
 * @since 14.04.2018 17:08
 */
public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getName();

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
        registerViewEvents();
    }

    private void initializeComponents() {
        txtUsername = findViewById(R.id.txt_username);
        txtPassword = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    private void registerViewEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                Task<AuthResult> resultTask = auth.signInWithEmailAndPassword(txtUsername.getText().toString(), txtPassword.getText().toString());
                resultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "onComplete: " + task.getException());
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "User name or password wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
