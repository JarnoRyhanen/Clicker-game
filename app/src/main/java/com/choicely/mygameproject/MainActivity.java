package com.choicely.mygameproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button newGameBtn;
    private Button loadGameBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        loadGameBtn = findViewById(R.id.main_activity_load_game);
        newGameBtn = findViewById(R.id.main_activity_new_game);

        newGameBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, GameScreen.class);
            startActivity(intent);

        });

        loadGameBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SaveLoadActivity.class);
            startActivity(intent);
        });

    }
}

