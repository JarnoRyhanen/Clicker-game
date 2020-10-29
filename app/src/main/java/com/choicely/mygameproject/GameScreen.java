package com.choicely.mygameproject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.choicely.mygameproject.db.RealmHelper;

import java.util.Random;

import io.realm.Realm;
import io.realm.Sort;

public class GameScreen extends AppCompatActivity {

    private static final String TAG = "GameScreen";
    private TextView title;
    private Button saveGame;
    private Button plusOne;
    private TextView outPut;
    private Button plusTen;
    private Button plusOneHundred;
    private Button addRandomNumber;
    private ProgressBar progressBar10;
    private ProgressBar progressBar100;
    private ProgressBar progressBarRandomNumber;
    private long counter = 0;
    private int scoreForProgressBar10 = 0;
    private int scoreForProgressBar100 = 0;
    private int scoreForProgressBarRandom = 0;
    private int gameID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        title = findViewById(R.id.game_screen_title);
        saveGame = findViewById(R.id.game_screen_save_button);
        plusOne = findViewById(R.id.game_screen_plus_1);
        outPut = findViewById(R.id.game_screen_output);
        plusTen = findViewById(R.id.game_screen_plus_10);
        plusOneHundred = findViewById(R.id.game_screen_plus_100);
        addRandomNumber = findViewById(R.id.game_screen_random_number);
        progressBar10 = findViewById(R.id.game_screen_plus_10_progress);
        progressBar100 = findViewById(R.id.game_screen_plus_100_progress);
        progressBarRandomNumber = findViewById(R.id.game_screen_random_number_progress);
        
        gameID = (int) getIntent().getLongExtra(IntentKeys.GAME_ID, -1);

        if (gameID == -1) {
            newGame();
        } else {
            loadGame();
        }

        plusOne.setOnClickListener(v -> {
            counter += 1;
            levelSum();
        });

        plusTen.setOnClickListener(v -> {
            counter += 10;
            plusTen.setEnabled(false);
            timer10.start();
            levelSum();
        });


        plusOneHundred.setOnClickListener(v -> {
            counter += 100;
            plusOneHundred.setEnabled(false);
            timer100.start();
            levelSum();
        });

        addRandomNumber.setOnClickListener(v -> {
            final int random = new Random().nextInt(1000);
            counter += random;
            addRandomNumber.setEnabled(false);
            timerRandom.start();
            levelSum();
        });

        saveGame.setOnClickListener(v -> {

            saveGame();
        });

    }

    private void loadGame() {
        Realm realm = RealmHelper.getInstance().getRealm();

        GameData game = realm.where(GameData.class).equalTo("id", gameID).findFirst();
        counter = game.getLevel();
        levelSum();
    }

    private void newGame() {
        Realm realm = RealmHelper.getInstance().getRealm();
        GameData lastGame = realm.where(GameData.class).sort("id", Sort.DESCENDING).findFirst();
        if (lastGame != null) {
            gameID = (int) (lastGame.getId() + 1);
        } else {
            gameID = 0;
        }
    }

    private void levelSum() {
        outPut.setText("Level: " + counter);
    }


    private CountDownTimer timer10 = new CountDownTimer(10000, 100) {
        @Override
        public void onTick(long millisUntilFinished) {
            scoreForProgressBar10 += 1;
            progressBar10.setProgress(scoreForProgressBar10);
        }

        @Override
        public void onFinish() {
            plusTen.setEnabled(true);
            emptyProgressFor10();
        }
    };

    private CountDownTimer timer100 = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            scoreForProgressBar100 += 1;
            progressBar100.setProgress(scoreForProgressBar100);
        }

        @Override
        public void onFinish() {
            plusOneHundred.setEnabled(true);
            emptyProgressFor100();
        }
    };

    private CountDownTimer timerRandom = new CountDownTimer(100000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            scoreForProgressBarRandom += 1;
            progressBarRandomNumber.setProgress(scoreForProgressBarRandom);
        }

        @Override
        public void onFinish() {
            addRandomNumber.setEnabled(true);
            emptyProgressForRandom();
        }
    };

    private void emptyProgressFor100() {
        scoreForProgressBar100 = 0;
        progressBar100.setProgress(scoreForProgressBar100);
    }

    private void emptyProgressFor10() {
        scoreForProgressBar10 = 0;
        progressBar10.setProgress(scoreForProgressBar10);
    }

    private void emptyProgressForRandom() {
        scoreForProgressBarRandom = 0;
        progressBarRandomNumber.setProgress(scoreForProgressBarRandom);
    }

    private void saveGame() {
        Realm realm = RealmHelper.getInstance().getRealm();
        GameData game = new GameData();


        game.setLevel(counter);
        game.setId(gameID);
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(game));
        Log.d(TAG, "Pelin id on " + game.getId());


    }

}