package com.example.firstapp

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.OnBackPressedCallback
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
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showLogoutDialog()
            }
        })
    }
    private fun showLogoutDialog() {
        val dialog = Dialog(this)
        val view: View = LayoutInflater.from(this).inflate(R.layout.dialoglogout_box,null)
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val buttonConfirmar = view.findViewById<View>(R.id.buttonConfirmar)
        val buttonCancelar = view.findViewById<View>(R.id.buttonCancelar)

        buttonConfirmar.setOnClickListener {
            finish()
        }
        buttonCancelar.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}