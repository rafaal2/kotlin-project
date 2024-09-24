package com.example.firstapp

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.firstapp.model.Produto

class DB(context : Context) : SQLiteOpenHelper(context, "baseteste", null, 1) {

    val sql = arrayOf(
        "CREATE TABLE user (user_id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR, senha VARCHAR)",
        "CREATE TABLE produto (produto_id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, quantidade INTEGER, preco DOUBLE)",
        "INSERT INTO produto (nome, quantidade, preco) VALUES ('brinquedo','3','10.50');",
        "INSERT INTO produto (nome, quantidade, preco) VALUES ('mala','2','21.00');"
    )
    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun produtoFindAll(): Cursor {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM produto", null)
        db.close()
        return c
    }

    fun produtoListSelectAll(): ArrayList<Produto> {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM produto", null)
        val listaProduto: ArrayList<Produto> = ArrayList()
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val produtoidIndex = c.getColumnIndex("produto_id")
                val nomeIndex = c.getColumnIndex("nome")
                val quantidadeIndex = c.getColumnIndex("quantidade")
                val precoIndex = c.getColumnIndex("preco")
                val produtoid = c.getInt(produtoidIndex)
                val nome = c.getString(nomeIndex)
                val quantidade = c.getInt(quantidadeIndex)
                val preco = c.getDouble(precoIndex)
                listaProduto.add(Produto(produtoid, nome, quantidade, preco))
            } while (c.moveToNext())
        }
        db.close()
        return listaProduto
    }
}