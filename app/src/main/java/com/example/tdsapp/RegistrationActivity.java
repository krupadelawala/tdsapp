package com.example.tdsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;

public class RegistrationActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button btnSignup;
    private Button btnSignIn;

    //firebase..

    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth=FirebaseAuth.getInstance();
        mDialog=new ProgressDialog(this);

        email=(EditText)findViewById(R.id.email_reg);
        pass=(EditText)findViewById(R.id.password_reg);

        btnSignIn=(Button)findViewById(R.id.btnsignin_reg);
        btnSignup=(Button)findViewById(R.id.btnsignup_reg);

        //for login
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });

        //for signup
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mEmail=email.getText().toString().trim();
                String mPass=pass.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail))
                {
                    email.setError("Required Field..");
                    return;
                }
                if(TextUtils.isEmpty(mPass))
                {
                    pass.setError("Required Field..");
                    return;
                }

                mDialog.setMessage("Processing..");
                mDialog.dismiss();

                mAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            mDialog.dismiss();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                        }
                    }
                });

            }
        });
    }
}
