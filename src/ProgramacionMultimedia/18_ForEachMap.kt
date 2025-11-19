package ProgramacionMultimedia// forEach, map, filter

fun main() {
    val numeros = listOf(1, 2, 3, 4, 5)
    
    // forEach
    println("forEach:")
    numeros.forEach { numero ->
        println(numero)
    }
    
    // map
    println("\nmap:")
    val dobles = numeros.map { numero -> numero * 2 }
    println(dobles)
    
    // filter
    println("\nfilter:")
    val pares = numeros.filter { numero -> numero % 2 == 0 }
    println(pares)
    
    // Encadenamiento
    println("\nEncadenamiento:")
    numeros
        .filter { it > 2 }
        .map { it * 2 }
        .forEach { println(it) }
    
    // any
    val tienePares = numeros.any { it % 2 == 0 }
    println("\nTiene pares: " + tienePares)
    
    // find
    val primerPar = numeros.find { it % 2 == 0 }
    println("Primer par: " + primerPar)
    
    // reduce
    val suma = numeros.reduce { acumulado, numero -> acumulado + numero }
    println("Suma: " + suma)
}