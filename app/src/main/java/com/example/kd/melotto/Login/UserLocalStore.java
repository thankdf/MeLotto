package com.example.kd.melotto.Login;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.Serializable;

/**
 * Created by Patrick Vu on 11/21/2015.
 */
public class UserLocalStore implements Serializable {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;
    private static final long serialVersionUID = 465434455;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public SharedPreferences getUserLocalDatabase() {
        return userLocalDatabase;
    }

    public void setUserLocalDatabase(SharedPreferences userLocalDatabase) {
        this.userLocalDatabase = userLocalDatabase;
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.putInt("phoneNum", user.phoneNum);
        spEditor.putString("email", user.email);
        spEditor.commit();
    }

    public User getUser() {
        String username = userLocalDatabase.getString("username", 0 + "");
        String password = userLocalDatabase.getString("password", 0 + "");
        int phoneNum = userLocalDatabase.getInt("phoneNum", 0);
        String email = userLocalDatabase.getString("email", 0 + "");

        User storedUser = new User(username, password, phoneNum, email);
        return storedUser;

    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn() {
        return userLocalDatabase.getBoolean("loggedIn", false) == true;

    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
