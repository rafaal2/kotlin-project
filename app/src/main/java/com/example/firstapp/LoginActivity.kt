package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonConfirmar.setOnClickListener {
            val username = binding.editTextuser.text.toString().trim()
            val senha = binding.editTextSenha.text.toString().trim()
            if(username.equals("user") && senha.equals("senha")) {
                Toast.makeText(applicationContext, "senha correta", Toast.LENGTH_SHORT).show()
                val i = Intent(this, ProfileActivity::class.java)
                startActivity(i)
            }else{
                Toast.makeText(applicationContext, "acesso negado", Toast.LENGTH_SHORT).show()
            }
            binding.editTextuser.setText("")
            binding.editTextSenha.setText("")
        }
        binding.buttonCriarConta.setOnClickListener {
            val a = Intent(this, RegisterActivity::class.java)
            startActivity(a)
        }
    }
}
