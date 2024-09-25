package com.example.firstapp.DAOs

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(context : Context) : SQLiteOpenHelper(context, "baseteste", null, 1) {

    val sql = arrayOf(
        "CREATE TABLE user (user_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR, senha VARCHAR)",
        "CREATE TABLE produto (produto_id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, quantidade INTEGER, preco DOUBLE," +
                " user_id INTEGER, FOREIGN KEY(user_id) references user(user_id))"
    )
    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun getProdutoDAO(): ProdutoDAO {
        return ProdutoDAO(this.readableDatabase)
    }
    fun getUserDAO(): UserDAO {
        return UserDAO(this.readableDatabase)
    }
}