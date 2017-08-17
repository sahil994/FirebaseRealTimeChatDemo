package com.hocrox.firebasechatapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sahilgoyal on 16/8/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<RegisterUserModel> mDataList;

    public ListAdapter(HomeActivity homeActivity, ArrayList<RegisterUserModel> mDataList) {

        mContext = homeActivity;
        this.mDataList = mDataList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final RegisterUserModel registerUserModel = mDataList.get(position);
        holder.mUserName.setText(registerUserModel.getName());

        holder.mRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //*************This is to start Activtiy*****************

                mContext.startActivity(new Intent(mContext, ChatActivity.class).putExtra("username",registerUserModel.getName()).putExtra("email",registerUserModel.getEmail()).putExtra("mobile",registerUserModel.getMobileno()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mUserName;
        LinearLayout mRootLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            //***********This is to intialize the view******************
            mRootLayout = (LinearLayout) itemView.findViewById(R.id.ll_root);
            mUserName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
