package com.hocrox.firebasechatapp;

/**
 * Created by sahilgoyal on 16/8/17.
 */

public class MessageModel {

    public String senderid;

    public String getSenderid() {
        return senderid;
    }

    MessageModel(){}

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getRecieverid() {
        return recieverid;
    }

    public void setRecieverid(String recieverid) {
        this.recieverid = recieverid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String recieverid;
    public String message;

    MessageModel(String senderid, String recieverid, String message) {

        this.senderid = senderid;
        this.recieverid = recieverid;
        this.message = message;
    }


}
