package com.example.myadapterplayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class mySqlOpenHelper extends SQLiteOpenHelper {
    public mySqlOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS equipesTable(teamId integer PRIMARY KEY AUTOINCREMENT,nom text,ville text,pays text,classement text)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS playersTable(playerId integer PRIMARY KEY AUTOINCREMENT,nom text,prenom text,equipe text,poste text,img integer,teamId integer,FOREIGN KEY (teamId) REFERENCES equipesTable (teamId));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
        _db.execSQL("DROP TABLE IF EXISTS playersTable;");
        onCreate(_db);
        _db.execSQL("DROP TABLE IF EXISTS equipe;");
        onCreate(_db);
    }
}
