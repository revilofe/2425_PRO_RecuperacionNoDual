// ------------------------------------------------------------
// dao/inmemory/SolicitudInMemoryDAO.kt
// ------------------------------------------------------------
package dao.inmemory

import dao.CrudDAO
import domain.Solicitud

object SolicitudInMemoryDAO : CrudDAO<Int, Solicitud> {
    private val data = mutableMapOf<Int, Solicitud>()
    override fun create(entity: Solicitud): Solicitud = entity.also { data[it.id] = it }
    override fun findById(id: Int) = data[id]
    override fun findAll() = data.values.toList()
    override fun update(entity: Solicitud) = if (data.containsKey(entity.id)) {
        data[entity.id] = entity; true } else false
    override fun delete(id: Int) = data.remove(id) != null
}