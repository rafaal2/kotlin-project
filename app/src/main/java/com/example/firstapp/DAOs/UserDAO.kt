package com.example.firstapp.DAOs

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.firstapp.model.User

class UserDAO(private val db: SQLiteDatabase) {
    fun userCreate(user: User): Long {
        val contentValues = ContentValues()
        contentValues.put("username", user.username)
        contentValues.put("senha", user.senha)
        return db.insert("user", null, contentValues)
    }

}