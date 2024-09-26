package com.example.firstapp.DAOs

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.example.firstapp.model.Produto

class ProdutoDAO(private val db: SQLiteDatabase) {

    fun produtoListSelectAll(): ArrayList<Produto> {
        val c = db.rawQuery("SELECT * FROM produto", null)
        val listaProduto: ArrayList<Produto> = ArrayList()
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val produtoidIndex = c.getColumnIndex("produto_id")
                val nomeIndex = c.getColumnIndex("nome")
                val quantidadeIndex = c.getColumnIndex("quantidade")
                val precoIndex = c.getColumnIndex("preco")
                val useridIndex = c.getColumnIndex("user_id")
                val produtoid = c.getInt(produtoidIndex)
                val nome = c.getString(nomeIndex)
                val quantidade = c.getInt(quantidadeIndex)
                val preco = c.getDouble(precoIndex)
                val user_id = c.getInt(precoIndex)
                listaProduto.add(Produto(produtoid, nome, quantidade, preco, user_id))
            } while (c.moveToNext())
        }
        return listaProduto
    }

    fun produtoObjectFindByID(produto_id: Int): Produto? {
        val c = db.rawQuery("SELECT * FROM produto WHERE produto_id=?", arrayOf(produto_id.toString()))
        var produto: Produto? = null
        if (c.count == 1) {
            c.moveToFirst()
            val produtoidIndex = c.getColumnIndex("produto_id")
            val nomeIndex = c.getColumnIndex("nome")
            val quantidadeIndex = c.getColumnIndex("quantidade")
            val precoIndex = c.getColumnIndex("preco")
            val useridIndex = c.getColumnIndex("user_id")
            val produtoid = c.getInt(produtoidIndex)
            val nome = c.getString(nomeIndex)
            val quantidade = c.getInt(quantidadeIndex)
            val preco = c.getDouble(precoIndex)
            val user_id = c.getInt(precoIndex)
            produto = Produto(produto_id, nome, quantidade, preco, user_id)
        }
        return produto
    }

    fun produtoListByUser(user_id: Int): ArrayList<Produto> {
        val c = db.rawQuery("SELECT * FROM produto WHERE user_id = ?", arrayOf(user_id.toString()))
        val listaProduto: ArrayList<Produto> = ArrayList()
        if (c.count > 0) {
            c.moveToFirst()
            do {
                val produtoidIndex = c.getColumnIndex("produto_id")
                val nomeIndex = c.getColumnIndex("nome")
                val quantidadeIndex = c.getColumnIndex("quantidade")
                val precoIndex = c.getColumnIndex("preco")
                val useridIndex = c.getColumnIndex("user_id")
                val produtoid = c.getInt(produtoidIndex)
                val nome = c.getString(nomeIndex)
                val quantidade = c.getInt(quantidadeIndex)
                val preco = c.getDouble(precoIndex)
                val user_id = c.getInt(useridIndex)

                listaProduto.add(Produto(produtoid, nome, quantidade, preco, user_id))
            } while (c.moveToNext())
        }
        c.close()
        return listaProduto
    }

    fun produtoCreate(produto: Produto): Long {
        val contentValues = ContentValues()
        contentValues.put("nome", produto.nome)
        contentValues.put("quantidade", produto.quantidade)
        contentValues.put("preco", produto.preco)
        contentValues.put("user_id", produto.user_id)
        return db.insert("produto", null, contentValues)
    }

    fun produtoUpdate(produto_id: Int, nome: String, quantidade: Int, preco: Double, user_id: Int): Int {
        val contentValues = ContentValues()
        contentValues.put("nome", nome)
        contentValues.put("quantidade", quantidade)
        contentValues.put("preco", preco)
        contentValues.put("user_id", user_id)
        return db.update("produto", contentValues, "produto_id=?", arrayOf(produto_id.toString()))
    }

    fun produtoDelete(produto_id: Int): Int {
        return db.delete("produto", "produto_id=?", arrayOf(produto_id.toString()))
    }
}