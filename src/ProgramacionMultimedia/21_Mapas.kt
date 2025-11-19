package ProgramacionMultimedia// Mapas

fun main() {
    // Mapa inmutable
    val telefonos = mapOf(
        "Ana" to "555-1234",
        "Carlos" to "555-5678"
    )
    
    println("Telefono de Ana: " + telefonos["Ana"])
    
    // Mapa mutable
    val edades = mutableMapOf<String, Int>()
    edades["Ana"] = 25
    edades["Carlos"] = 30
    
    for ((nombre, edad) in edades) {
        println(nombre + " tiene " + edad + " anos")
    }
    
    println("Tamano: " + edades.size)
}