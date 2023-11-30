package com.example.yp2ex.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.yp2ex.R;
import com.example.yp2ex.adapters.TamagochiAdapter;
import com.example.yp2ex.data.Tamagochi;
import com.example.yp2ex.data_base.DataBaseManager;
import com.example.yp2ex.databinding.ActivityDeadTamogochiBinding;

import java.util.List;

public class DeadTamogochiActivity extends AppCompatActivity {
    ActivityDeadTamogochiBinding binding;
    DataBaseManager dataBaseManager;
    List<Tamagochi> tamagochiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dead_tamogochi);
        binding = ActivityDeadTamogochiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dataBaseManager = new DataBaseManager(this);
        dataBaseManager.openDb();
        tamagochiList = dataBaseManager.getTamogochis();
        setBestTime(tamagochiList);
        TamagochiAdapter tamagochiAdapter = new TamagochiAdapter(this, tamagochiList);
        binding.recyclerView.setAdapter(tamagochiAdapter);
        binding.buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeadTamogochiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }

    @SuppressLint("SetTextI18n")
    public void setBestTime(List<Tamagochi> tamagochiList) {
        Tamagochi tamagochiBestTime = new Tamagochi();
        tamagochiBestTime.setDays(0);
        for (Tamagochi tamagochi : tamagochiList)
            if (tamagochi.getDays() > tamagochiBestTime.getDays())
                tamagochiBestTime = tamagochi;
        binding.textViewID.setText("ID: " + tamagochiBestTime.getId() + "");
        binding.textViewBore.setText("Веселье: " + tamagochiBestTime.getBore() + "");
        binding.textViewHappy.setText("Счастье: " + tamagochiBestTime.getHappy() + "");
        binding.textViewHungry.setText("Сытость: " + tamagochiBestTime.getHunger() + "");
        binding.textViewTired.setText("Бодрость: " + tamagochiBestTime.getTired() + "");
        binding.textViewTime.setText("Время жизни: " + timeToString(tamagochiBestTime.getDays()));
    }

    @SuppressLint("DefaultLocale")
    public String timeToString(int secondsAlive) {
        long hour = secondsAlive / 3600,
                min = secondsAlive / 60 % 60,
                sec = secondsAlive / 1 % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}