// ------------------------------------------------------------
// dao/inmemory/ProductoInMemoryDAO.kt
// ------------------------------------------------------------
package dao.inmemory

import dao.CrudDAO
import domain.Producto

object ProductoInMemoryDAO : CrudDAO<Int, Producto> {
    private val data = mutableMapOf<Int, Producto>()
    override fun create(entity: Producto): Producto = entity.also { data[it.id] = it }
    override fun findById(id: Int) = data[id]
    override fun findAll() = data.values.toList()
    override fun update(entity: Producto) = if (data.containsKey(entity.id)) {
        data[entity.id] = entity; true } else false
    override fun delete(id: Int) = data.remove(id) != null
}