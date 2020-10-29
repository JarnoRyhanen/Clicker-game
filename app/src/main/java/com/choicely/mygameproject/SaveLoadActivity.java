package com.choicely.mygameproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.choicely.mygameproject.db.RealmHelper;

import io.realm.Realm;
import io.realm.RealmResults;


public class SaveLoadActivity extends AppCompatActivity {
    private static final String TAG = "SaveLoadActivity";

    private RecyclerView recyclerView;
    private GameAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_load_activity);
        recyclerView = findViewById(R.id.save_load_activity_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GameAdapter(this);
        recyclerView.setAdapter(adapter);


        UpdateContent();
    }

    private void UpdateContent() {
        adapter.clear();

        Realm realm = RealmHelper.getInstance().getRealm();
        RealmResults<GameData> games = realm.where(GameData.class).findAll();

        for (GameData game : games) {
            adapter.add(game);
        }

        adapter.notifyDataSetChanged();

    }

    ;
}

