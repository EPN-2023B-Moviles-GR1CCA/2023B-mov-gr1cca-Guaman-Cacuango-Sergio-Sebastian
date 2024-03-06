package com.example.examen02.accbicicletas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.examen02.R
import com.example.examen02.models.AccesorioBicicleta
import com.example.examen02.service.CRUDService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReadBicicletaActivity : AppCompatActivity() {
    private lateinit var listViewAccesorios : ListView
    private lateinit var crudService: CRUDService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_accesorio)

        listViewAccesorios = findViewById(R.id.listViewAccesorios)
        crudService = CRUDService(this)

        cargarAccesorios()
    }

    private fun cargarAccesorios() {
        CoroutineScope(Dispatchers.Main).launch {
            val accesorios = crudService.leerAccesorios()
            val adapter = ArrayAdapter(this@ReadAccesorioActivity, android.R.layout.simple_list_item_1, accesorios.map { "${it.nombre} - ${it.descripcion}" })
            listViewAccesorios.adapter = adapter
        }
    }
}
