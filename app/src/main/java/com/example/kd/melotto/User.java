package com.example.kd.melotto;

/**
 * Created by Patrick Vu on 11/30/2015.
 */
public class User {
    public String username, password, email, phoneNum;

    public User(String user, String pass, String phone, String recover)
    {
        username = user;
        password = pass;
        phoneNum = phone;
        email = recover;

    }
}
