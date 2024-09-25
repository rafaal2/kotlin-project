package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userId = intent.getIntExtra("user_id", -1)

        binding.buttonCadastro.setOnClickListener {
            val intent = Intent(this, AddProdutoActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
        }

        binding.buttonLista.setOnClickListener {
            val intent = Intent(this, ReadProdutoActivity::class.java)
            intent.putExtra("user_id", userId)
            startActivity(intent)
        }
    }
}