package com.hocrox.firebasechatapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sahilgoyal on 16/8/17.
 */

public class UserCredentials {

    SharedPreferences mPrefrence;
    Context mContext;

    public String USERDETAIL = "UserDetail";
    public String USERNAME = "name";
    public String USEREMAIL = "email";
    public String USERMOBILE = "mobileNo";


    UserCredentials(Context context) {

        mContext = context;

    }

    public void seUserDetail(String userName, String userEmail, String userMobile) {

        //***************This is to set the user detail*******************

        mPrefrence = mContext.getSharedPreferences(USERDETAIL, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = mPrefrence.edit();
        edit.putString(USERNAME, userName);
        edit.putString(USEREMAIL, userEmail);
        edit.putString(USERMOBILE, userMobile);
        edit.apply();

    }

    public String getUserName() {

        //************This is to get the name*****************

        mPrefrence = mContext.getSharedPreferences(USERDETAIL, Context.MODE_PRIVATE);
        return mPrefrence.getString(USERNAME, "");
    }

    public String getUserEmail() {

        //*****************This is to get the user email*****************

        mPrefrence = mContext.getSharedPreferences(USERDETAIL, Context.MODE_PRIVATE);
        return mPrefrence.getString(USEREMAIL, "");

    }

    public String getUserMobile() {

        //*****************This is to get the user mobile***********************

        mPrefrence = mContext.getSharedPreferences(USERDETAIL, Context.MODE_PRIVATE);
        return mPrefrence.getString(USERMOBILE, "");
    }


}
