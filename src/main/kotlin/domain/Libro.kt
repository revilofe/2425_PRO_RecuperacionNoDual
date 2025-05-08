// ------------------------------------------------------------
// domain/Libro.kt
// ------------------------------------------------------------
package domain

import kotlinx.serialization.Serializable
import utils.IdGenerator

/** Caso concreto de [Producto] que añade el autor. */
@Serializable
data class Libro(
    override val id: Int = IdGenerator.nextId(),
    override val titulo: String,
    override val descripcion: String,
    override val propietario: Usuario,
    val autor: String
) : Producto(id, titulo, descripcion, propietario)
