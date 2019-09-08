package com.syahrul.loginregistersqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, dbname,factory, version){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table user(id integer primary key autoincrement," + "username varchar(30),email varchar(100),password varchar(20))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertUser(username: String, email: String, password: String){
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put("username",username)
        values.put("email",email)
        values.put("password",password)

        db.insert("user",null,values)
        db.close()
    }

    fun userPresent(email: String, password: String): Boolean{
        val db = writableDatabase
        val query = "select * from user where email='$email' and password='$password'"
        val cursor = db.rawQuery(query,null)
        if(cursor.count <= 0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    companion object {
        internal val dbname = "dbUser"
        internal val factory = null
        internal val version = 1
    }
}