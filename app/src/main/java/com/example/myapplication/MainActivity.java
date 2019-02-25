package com.example.myapplication;

import android.hardware.camera2.CameraAccessException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.provider.MediaStore;


public class MainActivity extends AppCompatActivity {
    private Button sendEmailButton;
    private Button launchActivityButton;
    private Button cameraButton;
    private EditText titleText;
    private EditText messageText;
    private CheckBox titleOnly;
    private boolean onlyTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleText = (EditText) findViewById(R.id.title_edit_view);
        messageText = (EditText) findViewById(R.id.message_edit_view);
        titleOnly = (CheckBox) findViewById(R.id.titleOnly);


        sendEmailButton = (Button) findViewById(R.id.send_email_button);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        launchActivityButton = (Button) findViewById(R.id.launch_activity_button);
        launchActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleText.getText().toString();
                String message = messageText.getText().toString();
                onlyTitle = titleOnly.isChecked();

                Intent intent2 = new Intent(MainActivity.this, LaunchActivity.class);
                intent2.putExtra("message", message);
                intent2.putExtra("title", title);
                intent2.putExtra("onlyTitle", onlyTitle);

                startActivity(intent2);
            }
        });

        cameraButton = (Button) findViewById(R.id.camera_button);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();

            }
        });

    }

    private void sendEmail() {
        String title = titleText.getText().toString();
        String message = messageText.getText().toString();
        onlyTitle = titleOnly.isChecked();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto"));

        if (onlyTitle) {

            intent.putExtra(intent.EXTRA_SUBJECT, title);

        } else {
            intent.putExtra(intent.EXTRA_SUBJECT, title);
            intent.putExtra(intent.EXTRA_TEXT, message);
        }
        intent.setType("message/rfc822");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
