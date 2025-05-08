// ------------------------------------------------------------
// utils/IdGenerator.kt – Generador sencillo de IDs incrementales
// ------------------------------------------------------------
package utils

/**
 * Generador de identificadores incrementales thread‑safe mediante un [AtomicInteger].
 */
object IdGenerator {
    private val seq = java.util.concurrent.atomic.AtomicInteger(1)
    fun nextId(): Int = seq.getAndIncrement()
}