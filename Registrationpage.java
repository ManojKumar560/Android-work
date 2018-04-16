package com.example.manoj.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registrationpage extends AppCompatActivity {

private EditText Name,password,Email;

    private Button signin;
    private TextView Login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationpage);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String user_email = Email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Registrationpage.this, "Registred Succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registrationpage.this, MainActivity.class));

                            } else {
                                Toast.makeText(Registrationpage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }
            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registrationpage.this, MainActivity.class));

            }
        });

    }

    public void setupUIViews(){
        Name=(EditText) findViewById(R.id.en);
        password=(EditText) findViewById(R.id.ep);
        Email=(EditText) findViewById(R.id.ee);
        signin=(Button) findViewById(R.id.btnsign);
        Login=(TextView) findViewById(R.id.tvlogin);

    }
        private Boolean validate(){
        Boolean result = false;

        String name = Name.getText().toString();
        String pwd = password.getText().toString();
        String email = Email.getText().toString();

        if(name.isEmpty() || pwd.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"please enter the details",Toast.LENGTH_SHORT).show();
        }
        else {
            return true;
        }
        return result;
    }
}


