package com.hocrox.firebasechatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEmail, mUserName, mMobile;
    FloatingActionButton mRegisterButton;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        //**************This is to intiate the view*************

        initView();

    }

    private void initView() {

        mEmail = (EditText) findViewById(R.id.etEmail);
        mUserName = (EditText) findViewById(R.id.etUserName);
        mMobile = (EditText) findViewById(R.id.etMobile);
        mRegisterButton = (FloatingActionButton) findViewById(R.id.fl_register);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //*********This is to intiate the button listener***************

        mRegisterButton.setOnClickListener(this);

        //**********THIS LISTENER CALL WHEN ALL CHANGE IN DATABASE OCCURS**********

        initFirebase();

    }

    public void initFirebase() {


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshots) {


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...

                Log.e("DataSan", "Called" + databaseError.getMessage());
            }
        };
        mDatabase.addValueEventListener(postListener);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.fl_register:

                //************This is to start next Activity******************


                final String email = mEmail.getText().toString();
                final String userName = mUserName.getText().toString();
                final String mobile = mMobile.getText().toString();


                RegisterUserModel registerUserModel = new RegisterUserModel(email, userName, mobile);
                mDatabase.child("users").child(mobile).setValue(registerUserModel, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                        UserCredentials userCredentials=new UserCredentials(RegisterActivity.this);
                        userCredentials.seUserDetail(userName,email,mobile);
                        Toast.makeText(RegisterActivity.this, "Data Saved Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));

                    }
                });



                break;

        }

    }
}
