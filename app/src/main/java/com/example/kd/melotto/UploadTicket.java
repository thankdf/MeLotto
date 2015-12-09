package com.example.kd.melotto;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * Created by kd on 12/2/15.
 */
public class UploadTicket extends AppCompatActivity implements View.OnClickListener {

    private Button scan;
    private TextView firstText, secondText;
    private FrameLayout frame;
    private Camera mCamera = null;
    private CameraView mCameraView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_ticket);
        scan = (Button) findViewById(R.id.scanButton);
        firstText = (TextView) findViewById(R.id.result1);
        secondText = (TextView) findViewById(R.id.result2);
        frame = (FrameLayout) findViewById(R.id.cameraFrame);
        scan.setOnClickListener(this);
        try{
            mCamera = Camera.open();//you can use open(int) to use different cameras
        } catch (Exception e){
            Log.d("ERROR", "Failed to get camera: " + e.getMessage());
        }

        if(mCamera != null) {
            mCameraView = new CameraView(this, mCamera);//create a SurfaceView to show camera data
            FrameLayout camera_view = (FrameLayout)findViewById(R.id.cameraFrame);
            camera_view.addView(mCameraView);//add the SurfaceView to the layout
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case(R.id.scanButton):
            {
                IntentIntegrator newIntent = new IntentIntegrator(this);
                newIntent.initiateScan();
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
            firstText.setText("FORMAT: " + format);
            secondText.setText("CONTENT: " + data);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No scanning data received.", Toast.LENGTH_LONG).show();
        }
    }
}
