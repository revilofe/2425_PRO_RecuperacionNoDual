// ------------------------------------------------------------
// service/ProductoService.kt
// ------------------------------------------------------------
package service

import dao.inmemory.ProductoInMemoryDAO
import domain.*

class ProductoService {
    private val dao = ProductoInMemoryDAO

    fun agregarVideojuego(titulo: String, descripcion: String, plataforma: String, propietario: Usuario): Result<Videojuego> =
        when {
            titulo.isBlank() -> Result.failure(IllegalArgumentException("El título no puede estar vacío."))
            descripcion.isBlank() -> Result.failure(IllegalArgumentException("La descripción no puede estar vacía."))
            plataforma.isBlank() -> Result.failure(IllegalArgumentException("La plataforma no puede estar vacía."))
            else -> Result.success(dao.create(Videojuego(titulo = titulo, descripcion = descripcion, plataforma = plataforma, propietario = propietario)) as Videojuego)
        }

    fun agregarLibro(titulo: String, descripcion: String, autor: String, propietario: Usuario): Result<Libro> =
        when {
            titulo.isBlank() -> Result.failure(IllegalArgumentException("El título no puede estar vacío."))
            descripcion.isBlank() -> Result.failure(IllegalArgumentException("La descripción no puede estar vacía."))
            autor.isBlank() -> Result.failure(IllegalArgumentException("El autor no puede estar vacío."))
            else -> Result.success(dao.create(Libro(titulo = titulo, descripcion = descripcion, autor = autor, propietario = propietario)) as Libro)
        }

    fun listarProductos(filtro: (Producto) -> Boolean = { true }): List<Producto> = dao.findAll().filter(filtro)
    fun buscarPorId(id: Int) = dao.findById(id)
}