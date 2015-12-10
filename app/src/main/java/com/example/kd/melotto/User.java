package com.example.kd.melotto;

/**
 * Created by Patrick Vu on 11/30/2015.
 */
public class User {
    String username, password, email;
    int phoneNum;

    public User(String username, String password, int phoneNum, String email) {
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
    }


}
