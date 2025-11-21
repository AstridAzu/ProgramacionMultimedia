package PracticaKotlin2

import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Nota(
    val titulo: String,
    val contenido: String,
    val fechaCreacion: LocalDateTime = LocalDateTime.now(),
    var importante: Boolean = false
)

class SistemaNotas {
    private val notas = mutableListOf<Nota>()
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun crearNota(titulo: String, contenido: String) {
        val nuevaNota = Nota(titulo, contenido)
        notas.add(nuevaNota)
        println("Nota creada exitosamente")
    }

    fun buscarNotas(termino: String): List<Nota> {
        return notas.filter {
            it.titulo.contains(termino, ignoreCase = true) ||
                    it.contenido.contains(termino, ignoreCase = true)
        }
    }

    fun marcarImportante(titulo: String) {
        notas.find { it.titulo == titulo }?.importante = true
        println("No se encontró la nota '$titulo'")
    }

    fun mostrarNotasOrdenadas() {
        notas.sortedBy { it.fechaCreacion }
            .forEach { nota ->
                println("Título: ${nota.titulo}");
                println("Contenido: ${nota.contenido}")
                println("Fecha: ${nota.fechaCreacion.format(formatter)}")
                println("Importante: ${if (nota.importante) "Sí" else "No"}"  )

            }
    }

    fun exportarNotas(nombreArchivo: String) {
        val sb = StringBuilder()
        notas.forEach { nota ->
            sb.append("""
        Título: ${nota.titulo}
        Contenido: ${nota.contenido}
        Fecha: ${nota.fechaCreacion.format(formatter)}
        Importante: ${if (nota.importante) "Sí" else "No"}
            """)
        }

        File(nombreArchivo).writeText(sb.toString())
        println("Notas exportadas a '$nombreArchivo'")
    }

    fun eliminarNota(titulo: String) {
        notas.removeIf { it.titulo == titulo }
        println("Nota '$titulo' eliminada")
    }
}

fun main() {
    val sistema = SistemaNotas()
    var opcion: Int

    do {
        println("SISTEMA DE NOTAS")
        println("1. Crear nota")
        println("2. Buscar notas")
        println("3. Marcar como importante")
        println("4. Mostrar todas las notas")
        println("5. Exportar notas")
        println("6. Eliminar nota")
        println("0. Salir")
        println("Seleccione una opción: ")
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                print("Ingrese título: ")
                val titulo = readLine() ?: ""
                print("Ingrese contenido: ")
                val contenido = readLine() ?: ""
                sistema.crearNota(titulo, contenido)
            }
            2 -> {
                print("Término de búsqueda: ")
                val termino = readLine() ?: ""
                val resultados = sistema.buscarNotas(termino)
                resultados.forEach { println("${it.titulo} - ${it.contenido}") }
            }
            3 -> {
                print("Título de la nota a marcar: ")
                val titulo = readLine() ?: ""
                sistema.marcarImportante(titulo)
            }
            4 -> sistema.mostrarNotasOrdenadas()
            5 -> {
                print("Nombre del archivo: ")
                val nombre = readLine() ?: "notas.txt"
                sistema.exportarNotas(nombre)
            }
            6 -> {
                print("Título de la nota a eliminar: ")
                val titulo = readLine() ?: ""
                sistema.eliminarNota(titulo)
            }
        }
    } while (opcion != 0)
}