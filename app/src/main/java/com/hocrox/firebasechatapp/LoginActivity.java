package com.hocrox.firebasechatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEmail;
    TextView mRegisterNow;
    FloatingActionButton mLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //************This is intiate the view*************

        initView();
    }

    private void initView() {

    mEmail= (EditText) findViewById(R.id.etEmail);
    mRegisterNow= (TextView) findViewById(R.id.tv_registerNow);
    mLoginButton= (FloatingActionButton) findViewById(R.id.fl_login);

    //**********This is to intiate the listener on view***************

    mRegisterNow.setOnClickListener(this);
    mLoginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


    switch (v.getId()){

        case R.id.tv_registerNow:

            //************This is to start next Activity******************

            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

            break;

        case R.id.fl_login:

            //************This is to start next Activity******************

            startActivity(new Intent(LoginActivity.this,HomeActivity.class));

            break;

    }

    }
}
