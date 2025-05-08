// ------------------------------------------------------------
// domain/Usuario.kt
// ------------------------------------------------------------
package domain

import kotlinx.serialization.Serializable
import utils.IdGenerator

/**
 * Entidad de dominio que representa a un usuario registrado.
 * Almacena el hash de la contraseña para evitar guardar texto plano.
 */
@Serializable
data class Usuario(
    val id: Int = IdGenerator.nextId(),
    val nombre: String,
    val email: String,
    val passwordHash: String
)
