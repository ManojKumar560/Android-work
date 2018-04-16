package com.example.manoj.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {
    
    private Button logout,Telugu,Hindi;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firebaseAuth = FirebaseAuth.getInstance();
        logout = (Button) findViewById(R.id.btnout);
        Telugu =(Button) findViewById(R.id.btntelugu);
        Hindi = (Button) findViewById(R.id.btnhindi);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();

            }
        });


        Telugu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,Telugump3Activity.class);
                startActivity(intent);
                Toast.makeText(SecondActivity.this, "You are entering to Telugu mp3 zone... Enjoy the music", Toast.LENGTH_SHORT).show();
            }
        });

    }
        private void Logout () {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(SecondActivity.this, MainActivity.class));
        }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnout:{
                Logout();
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
