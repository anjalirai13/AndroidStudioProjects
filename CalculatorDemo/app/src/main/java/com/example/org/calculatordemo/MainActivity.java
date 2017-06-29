package com.example.org.calculatordemo;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button addition, subtract, multiply, division, reset;
    EditText text1, text2;
    TextView result;
    Integer number1, number2, res = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addition = (Button) findViewById(R.id.addition);
        subtract = (Button) findViewById(R.id.subtraction);
        multiply = (Button) findViewById(R.id.multiplication);
        division = (Button) findViewById(R.id.division);
        reset    = (Button) findViewById(R.id.reset);

        text1 = (EditText) findViewById(R.id.text2);
        text2 = (EditText) findViewById(R.id.text3);
        result = (TextView) findViewById(R.id.text5);

        View.OnClickListener myOnClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((text1.getText().toString() != null) && (text2.getText().toString() != null)) {
                    try {
                        number1 = Integer.parseInt(text1.getText().toString());
                        number2 = Integer.parseInt(text2.getText().toString());
                        switch (v.getId()) {
                            case R.id.addition:
                                res = number1 + number2;
                                Log.i("result", "result is" + res);
                                break;
                            case R.id.multiplication:
                                res = number1 * number2;
                                Log.i("result", "result is" + res);
                                break;
                            case R.id.subtraction:
                                res = number1 - number2;
                                Log.i("result", "result is" + res);
                                break;
                            case R.id.division:
                                res = number1 / number2;
                                Log.i("result", "result is" + res);
                                break;
                            case R.id.equal:
                                result.setText(String.valueOf(res));
                                break;
                        }
                    }catch (Exception e){
                        res = 0;
                        Log.e("error", "exception is " + e);
                    }
                    result.setText(String.valueOf(res));
                }
                else
                    Toast.makeText(MainActivity.this, "Enter two numbers", Toast.LENGTH_LONG).show();
            }
        };
        addition.setOnClickListener(myOnClickListener);
        subtract.setOnClickListener(myOnClickListener);
        multiply.setOnClickListener(myOnClickListener);
        division.setOnClickListener(myOnClickListener);
        reset.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                result.setText("0");
                text1.setText("");
                text2.setText("");
            }
        });
    }
}
