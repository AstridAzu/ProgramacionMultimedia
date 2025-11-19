package ProgramacionMultimedia// Acceso seguro con operadores ?. y ?:

fun main() {
    val nombre: String = "Carlos"
    val longitud = nombre.length  // Seguro
    println("Longitud de nombre: \$longitud")
    
    val apellido: String? = "Garcia"
    
    // Acceso seguro con ?.
    val long_apellido = apellido?.length
    println("Longitud de apellido: \$long_apellido")
    
    // Elvis operator
    val long_final = apellido?.length ?: 0
    println("Longitud final: \$long_final")
    
    val nom_completo = nombre + (apellido ?: "Desconocido")
    println("Nombre completo: \$nom_completo")
}