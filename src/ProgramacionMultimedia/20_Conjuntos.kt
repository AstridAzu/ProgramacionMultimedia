package ProgramacionMultimedia// Conjuntos (Sets)

fun main() {
    // Conjunto inmutable
    val colores = setOf("rojo", "verde", "azul", "rojo")
    println("Colores: " + colores)
    println("Tamano: " + colores.size)
    
    // Conjunto mutable
    val etiquetas = mutableSetOf("Java", "Kotlin", "Python")
    etiquetas.add("JavaScript")
    etiquetas.remove("Java")
    println("\nEtiquetas: " + etiquetas)
    
    // Operaciones
    println("\nOperaciones:")
    println("Contiene rojo: " + colores.contains("rojo"))
    
    // Operaciones matemáticas
    println("\nOperaciones matemáticas:")
    val set1 = setOf(1, 2, 3, 4)
    val set2 = setOf(3, 4, 5, 6)
    println("Union: " + set1.union(set2))
    println("Interseccion: " + set1.intersect(set2))
    println("Diferencia: " + set1.subtract(set2))
    
    // Conversion
    println("\nConversion:")
    val lista = listOf(1, 2, 2, 3, 3, 3)
    val conjunto = lista.toSet()
    println("Lista: " + lista)
    println("Conjunto: " + conjunto)
}