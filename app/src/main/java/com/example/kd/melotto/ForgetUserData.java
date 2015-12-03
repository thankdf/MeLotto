package com.example.kd.melotto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kd on 12/1/15.
 */
public class ForgetUserData extends AppCompatActivity implements View.OnClickListener{

    private Button username, password, back;
    private UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_user_data);
        username = (Button) findViewById(R.id.forgetUsernameButton);
        password = (Button) findViewById(R.id.forgetPasswordButton);
        back = (Button) findViewById(R.id.backButton);
        username.setOnClickListener(this);
        password.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.forgetUsernameButton): {
                startActivity(new Intent(this, ForgetUsername.class));
                break;
            }
            case (R.id.forgetPasswordButton): {
                startActivity(new Intent(this, ForgetPassword.class));
                break;
            }
            case (R.id.backButton): {
                startActivity(new Intent(this, OpeningScreen.class));
                break;
            }
        }
    }
}
