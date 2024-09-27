package com.example.firstapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.DAOs.DB
import com.example.firstapp.DAOs.ProdutoDAO
import com.example.firstapp.databinding.ActivityEditprodutoBinding

class EditProdutoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditprodutoBinding
    private lateinit var produtoDAO: ProdutoDAO
    private var userId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditprodutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("nome")
        val quantidade = intent.getIntExtra("quantidade", 0)
        val preco = intent.getDoubleExtra("preco", 0.00)

        val textNomer = findViewById<TextView>(R.id.textNomer)
        val textViewQuantidader = findViewById<TextView>(R.id.textViewQuantidader)
        val textViewPrecor = findViewById<TextView>(R.id.textViewPrecor)
        val db = DB(this)

        textNomer.text = nome
        textViewQuantidader.text = quantidade.toString()
        textViewPrecor.text = "R$ $preco"

        produtoDAO = db.getProdutoDAO()
        userId = intent.getIntExtra("user_id", -1)

        binding.buttonCancelar.setOnClickListener {
            finish()
            val intent = Intent(this, ReadProdutoActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
        }

        binding.buttonExcluirProduto.setOnClickListener {
            showdeleteDialog()
        }

        binding.buttonEditarProduto.setOnClickListener {
            val produtoId = intent.getIntExtra("produto_id", -1)
            val nome = binding.editTextNovoNome.text.toString().trim()
            val quantidadeStr = binding.editTextNovaQuantidade.text.toString().trim()
            var precoStr = binding.editTextNovoPreco.text.toString().trim()

            if (nome.isEmpty() || quantidadeStr.isEmpty() || precoStr.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            precoStr = precoStr.replace(",", ".")


            val quantidade = quantidadeStr.toIntOrNull()
            val preco = precoStr.toDoubleOrNull()

            if (quantidade == null || preco == null) {
                Toast.makeText(this, "Quantidade e preço devem ser números válidos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (produtoId != -1) {
                val result = produtoDAO.produtoUpdate(produtoId, nome, quantidade, preco, userId)

                if (result > 0) {
                    Toast.makeText(this, "Produto atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                    val intent = Intent(this, ReadProdutoActivity::class.java)
                    intent.putExtra("user_id", userId)
                    startActivity(intent)
                }
            }
        }

    }
    private fun showdeleteDialog() {
        val dialog = Dialog(this, R.style.CustomDialog)
        val view: View = LayoutInflater.from(this).inflate(R.layout.dialogdelete_box, null)
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val buttonConfirmarC = view.findViewById<View>(R.id.buttonConfirmarC)
        val buttonCancelarC = view.findViewById<View>(R.id.buttonCancelarC)

        buttonConfirmarC.setOnClickListener {
            val produtoId = intent.getIntExtra("produto_id", -1)
            if (produtoId != -1) {
                val result = produtoDAO.produtoDelete(produtoId)
                if (result > 0) {
                    Toast.makeText(this, "Produto excluído com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                    val intent = Intent(this, ReadProdutoActivity::class.java)
                    intent.putExtra("user_id", userId)
                    startActivity(intent)
                }
            }
        }

        buttonCancelarC.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
