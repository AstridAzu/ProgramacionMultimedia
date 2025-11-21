package PracticaKotlin2

import kotlin.math.pow

data class Medicion(
    val peso: Double,
    val altura: Double,
    val imc: Double,
    val categoria: String
)

class CalculadoraIMC {
    private val historial = mutableListOf<Medicion>()

    fun calcularIMC(peso: Double, altura: Double): Medicion {
        require(peso > 0 && altura > 0) { "El peso y la altura deben ser valores positivos" }

        val imc = peso / (altura.pow(2))
        val categoria = when (imc) {
            in 0.0..18.4 -> "Bajo peso"
            in 18.5..24.9 -> "Peso normal"
            in 25.0..29.9 -> "Sobrepeso"
            else -> "Obesidad"
        }

        val medicion = Medicion(peso, altura, imc, categoria)
        historial.add(medicion)
        return medicion
    }

    fun obtenerHistorial(): List<Medicion> = historial.toList()

    fun obtenerTendencia(): String {
        if (historial.size < 2) return "No hay suficientes datos para determinar tendencia"

        val actual = historial.last()
        val anterior = historial[historial.size - 2]
        val diferencia = actual.peso - anterior.peso

        return when {
            diferencia > 0 -> "Ganancia de peso: +${"%.1f".format(diferencia)} kg"
            diferencia < 0 -> "Pérdida de peso: ${"%.1f".format(diferencia)} kg"
            else -> "Peso estable"
        }
    }

    fun mostrarResumen() {
        if (historial.isEmpty()) {
            println("No hay mediciones en el historial")
            return
        }

        val ultima = historial.last()
        println(" RESUMEN ACTUAL ")
        println("Peso: ${ultima.peso} kg")
        println("Altura: ${ultima.altura} m")
        println("IMC: ${"%.2f".format(ultima.imc)}")
        println("Categoría: ${ultima.categoria}")

        if (historial.size > 1) {
            println("Tendencia: ${obtenerTendencia()}")
        }
    }
}

fun leerDouble(mensaje: String): Double {
    while (true) {
        print(mensaje)
        try {
            val input = readLine()
            if (input != null) {
                // Reemplazar coma por punto para soportar ambos formatos
                val numero = input.replace(",", ".").toDouble()
                if (numero > 0) {
                    return numero
                } else {
                    println("Error: El valor debe ser positivo")
                }
            }
        } catch (e: NumberFormatException) {
            println("Error: Ingrese un número válido (ej: 70.5 o 70,5)")
        }
    }
}

fun leerInt(mensaje: String): Int {
    while (true) {
        print(mensaje)
        try {
            return readLine()?.toInt() ?: -1
        } catch (e: NumberFormatException) {
            println("Error: Ingrese un número entero válido")
        }
    }
}

fun main() {
    val calculadora = CalculadoraIMC()
    var opcion: Int

    do {
        println("\n=== CALCULADORA DE IMC ===")
        println("1. Calcular nuevo IMC")
        println("2. Ver historial completo")
        println("3. Ver resumen actual")
        println("4. Salir")

        opcion = leerInt("Seleccione una opción: ")

        when (opcion) {
            1 -> {
                println("NUEVO CÁLCULO DE IMC ")

                val peso = leerDouble("Ingrese el peso en kg: ")
                val altura = leerDouble("Ingrese la altura en metros: ")

                try {
                    val resultado = calculadora.calcularIMC(peso, altura)
                    println("\n✓ Cálculo realizado exitosamente!")
                    println("IMC: ${"%.2f".format(resultado.imc)} - ${resultado.categoria}")
                } catch (e: IllegalArgumentException) {
                    println("Error: ${e.message}")
                }
            }

            2 -> {
                println(" HISTORIAL COMPLETO ")
                val historial = calculadora.obtenerHistorial()
                if (historial.isEmpty()) {
                    println("No hay mediciones en el historial")
                } else {
                    historial.forEachIndexed { index, medicion ->
                        println("${index + 1}. Peso: ${medicion.peso} kg | " +
                                "Altura: ${medicion.altura} m | " +
                                "IMC: ${"%.2f".format(medicion.imc)} | " +
                                "Categoría: ${medicion.categoria}")
                    }
                }
            }

            3 -> {
                calculadora.mostrarResumen()
            }

            4 -> {
                println("¡Hasta luego!")
            }

            else -> {
                println("Opción no válida. Por favor, seleccione 1-4")
            }
        }

    } while (opcion != 4)
}