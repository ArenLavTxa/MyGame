package com.example.mygame;

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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    Button buttonReg;
    FirebaseAuth mAuth;
    TextView textView;
    ProgressBar progressBar;
    FirebaseUser currentUser;

    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    sendVerificationEmail();
                                    Toast.makeText(Register.this, "Registration successful, please verify your email",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void sendVerificationEmail() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register.this, "Verification email sent.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
//
//package com.example.mygame;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
//import android.nfc.Tag;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//
//public class Register extends AppCompatActivity {
//
//    TextInputEditText editTextEmail, editTextPassword;
//    Button buttonReg;
//    FirebaseAuth mAuth;
//    TextView textView;
//    Button sendCode;
//    Button verify;
//    EditText verificationCodeBox;
//    ProgressBar progressBar;
//    FirebaseUser currentUser;
//    @Override
//    public void onStart() {
//        super.onStart();
//        currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        mAuth=FirebaseAuth.getInstance();
//        editTextEmail=findViewById(R.id.email);
//        editTextPassword=findViewById(R.id.password);
//        buttonReg=findViewById(R.id.btn_register);
//        progressBar=findViewById(R.id.progressBar);
//        textView=findViewById(R.id.loginNow);
//        sendCode=findViewById(R.id.sendCode);
//        verify=findViewById(R.id.verify);
//        verificationCodeBox=findViewById(R.id.verficationCode);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(), Login.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        sendCode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        buttonReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
//                String email,password;
//                email=String.valueOf(editTextEmail.getText());
//               password=String.valueOf(editTextPassword.getText());
//
//               if(TextUtils.isEmpty(email)){
//                   Toast.makeText(Register.this,"Enter email",Toast.LENGTH_SHORT).show();
//                   return;
//               }
//                if(TextUtils.isEmpty(password)){
//                    Toast.makeText(Register.this,"Enter password",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressBar.setVisibility(View.GONE);
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(Register.this, "Account crated!",
//                                     Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(Register.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//
//                                }
//                            }});
//            }
//        });
//    }
//}