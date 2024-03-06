package com.example.examen02.service

import android.content.Context
import com.example.examen02.models.AccesorioBicicleta
import com.example.examen02.models.Bicicleta
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CRUDService(private val context: Context) {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addBicicleta(bicicleta: Bicicleta) {
        db.collection("bicicletas").add(bicicleta)
    }

    suspend fun leerBicicletas(): List<Bicicleta> {
        return try {
            db.collection("bicicletas").get().await().documents.mapNotNull { snapshot ->
                snapshot.toObject(Bicicleta::class.java)
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    fun actualizarBicicleta(id: String, nuevaBicicleta: Bicicleta) {
        db.collection("bicicletas").document(id).set(nuevaBicicleta)
    }

    fun eliminarBicicleta(id: String) {
        db.collection("bicicletas").document(id).delete()
    }

    fun addAccesorio(accBicicleta: AccesorioBicicleta) {
        db.collection("accesorios_bicicletas").add(accBicicleta)
    }

    suspend fun leerAccesorios(): List<AccesorioBicicleta> {
        return try {
            db.collection("accesorios_bicicletas").get().await().documents.mapNotNull { snapshot ->
                snapshot.toObject(AccesorioBicicleta::class.java)
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    fun actualizarAccesorio(id: String, nuevoAccesorio: AccesorioBicicleta) {
        db.collection("accesorios_bicicletas").document(id).set(nuevoAccesorio)
    }

    fun eliminarAccesorio(id: String) {
        db.collection("accesorios_bicicletas").document(id).delete()
    }
}
