// ------------------------------------------------------------
// utils/FileHandler.kt – Importación/Exportación TXT y JSON
// ------------------------------------------------------------
package utils

import domain.Producto
import domain.Videojuego
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

object FileHandler {
    fun exportarJSON(ruta: String, productos: List<Producto>) {
        File(ruta).writeText(Json.encodeToString(productos))
    }

    fun importarJSON(ruta: String, ownerProvider: () -> domain.Usuario): List<Producto> {
        val json = File(ruta).readText()
        // IMPORTANTE: Para simplificar no diferenciamos subclases en la deserialización automática.
        // En un proyecto real usaríamos Json.decodeFromString<List<Videojuego>>() etc.
        return Json.decodeFromString<List<Videojuego>>(json)
            .map { it.copy(propietario = ownerProvider()) }
    }

    fun exportarTXT(ruta: String, productos: List<Producto>) {
        File(ruta).printWriter().use { out ->
            productos.forEach { p ->
                val extra = when (p) {
                    is domain.Videojuego -> "Videojuego;${p.plataforma}"
                    is domain.Libro -> "Libro;${p.autor}"
                }
                out.println("${p.id};${p.titulo};${p.descripcion};$extra")
            }
        }
    }

    fun importarTXT(ruta: String, ownerProvider: () -> domain.Usuario): List<Producto> {
        val productos = mutableListOf<Producto>()
        File(ruta).forEachLine { line ->
            val parts = line.split(';')
            if (parts.size < 5) return@forEachLine  // línea mal formada
            val (_, titulo, descripcion, tipo, extra) = parts
            val propietario = ownerProvider()
            when (tipo) {
                "Videojuego" -> productos += domain.Videojuego(titulo = titulo, descripcion = descripcion, plataforma = extra, propietario = propietario)
                "Libro" -> productos += domain.Libro(titulo = titulo, descripcion = descripcion, autor = extra, propietario = propietario)
            }
        }
        return productos
    }
}
