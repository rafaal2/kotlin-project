package com.example.firstapp

import android.content.Intent
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


        binding.buttonConfirmar.setOnClickListener {
            val nome = binding.editTextnome.text.toString().trim()
            val username = binding.editTextuser.text.toString().trim()
            val senha = binding.editTextSenha.text.toString().trim()
            if(username.equals("user") && senha.equals("senha")) {
                Toast.makeText(applicationContext, "senha correta", Toast.LENGTH_SHORT).show()
                val i = Intent(this, ProfileActivity::class.java)
                i.putExtra("nome", nome)
                startActivity(i)
            }else{
                Toast.makeText(applicationContext, "acesso negado", Toast.LENGTH_SHORT).show()
            }
            binding.editTextuser.setText("")
            binding.editTextSenha.setText("")
        }
    }
}
