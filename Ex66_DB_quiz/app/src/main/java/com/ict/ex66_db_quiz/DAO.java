package com.ict.ex66_db_quiz;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DAO {
    Mydatabase mydatabase;
    SQLiteDatabase db;

    public DAO() {}

    public DAO(Context context){
        mydatabase = new Mydatabase(context);
        db = mydatabase.getWritableDatabase();
    }

    public static DAO db_open(Context context) throws SQLException {
        DAO dao = new DAO(context);
        return dao;
    }

    public void db_close(){
        mydatabase.close();
        db.close();
    }

    // db처리에 필요한 메소드
    // 날짜를 받아서 해당 정보 리턴
    public Cursor select_data(String date){
        Cursor cursor = null;
        String sql = "select * from schedule where dailyDate=?";
        String[] arr = {date};
        cursor = db.rawQuery(sql, arr);

        return cursor;
    }

    public void insertData(String date, String schedule){
        String sql = "insert into schedule values(null, ?, ?) ";
        String[] arr = {date, schedule};
        db.execSQL(sql,arr);
    }

    public void updateData(String idx, String plan){
        String sql = "update schedule set content=? where idx=?";
        String[] arr = {plan, idx};
        db.execSQL(sql, arr);
    }

    public void deleteData(String idx){
        String sql = "delete from schedule where idx = ?";
        String[] arr = {idx};
        db.execSQL(sql, arr);
    }

    public Cursor selectDesc(){
        Cursor cursor = null;
        String sql = "select * from schedule order by dailyDate desc";
        cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public Cursor selectAsc(){
        Cursor cursor = null;
        String sql = "select * from schedule order by dailyDate asc";
        cursor = db.rawQuery(sql, null);
        return cursor;
    }

}
