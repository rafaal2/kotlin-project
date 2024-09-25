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

    fun verifyUser(username: String, senha: String): Boolean {
        val query = "SELECT * FROM user WHERE username = ? AND senha = ?"
        val cursor = db.rawQuery(query, arrayOf(username, senha))

        val isValidUser = cursor.count > 0
        cursor.close()

        return isValidUser
    }

    fun getUserIdByLogin(username: String, senha: String): Int? {
        val cursor = db.rawQuery(
            "SELECT user_id FROM user WHERE username = ? AND senha = ?",
            arrayOf(username, senha)
        )

        var userId: Int? = null

        if (cursor.moveToFirst()) {
            val userIdIndex = cursor.getColumnIndex("user_id")
            userId = cursor.getInt(userIdIndex)
        }

        cursor.close()
        return userId
    }
}