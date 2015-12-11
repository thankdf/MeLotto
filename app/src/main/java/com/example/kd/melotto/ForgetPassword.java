package com.example.kd.melotto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by kd on 12/1/15.
 */
public class ForgetPassword extends AppCompatActivity implements View.OnClickListener, Account {
    private Button submit, back;
    private EditText username, phoneNumber, recoveryUsername;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        submit = (Button) findViewById(R.id.submitButton);
        username = (EditText) findViewById(R.id.usernameInput);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberInput);
        recoveryUsername = (EditText) findViewById(R.id.recoveryUsernameInput);
        back = (Button) findViewById(R.id.backButton);
        submit.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.registerButton): {
                String user = username.getText().toString();
                String phone = phoneNumber.getText().toString();
                String recovery = recoveryUsername.getText().toString();
                if (user.equals("") || phone.equals("") || recovery.equals("")) {
                    Toast.makeText(getApplicationContext(), "One or more text fields are not filled. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                if (!validEmail(user)) {
                    Toast.makeText(getApplicationContext(), "Invalid email. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                if (phone.length() != 10 && phone.length() > 0) {
                    Toast.makeText(getApplicationContext(), "Phone number has to be exactly 10 digits.", Toast.LENGTH_LONG).show();
                    break;
                }
                if (!validEmail(recovery)) {
                    Toast.makeText(getApplicationContext(), "Invalid recovery email. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                if (user.equals(recovery)) {
                    Toast.makeText(getApplicationContext(), "User email and recovery email are the same. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                else {
                    //retrieve password based on info
                    String password = "";
                    Toast.makeText(getApplicationContext(), "The password to your account is " + password + ".", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, OpeningScreen.class));
                    break;

                }
            }
            case (R.id.backButton): {
                startActivity(new Intent(this, ForgetUserData.class));
                break;
            }
        }
    }

    @Override
    public boolean validEmail(String email)
    {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        if(email.matches(EMAIL_PATTERN))
        {
            return true;
        }
        return false;
    }
}
