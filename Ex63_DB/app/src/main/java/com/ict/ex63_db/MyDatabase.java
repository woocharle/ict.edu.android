package com.ict.ex63_db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// 헬퍼 클래스 이용해서 DB처리하기
// 반드시 SQLiteOpenHelper를 상속받는다.
// 1. 생성자, 2. implement method 처리(onCreate, onOpen, onUpgrade)
public class MyDatabase extends SQLiteOpenHelper {
    public static int version = 1;
    Context context;
    String name;

    public MyDatabase(@Nullable Context context, @Nullable String name) {
        super(context, name, null, version);
        this.context = context;
        this.name = name;
        Log.i("my", "MyDataBase()");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("my", "onUpgrade()");
    }
}
