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
                val produtoid = c.getInt(produtoidIndex)
                val nome = c.getString(nomeIndex)
                val quantidade = c.getInt(quantidadeIndex)
                val preco = c.getDouble(precoIndex)
                listaProduto.add(Produto(produtoid, nome, quantidade, preco))
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
            val produtoid = c.getInt(produtoidIndex)
            val nome = c.getString(nomeIndex)
            val quantidade = c.getInt(quantidadeIndex)
            val preco = c.getDouble(precoIndex)
            produto = Produto(produto_id, nome, quantidade, preco)
        }
        return produto
    }

    fun produtoCreate(produto: Produto): Long {
        val contentValues = ContentValues()
        contentValues.put("nome", produto.nome)
        contentValues.put("quantidade", produto.quantidade)
        contentValues.put("preco", produto.preco)
        return db.insert("produto", null, contentValues)
    }

    fun produtoUpdate(produto_id: Int, nome: String, quantidade: Int, preco: Double): Int {
        val contentValues = ContentValues()
        contentValues.put("nome", nome)
        contentValues.put("quantidade", quantidade)
        contentValues.put("preco", preco)
        return db.update("produto", contentValues, "produto_id=?", arrayOf(produto_id.toString()))
    }

    fun produtoDelete(produto_id: Int): Int {
        return db.delete("produto", "produto_id=?", arrayOf(produto_id.toString()))
    }
}