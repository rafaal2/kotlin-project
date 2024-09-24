package com.example.firstapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(context : Context) : SQLiteOpenHelper(context, "baseteste", null, 1) {

    val sql = arrayOf(
        "CREATE TABLE user (user_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR, senha VARCHAR)",
        "CREATE TABLE produto (product_id INTEGER PRIMARY KEY AUTOINCREMENT, descricao VARCHAR, quantidade INTEGER, preco DOUBLE)"
    )
    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}