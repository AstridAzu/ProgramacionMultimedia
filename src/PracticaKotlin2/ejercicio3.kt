package PracticaKotlin2

import java.io.File
import java.nio.file.Paths

data class ResultadoAnalisis(
    val caracteres: Int,
    val palabras: Int,
    val lineas: Int,
    val palabraMasFrecuente: String?,
    val longitudPromedioPalabras: Double,
    val sentimiento: Sentimiento,
    val frasesFrecuentes: List<Pair<String, Int>>
)
fun normalizarTexto(texto: String): String {
    return texto
        .lowercase()
        .replace(Regex("[^a-zA-Záéíóúñü0-9\\s]"), "") // eliminar puntuación
}

fun contarCaracteres(texto: String): Int {
    return texto.replace(" ", "").length
}


fun contarPalabras(texto: String): Int {
    return texto
        .split(Regex("\\s+"))
        .filter { it.isNotBlank() }
        .size
}


fun contarLineas(texto: String): Int {
    return texto.split("\n").size
}


fun encontrarPalabraMasFrecuente(texto: String): String? {
    val palabras = texto
        .split(Regex("\\s+"))
        .filter { it.isNotBlank() }

    return palabras
        .groupBy { it }
        .maxByOrNull { it.value.size }
        ?.key
}


fun longitudPromedioPalabras(texto: String): Double {
    val palabras = texto
        .split(Regex("\\s+"))
        .filter { it.isNotBlank() }

    return palabras
        .map { it.length }
        .average()
}
//Función principal
fun analizarTexto(textoOriginal: String): ResultadoAnalisis {
    val texto = normalizarTexto(textoOriginal)

    return ResultadoAnalisis(
        caracteres = contarCaracteres(texto),
        palabras = contarPalabras(texto),
        lineas = contarLineas(textoOriginal),
        palabraMasFrecuente = encontrarPalabraMasFrecuente(texto),
        longitudPromedioPalabras = longitudPromedioPalabras(texto),
        sentimiento = analizarSentimiento(texto),
        frasesFrecuentes=frasesFrecuentes(texto)
    )
}

fun menu() {
    println(" MENÚ PRINCIPAL ")
    println("1. Ingresar texto")
    println("2. Analizar texto")
    println("3. Ver resultados")
    println("4. Exportar análisis a TXT")
    println("5. Salir")
    print("Seleccione una opción: ")
}


fun main() {
    var textoIngresado = ""
    var resultado: ResultadoAnalisis? = null
    val textoDePrueba = """
        Hola hola mundo.
        Kotlin es genial, y aprender procesamiento de texto es útil.
        Hola de nuevo!
    """.trimIndent()

    while (true) {
        menu()
        val opcion = readLine()?.trim()

        when (opcion) {

            "1" -> {
                println("Ingrese el texto a analizar:")
                textoIngresado = readLine() ?: ""
                println("Texto guardado.")
            }

            "2" -> {
                // NUEVA CONDICIÓN
                val textoAAnalizar = if (textoIngresado.isBlank()) {
                    println("No ingresó texto. Usando texto de prueba...")
                    textoDePrueba
                } else {
                    textoIngresado
                }

                resultado = analizarTexto(textoAAnalizar)
                println("Texto analizado correctamente.")
            }

            "3" -> {
                if (resultado == null) {
                    println("Debe analizar un texto antes (opción 2).")
                } else {
                    println(" RESULTADOS ")
                    println("Caracteres: ${resultado!!.caracteres}")
                    println("Palabras: ${resultado!!.palabras}")
                    println("Líneas: ${resultado!!.lineas}")
                    println("Palabra más frecuente: ${resultado!!.palabraMasFrecuente}")
                    println("Longitud promedio palabras: ${"%.2f".format(resultado!!.longitudPromedioPalabras)}")

                    println(" Frases frecuentes (3 palabras) ")
                    resultado!!.frasesFrecuentes.forEach { (frase, n) ->
                        println("\"$frase\" → $n vez/veces")
                    }

                    println("Análisis de sentimiento ")
                    println("Positivas: ${resultado!!.sentimiento.scorePositivo}")
                    println("Negativas: ${resultado!!.sentimiento.scoreNegativo}")
                    println("Sentimiento general: ${resultado!!.sentimiento.sentimiento}")
                }
            }

            "4" -> {
                if (resultado == null) {
                    println("No puede exportar sin analizar (opción 2).")
                } else {
                    exportarAnalisisATxt(resultado!!)
                    println("Archivo TXT exportado correctamente.")
                }
            }

            "5" -> {
                println("Saliendo del programa…")
                break
            }

            else -> println("Opción no válida, intente nuevamente.")
        }
    }

}


//extra
fun frasesFrecuentes(texto: String): List<Pair<String, Int>> {
    val n=3;
    val palabras = texto
        .split(Regex("\\s+"))
        .filter { it.isNotBlank() }

    val frases = mutableListOf<String>()

    for (i in 0..palabras.size - n) {
        val frase = palabras.subList(i, i + n).joinToString(" ")
        frases.add(frase)
    }

    return frases
        .groupBy { it }
        .mapValues { it.value.size }
        .entries
        .sortedByDescending { it.value }
        .map { it.key to it.value }
}

//sentimientos
val positivas = listOf("bueno", "genial", "excelente", "feliz", "útil", "maravilloso")
val negativas = listOf("malo", "terrible", "horrible", "triste", "feo", "desagradable")

data class Sentimiento(
    val scorePositivo: Int,
    val scoreNegativo: Int,
    val sentimiento: String
)

fun analizarSentimiento(texto: String): Sentimiento {
    val palabras = texto.split(Regex("\\s+")).map { it.lowercase() }

    val pos = palabras.count { it in positivas }
    val neg = palabras.count { it in negativas }

    val resultado = when {
        pos > neg -> "positivo"
        neg > pos -> "negativo"
        else -> "neutral"
    }

    return Sentimiento(pos, neg, resultado)
}

fun exportarAnalisisATxt(resultado: ResultadoAnalisis) {
    // Obtener ruta del archivo .kt ejecutado
    val rutaActual = Paths.get("").toAbsolutePath().toString()

    val contenido = buildString {
        appendLine("RESULTADO DEL ANÁLISIS DE TEXTO")
        appendLine("Caracteres: ${resultado.caracteres}")
        appendLine("Palabras: ${resultado.palabras}")
        appendLine("Líneas: ${resultado.lineas}")
        appendLine("Palabra más frecuente: ${resultado.palabraMasFrecuente}")
        appendLine("Longitud promedio palabras: ${"%.2f".format(resultado.longitudPromedioPalabras)}")
        appendLine()
        appendLine("Frases frecuentes (3 palabras):")
        resultado.frasesFrecuentes.forEach { (frase, n) ->
            appendLine("- \"$frase\" → $n vez/veces")
        }
        appendLine()
        appendLine("Análisis de sentimiento:")
        appendLine("Positivas: ${resultado.sentimiento.scorePositivo}")
        appendLine("Negativas: ${resultado.sentimiento.scoreNegativo}")
        appendLine("Sentimiento: ${resultado.sentimiento.sentimiento}")
    }

    val archivo = File("$rutaActual/analisis.txt")
    archivo.writeText(contenido)

    println("Archivo exportado correctamente en: ${archivo.absolutePath}")
}

