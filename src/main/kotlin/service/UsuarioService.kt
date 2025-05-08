// ------------------------------------------------------------
// service/UsuarioService.kt
// ------------------------------------------------------------
package service

import dao.inmemory.UsuarioInMemoryDAO
import domain.Usuario
import utils.Validators
import java.security.MessageDigest

/**
 * Capa de negocio para operaciones sobre [Usuario].
 */
class UsuarioService {
    private val dao = UsuarioInMemoryDAO

    /** Registra un usuario validando email y unicidad. */
    fun registrar(nombre: String, email: String, password: String): Result<Usuario> =
        when {
            nombre.isBlank() -> Result.failure(IllegalArgumentException("El nombre no puede estar vacío."))
            !Validators.isValidEmail(email) -> Result.failure(IllegalArgumentException("Email inválido."))
            dao.findByEmail(email) != null -> Result.failure(IllegalStateException("El email ya está registrado."))
            password.isBlank() -> Result.failure(IllegalArgumentException("La contraseña no puede estar vacía."))
            else -> Result.success(dao.create(Usuario(nombre = nombre, email = email, passwordHash = hash(password))))
        }

    fun login(email: String, password: String): Usuario? =
        dao.findByEmail(email)?.takeIf { it.passwordHash == hash(password) }

    private fun hash(pwd: String): String = MessageDigest.getInstance("SHA-256")
        .digest(pwd.toByteArray())
        .joinToString("") { "%02x".format(it) }
}