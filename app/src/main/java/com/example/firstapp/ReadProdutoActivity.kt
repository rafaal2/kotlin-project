package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapp.DAOs.DB
import com.example.firstapp.DAOs.ProdutoDAO
import com.example.firstapp.databinding.ActivityReadBinding
import com.example.firstapp.model.Produto

class ReadProdutoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding
    private lateinit var produtoDAO: ProdutoDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DB(this)
        produtoDAO = db.getProdutoDAO()

        val allProducts = produtoDAO.produtoListSelectAll()

        val adapter = object : ArrayAdapter<Produto>(this, 0, allProducts) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_product, parent, false)

                val product = getItem(position)

                val textViewProduct = view.findViewById<TextView>(R.id.textViewProduct)
                textViewProduct.text = "Nome: ${product?.nome}"

                val textViewQuantity = view.findViewById<TextView>(R.id.textViewQuantity)
                textViewQuantity.text = "Quantidade: ${product?.quantidade?.toString() ?: "0"}"

                val textViewPrice = view.findViewById<TextView>(R.id.textViewPrice)
                textViewPrice.text = "Preço: R$ ${product?.preco?.toString() ?: "0.00"}"


                val buttonEdit = view.findViewById<Button>(R.id.buttonEdit)
                buttonEdit.setOnClickListener {
                    val i = Intent(context, EditProdutoActivity::class.java)
                    i.putExtra("produto_id", product?.produto_id)
                    startActivity(i)
                }

                return view
            }
        }

        binding.listView.adapter = adapter
    }
}