package com.example.firstapp

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivityReadBinding

class ReadProdutoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = DB(this)
        val allProducts = db.produtoListSelectAll()

        binding.ListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, allProducts)
    }}