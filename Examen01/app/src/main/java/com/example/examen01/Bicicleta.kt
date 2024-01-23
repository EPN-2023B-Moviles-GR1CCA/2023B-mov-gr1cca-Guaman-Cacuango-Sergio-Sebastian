package com.example.examen01

class Bicicleta(
    var id: Int,
    var marca: String,
    var modelo: String,
    var precio: Double,
    var esNueva: Boolean
) {
    override fun toString(): String {
        return """
            ID: $id
            Marca: $marca
            Modelo: $modelo
            Precio: $precio
            ¿Es nueva?: ${if (esNueva) "Sí" else "No"}
        """.trimIndent()
    }
}
