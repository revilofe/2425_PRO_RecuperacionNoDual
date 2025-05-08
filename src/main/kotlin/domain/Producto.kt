// ------------------------------------------------------------
// domain/Producto.kt (clase base abstracta)
// ------------------------------------------------------------
package domain

import kotlinx.serialization.Serializable
import utils.IdGenerator

/**
 * Producto abstracto susceptible de prestarse o intercambiarse.
 * Usa herencia para ser especializado por [Videojuego] y [Libro].
 */
@Serializable
sealed class Producto(
    open val id: Int = IdGenerator.nextId(),
    open val titulo: String,
    open val descripcion: String,
    open val propietario: Usuario
) {
    /** estado informativo (no forma parte del esquema de la práctica pero es útil para filtros) */
    open var estado: String = "Disponible"
}