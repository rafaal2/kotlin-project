package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.DAOs.DB
import com.example.firstapp.DAOs.UserDAO
import com.example.firstapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbh = DB(this)
        val db = dbh.writableDatabase
        userDAO = UserDAO(db)

        binding.buttonConfirmar.setOnClickListener {
            val username = binding.editTextuser.text.toString().trim()
            val senha = binding.editTextSenha.text.toString().trim()

            if (username.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userId = userDAO.getUserIdByLogin(username, senha)

            if (userId != null) {
                Toast.makeText(applicationContext, "Login bem-sucedido", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("user_id", userId)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Acesso negado", Toast.LENGTH_SHORT).show()
            }

            binding.editTextuser.setText("")
            binding.editTextSenha.setText("")
        }

        binding.buttonCriarConta.setOnClickListener {
            val intent = Intent(this, RegisterUserActivity::class.java)
            startActivity(intent)
        }
    }
}