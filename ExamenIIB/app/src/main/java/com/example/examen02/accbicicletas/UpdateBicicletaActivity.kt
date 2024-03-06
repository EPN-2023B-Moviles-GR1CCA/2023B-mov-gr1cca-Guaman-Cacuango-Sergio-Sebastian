package com.example.examen02.accbicicletas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.Bicicleta
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateBicicletaActivity : AppCompatActivity() {
    private lateinit var spinnerBicicletas: Spinner
    private lateinit var editTextMarca: EditText
    private lateinit var editTextModelo: EditText
    private lateinit var editTextPrecio: EditText
    private lateinit var checkboxEsNueva: CheckBox
    private lateinit var botonActualizar: Button

    private lateinit var crudService: CRUDService
    private var bicicletas: List<Bicicleta> = listOf()
    private var bicicletaSeleccionada: Bicicleta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_bicicleta)

        spinnerBicicletas = findViewById(R.id.spinnerBicicletasUpdate)
        editTextMarca = findViewById(R.id.editTextMarcaBicicletaUpdate)
        editTextModelo = findViewById(R.id.editTextModeloBicicletaUpdate)
        editTextPrecio = findViewById(R.id.editTextPrecioBicicletaUpdate)
        checkboxEsNueva = findViewById(R.id.checkBoxEsNuevaBicicletaUpdate)
        botonActualizar = findViewById(R.id.botonActualizarBicicleta)

        crudService = CRUDService(this)

        cargarBicicletas()
        configurarSpinnerBicicletas()
    }

    private fun cargarBicicletas() {
        CoroutineScope(Dispatchers.Main).launch {
            bicicletas = crudService.leerBicicletas()
            val adapter = ArrayAdapter(
                this@UpdateBicicletaActivity,
                android.R.layout.simple_spinner_item,
                bicicletas.map { it.marca + " " + it.modelo })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerBicicletas.adapter = adapter
        }
    }

    private fun configurarSpinnerBicicletas() {
        spinnerBicicletas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                bicicletaSeleccionada = bicicletas[position]
                cargarDatosBicicleta(bicicletaSeleccionada!!)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                bicicletaSeleccionada = null
            }
        }
    }

    private fun cargarDatosBicicleta(bicicleta: Bicicleta) {
        editTextMarca.setText(bicicleta.marca)
        editTextModelo.setText(bicicleta.modelo)
        editTextPrecio.setText(bicicleta.precio.toString())
        checkboxEsNueva.isChecked = bicicleta.esNueva
    }

    fun onActualizarBicicletaClick(view: View) {
        bicicletaSeleccionada?.let {
            val marca = editTextMarca.text.toString()
            val modelo = editTextModelo.text.toString()
            val precio = editTextPrecio.text.toString().toDoubleOrNull() ?: 0.0
            val esNueva = checkboxEsNueva.isChecked

            val bicicletaActualizada = Bicicleta(
                it.id, marca, modelo, precio, esNueva
            )

            try {
                crudService.actualizarBicicleta(it.id, bicicletaActualizada)
                Toast.makeText(this, "Bicicleta actualizada con éxito!", Toast.LENGTH_SHORT).show()
                cargarBicicletas()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al actualizar la bicicleta", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Por favor selecciona una bicicleta", Toast.LENGTH_SHORT).show()
    }
}
