package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCadastro.setOnClickListener {
            val i = Intent(this, AddProductActivity::class.java)
            startActivity(i)
        }
        binding.buttonLista.setOnClickListener {
            val a= Intent(this, ReadProductActivity::class.java)
            startActivity(a)
        }

    }}
