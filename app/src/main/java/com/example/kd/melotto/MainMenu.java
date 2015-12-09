package com.example.kd.melotto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by kd on 12/1/15.
 */
public class MainMenu extends AppCompatActivity implements View.OnClickListener
{
    private Button viewProfile, viewLotteryResults, uploadTicket, viewTickets, settings, logoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        viewProfile = (Button) findViewById(R.id.viewProfileButton);
        viewLotteryResults = (Button) findViewById(R.id.viewLotteryResultsButton);
        uploadTicket= (Button) findViewById(R.id.uploadTicketButton);
        viewTickets = (Button) findViewById(R.id.viewTicketsButton);
        settings = (Button) findViewById(R.id.settingsButton);
        logoff = (Button) findViewById(R.id.logoffButton);
        viewProfile.setOnClickListener(this);
        viewLotteryResults.setOnClickListener(this);
        uploadTicket.setOnClickListener(this);
        viewTickets.setOnClickListener(this);
        settings.setOnClickListener(this);
        logoff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.viewProfileButton): {
                startActivity(new Intent(this, ViewProfile.class));
                break;
            }
            case (R.id.viewLotteryResultsButton): {
                startActivity(new Intent(this, ViewLotteryResults.class));
                break;
            }
            case (R.id.uploadTicketButton): {
                IntentIntegrator newIntent = new IntentIntegrator(this);
                newIntent.initiateScan();
                break;
            }
            case (R.id.viewTicketsButton): {
                startActivity(new Intent(this, ViewTickets.class));
                break;
            }
            case (R.id.settingsButton): {
                startActivity(new Intent(this, Settings.class));
                break;
            }
            case (R.id.logoffButton): {
                startActivity(new Intent(this, OpeningScreen.class));
                break;
            }
        }
    }

    public void onActivityResult(int request, int result, Intent intent)
    {
        IntentResult resultIntent = IntentIntegrator.parseActivityResult(request, result, intent);
        if(resultIntent != null)
        {
            String data = resultIntent.getContents();
            String format = resultIntent.getFormatName();
            Toast.makeText(getApplicationContext(), "FORMAT: " + format + ". CONTENT: " + data + ".", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No scanning data received.", Toast.LENGTH_LONG).show();
        }
    }
}
