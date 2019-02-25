package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity {
    private TextView launchText;
    boolean onlyTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Bundle bundle = getIntent().getExtras();

onlyTitle=bundle.getBoolean("onlyTitle");
    if(onlyTitle){
    String title=bundle.getString("title");
     setTitle(title);}
        else{
        String title=bundle.getString("title");
        setTitle(title);
        launchText=(TextView)findViewById(R.id.lauchText);

        String message=bundle.getString("message");
        launchText.setText(message);

  }

    }
}
