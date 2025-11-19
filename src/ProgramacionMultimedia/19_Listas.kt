package ProgramacionMultimedia// Listas

fun main() {
    // Lista inmutable
    val diasSemana = listOf("Lunes", "Martes", "Miercoles")
    val numeros = listOf(1, 2, 3, 4, 5)
    val vacia: List<String> = emptyList()
    
    // Lista mutable
    val lista = mutableListOf("a", "b", "c")
    lista.add("d")
    lista.remove("a")
    lista[0] = "z"
    
    // Acceso
    println("Primer elemento: " + numeros[0])
    println("Ultimo: " + numeros.last())
    println("Tamano: " + numeros.size)
    
    // Operaciones
    println("\nOperaciones:")
    println("Contiene 3: " + numeros.contains(3))
    println("Indice de 3: " + numeros.indexOf(3))
    
    // Transformaciones
    println("\nTransformaciones:")
    val incrementados = numeros.map { it + 1 }
    println("Incrementados: " + incrementados)
    
    val pares = numeros.filter { it % 2 == 0 }
    println("Pares: " + pares)
    
    // Ordenamiento
    println("\nOrdenamiento:")
    val desordenada = listOf(3, 1, 4, 1, 5, 9)
    println("Ordenada: " + desordenada.sorted())
    println("Descendente: " + desordenada.sortedDescending())
}