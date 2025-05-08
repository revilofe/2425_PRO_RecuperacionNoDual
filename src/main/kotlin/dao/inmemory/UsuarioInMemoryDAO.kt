// ------------------------------------------------------------
// dao/inmemory/UsuarioInMemoryDAO.kt
// ------------------------------------------------------------
package dao.inmemory

import dao.CrudDAO
import domain.Usuario

/** Almacén en memoria – ideal para la demo, sustituible por DAO real */
object UsuarioInMemoryDAO : CrudDAO<Int, Usuario> {
    private val data = mutableMapOf<Int, Usuario>()
    override fun create(entity: Usuario): Usuario = entity.also { data[it.id] = it }
    override fun findById(id: Int) = data[id]
    override fun findAll() = data.values.toList()
    override fun update(entity: Usuario) = if (data.containsKey(entity.id)) {
        data[entity.id] = entity; true } else false
    override fun delete(id: Int) = data.remove(id) != null
    fun findByEmail(email: String) = data.values.firstOrNull { it.email.equals(email, true) }
}
