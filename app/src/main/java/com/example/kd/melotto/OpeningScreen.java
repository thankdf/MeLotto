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
public class OpeningScreen extends AppCompatActivity implements View.OnClickListener
{
    private Button login, register, forget;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
        login = (Button) findViewById(R.id.loginButton);
        register = (Button) findViewById(R.id.registerButton);
        email = (EditText) findViewById(R.id.usernameInput);
        password = (EditText) findViewById(R.id.passwordInput);
        forget = (Button) findViewById(R.id.forget);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        forget.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.loginButton): {
                String user = email.getText().toString();
                String pass = password.getText().toString();
                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "One or more text fields are not filled. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                else if (!validEmail(user)) {
                    Toast.makeText(getApplicationContext(), "Invalid email. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                else if (password.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password length has to be at least 8 characters.", Toast.LENGTH_LONG).show();
                    break;
                }
                else {
                    startActivity(new Intent(this, MainMenu.class));
                    break;
                }
            }
            case (R.id.registerButton): {
                startActivity(new Intent(this, Registration.class));
                break;
            }
            case (R.id.forget): {
                startActivity(new Intent(this, ForgetUserData.class));
                break;
            }
        }
    }

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
