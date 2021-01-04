package com.example.clickergame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button btnTap, btnDoubleTap, btnDoubleExp;
    ImageButton btnStart, btnRestart;
    TextView tvTime, tvStats;
    int taps, tapMod;
    int level = 0;
    int exp = 0, expMod = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnTap = findViewById(R.id.btnTap);
        btnRestart= findViewById(R.id.btnRestart);
        btnDoubleTap = findViewById(R.id.btnDoubleTap);
        btnDoubleExp = findViewById(R.id.btnDoubleExp);
        tvTime = findViewById(R.id.tvTime);
        tvStats = findViewById(R.id.tvStats);

        final CountDownTimer timer = new CountDownTimer(120000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText("Time Left: " + new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
            }

            @Override
            public void onFinish() {
                btnTap.setEnabled(false);
                btnStart.setEnabled(true);
            }
        };




        btnTap.setEnabled(false);



        btnTap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(level==5){
                    btnDoubleTap.setVisibility(View.VISIBLE);
                }
                if(level==7){
                    btnDoubleExp.setVisibility(View.VISIBLE);
                }
                exp = exp + 10 * expMod;
                level = (int) ((25+Math.sqrt(625+100*exp))/50);
                taps += tapMod;
                taps++;
                tvStats.setText("Lv "+level+" / "+exp+" XP / "+taps+" Taps");
            }
        });

        btnDoubleTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapMod = 1;
                expMod = 2;
                btnDoubleTap.setEnabled(false);
            }
        });

        btnDoubleExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expMod = 4;
                btnDoubleExp.setEnabled(false);
            }
        });




        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setEnabled(false);
                btnTap.setEnabled(true);
                timer.start();
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
