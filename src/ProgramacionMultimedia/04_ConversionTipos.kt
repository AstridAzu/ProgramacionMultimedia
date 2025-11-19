package ProgramacionMultimedia// En Kotlin, no hay conversión automática - debe ser explícita

fun main() {
    val entero: Int = 42
    val decimal: Double = entero.toDouble()    // 42.0
    val texto: String = entero.toString()       // "42"
    val byte: Byte = entero.toByte()           // Trunca si necesario
    val booleano: Boolean = entero != 0        // Conversión lógica
    
    println("Entero: \$entero")
    println("A Double: \$decimal")
    println("A String: \$texto")
}