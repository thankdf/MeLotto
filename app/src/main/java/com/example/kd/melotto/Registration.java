package com.example.kd.melotto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by kd on 11/30/15.
 */
public class Registration extends AppCompatActivity implements View.OnClickListener, Account
{
    private Button register, back;
    private EditText username, password, confirmPassword, phoneNumber, recoveryUsername;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = (Button) findViewById(R.id.registerButton);
        username = (EditText) findViewById(R.id.usernameInput);
        password = (EditText) findViewById(R.id.passwordInput);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordInput);
        phoneNumber = (EditText) findViewById(R.id.phoneNumberInput);
        recoveryUsername = (EditText) findViewById(R.id.recoveryUsernameInput);
        back = (Button) findViewById(R.id.backButton);
        register.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.registerButton): {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String confirmPass = confirmPassword.getText().toString();
                String phone = phoneNumber.getText().toString();
                String recovery = recoveryUsername.getText().toString();
                if (user.equals("") || pass.equals("") || confirmPass.equals("") || phone.equals("")) {
                    Toast.makeText(getApplicationContext(), "One or more text fields are not filled. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                if (!validEmail(user)) {
                    Toast.makeText(getApplicationContext(), "Invalid email. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                else if (password.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password length has to be at least 8 characters.", Toast.LENGTH_LONG).show();
                    break;
                }
                else if (!pass.equals(confirmPass)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match. Try inputting again.", Toast.LENGTH_LONG).show();
                    break;
                }
                else if (phone.length() != 10 && phone.length() > 0) {
                    Toast.makeText(getApplicationContext(), "Phone number has to be exactly 10 digits.", Toast.LENGTH_LONG).show();
                    break;
                }
                else if (!validEmail(recovery)) {
                    Toast.makeText(getApplicationContext(), "Invalid recovery email. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                else if (user.equals(recovery)) {
                    Toast.makeText(getApplicationContext(), "User email and recovery email are the same. Try again.", Toast.LENGTH_LONG).show();
                    break;
                }
                else {
                    User registeredData = new User(user, pass, phone, recovery);
                    OpeningScreen.userLocalStore.storeUserData(registeredData);
                    registerUser(registeredData);
                    break;
                }
            }
            case (R.id.backButton): {
                startActivity(new Intent(this, OpeningScreen.class));
                break;
            }
        }
    }

    private void registerUser(User user) {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.storeUserDataBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Toast.makeText(getApplicationContext(),"Registration Complete!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Registration.this, OpeningScreen.class));
            }
        });
    }

    private void saveObject(UserLocalStore userLocalStore) {
        try {
            String filename = Environment.getExternalStorageDirectory()
                    .getPath() + "/UserLocalStore.bin";
            ObjectOutputStream oos = new ObjectOutputStream
                    (new FileOutputStream(new File(filename)));
            //Select where you wish to save the file...
            oos.writeObject(userLocalStore);// write the class as an 'object'
            oos.flush(); // flush the stream to insure all of the
            // information was written to 'save_object.bin'
            oos.close();// close the stream
        } catch (Exception ex) {
            Log.v("Seriazation Save Error:", ex.getMessage());
            ex.printStackTrace();
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