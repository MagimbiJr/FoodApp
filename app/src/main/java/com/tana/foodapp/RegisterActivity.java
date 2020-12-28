package com.tana.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.tana.foodapp.Model.User;

enum Gender{
    male,
    female
}
public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputEditText mFirstName, mLastName, mRegEmail, mRegPassword, mConfirmPassword;
    RadioButton mMaleBtn;
    RadioButton mFemaleBtn;
    Button mRegisterBtn;
    ProgressBar mProgressBar;
    String gender;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        TextView btnToLogin = (TextView) findViewById(R.id.toLogin);
        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mFirstName = (TextInputEditText) findViewById(R.id.firstname);
        mLastName = (TextInputEditText) findViewById(R.id.lastname);
        mRegEmail = (TextInputEditText) findViewById(R.id.register_email);
        mRegPassword = (TextInputEditText) findViewById(R.id.register_password);
        mConfirmPassword = (TextInputEditText) findViewById(R.id.register_retype_password);
        mMaleBtn = (RadioButton) findViewById(R.id.male);
        mFemaleBtn = (RadioButton) findViewById(R.id.female);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mRegisterBtn = (Button) findViewById(R.id.registerBtn);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleRegisterBtn();
            }
        });

        mFemaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioClicked(v);
            }
        });

        mMaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioClicked(v);
            }
        });



    }

    private void onRadioClicked(View v) {
        boolean checked = ((RadioButton)v).isChecked();

        int id = v.getId();
        if(id == R.id.male && checked){
            gender =  Gender.male.toString();
        }else{
            gender =  Gender.female.toString();
        }

        Log.d("Gender" , gender);
    }


    private void handleRegisterBtn() {
        String firstName = mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String email = mRegEmail.getText().toString().trim();
        String password = mRegPassword.getText().toString().trim();


        if (firstName.isEmpty()) {
            mFirstName.setError("Please enter your first name");
            mFirstName.requestFocus();
            return;
        }
        if (lastName.isEmpty()) {
            mLastName.setError("Please enter last name");
            mLastName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            mRegEmail.setError("Please enter your email address");
            mRegEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mRegEmail.setError("Please enter valid email");
            mRegEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            mRegPassword.setError("Please enter your password");
            mRegPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            mRegPassword.setError("Password must not be less than 6 character");
            mRegPassword.requestFocus();
        }
        if (gender.isEmpty()) {
            Toast.makeText(this, "Please choose your gender", Toast.LENGTH_LONG).show();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userId = mAuth.getCurrentUser().getUid();
                            User user = new User(firstName, lastName, email, gender);

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(userId)
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "User has been registered successfully!",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Fail to register!. Try again",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                            Toast.makeText(getApplicationContext(), "User has been registered successfully!",
                                    Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.INVISIBLE);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Fail to register",
                                    Toast.LENGTH_SHORT).show();
                        }
                       finish();
                    }
                });
    }
}