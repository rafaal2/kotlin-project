package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCadastro.setOnClickListener {
            val intent = Intent(this, AddProdutoActivity::class.java)
            startActivity(intent)
        }
        binding.buttonLista.setOnClickListener {
            val a= Intent(this, ReadProdutoActivity::class.java)
            startActivity(a)
        }

    }}
