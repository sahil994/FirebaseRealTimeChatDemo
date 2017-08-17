package com.hocrox.firebasechatapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sahilgoyal on 16/8/17.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<MessageModel> mDataList;
    String senderId;
    View view;

    public ChatListAdapter(ChatActivity chatActivity, ArrayList<MessageModel> mDataList) {

        mContext = chatActivity;
        this.mDataList = mDataList;
        senderId = new UserCredentials(mContext).getUserMobile();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 1) {

            view = LayoutInflater.from(mContext).inflate(R.layout.custom_right, parent, false);

        } else {

            view = LayoutInflater.from(mContext).inflate(R.layout.custom_left, parent, false);
        }

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MessageModel messageModel = mDataList.get(position);
        holder.mUserMessage.setText(messageModel.getMessage());

    }


    @Override
    public int getItemViewType(int position) {

       int d=0;
        if (mDataList.get(position).senderid.equalsIgnoreCase(senderId)) {

            d=1;
        }
        if(!mDataList.get(position).senderid.equalsIgnoreCase(senderId)) {

            d=2;
        }
       return d;
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mUserMessage;


        public MyViewHolder(View itemView) {
            super(itemView);
            //**********This is to intialize view**********
            mUserMessage = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }
}
