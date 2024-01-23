package com.example.examen01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class CrudBicicleta : AppCompatActivity() {

    var arregloBicicletas = MemoriaVirtual.arregloBicicletas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_bicicleta)

        val botonGuardarBicicleta = findViewById<Button>(R.id.btn_guardar_bicicleta)
        botonGuardarBicicleta.setOnClickListener {
            val id = findViewById<EditText>(R.id.input_id_bicicleta)
            val marca = findViewById<EditText>(R.id.input_marca_bicicleta)
            val modelo = findViewById<EditText>(R.id.input_modelo_bicicleta)
            val precio = findViewById<EditText>(R.id.input_precio_bicicleta)
            val esNueva = findViewById<CheckBox>(R.id.input_nueva_bicicleta).isChecked

            arregloBicicletas.forEachIndexed { index, bicicleta ->
                if (id.text.toString().toInt() == bicicleta.id) {
                    arregloBicicletas[index] = Bicicleta(
                        id.text.toString().toInt(),
                        marca.text.toString(),
                        modelo.text.toString(),
                        precio.text.toString().toDouble(),
                        esNueva
                    )
                    mostrarSnackbar("Bicicleta Editada")
                } else {
                    arregloBicicletas.add(
                        Bicicleta(
                            id.text.toString().toInt(),
                            marca.text.toString(),
                            modelo.text.toString(),
                            precio.text.toString().toDouble(),
                            esNueva
                        )
                    )
                    mostrarSnackbar("Bicicleta Creada")
                }
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(R.id.cl_crud_bicicleta),
                texto,
                Snackbar.LENGTH_LONG
            )
            .show()
    }
}
