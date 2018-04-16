package com.example.manoj.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseUser;



public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView newuser;
    private FirebaseAuth firebaseAuth;
     private ProgressDialog progressDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Name = (EditText) findViewById(R.id.un);
        password = (EditText) findViewById(R.id.up);
        Info = (TextView) findViewById(R.id.textView2);
        Login = (Button) findViewById(R.id.btn);
        newuser = (TextView) findViewById(R.id.tvNew);

        Info.setText("No of attempts:5");
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
       progressDialogue = new ProgressDialog(this);

       if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), password.getText().toString());
            }
        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Registrationpage.class));
            }
        });
    }

      private void validate(String UserName, String Userpassword){
          progressDialogue.setMessage("welcome to mymusic app");
          progressDialogue.show();

        firebaseAuth.signInWithEmailAndPassword(UserName,Userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialogue.dismiss();
                    Toast.makeText(MainActivity.this,"Succesfull",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                }else
                    Toast.makeText(MainActivity.this,"Loginfailed",Toast.LENGTH_SHORT).show();
                counter--;

                Info.setText("No of attempts:"+String.valueOf(counter));
                progressDialogue.dismiss();
                if (counter == 0)
                {
                    Login.setEnabled(false);
                }
            }
        });





        }
    }

