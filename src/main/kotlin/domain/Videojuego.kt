// ------------------------------------------------------------
// domain/Videojuego.kt
// ------------------------------------------------------------
package domain

import kotlinx.serialization.Serializable
import utils.IdGenerator

/** Caso concreto de [Producto] que añade la plataforma de ejecución. */
@Serializable
data class Videojuego(
    override val id: Int = IdGenerator.nextId(),
    override val titulo: String,
    override val descripcion: String,
    override val propietario: Usuario,
    val plataforma: String
) : Producto(id, titulo, descripcion, propietario)
