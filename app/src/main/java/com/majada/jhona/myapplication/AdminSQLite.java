package com.majada.jhona.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLite  extends  SQLiteOpenHelper {

    public AdminSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tareas(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tarea text, estado text, prioridad INTEGER, hora text, fecha text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
