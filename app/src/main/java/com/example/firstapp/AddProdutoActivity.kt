package com.example.firstapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.DAOs.DB
import com.example.firstapp.DAOs.ProdutoDAO
import com.example.firstapp.databinding.ActivityAddBinding
import com.example.firstapp.model.Produto

class AddProdutoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var produtoDAO: ProdutoDAO
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DB(this)
        produtoDAO = db.getProdutoDAO()

        userId = intent.getIntExtra("user_id", -1)

        binding.buttonSalvarProduto.setOnClickListener {
            val nome = binding.editTextNome.text.toString().trim()
            val quantidadeStr = binding.editTextQuantidade.text.toString().trim()
            val precoStr = binding.editTextPreco.text.toString().trim()

            if (nome.isEmpty() || quantidadeStr.isEmpty() || precoStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantidade = quantidadeStr.toIntOrNull()
            val preco = precoStr.toDoubleOrNull()

            if (quantidade == null || preco == null) {
                Toast.makeText(this, "Quantidade e preço devem ser números válidos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val produto = Produto(0, nome, quantidade, preco, userId)
            val result = produtoDAO.produtoCreate(produto)

            if (result != -1L) {
                Toast.makeText(this, "Produto adicionado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Erro ao adicionar o produto. Tente novamente!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonCancelar.setOnClickListener {
            finish()
        }
    }
}