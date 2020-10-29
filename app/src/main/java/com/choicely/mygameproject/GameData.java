package com.choicely.mygameproject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class GameData extends RealmObject {

    @PrimaryKey
    private long id;
    private long level;

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
