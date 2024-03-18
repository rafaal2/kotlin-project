package com.example.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAperte.setOnClickListener {
            if(!binding.editTextCelsius.text.toString().isEmpty()) {
                val celsius = binding.editTextCelsius.text.toString().toDouble()
                val fahrenheit = celsius * 1.8 + 32
                binding.textResult.text = "$fahrenheit F"
            }else{
                binding.textResult.text = "temperatura invalida"
            }
        }
    }
}
