package com.example.examen01

class MemoriaVirtual {
    companion object {
        val arregloBicicletas = arrayListOf<Bicicleta>()
        val arregloAccesorios = arrayListOf<AccesorioBicicleta>()

        init {
            arregloBicicletas.add(
                Bicicleta(1, "Marca1", "Modelo1", 500.0, true)
            )
            arregloAccesorios.add(
                AccesorioBicicleta(1, "Casco", "Casco protector para ciclismo", 50.0)
            )
        }
    }
}
