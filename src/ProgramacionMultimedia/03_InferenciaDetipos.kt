package ProgramacionMultimedia// El compilador deduce tipos automáticamente

fun main() {
    val numero = 42              // Deduce Int
    val decimal = 3.14           // Deduce Double
    val texto = "Kotlin"         // Deduce String
    val booleano = true          // Deduce Boolean
    val listaNumeros = listOf(1, 2, 3)  // Deduce List<Int>
    
    println("Numero: \$numero")
    println("Decimal: \$decimal")
    println("Texto: \$texto")
    println("Booleano: \$booleano")
    println("Lista: \$listaNumeros")
}