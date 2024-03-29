package com.example.examen01

class AccesorioBicicleta(
    var id: Int,
    var nombre: String,
    var descripcion: String,
    var precio: Double
) {
    override fun toString(): String {
        return """
            ID: ${id}
            Nombre: ${nombre}
            Descripción: ${descripcion}
            Precio: ${precio}
        """.trimIndent()
    }
}
