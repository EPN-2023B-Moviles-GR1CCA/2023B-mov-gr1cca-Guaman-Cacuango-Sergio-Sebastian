package com.example.examen01

import com.example.examen02.models.AccesorioBicicleta

class Bicicleta(
    var id: Int,
    var marca: String,
    var modelo: String,
    var precio: Double,
    var esNueva: Boolean,
    var accbicicletas: MutableList<AccesorioBicicleta> = mutableListOf()

) {
    override fun toString(): String {
        return """
            ID: ${id}
            Marca: ${marca}
            Modelo: ${modelo}
            Precio: ${precio}
            ¿Es nueva?: ${if (esNueva) "Sí" else "No"}
        """.trimIndent()
    }
    fun agregarAccBicicleta(accbicicleta: AccesorioBicicleta) {
        accbicicletas.add(accbicicleta)
    }
}
