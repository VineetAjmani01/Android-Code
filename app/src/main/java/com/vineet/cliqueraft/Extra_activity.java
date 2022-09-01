package com.vineet.cliqueraft;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Extra_activity extends AppCompatActivity {
    TextView extratext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra);

        extratext = findViewById(R.id.text_view);
    }
}