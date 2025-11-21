package PracticaKotlin2

// Ejercicio 2: Conversor de temperaturas con validación

// Paso 1: Excepciones personalizadas
class TemperaturaInvalidaException(mensaje: String) : Exception(mensaje)
class EscalaInvalidaException(mensaje: String) : Exception(mensaje)

// Paso 2: Data class para resultado
data class Temperatura(
    val valor: Double,
    val escala: String
) {
    override fun toString(): String = "%.2f°%s".format(valor, escala)
}

// Constantes físicas
const val CERO_ABSOLUTO_CELSIUS = -273.15
const val CERO_ABSOLUTO_KELVIN = 0.0

// Paso 3: Convertir Celsius a Fahrenheit
fun celsiusAFahrenheit(celsius: Double): Double {
    return (celsius * 9.0 / 5.0) + 32.0
}

// Paso 4: Convertir Kelvin a Celsius
fun kelvinACelsius(kelvin: Double): Double {
    return kelvin - 273.15
}

// Conversiones inversas (Extra)
fun fahrenheitACelsius(fahrenheit: Double): Double {
    return (fahrenheit - 32.0) * 5.0 / 9.0
}

fun celsiusAKelvin(celsius: Double): Double {
    return celsius + 273.15
}

// Paso 5: Validar temperatura según su escala
fun validarTemperatura(valor: Double, escala: String): Result<Unit> {
    return try {
        when (escala.uppercase()) {
            "C", "CELSIUS" -> {
                if (valor < CERO_ABSOLUTO_CELSIUS) {
                    throw TemperaturaInvalidaException(
                        "La temperatura no puede ser menor que el cero absoluto (${CERO_ABSOLUTO_CELSIUS}°C)"
                    )
                }
            }
            "K", "KELVIN" -> {
                if (valor < CERO_ABSOLUTO_KELVIN) {
                    throw TemperaturaInvalidaException(
                        "La temperatura en Kelvin no puede ser negativa (mínimo ${CERO_ABSOLUTO_KELVIN}K)"
                    )
                }
            }
            "F", "FAHRENHEIT" -> {
                val celsiusEquivalente = fahrenheitACelsius(valor)
                if (celsiusEquivalente < CERO_ABSOLUTO_CELSIUS) {
                    throw TemperaturaInvalidaException(
                        "La temperatura no puede ser menor que el cero absoluto (-459.67°F)"
                    )
                }
            }
            else -> {
                throw EscalaInvalidaException(
                    "Escala '$escala' no válida. Use: C (Celsius), F (Fahrenheit) o K (Kelvin)"
                )
            }
        }
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}

// Función auxiliar para alertas de temperatura extrema (Extra)
fun obtenerAlertaTemperatura(celsius: Double): String? {
    return when {
        celsius < -200 -> "ALERTA: Temperatura extremadamente baja"
        celsius < 0 -> "Temperatura bajo cero"
        celsius > 100 -> "ALERTA: Temperatura de ebullición del agua superada"
        celsius > 1000 -> "ALERTA: Temperatura extremadamente alta"
        else -> null
    }
}

// Paso 6: Convertir temperatura con validación
fun convertir(
    valor: Double,
    escalaOrigen: String,
    escalaDestino: String
): Result<Temperatura> {
    return try {
        // Validar temperatura de origen
        validarTemperatura(valor, escalaOrigen)
            .onFailure { return Result.failure(it) }

        val origenNormalizado = escalaOrigen.uppercase()
        val destinoNormalizado = escalaDestino.uppercase()

        // Convertir primero todo a Celsius como escala intermedia
        val celsius = when (origenNormalizado) {
            "C", "CELSIUS" -> valor
            "F", "FAHRENHEIT" -> fahrenheitACelsius(valor)
            "K", "KELVIN" -> kelvinACelsius(valor)
            else -> throw EscalaInvalidaException("Escala de origen '$escalaOrigen' no válida")
        }

        // Convertir de Celsius a la escala destino
        val resultado = when (destinoNormalizado) {
            "C", "CELSIUS" -> celsius
            "F", "FAHRENHEIT" -> celsiusAFahrenheit(celsius)
            "K", "KELVIN" -> celsiusAKelvin(celsius)
            else -> throw EscalaInvalidaException("Escala de destino '$escalaDestino' no válida")
        }

        // Validar temperatura de destino
        validarTemperatura(resultado, destinoNormalizado)
            .onFailure { return Result.failure(it) }

        val simboloEscala = when (destinoNormalizado) {
            "C", "CELSIUS" -> "C"
            "F", "FAHRENHEIT" -> "F"
            "K", "KELVIN" -> "K"
            else -> destinoNormalizado
        }

        Result.success(Temperatura(resultado, simboloEscala))

    } catch (e: Exception) {
        Result.failure(e)
    }
}

// Historial de conversiones (Extra)
val historialConversiones = mutableListOf<String>()

// Paso 7: Menú interactivo
fun menuInteractivo() {

    println("   CONVERSOR DE TEMPERATURAS")


    var continuar = true

    while (continuar) {
        println("--- MENÚ PRINCIPAL ---")
        println("1. Convertir temperatura")
        println("2. Ver historial de conversiones")
        println("3. Información sobre escalas")
        println("4. Salir")
        print("Seleccione una opción: ")

        when (readLine()?.trim()) {
            "1" -> realizarConversion()
            "2" -> mostrarHistorial()
            "3" -> mostrarInformacion()
            "4" -> {
                println("¡Gracias por usar el conversor! Hasta pronto.")
                continuar = false
            }
            else -> println(" Opción no válida. Por favor, seleccione 1-4.")
        }
    }
}

fun realizarConversion() {
    println(" CONVERSIÓN DE TEMPERATURA ")

    // Leer temperatura de origen
    print("Ingrese la temperatura: ")
    val valorStr = readLine()?.trim()
    val valor = valorStr?.toDoubleOrNull()

    if (valor == null) {
        println(" Error: '$valorStr' no es un número válido")
        return
    }

    // Leer escala de origen
    print("Escala de origen (C/F/K): ")
    val escalaOrigen = readLine()?.trim() ?: ""

    if (escalaOrigen.isEmpty()) {
        println(" Error: Debe ingresar una escala")
        return
    }

    // Leer escala de destino
    print("Escala de destino (C/F/K): ")
    val escalaDestino = readLine()?.trim() ?: ""

    if (escalaDestino.isEmpty()) {
        println(" Error: Debe ingresar una escala")
        return
    }

    // Realizar conversión
    convertir(valor, escalaOrigen, escalaDestino)
        .onSuccess { temperatura ->
            println(" RESULTADO:")
            println("   $valor°${escalaOrigen.uppercase()} = $temperatura")

            // Mostrar alerta si es temperatura extrema
            val celsius = when (escalaOrigen.uppercase()) {
                "C", "CELSIUS" -> valor
                "F", "FAHRENHEIT" -> fahrenheitACelsius(valor)
                "K", "KELVIN" -> kelvinACelsius(valor)
                else -> valor
            }

            obtenerAlertaTemperatura(celsius)?.let { alerta ->
                println("   $alerta")
            }

            // Guardar en historial
            val registro = "$valor°${escalaOrigen.uppercase()} → $temperatura"
            historialConversiones.add(registro)
        }
        .onFailure { error ->
            println(" ERROR: ${error.message}")
        }
}

fun mostrarHistorial() {
    println("HISTORIAL DE CONVERSIONES ")

    if (historialConversiones.isEmpty()) {
        println("No hay conversiones en el historial.")
    } else {
        historialConversiones.forEachIndexed { index, conversion ->
            println("${index + 1}. $conversion")
        }
        println("\nTotal de conversiones: ${historialConversiones.size}")
    }
}

private fun mostrarInformacion() {
    println("INFORMACIÓN SOBRE ESCALAS DE TEMPERATURA")
    println("ESCALAS DISPONIBLES:")
    println("   • Celsius (C): Escala métrica, 0°C = punto de congelación del agua")
    println("   • Fahrenheit (F): Escala imperial, 32°F = punto de congelación del agua")
    println("   • Kelvin (K): Escala absoluta, 0K = cero absoluto")
    println("PUNTOS DE REFERENCIA:")
    println("   • Cero absoluto: -273.15°C = -459.67°F = 0K")
    println("   • Congelación agua: 0°C = 32°F = 273.15K")
    println("   • Ebullición agua: 100°C = 212°F = 373.15K")
    println("FÓRMULAS:")
    println("   • °F = (°C × 9/5) + 32")
    println("   • °C = (°F - 32) × 5/9")
    println("   • K = °C + 273.15")
}

// Paso 8: Función de pruebas
fun pruebasConversiones() {
    println("   PRUEBAS DEL CONVERSOR")
    val pruebas = listOf(
        Triple(0.0, "C", "F") to "32.00°F",
        Triple(100.0, "C", "F") to "212.00°F",
        Triple(0.0, "K", "C") to "-273.15°C",
        Triple(273.15, "K", "C") to "0.00°C",
        Triple(-40.0, "C", "F") to "-40.00°F",
        Triple(37.0, "C", "K") to "310.15K"
    )

    println(" Pruebas válidas:")
    pruebas.forEach { (entrada, esperado) ->
        val (valor, origen, destino) = entrada
        convertir(valor, origen, destino)
            .onSuccess { resultado ->
                val coincide = if (resultado.toString() == esperado) "✓" else "✗"
                println("$coincide $valor°$origen → $resultado (esperado: $esperado)")
            }
            .onFailure { error ->
                println("✗ $valor°$origen → ERROR: ${error.message}")
            }
    }

    println(" Pruebas de validación (deben fallar):")
    val pruebasInvalidas = listOf(
        Triple(-300.0, "C", "F") to "Temperatura bajo cero absoluto",
        Triple(-1.0, "K", "C") to "Kelvin negativo",
        Triple(-500.0, "F", "C") to "Fahrenheit bajo cero absoluto"
    )

    pruebasInvalidas.forEach { (entrada, descripcion) ->
        val (valor, origen, destino) = entrada
        convertir(valor, origen, destino)
            .onSuccess { println("✗ $descripcion: Debería haber fallado") }
            .onFailure { error->println("✓ $descripcion: ${error.message}") }
    }


}

// Función principal
fun main() {
    // Ejecutar pruebas automáticas primero
    pruebasConversiones()

    // Iniciar menú interactivo
    menuInteractivo()
}
