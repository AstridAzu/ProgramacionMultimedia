package PracticaKotlin2

data class Libro(
    val titulo:String,
    val autor:String,
    val año:Int,
    val disponible:Boolean

)

fun buscarPorAutor(libros:List<Libro>, autor:String): List<Libro> {
    return libros.filter {
        libro -> libro.autor.lowercase().contains(autor.lowercase())

    }
}
fun buscarPorAño(libros: List<Libro>, añoInicio:Int, añoFin:Int): List<Libro> {
    return libros.filter { libro ->
        libro.año >= añoInicio &&libro.año <=añoFin
    }

}
fun buscarPorDisponible(libros: List<Libro>): List<Libro> {
    return libros.filter {
        libro-> libro.disponible
    }
}
fun calcularEstadisticas(libros: List<Libro>) {
    println("== ESTADÍSTICAS DE LA BIBLIOTECA ==")

    // Total de libros en la colección
    val total = libros.size
    println("Total de libros: $total")

    // Cuenta cuántos libros están disponibles
    val disponibles = libros.count { libro -> libro.disponible }
    println("Libros disponibles: $disponibles")

    // Calcula libros prestados por diferencia
    val prestados = total - disponibles
    println("Libros prestados: $prestados")

    // Encuentra el autor con más libros
    // 1. groupBy agrupa los libros por autor, creando un Map<String, List<Libro>>
    // 2. maxByOrNull encuentra el grupo con más elementos (puede ser null si la lista está vacía)
    // 3. ?.key accede de forma segura a la clave (nombre del autor)
    val autorMasLibros = libros
        .groupBy { libro ->libro.autor }  // Map de autor -> lista de sus libros
        .maxByOrNull { entrada->entrada.value.size }  // Entrada con la lista más grande
        ?.key  // Obtiene solo el nombre del autor (null-safe)

    // El operador ?: proporciona un valor por defecto si autorMasLibros es null
    if (autorMasLibros != null) {
        println("Autor mas: $autorMasLibros")
    }else{
        println("N/A")
    }

}
fun mostrarMenu() {
    println("    SISTEMA DE GESTIÓN DE BIBLIOTECA           ")
    println(" 1. Buscar libros por autor                   ")
    println(" 2. Buscar libros por rango de años            ")
    println(" 3. Buscar libros por título                   ")
    println(" 4. Mostrar libros disponibles                 ")
    println(" 5. Mostrar estadísticas                       ")
    println(" 6. Mostrar todos los libros ordenados por año ")
    println(" 7. Mostrar todos los libros                   ")
    println(" 8. Agrega un libro                   ")
    println(" 0. Salir                                      ")
    print("\n Seleccione una opción: ")
}
fun solicitarTexto(mensaje: String): String {
    print(mensaje)
    val datoIngresado=readLine().toString()
    if(!datoIngresado.isEmpty()){
        return datoIngresado
    }else{
        return ""
    }
    //return readLine() ?: ""
}
fun solicitarNumero(mensaje: String): Int? {
    print(mensaje)
    val datoIngresado=readLine().toString()
    if(!datoIngresado.isEmpty()){
        return datoIngresado.toIntOrNull()
    }else{
        return null
    }
    //return readLine()?.toIntOrNull()
}
fun mostrarResultados(libros: List<Libro>) {

    if (libros.isEmpty()) {
        println("No se encontraron resultados.")
    } else {
        println("Se encontraron ${libros.size} libro(s):\n")
        libros.forEachIndexed { index, libro ->
            val estado = if (libro.disponible) " Disponible" else " Prestado"
            println("${index + 1}. ${libro.titulo}")
            println(" Autor: ${libro.autor}")
            println(" Año: ${libro.año}")
            println(" $estado")
            println()
        }
    }
}
fun agregarLibro(biblioteca: MutableList<Libro>): Boolean {
    println("   AGREGAR NUEVO LIBRO")

    // Solicitar título
    val titulo = solicitarTexto(" Ingrese el título del libro: ")
    if (titulo.isBlank()) {
        println("  Error: El título no puede estar vacío.")
        return false
    }

    // Solicitar autor
    val autor = solicitarTexto(" Ingrese el autor del libro: ")
    if (autor.isBlank()) {
        println("️  Error: El autor no puede estar vacío.")
        return false
    }

    // Solicitar año
    val año = solicitarNumero("Ingrese el año de publicación: ")
    if (año == null || año < 0) {
        println("  Error: Debe ingresar un año válido.")
        return false
    }

    // Solicitar disponibilidad
    print("¿El libro está disponible? (s/n): ")
    val disponibleInput = readLine()?.lowercase() ?: "s"
    val disponible = disponibleInput == "s" || disponibleInput == "si" || disponibleInput == "sí"

    // Crear y agregar el libro
    val nuevoLibro = Libro(titulo, autor, año, disponible)
    biblioteca.add(nuevoLibro)

    println("¡Libro agregado exitosamente!")
    println("Datos del libro:")
    println("   Título: ${nuevoLibro.titulo}")
    println("   Autor: ${nuevoLibro.autor}")
    println("   Año: ${nuevoLibro.año}")
    println("   Estado: ${if (nuevoLibro.disponible) "Disponible" else "Prestado"}")

    return true
}
fun main() {
    // Creamos una lista inmutable de libros para la biblioteca
    val biblioteca = mutableListOf(
        Libro("Cien años de soledad", "Gabriel García Márquez", 1967, true),
        Libro("El amor en los tiempos del cólera", "Gabriel García Márquez", 1985, false),
        Libro("1984", "George Orwell", 1949, true),
        Libro("Rebelión en la granja", "George Orwell", 1945, true),
        Libro("El principito", "Antoine de Saint-Exupéry", 1943, false),
        Libro("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, true),
        Libro("Rayuela", "Julio Cortázar", 1963, true),
        Libro("Ficciones", "Jorge Luis Borges", 1944, false)
    )
    //menu
    var continuar = true

    // Bucle principal del menú
    while (continuar) {
        mostrarMenu()

        // Leer opción del usuario
        val opcion = readLine()?.toIntOrNull() ?: -1

        when (opcion) {
            // Opción 1: Buscar por autor
            1 -> {
                println("   BÚSQUEDA POR AUTOR")
                val autor = solicitarTexto(" Ingrese el nombre del autor: ")

                if (autor.isNotBlank()) {
                    val resultados = buscarPorAutor(biblioteca, autor)
                    mostrarResultados(resultados)
                } else {
                    println("  Error: Debe ingresar un nombre de autor.")
                }
            }

            // Opción 2: Buscar por rango de años
            2 -> {
                println("   BÚSQUEDA POR RANGO DE AÑOS")
                val añoInicio = solicitarNumero("Ingrese el año de inicio: ")
                val añoFin = solicitarNumero("Ingrese el año de fin: ")

                if (añoInicio != null && añoFin != null) {
                    if (añoInicio <= añoFin) {
                        val resultados = buscarPorAño(biblioteca, añoInicio, añoFin)
                        mostrarResultados(resultados)
                    } else {
                        println(" Error: El año de inicio debe ser menor o igual al año de fin.")
                    }
                } else {
                    println(" Error: Debe ingresar años válidos.")
                }
            }

            // Opción 3: Buscar por título
            3 -> {

                println("   BÚSQUEDA POR TÍTULO")

                val titulo = solicitarTexto(" Ingrese parte del título: ")

                if (titulo.isNotBlank()) {
                    val resultados = buscarPorAutor(biblioteca, titulo)
                    mostrarResultados(resultados)
                } else {
                    println(" Error: Debe ingresar un título.")
                }
            }

            // Opción 4: Libros disponibles
            4 -> {
                val resultados = buscarPorDisponible(biblioteca)
                mostrarResultados(resultados)
            }

            // Opción 5: Estadísticas
            5 -> {
                calcularEstadisticas(biblioteca)
            }

            // Opción 6: Mostrar ordenados por año
            6 -> {
                // Ordenar por año
                println("\n=== LIBROS ORDENADOS POR AÑO (EXTRA) ===")
                val librosOrdenados = biblioteca.sortedBy { it.año }
                librosOrdenados.forEach { libro ->
                    println("  ${libro.año} - ${libro.titulo}")
                }

            }

            // Opción 7: Mostrar todos los libros
            7 -> {
                mostrarResultados(biblioteca)
            }
            8 ->{
                agregarLibro(biblioteca)
            }

            // Opción 0: Salir
            0 -> {

                println("  Gracias por usar el sistema     ")
                println("    ¡Hasta pronto!                ")

                continuar = false
            }

            // Opción inválida
            else -> {
                println("Error: Opción no válida. Por favor, seleccione una opción del 0 al 7.")
            }
        }

        // Pausa antes de volver al menú
        if (continuar) {
            print("  Presione Enter para continuar...")
            readLine()
        }


    }
}