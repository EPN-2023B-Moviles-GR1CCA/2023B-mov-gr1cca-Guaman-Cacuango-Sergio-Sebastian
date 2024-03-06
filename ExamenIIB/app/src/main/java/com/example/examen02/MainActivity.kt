package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen02.accbicicletas.BicicletasActivity
import com.example.examen02.accbicicletas.AddAccBicicletaActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAgregarAccesorio = findViewById<Button>(R.id.btnAgregarAccesorio)
        val btnVerBicicletas = findViewById<Button>(R.id.btnVerBicicletas)

        btnAgregarAccesorio.setOnClickListener {
            val intent = Intent(this, AddAccBicicletaActivity::class.java)
            startActivity(intent)
        }
        btnVerBicicletas.setOnClickListener {
            val intent = Intent(this, BicicletasActivity::class.java)
            startActivity(intent)
        }
    }
}
