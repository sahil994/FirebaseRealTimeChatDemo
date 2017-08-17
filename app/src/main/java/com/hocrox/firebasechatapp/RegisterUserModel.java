package com.hocrox.firebasechatapp;

/**
 * Created by sahilgoyal on 16/8/17.
 */

public class RegisterUserModel {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    RegisterUserModel(String email,String name,String mobileno){

        this.email=email;
        this.mobileno=mobileno;
        this.name=name;


    }
    RegisterUserModel(){

    }


    public String email;
    public String name;
    public String mobileno;
}
