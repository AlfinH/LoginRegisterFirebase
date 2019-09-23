package com.alfin.loginregisterfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText edRegisEmail,edRegisPassword;
    private Button btnRegis;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();

        edRegisEmail = findViewById(R.id.ed_regis_email);
        edRegisPassword = findViewById(R.id.ed_regis_password);
        btnRegis = findViewById(R.id.btn_regis);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edRegisEmail.getText().toString().trim();
                String password = edRegisPassword.getText().toString();
                if(email.isEmpty()){
                    edRegisEmail.setError("Please enter your new email");
                    edRegisEmail.requestFocus();
                }
                else if(password.isEmpty()){
                    edRegisPassword.setError("Please enter your new password");
                    edRegisPassword.requestFocus();
                }
                else if(!email.isEmpty() && !password.isEmpty()){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Error Occured!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goLoginAct(View v){
        Intent goLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(goLogin);
    }
}
