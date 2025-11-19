package ProgramacionMultimedia// Null-safety - tipos nullable vs non-nullable

fun main() {
    // Non-nullable
    val nombre: String = "Ana"
    
    // Nullable
    val apellido: String? = "Lopez"
    // apellido = null  // Permitido
    
    val edad: Int? = 30
    
    println("Nombre: \$nombre")
    println("Apellido: \$apellido")
    println("Edad: \$edad")
}