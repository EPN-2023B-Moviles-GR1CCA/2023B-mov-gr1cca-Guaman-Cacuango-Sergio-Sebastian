package com.example.examen02.accbicicletas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.AccesorioBicicleta
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DeleteBicicletaActivity : AppCompatActivity() {
    private lateinit var spinnerAccesorios: Spinner
    private lateinit var botonEliminar: Button
    private lateinit var crudService: CRUDService
    private var accesorios: List<AccesorioBicicleta> = listOf()
    private var accesorioSeleccionado: AccesorioBicicleta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_accesorio)

        spinnerAccesorios = findViewById(R.id.spinnerAccesorios)
        botonEliminar = findViewById(R.id.botonEliminar)
        crudService = CRUDService(this)

        cargarAccesorios()
        configurarSpinnerAccesorios()
    }

    private fun cargarAccesorios() {
        CoroutineScope(Dispatchers.Main).launch {
            accesorios = crudService.leerAccesorios()
            val adapter = ArrayAdapter(
                this@DeleteAccesorioActivity,
                android.R.layout.simple_spinner_item,
                accesorios.map { it.nombre })
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerAccesorios.adapter = adapter
        }
    }

    private fun configurarSpinnerAccesorios() {
        spinnerAccesorios.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                accesorioSeleccionado = accesorios[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                accesorioSeleccionado = null
            }
        }
    }

    fun onEliminarAccesorioClick(view: View) {
        accesorioSeleccionado?.let {
            try {
                crudService.eliminarAccesorio(it.nombre)
                Toast.makeText(this, "Accesorio eliminado con éxito", Toast.LENGTH_SHORT).show()
                cargarAccesorios()
            } catch (e: Exception) {
                Toast.makeText(this, "Error al eliminar el accesorio", Toast.LENGTH_SHORT).show()
            }
        } ?: Toast.makeText(this, "Por favor selecciona un accesorio", Toast.LENGTH_SHORT).show()
    }
}
