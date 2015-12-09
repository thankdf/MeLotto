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
public class ForgetUsername extends AppCompatActivity implements View.OnClickListener, Account
{
    private Button submit, back;
    private EditText password, phoneNumber, recoveryUsername;
    private UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_username);
        submit = (Button) findViewById(R.id.submitButton);
        password = (EditText) findViewById(R.id.passwordInput);
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
                String pass = password.getText().toString();
                int phone = 0;
                String recovery = recoveryUsername.getText().toString();
                if (pass.equals("") || (phoneNumber.getText().toString()).equals("") || recovery.equals("")) {
                    Toast.makeText(getApplicationContext(), "One or more text fields are not filled. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                if (pass.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password has to be at least 8 characters. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                if (phoneNumber.length() != 10 && phoneNumber.length() > 0) {
                    Toast.makeText(getApplicationContext(), "Phone number has to be exactly 10 digits.", Toast.LENGTH_LONG).show();
                    break;
                }
                if (!validEmail(recovery)) {
                    Toast.makeText(getApplicationContext(), "Invalid recovery email. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                else {
                    String username = "";
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
