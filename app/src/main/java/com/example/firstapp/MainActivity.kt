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


        binding.buttonDolar.setOnClickListener {
            val real = binding.editTextReal.text.toString().trim()
            if(real.isEmpty()) {
                Toast.makeText(applicationContext, "insira um valor", Toast.LENGTH_SHORT).show()
            }else{
                val resultado = real.toDouble() * 0.2
                binding.textResult.text = "${resultado}"
            }
        }
        binding.buttonEuro.setOnClickListener {
            val real = binding.editTextReal.text.toString().trim()
            if(real.isEmpty()) {
                Toast.makeText(applicationContext, "insira um valor", Toast.LENGTH_SHORT).show()
            }else{
                val resultado = real.toDouble() * 0.18
                binding.textResult.text = "${resultado}"
            }
        }
        binding.buttonYuan.setOnClickListener {
            val real = binding.editTextReal.text.toString().trim()
            if(real.isEmpty()) {
                Toast.makeText(applicationContext, "insira um valor", Toast.LENGTH_SHORT).show()
            }else{
                val resultado = real.toDouble() * 1.44
                binding.textResult.text = "${resultado}"
            }
        }
    }
}
