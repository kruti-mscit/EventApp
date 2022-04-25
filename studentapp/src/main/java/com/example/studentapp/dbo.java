package com.example.studentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbo extends SQLiteOpenHelper {
    public dbo(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"StudentDB", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table student(id integer primary key autoincrement,name text,city text,dob text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists student");
    }

    public void addData(String name,String city,String dob)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name); // 1st table column name
        cv.put("city",city);
        cv.put("dob",dob);
        db.insert("student",null,cv); // tbalename ,
    }

    public Cursor getdata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from " + "student",null);
        return  cursor;
    }

    public Cursor getSearchCityData(String city)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from student where city = ?",new String[]{city});
        return  c;
    }

    public void deletedata(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("student","id" + "=" + id, null) ;

    }

    public void updatedata(String id,String name, String city, String dob)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name); // 1st table column name
        cv.put("city",city);
        cv.put("dob",dob);
        db.update("student", cv, "id= "+ id,null);
    }
    public boolean checkemailpsw(String email,String psw)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery("select * from voter where email = ? and psw = ?",new String[]{email,psw});
        if(c.getCount()>0)

            return  true;
        else
            return  false;
    }
}
