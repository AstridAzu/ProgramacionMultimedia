package ProgramacionMultimedia// Callbacks y manejo de eventos

fun botonPulsado(callback: () -> Unit) {
    callback()
}

fun procesarDatos(datos: String, onSuccess: (String) -> Unit) {
    if (datos.isNotEmpty()) {
        onSuccess("Procesado: " + datos)
    }
}

fun main() {
    botonPulsado {
        println("Boton pulsado!")
    }
    
    procesarDatos("informacion") { resultado ->
        println(resultado)
    }
}