package com.example.firstapp.model

class User(val user_id: Int = 0, val username: String="", val senha: String = "") {
    override fun toString(): String {
        return "User(user_id=$user_id, username='$username', senha='$senha')"
    }
}