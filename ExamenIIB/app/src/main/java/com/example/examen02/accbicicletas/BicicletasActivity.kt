package com.example.examen02.accbicicletas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen02.R

class BicicletasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bicicletas)

        val btnAddAccesorio = findViewById<Button>(R.id.btnAddAccesorio)
        val btnReadAccesorio = findViewById<Button>(R.id.btnReadAccesorios)
        val btnDeleteAccesorio = findViewById<Button>(R.id.btnDeleteAccesorio)
        val btnUpdateAccesorio = findViewById<Button>(R.id.btnUpdateAccesorio)

        btnAddAccesorio.setOnClickListener {
            val intent = Intent(this, AddAccBicicletaActivity::class.java)
            startActivity(intent)
        }
        btnReadAccesorio.setOnClickListener {
            val intent = Intent(this, ReadAccesorioActivity::class.java)
            startActivity(intent)
        }
        btnDeleteAccesorio.setOnClickListener {
            val intent = Intent(this, DeleteAccesorioActivity::class.java)
            startActivity(intent)
        }
        btnUpdateAccesorio.setOnClickListener {
            val intent = Intent(this, UpdateAccesorioActivity::class.java)
            startActivity(intent)
        }
    }
}
