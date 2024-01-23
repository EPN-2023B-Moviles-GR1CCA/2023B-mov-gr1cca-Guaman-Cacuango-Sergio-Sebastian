package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class CrudAccesorioBicicleta : AppCompatActivity() {

    val arregloAccesorios = MemoriaVirtual.arregloAccesorios

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_accesorio_bicicleta)

        val botonGuardarAccesorio = findViewById<Button>(R.id.btn_guardar_accesorio)
        botonGuardarAccesorio.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_accesorio)
            val nombre = findViewById<EditText>(R.id.input_nombre_accesorio)
            val descripcion = findViewById<EditText>(R.id.input_descripcion_accesorio)
            val precio = findViewById<EditText>(R.id.input_precio_accesorio)

            arregloAccesorios.forEachIndexed { index, accesorio ->
                if (id.text.toString().toInt() == accesorio.id) {
                    arregloAccesorios[index] = AccesorioBicicleta(
                        id.text.toString().toInt(),
                        nombre.text.toString(),
                        descripcion.text.toString(),
                        precio.text.toString().toDouble()
                    )
                    mostrarSnackbar("Accesorio Editado")
                } else {
                    arregloAccesorios.add(
                        AccesorioBicicleta(
                            id.text.toString().toInt(),
                            nombre.text.toString(),
                            descripcion.text.toString(),
                            precio.text.toString().toDouble()
                        )
                    )
                    mostrarSnackbar("Accesorio Creado")
                }
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", id.text.toString())
            startActivity(intent)
        }
    }

    fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(R.id.cl_crud_accesorio_bicicleta),
                texto,
                Snackbar.LENGTH_LONG
            )
            .show()
    }
}
