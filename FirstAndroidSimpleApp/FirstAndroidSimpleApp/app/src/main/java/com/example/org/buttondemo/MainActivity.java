package com.example.org.buttondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
    }

    public void b1Clicked(View v) {
        Toast toast = Toast.makeText(this, "Button 2 is gone", Toast.LENGTH_LONG);
        toast.show();
        b2.setVisibility(View.GONE); }

    public void b2Clicked(View v) {
        Toast toast = Toast.makeText(this, "Button 1 is gone", Toast.LENGTH_LONG);
        toast.show();
        b1.setVisibility(View.GONE); }

    public void b3Clicked(View v) {
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
    }
}
