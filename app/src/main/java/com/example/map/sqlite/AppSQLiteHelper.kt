package com.example.map.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppSQLiteHelper(
    private val appContext: Context
): SQLiteOpenHelper(appContext, "database.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = appContext.assets.open("db_init.sql").bufferedReader().use {
            it.readText()
        }
        sql.split(";").forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}