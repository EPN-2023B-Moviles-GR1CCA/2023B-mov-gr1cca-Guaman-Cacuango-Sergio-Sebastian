package com.example.examen02.accbicicletas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.examen02.R
import com.example.examen02.models.AccesorioBicicleta
import com.example.examen02.models.Bicicleta
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddAccBicicletaActivity : AppCompatActivity() {
    private lateinit var spinnerBicicletas: Spinner
    private lateinit var editTextNombre: EditText
    private lateinit var editTextDescripcion: EditText
    private lateinit var editTextPrecio: EditText

    private lateinit var crudService: CRUDService
    private var bicicletas: List<Bicicleta> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_accbicicleta)

        spinnerBicicletas = findViewById(R.id.spinnerBicicletas)
        editTextNombre = findViewById(R.id.editTextNombreAccesorio)
        editTextDescripcion = findViewById(R.id.editTextDescripcionAccesorio)
        editTextPrecio = findViewById(R.id.editTextPrecioAccesorio)

        crudService = CRUDService(this)

        cargarBicicletas()
        configurarSpinnerBicicletas()
    }

    private fun cargarBicicletas() {
        CoroutineScope(Dispatchers.Main).launch {
            bicicletas = crudService.leerBicicletas()
            val adapter = ArrayAdapter(
                this@AddAccBicicletaActivity,
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
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    fun onAgregarAccesorioClick(view: View) {
        val bicicletaSeleccionada = bicicletas[spinnerBicicletas.selectedItemPosition]
        val nombre = editTextNombre.text.toString()
        val descripcion = editTextDescripcion.text.toString()
        val precio = editTextPrecio.text.toString().toDoubleOrNull() ?: 0.0

        val accesorio = AccesorioBicicleta(nombre, descripcion, precio)
        bicicletaSeleccionada.agregarAccBicicleta(accesorio)
        crudService.actualizarBicicleta(bicicletaSeleccionada.id, bicicletaSeleccionada)
        crudService.addAccesorioBicicleta(accesorio)

        Toast.makeText(this, "Accesorio agregado a la bicicleta ${bicicletaSeleccionada.marca} ${bicicletaSeleccionada.modelo}", Toast.LENGTH_SHORT).show()
    }
}
