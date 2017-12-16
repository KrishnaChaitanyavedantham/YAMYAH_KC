package com.example.krishnachaitanya.redcrossreliefeffort;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail,editTextPassword;
    private Button buttonRegister;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ImageView iv = (ImageView)findViewById(R.id.logo_id);
        // TextView tv = (TextView)findViewById(R.id.name_id);
        //  iv.setImageResource(R.drawable.viselogo);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, HqActivity.class));
            finish();
        }

        editTextEmail = (EditText)findViewById(R.id.email_id);
        editTextPassword = (EditText) findViewById(R.id.password_id);
        buttonRegister = (Button) findViewById(R.id.register_btnid);
        final String[] items = {"Head Quaters","Distribution Centers", "Relief Centers", "Volunteer"};

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Toast.makeText(getApplicationContext(),"Sign In Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),HeadQuaterActivity.class));
                }
            }
        };
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //Intent myIntent = new Intent(MainActivity.this, HqActivity.class);
               // MainActivity.this.startActivity(myIntent);
                startSignIn();
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    private void startSignIn(){
       String email = editTextEmail.getText().toString().trim();
       String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
           Toast.makeText(getApplicationContext(),email+"Fields are Empry"+password, Toast.LENGTH_SHORT).show();
        }
         else {

            Toast.makeText(getApplicationContext(),email+"  "+password, Toast.LENGTH_LONG).show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(getApplicationContext(),"Sign ***********", Toast.LENGTH_LONG).show();
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Sign In Problem", Toast.LENGTH_LONG).show();
                               // startActivity(new Intent(getApplicationContext(),ServiceActivity.class));
                                // Toast.makeText(getApplicationContext(), getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                           }
                            else {
                                startActivity(new Intent(getApplicationContext(), HeadQuaterActivity.class));
                                finish();
                                Toast.makeText(getApplicationContext(), "Sign In Successful", Toast.LENGTH_LONG).show();
                            }
                        }

                    });
        }
    }

    @Override
    public void onBackPressed() {
        //startActivity(new Intent(getApplicationContext(),ServiceActivity.class));
        finish();
    }

}


