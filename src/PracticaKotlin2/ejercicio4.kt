package PracticaKotlin2

import java.util.regex.Pattern
import kotlin.system.exitProcess

data class Contacto(
    val nombre: String,
    val email: String,
    val telefono: String,
    var favorito: Boolean = false
){
    override fun toString(): String {
        return "nombre:" + nombre+ ", email:" + email + ", telefono:" + telefono + ", favorito:" + favorito
    }
}
class ContactoValidationException(message: String) : Exception(message)
object GestorContactos {
    private val contactos = mutableListOf<Contacto>()
    private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
    private val TELEFONO_REGEX = "^[+]?[0-9\\s-()]{10,15}\$"

    fun validarEmail(email: String): Result<Boolean> = runCatching {
        if (!Pattern.matches(EMAIL_REGEX, email)) throw ContactoValidationException(
            "Formato de email inválido. Ejemplo: usuario@dominio.com"
        )
        true
    }

    fun validarTelefono(telefono: String): Result<Boolean> = runCatching {
        if (!Pattern.matches(TELEFONO_REGEX, telefono)) throw ContactoValidationException(
            "Formato de teléfono inválido. Debe contener 10-15 dígitos"
        )
        true
    }

    fun crearContacto(nombre: String, email: String, telefono: String): Result<Contacto> = runCatching {
        validarEmail(email).getOrThrow()
        validarTelefono(telefono).getOrThrow()

        if (contactos.any { it.nombre.equals(nombre, ignoreCase = true) }) {
            throw ContactoValidationException("Ya existe un contacto con este nombre")
        }

        val nuevoContacto = Contacto(nombre, email, telefono)
        contactos.add(nuevoContacto)
        nuevoContacto
    }

    fun buscarContactos(consulta: String): Result<List<Contacto>> = runCatching {
        val resultados = contactos.filter {
            it.nombre.contains(consulta.lowercase(), ignoreCase = true)
        }
        if (resultados.isEmpty()) throw NoSuchElementException("No se encontraron contactos")
        resultados
    }

    fun obtenerFavoritos(): Result<List<Contacto>> = runCatching {
        val favoritos = contactos.filter { it.favorito }
        if (favoritos.isEmpty()) throw NoSuchElementException("No hay contactos favoritos")
        favoritos
    }

    fun toggleFavorito(nombre: String): Result<Contacto> = runCatching {
        val contacto = contactos.find { it.nombre.lowercase().contains(nombre.lowercase()) }
            ?: throw NoSuchElementException("Contacto '$nombre' no encontrado")

        contacto.favorito = !contacto.favorito
        contacto
    }
    // Buscar contacto por nombre exacto (case insensitive)
    private fun encontrarContactoPorNombre(nombre: String): Contacto? {
        return contactos.find { it.nombre.lowercase().contains(nombre.lowercase()) }
    }
    fun eliminarContacto(nombre: String): Boolean {
        val contacto = encontrarContactoPorNombre(nombre)
        return if (contacto != null) {
            contactos.remove(contacto)
            true
        } else {
            false
        }
    }

    fun ordenarContactos(): Result<List<Contacto>> = runCatching {
        if (contactos.isEmpty()) throw NoSuchElementException("No hay contactos")
        contactos.sortedBy { it.nombre }
    }

    fun obtenerTodos(): Result<List<Contacto>> = runCatching {
        if (contactos.isEmpty()) throw NoSuchElementException("No hay contactos")
        contactos.toList()
    }
}

fun main() {
    val scanner = java.util.Scanner(System.`in`)
    inicializarDatosEjemplo()

    while (true) {
        try {
            mostrarMenuPrincipal()
            when (scanner.nextLine().toIntOrNull() ?: -1) {
                1 -> crearContacto(scanner)
                2 -> listarContactos()
                3 -> buscarContactos(scanner)
                4 -> eliminarContacto(scanner)
                5 -> gestionarFavoritos(scanner)
                6 -> mostrarFavoritos()
                7 -> ordenarYMostrar()
                0 -> {
                    println("¡Hasta pronto!")
                    exitProcess(0)
                }
                else -> println("Opción inválida")
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
        println()
    }
}

fun inicializarDatosEjemplo() {
    val datos = listOf(
        Triple("Juan ", "juan@email.com", "1234567890"),
        Triple("María ", "maria@empresa.com", "0987654321"),
        Triple("Carlos ", "carlos@dominio.com", "5551234567")
    )

    datos.forEach { (nombre, email, telefono) ->
        GestorContactos.crearContacto(nombre, email, telefono).onSuccess {
            if (nombre == "Juan Pérez") it.favorito = true
        }
    }
}

fun mostrarMenuPrincipal() {
    println("""
       GESTOR DE CONTACTOS 
        1. Crear contacto
        2. Listar contactos
        3. Buscar por nombre
        4. Eliminar contacto
        5. Marcar/desmarcar favorito
        6. Ver favoritos
        7. Ordenar alfabéticamente
        0. Salir
        Seleccione una opción: 
    """.trimIndent())
}

fun crearContacto(scanner: java.util.Scanner) {
    println("CREAR CONTACTO")

    print("Nombre: ")
    val nombre = scanner.nextLine()

    print("Email: ")
    val email = scanner.nextLine()

    print("Teléfono: ")
    val telefono = scanner.nextLine()

    GestorContactos.crearContacto(nombre, email, telefono).fold(
        onSuccess = { println("Contacto creado: $it") },
        onFailure = { println("${it.message}") }
    )
}

fun listarContactos() {
    println(" TODOS LOS CONTACTOS")
    GestorContactos.obtenerTodos().fold(
        onSuccess = { it.forEachIndexed { i, c -> println("${i + 1}. $c") } },
        onFailure = { println("ℹ${it.message}") }
    )
}

fun buscarContactos(scanner: java.util.Scanner) {
    print("Buscar: ")
    val consulta = scanner.nextLine()

    GestorContactos.buscarContactos(consulta).fold(
        onSuccess = {
            println("RESULTADOS ")
            it.forEachIndexed { i, c -> println("${i + 1}. $c") }
        },
        onFailure = { println(" ${it.message}") }
    )
}


fun eliminarContacto(scanner: java.util.Scanner) {
    println(" ELIMINAR CONTACTO ")

    print("Ingrese el nombre exacto del contacto a eliminar: ")
    val nombre = scanner.nextLine()

    if (GestorContactos.eliminarContacto(nombre)) {
        println("Contacto eliminado exitosamente")
    } else {
        println("No se encontró un contacto con ese nombre")
    }
}

fun gestionarFavoritos(scanner: java.util.Scanner) {
    println("GESTIONAR FAVORITOS")
    listarContactos()

    print("Nombre del contacto: ")
    val nombre = scanner.nextLine()

    GestorContactos.toggleFavorito(nombre).fold(
        onSuccess = {
            val estado = if (it.favorito) "marcado como favorito ★" else "quitado de favoritos ☆"
            println("Contacto $estado")
        },
        onFailure = { println("${it.message}") }
    )
}

fun mostrarFavoritos() {
    println("FAVORITOS")
    GestorContactos.obtenerFavoritos().fold(
        onSuccess = { it.forEachIndexed { i, c -> println("${i + 1}. $c") } },
        onFailure = { println("${it.message}") }
    )
}

fun ordenarYMostrar() {
    println("ORDENADOS ALFABÉTICAMENTE")
    GestorContactos.ordenarContactos().fold(
        onSuccess = { it.forEachIndexed { i, c -> println("${i + 1}. $c") } },
        onFailure = { println("${it.message}") }
    )
}