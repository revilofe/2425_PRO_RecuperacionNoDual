// ------------------------------------------------------------
// service/SolicitudService.kt
// ------------------------------------------------------------
package service

import dao.inmemory.SolicitudInMemoryDAO
import domain.*
import domain.enums.*

class SolicitudService(private val productoService: ProductoService) {
    private val dao = SolicitudInMemoryDAO

    fun crearSolicitud(idProducto: Int, tipo: TipoSolicitud, solicitante: Usuario): Result<Solicitud> {
        val producto = productoService.buscarPorId(idProducto)
            ?: return Result.failure(NoSuchElementException("Producto no encontrado."))
        if (producto.propietario.id == solicitante.id)
            return Result.failure(IllegalArgumentException("No puedes solicitar tu propio producto."))
        return Result.success(dao.create(Solicitud(tipo = tipo, solicitante = solicitante, producto = producto)))
    }

    fun listarSolicitudes() = dao.findAll()

    fun actualizarEstado(id: Int, nuevoEstado: EstadoSolicitud): Result<Solicitud> {
        val solicitud = dao.findById(id) ?: return Result.failure(NoSuchElementException("Solicitud no encontrada."))
        val transicionValida = when (solicitud.estado) {
            EstadoSolicitud.PENDIENTE -> nuevoEstado in listOf(EstadoSolicitud.ACEPTADA, EstadoSolicitud.RECHAZADA)
            EstadoSolicitud.ACEPTADA -> nuevoEstado == EstadoSolicitud.FINALIZADA
            else -> false
        }
        return if (transicionValida) {
            solicitud.estado = nuevoEstado
            dao.update(solicitud)
            Result.success(solicitud)
        } else Result.failure(IllegalStateException("Transición de estado no permitida."))
    }
}