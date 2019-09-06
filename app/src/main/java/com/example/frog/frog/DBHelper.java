package com.example.frog.frog;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    final int num = 40;

    UserInformation ui;

    //DBHelper 생성자로 관리할 db이름과 버젼 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public int i = 0;
    public String location, userName = "";
    public int numb;
    public int salary, graduation, category, exLevel;
    BKInfo[] bkInfo = new BKInfo[20];

    public class BKInfo {
        String companyID, title, deadLine, companyName;
    }

    //DB 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        //새로운 테이블 생성
        db.execSQL("CREATE TABLE INFORMATION (numb int not null, username char(10) not null, graduation int, exlevel int, location varchar(10), category int, salary int);");
        db.execSQL("CREATE TABLE BOOKMARK(companyID varchar(20) not null, companyName varchar(30), title varchar(30), deadLine varchar(30));");
    }

    //DB 업그레이드 위해 버젼이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //사용자 정보 삽입
    public void insertInfo(int numb, String username, int graduation, int exlevel, String location, int category, int salary, int check) {
        SQLiteDatabase db = getWritableDatabase();
        if (check == 1) {
            db.execSQL("UPDATE INFORMATION SET username='" + username + "' WHERE numb=1;");
            db.execSQL("UPDATE INFORMATION SET graduation=" + graduation + " WHERE numb=1;");
            db.execSQL("UPDATE INFORMATION SET exlevel=" + exlevel + " WHERE numb=1;");
            db.execSQL("UPDATE INFORMATION SET location='" + location + "' WHERE numb=1;");
            db.execSQL("UPDATE INFORMATION SET category=" + category + " WHERE numb=1;");
            db.execSQL("UPDATE INFORMATION SET salary=" + salary + " WHERE numb=1;");
        } else
            db.execSQL("INSERT INTO INFORMATION VALUES(" + numb + ",'" + username + "'," + graduation + "," + exlevel + ",'" + location + "'," + category + "," + salary + ");");


        db.close();

    }

    //즐겨찾기 정보 삽입
    public void insertMark(String companyID, String companyName, String title, String deadLine) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO BOOKMARK VALUES('" + companyID + "','" + companyName + "','" + title + "','" + deadLine + "');");
        db.close();
    }

    //BOOKMARK 테이블에서 정보 불러오기
    public void select() {
        SQLiteDatabase db = getReadableDatabase();


        i = 0;
        int companyID;
        String companyName, title, deadLine;
        Cursor cursor = db.rawQuery("SELECT * FROM BOOKMARK", null);
        while (cursor.moveToNext()) {
            bkInfo[i] = new BKInfo();
            bkInfo[i].companyID = cursor.getString(0);
            bkInfo[i].companyName = cursor.getString(1);
            bkInfo[i].title = cursor.getString(2);
            bkInfo[i].deadLine = cursor.getString(3);
            i++;
        }
        db.close();
    }

    public void getResult() {
        SQLiteDatabase db = getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM INFORMATION", null);
        while (cursor.moveToNext()) {
            numb = cursor.getInt(0);
            userName = cursor.getString(1);
            location = cursor.getString(4);
            salary = cursor.getInt(6);
            graduation = cursor.getInt(2);
            category = cursor.getInt(5);
            exLevel = cursor.getInt(3);


        }

    }

    //BOOKMARK 테이블에서만 쓰임.
    public void delete(String companyID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM BOOKMARK WHERE companyID='" + companyID + "';");
        db.close();
    }

}