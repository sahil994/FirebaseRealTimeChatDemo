package com.hocrox.firebasechatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView mChatList;
    EditText mChatMessage;
    FloatingActionButton mSend;
    ArrayList<MessageModel> mDataList;
    String mUserName, mUserEmail, mUserMobile, mLoginUserID;
    TextView mUser;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //**********This is to intialize the view**************
        Intent intent = getIntent();

        mUserName = intent.getStringExtra("username");
        mUserEmail = intent.getStringExtra("email");
        mUserMobile = intent.getStringExtra("mobile");
        mLoginUserID = new UserCredentials(ChatActivity.this).getUserMobile();
        initView();

    }

    public void initFirebase() {

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshots) {

               boolean value=dataSnapshots.child("message").child(mLoginUserID).child(mUserMobile).exists();

                if(value){

                    Iterable<DataSnapshot> dataSnapshots1=dataSnapshots.child("message").child(mLoginUserID).child(mUserMobile).getChildren();
                    mDataList.clear();
                    for(DataSnapshot messageModel:dataSnapshots1){

                        MessageModel messageModel1=messageModel.getValue(MessageModel.class);
                        mDataList.add(messageModel1);

                    }
                    if(mDataList.size()>0){
                        setAdapter();
                    }


                }

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

    private void initView() {

        mDataList = new ArrayList<MessageModel>();
        mChatList = (RecyclerView) findViewById(R.id.rl_chatList);
        mChatMessage = (EditText) findViewById(R.id.et_message);
        mSend = (FloatingActionButton) findViewById(R.id.fb_send);
        mUser = (TextView) findViewById(R.id.tv_name);
        mSend = (FloatingActionButton) findViewById(R.id.fb_send);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mSend.setOnClickListener(this);
        mUser.setText(mUserName);
        //**********This is to set the adapter*************
        initFirebase();
    }

    private void setAdapter() {

        //*********This is to set the adapter on list ***************

        ChatListAdapter chatListAdapter = new ChatListAdapter(ChatActivity.this, mDataList);
        mChatList.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        mChatList.setAdapter(chatListAdapter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fb_send:

                String message = mChatMessage.getText().toString();
                MessageModel messageModel = new MessageModel(mLoginUserID, mUserMobile, message);

                mDatabase.child("message").child(mLoginUserID).child(mUserMobile).child(message).setValue(messageModel, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                        Log.e("Testing Login", "Called");

                    }
                });


                break;

        }

    }
}
