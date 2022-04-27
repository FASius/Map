package com.example.map

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.map.model.account.AccountRepository
import com.example.map.model.account.SQLAccountManager
import com.example.map.model.account.SharedPreferencesAccount
import com.example.map.model.checkin.CheckInManager
import com.example.map.sqlite.AppSQLiteHelper

object Repositories {

    private lateinit var applicationContext: Context

    private val database: SQLiteDatabase by lazy<SQLiteDatabase> {
        AppSQLiteHelper(applicationContext).writableDatabase
    }

    lateinit var currentAccount:SharedPreferencesAccount

    val checkInsRepository: CheckInManager = CheckInManager()

    val accountsRepository: AccountRepository by lazy {
        SQLAccountManager(database, currentAccount)
    }

    fun initAppContext (context: Context) {
        applicationContext = context
    }

    fun initCurrentAccount () {
        currentAccount = SharedPreferencesAccount(applicationContext)
    }
}