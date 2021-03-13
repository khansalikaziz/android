package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class teacherlogin extends AppCompatActivity {
    EditText emailBox, passwordBox;
    Button loginBtn, signupBtn;
    FirebaseAuth auth;
    ProgressDialog dialog;
    CheckBox remember;
    TextView textView;
    String vali;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherlogin);
        dialog = new ProgressDialog(this);
        dialog.setMessage("You are logging in");
        dialog.setTitle("Sign in");
        auth = FirebaseAuth.getInstance();
        emailBox = findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);
        textView=(TextView) findViewById(R.id.textView3);
        loginBtn = findViewById(R.id.loginbtn);
        signupBtn = findViewById(R.id.createBtn);
        remember=findViewById(R.id.remember);

        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true")){
            Intent intent = new Intent(teacherlogin.this,Dashboard.class);
            startActivity(intent);

        }else if(checkbox.equals("false")){
            Toast.makeText(this, "Plz sign in", Toast.LENGTH_SHORT).show();
        }
        Executor executor= Executors.newSingleThreadExecutor();
        teacherlogin activity=this;
        BiometricPrompt biometricPrompt=new BiometricPrompt.Builder(this).setTitle("Finger print Authentication").setSubtitle("subtitle").setDescription("Description").setNegativeButton("cancel", executor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).build();
        biometricPrompt.authenticate(new CancellationSignal(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(teacherlogin.this, "Authenticated", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                String email, password;
                email = emailBox.getText().toString();
                password = passwordBox.getText().toString();

                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dialog.dismiss();
                            if (task.isSuccessful()) {
                                if (auth.getCurrentUser().isEmailVerified()) {
                                    startActivity(new Intent(teacherlogin.this, Dashboard.class));
                                } else {
                                    Toast.makeText(teacherlogin.this, "Please verify your email id", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(teacherlogin.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(teacherlogin.this, tsignup.class));
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(teacherlogin.this,forgotpassword.class));
            }
        });

    }
}