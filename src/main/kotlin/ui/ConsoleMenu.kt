// ------------------------------------------------------------
// ui/ConsoleMenu.kt – Interfaz de usuario en consola
// ------------------------------------------------------------
package ui

import domain.enums.EstadoSolicitud
import domain.enums.TipoSolicitud
import service.ProductoService
import service.SolicitudService
import service.UsuarioService

/**
 * Menú interactivo que orquesta la aplicación en consola.
 * Contiene el bucle principal y delega en los servicios.
 */
class ConsoleMenu {
    private val usuarioService = UsuarioService()
    private val productoService = ProductoService()
    private val solicitudService = SolicitudService(productoService)

    private var currentUser: domain.Usuario? = null

    fun iniciar() {
        println("=== Trueque & Préstamos Geek ===")
        while (true) {
            printMenu()
            when (readLine()?.trim()) {
                "1" -> registrarUsuario()
                "2" -> agregarProducto()
                "3" -> listarProductos()
                "4" -> crearSolicitud()
                "5" -> listarSolicitudes()
                "6" -> actualizarEstadoSolicitud()
                "7" -> cargaProductosDesdeFichero()
                "8" -> exportarProductosAFichero()
                "0" -> return
                else -> println("Opción no válida. Intente de nuevo.")
            }
        }
    }

    private fun printMenu() {
        println(
            """
            |1. Registrar nuevo usuario
            |2. Agregar producto
            |3. Listar productos
            |4. Crear solicitud
            |5. Listar solicitudes
            |6. Actualizar estado de solicitud
            |7. Ficheros Carga productos (txt,json)
            |8. Ficheros Descarga productos (txt,json)
            |0. Salir
            |Seleccione una opción:
            """.trimMargin()
        )
    }

    // --------------------------------------------------
    // Implementaciones de las opciones del menú
    // --------------------------------------------------
    private fun registrarUsuario() {
        println("=== Registro de Usuario ===")
        print("Ingrese nombre: ")
        val nombre = readLine()!!.trim()
        print("Ingrese email: ")
        val email = readLine()!!.trim()
        print("Ingrese contraseña: ")
        val password = readLine()!!.trim()
        val resultado = usuarioService.registrar(nombre, email, password)
        resultado.onSuccess {
            currentUser = it
            println("Usuario \"${it.nombre}\" registrado exitosamente y logueado.")
        }.onFailure { println("Error: ${it.message}") }
    }

    private fun requireLogin(): Boolean {
        if (currentUser == null) {
            println("Debe registrarse primero (opción 1).")
            return false
        }
        return true
    }

    private fun agregarProducto() {
        if (!requireLogin()) return
        println("=== Agregar Producto ===")
        print("Ingrese título del producto: ")
        val titulo = readLine()!!.trim()
        print("Ingrese descripción: ")
        val descripcion = readLine()!!.trim()
        print("Tipo de producto (1. Videojuego, 2. Libro): ")
        val tipo = readLine()!!.trim()
        val res = when (tipo) {
            "1" -> {
                print("Ingrese plataforma: ")
                val plataforma = readLine()!!.trim()
                productoService.agregarVideojuego(titulo, descripcion, plataforma, currentUser!!)
            }
            "2" -> {
                print("Ingrese autor: ")
                val autor = readLine()!!.trim()
                productoService.agregarLibro(titulo, descripcion, autor, currentUser!!)
            }
            else -> Result.failure(IllegalArgumentException("Tipo no reconocido."))
        }
        res.onSuccess { println("Producto \"${it.titulo}\" agregado correctamente.") }
            .onFailure { println("Error: ${it.message}") }
    }

    private fun listarProductos() {
        val productos = productoService.listarProductos()
        if (productos.isEmpty()) {
            println("No hay productos registrados.")
            return
        }
        println("=== Listado de Productos ===")
        productos.forEach {
            println("ID: ${it.id}, Título: ${it.titulo}, Propietario: ${it.propietario.nombre}, Estado: ${it.estado}")
        }
    }

    private fun crearSolicitud() {
        if (!requireLogin()) return
        println("=== Crear Solicitud ===")
        print("Ingrese ID del producto: ")
        val id = readLine()!!.trim().toIntOrNull() ?: run {
            println("Error: ID inválido."); return
        }
        print("Tipo de solicitud (1. Trueque, 2. Préstamo): ")
        val tipo = when (readLine()?.trim()) {
            "1" -> TipoSolicitud.TRUEQUE
            "2" -> TipoSolicitud.PRESTAMO
            else -> { println("Tipo no válido."); return }
        }
        val res = solicitudService.crearSolicitud(id, tipo, currentUser!!)
        res.onSuccess {
            println("Solicitud de ${it.tipo} creada con ID: ${it.id} para el producto \"${it.producto.titulo}\".")
        }.onFailure { println("Error: ${it.message}") }
    }

    private fun listarSolicitudes() {
        val solicitudes = solicitudService.listarSolicitudes()
        if (solicitudes.isEmpty()) { println("No hay solicitudes registradas."); return }
        println("=== Listado de Solicitudes ===")
        solicitudes.forEach {
            println("ID: ${it.id}, Producto: ${it.producto.titulo}, Estado: ${it.estado}, Tipo: ${it.tipo}, Solicitante: ${it.solicitante.nombre}")
        }
    }

    private fun actualizarEstadoSolicitud() {
        println("=== Actualizar Estado de Solicitud ===")
        print("Ingrese ID de la solicitud: ")
        val id = readLine()!!.trim().toIntOrNull() ?: run {
            println("ID inválido."); return
        }
        print("Nuevo estado (1. Aceptada, 2. Rechazada, 3. Finalizada): ")
        val nuevo = when (readLine()?.trim()) {
            "1" -> EstadoSolicitud.ACEPTADA
            "2" -> EstadoSolicitud.RECHAZADA
            "3" -> EstadoSolicitud.FINALIZADA
            else -> { println("Estado no válido."); return }
        }
        val res = solicitudService.actualizarEstado(id, nuevo)
        res.onSuccess { println("Solicitud ${it.id} actualizada a: ${it.estado}.") }
            .onFailure { println("Error: ${it.message}") }
    }

    private fun cargaProductosDesdeFichero() {
        if (!requireLogin()) return
        print("Indique el formato del archivo (1. TXT, 2. JSON): ")
        val tipo = readLine()?.trim()
        print("Ingrese la ruta/nombre del archivo: ")
        val ruta = readLine()!!.trim()
        try {
            val nuevos = when (tipo) {
                "1" -> utils.FileHandler.importarTXT(ruta) { currentUser!! }
                "2" -> utils.FileHandler.importarJSON(ruta) { currentUser!! }
                else -> { println("Formato no válido."); return }
            }
            nuevos.forEach { service.ProductoService().also { } } // aquí se crearían mediante el DAO
            println("Productos cargados correctamente: ${nuevos.size}.")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun exportarProductosAFichero() {
        if (!requireLogin()) return
        print("Indique el formato de descarga (1. TXT, 2. JSON): ")
        val tipo = readLine()?.trim()
        print("Nombre de archivo destino: ")
        val ruta = readLine()!!.trim()
        val productosPropios = productoService.listarProductos { it.propietario.id == currentUser!!.id }
        if (productosPropios.isEmpty()) { println("No tiene productos registrados para exportar."); return }
        try {
            when (tipo) {
                "1" -> utils.FileHandler.exportarTXT(ruta, productosPropios)
                "2" -> utils.FileHandler.exportarJSON(ruta, productosPropios)
                else -> { println("Formato no válido."); return }
            }
            println("Se han exportado ${productosPropios.size} productos a \"$ruta\".")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}