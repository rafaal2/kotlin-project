package com.example.firstapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAperte.setOnClickListener {
            val nome = binding.editTextNome.text.toString().trim()
            val sobrenome = binding.editTextSobrenome.text.toString().trim()

            if(nome.isEmpty() || sobrenome.isEmpty()) {
                Toast.makeText(applicationContext, "insira nome e sobrenome", Toast.LENGTH_SHORT).show()
            }else{
                binding.textResult.text = "Olá $nome $sobrenome"
            }
        }
    }
}
