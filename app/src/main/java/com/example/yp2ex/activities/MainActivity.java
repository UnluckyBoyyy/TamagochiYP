package com.example.yp2ex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yp2ex.R;
import com.example.yp2ex.data.Tamagochi;
import com.example.yp2ex.data_base.DataBaseManager;
import com.example.yp2ex.databinding.ActivityMainBinding;

import java.util.Timer;

import kotlin.text.UStringsKt;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Tamagochi tamagochi = new Tamagochi();
    Handler handler = new Handler();
    int secondsAlive = 0;
    DataBaseManager dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBaseManager = new DataBaseManager(MainActivity.this);
        dataBaseManager.openDb();
        setTamagochi();
        updProgressBars();
        threadTime.start();

        binding.buttonHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tamagochi.setHappy(tamagochi.getHappy() + 20);
                updProgressBars();
            }
        });
        binding.buttonBore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tamagochi.setBore(tamagochi.getBore() + 20);
                tamagochi.setTired(tamagochi.getTired() - 10);
                updProgressBars();
            }
        });
        binding.buttonTired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tamagochi.setTired(tamagochi.getTired() + 20);
                tamagochi.setHunger(tamagochi.getHunger() - 10);
                updProgressBars();
            }
        });
        binding.buttonHunger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tamagochi.setHunger(tamagochi.getHunger() + 20);
                tamagochi.setHappy(tamagochi.getHappy() - 10);
                updProgressBars();
            }
        });
    }

    public void setTamagochi() {
        tamagochi.setHappy(50);
        tamagochi.setTired(50);
        tamagochi.setBore(50);
        tamagochi.setHunger(50);
    }

    public void updProgressBars() {
        binding.progressBarHappy.setProgress(tamagochi.getHappy());
        binding.progressBarBore.setProgress(tamagochi.getBore());
        binding.progressBarTired.setProgress(tamagochi.getTired());
        binding.progressBarHunger.setProgress(tamagochi.getHunger());
    }

    Thread threadTime = new Thread(new Runnable() {
        @Override
        public void run() {
            for (; tamagochi.getDead() == 0; ) {
                if (tamagochiAlive(tamagochi)) {
                    secondsAlive++;
                    scaleTime(secondsAlive);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            binding.textViewTimer.setText(timeToString(secondsAlive));
                            animation();
                        }
                    });
                    updProgressBars();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    tamagochi.setDead(1);
                    tamagochi.setDays(secondsAlive);
                    Log.d("TAG", tamagochi.getDays() + " " + secondsAlive);
                    dataBaseManager.addTamogochi(tamagochi);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            animation();
                            Toast.makeText(MainActivity.this, "Ваш тамагочи умер!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity.this, DeadTamogochiActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }
    });

    public boolean tamagochiAlive(Tamagochi tamagochi) {
        return tamagochi.getHappy() > 0 &&
                tamagochi.getTired() > 0 &&
                tamagochi.getBore() > 0 &&
                tamagochi.getHunger() > 0;
    }

    @SuppressLint("DefaultLocale")
    public String timeToString(int secondsAlive){
        long hour = secondsAlive / 3600,
                min = secondsAlive / 60 % 60,
                sec = secondsAlive / 1 % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    public void scaleTime(int secondsAlive){
        if (secondsAlive <= 60){
            tamagochi.setHappy(tamagochi.getHappy() - 5);
            tamagochi.setTired(tamagochi.getTired() - 5);
            tamagochi.setBore(tamagochi.getBore() - 5);
            tamagochi.setHunger(tamagochi.getHunger() - 5);
        } else if (secondsAlive <= 120) {
            tamagochi.setHappy(tamagochi.getHappy() - 10);
            tamagochi.setTired(tamagochi.getTired() - 10);
            tamagochi.setBore(tamagochi.getBore() - 10);
            tamagochi.setHunger(tamagochi.getHunger() - 10);
        } else {
            tamagochi.setHappy(tamagochi.getHappy() - 15);
            tamagochi.setTired(tamagochi.getTired() - 15);
            tamagochi.setBore(tamagochi.getBore() - 15);
            tamagochi.setHunger(tamagochi.getHunger() - 15);
        }
    }

    public void animation(){
        if (!tamagochiAlive(tamagochi))
            binding.imageView.setImageResource(R.drawable.dead);
        else if (tamagochi.getBore() <= 50 &&
                tamagochi.getHunger() <= 50 &&
                tamagochi.getHappy() <= 50 &&
                tamagochi.getTired() <= 50)
            binding.imageView.setImageResource(R.drawable.normal);
        else
        if (tamagochi.getBore() >= 50 &&
                tamagochi.getHunger() >= 50 &&
                tamagochi.getHappy() >= 50 &&
                tamagochi.getTired() >= 50)
            binding.imageView.setImageResource(R.drawable.happy);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}