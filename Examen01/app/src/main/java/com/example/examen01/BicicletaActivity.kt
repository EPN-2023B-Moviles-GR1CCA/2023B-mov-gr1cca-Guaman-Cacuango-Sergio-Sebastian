package com.example.examen01

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class BicicletaActivity : AppCompatActivity() {

    val arreglo = MemoriaVirtual.arregloBicicletas
    var posicionItemSeleccionado = 0

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_bicicleta, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        posicionItemSeleccionado = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_bicicleta -> {
                val selectedItem = arreglo[posicionItemSeleccionado]
                val intent = Intent(this, CrudBicicleta::class.java)
                intent.putExtra("id", selectedItem.id.toString())
                startActivity(intent)
                return true
            }
            R.id.mi_eliminar_bicicleta -> {
                abrirDialogo(posicionItemSeleccionado)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bicicleta)

        val listView = findViewById<ListView>(R.id.lv_bicicletas)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)

        val botonNuevaBicicleta = findViewById<Button>(R.id.btn_nueva_bicicleta)
        botonNuevaBicicleta.setOnClickListener {
            irActividad(CrudBicicleta::class.java)
        }
    }

    fun mostrarSnackbar(texto: String) {
        Snackbar
            .make(
                findViewById(R.id.cl_main),
                texto,
                Snackbar.LENGTH_LONG
            )
            .show()
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirDialogo(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                eliminarBicicleta(id)
                mostrarSnackbar("Bicicleta eliminada")
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarBicicleta(id: Int) {
        val listView = findViewById<ListView>(R.id.lv_bicicletas)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        arreglo.removeAt(id)
    }
}
