// ------------------------------------------------------------
// dao/CrudDAO.kt – interfaz genérica CRUD
// ------------------------------------------------------------
package dao

interface CrudDAO<ID, T> {
    fun create(entity: T): T
    fun findById(id: ID): T?
    fun findAll(): List<T>
    fun update(entity: T): Boolean
    fun delete(id: ID): Boolean
}
