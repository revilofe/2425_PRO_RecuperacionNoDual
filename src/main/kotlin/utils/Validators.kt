// ------------------------------------------------------------
// utils/Validators.kt – Validadores reutilizables usando Exp. Regulares
// ------------------------------------------------------------
package utils

object Validators {
    private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    fun isValidEmail(email: String) = EMAIL_REGEX.matches(email)
}
