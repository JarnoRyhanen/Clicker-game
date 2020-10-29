package com.choicely.mygameproject.app;


import android.app.Application;
import android.util.Log;


import com.choicely.mygameproject.db.RealmHelper;

public class GameApplication extends Application {

    private static final String TAG = "GameApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "App start");

        RealmHelper.init(this);
    }
}
