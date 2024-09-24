package com.example.firstapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.DAOs.DB
import com.example.firstapp.DAOs.UserDAO
import com.example.firstapp.databinding.ActivityRegisterBinding
import com.example.firstapp.model.User

class RegisterUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var UserDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DB(this)
        UserDAO = db.getUserDAO()

        binding.buttonCriarConta.setOnClickListener {
            val username = binding.editTextNome.text.toString().trim()
            val senha = binding.editTextQuantidade.text.toString().trim()

            if (username.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(0, username, senha)
            val result = UserDAO.userCreate(user)

            if (result != -1L) {
                Toast.makeText(this, "conta criada com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "erro ao criar conta", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonCancelar.setOnClickListener {
            finish()
        }
    }}