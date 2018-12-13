package com.example.kch.task3excalculatorapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.regex.Pattern;

public class Calculator extends AppCompatActivity {

    private TextView _score;
    private String display = " ";
    private String currentOperation = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        _score = (TextView)findViewById(R.id.score);
        _score.setText(display);
    }

    private void updateScreen(){
        _score.setText(display);
    }

    protected void onClickNumber(View v){
        Button b = (Button) v;
        display += b.getText();
        updateScreen();
    }

    protected void onClickOperator(View v){
        Button b = (Button) v;
        display += b.getText();
        currentOperation = b.getText().toString();
        updateScreen();
    }

    private void clear(){
        display = "";
        currentOperation = "";
        updateScreen();
    }

    public void onClickClear(View v){
        clear();
        updateScreen();
    }

    private double operate(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b);
            case "-": return Double.valueOf(a) - Double.valueOf(b);
            case "X": return Double.valueOf(a) * Double.valueOf(b);
            case "/": try {
                return Double.valueOf(a) / Double.valueOf(b);
            } catch (Exception e){
                Log.d("Calc", e.getMessage());
            }
                default: return -1;
        }
    }

    public void onClickEqual(View v){
     String[] operation = display.split(Pattern.quote(currentOperation));
     if(operation.length < 2) return;

     Double _result = operate(operation[0], operation[1], currentOperation);
        _score.setText(display + "\n" + String.valueOf(_result));


    }
}
