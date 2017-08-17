package com.hocrox.firebasechatapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView mContactList;
    ArrayList<RegisterUserModel> mDataList;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //************This is intiate the Views***************

        initView();

    }

    private void initView() {

        mContactList = (RecyclerView) findViewById(R.id.rl_list);
        mDataList = new ArrayList<RegisterUserModel>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //**************This is to get the data from firebase and call listenr whenever data change************

        initFirebase();

    }


    private void setAdapter() {

        //**********This is to set the adapter on recyclerview***************

        ListAdapter listAdapter = new ListAdapter(HomeActivity.this, mDataList);
        mContactList.setLayoutManager(new LinearLayoutManager(this));
        mContactList.setAdapter(listAdapter);

    }

    public void initFirebase() {

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshots) {


                for (DataSnapshot dataSnapshot : dataSnapshots.getChildren()) {



                    if (dataSnapshot.getKey().equalsIgnoreCase("users")) {

                       Iterable<DataSnapshot> chatUserList = dataSnapshot.getChildren();

                        for(DataSnapshot registerUserModel:chatUserList){

                          Log.e("Tesign", "" + registerUserModel.getValue(RegisterUserModel.class));

                           mDataList.add(registerUserModel.getValue(RegisterUserModel.class));

                      }

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

}
