// ------------------------------------------------------------
// domain/Solicitud.kt
// ------------------------------------------------------------
package domain

import domain.enums.EstadoSolicitud
import domain.enums.TipoSolicitud
import kotlinx.serialization.Serializable
import utils.IdGenerator

/**
 * Petición de trueque o préstamo sobre un [Producto].
 */
@Serializable
data class Solicitud(
    val id: Int = IdGenerator.nextId(),
    val tipo: TipoSolicitud,
    val solicitante: Usuario,
    val producto: Producto,
    var estado: EstadoSolicitud = EstadoSolicitud.PENDIENTE
)
