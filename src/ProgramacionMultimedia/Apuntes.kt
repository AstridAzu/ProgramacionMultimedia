package ProgramacionMultimedia

class Apuntes {
    //Función con cuerpo de expresión sin parámetros
    fun obtenerPI(): Double = 3.14

    //Declaración de variables con tipo explícito

    val nombre1: String = "Maria"
    val edad: Int = 25
    val altura: Double = 1.75

    // Declaración de variables con inferencia de tipos (recomendado)
    val ciudad = "Madrid" // Se deduce que es String
    val contador = 42 // Se deduce que es Int


    // (MUTABLES - Pueden cambiar) Declaración de variables con tipo explícito
    var nombre2: String = "Marias"
    var edad2: Int = 25
    var altura2: Double = 1.75

    //Conversión explícita
    val entero: Int = 42

    //Nullable vs Non-Nullable
    var nombre3: String? = "Maria"

    //Tipos de String
    val multilinea = """
        Primera linea
        Segunda linea
        Tercera linea
    """.trimIndent() //Strings con saltos de línea

    val presentacion = "Me llamo $nombre1 y tengo $edad2 años"
    val a = 5
    val b = 3
    val resultado = "El resultado de $a + $b es ${a + b}"
    val lista = listOf(1,2,3) //List&lt
    val descripcion = "La lista tiene ${lista.size} elementos"
    val primera = "El primer elemento es ${lista.firstOrNull() ?: "vacia"}"

    //(EXPLICITA) Declaración de funciones con parámetros y valor de retorno
    fun suma(a: Int, b: Int = 0): Int {
        return a + b
    }



    //(Implícita) Declaración de funciones con parámetros y valor de retorno
    fun multiplicar(a: Int, b: Int): Int = a * b


    //(EXPLICITA) Función sin valor de retorno (void en java)
    fun saludar(){
        println("Hola a todos!")
    }

    //(Implícito) Función sin valor de retorno (void en java)
    fun despedir(nombre: String = "Maria"){
        println("Adios $nombre")
    }

    //Funcion que permite varias veces el mismo tipo de parametro
    fun sumarTodo(vararg numeros: Int): Int{
        //Inicializacion de la variable suma
        var suma = 0
        //Declaracion de un for-in, el cual numeros es el array
        //Y num cada uno de los numeros del array
        for(num in numeros){
            suma += num
        }
        //Retorna la variable suma
        return suma
    }

    //Variables para los condicionales
    val edad4 = 21
    val nota = 9


    // Sentencia WHEN basica con valores
    fun obtenerDia(numero: Int): String = when (numero) {
        1 -> "Lunes"
        2 -> "Martes"
        3 -> "Miercoles"
        4 -> "Jueves"
        5 -> "Viernes"
        6 -> "Sabado"
        7 -> "Domingo"
        else -> "Invalido"
    }

    // Sentencia WHEN basica con rangos
    fun clasificarEdad(numero: Int): String = when (numero) {
        in 0..12 -> "Niñ@"
        in 13..19 -> "Adolescente"
        in 20..59 -> "Adult@"
        in 60..150 -> "Adult@ mayor"
        else -> "Edad invalida"
    }

    //Sentencia WHEN con tipos
    //Any prermite instanciar cualquier tipo de objeto
    fun describir(objeto: Any): String = when (objeto){
        is String ->"Cadena de texto: $objeto"
        is Int ->"Numero entero: $objeto"
        is Double ->"Numero decimal: $objeto"
        is Boolean ->"Boolean: $objeto"
        else -> "Tipo desconocido"
    }

    //Sentencia WHEN con expresiones complejas
    fun procesarEstado(estado: String): String = when{
        estado.isEmpty() -> "Estado vacio"
        estado.length > 100 -> "Estado demasiado largo"
        estado.contains("ERROR") -> "Estado contiene error"
        else -> "Estado valido: $estado"
    }

    //OPERADORES LOGICOS
    //Operador OR (||)
    //Operador AND (&&)
    //Operador NOT (!)
    //Operador in
    //Operador is




    //Equivalente a public static void main en java
    fun main(){
        //Reasignación de valores
        nombre2 = "Maria"
        edad2 = 24

        //CConversion de entero
        val decimal: Double = entero.toDouble()
        val texto: String = entero.toString()
        val booleano: Boolean = entero != 0 //True

        nombre3 = null

        //Llamadas con diferentes combinaciones
        suma(1)
        suma(1, 2)
        suma(a = 1, b = 2)
        suma(a = 1, 3)

        //Uso con diferentes cantidades de argumentos
        println(sumarTodo())
        println(sumarTodo(1, 2))
        println(sumarTodo(1, 2,3,4,5))

        var array = intArrayOf(10, 20, 30)
        println(sumarTodo(*array))

        //IF
        if(edad4 >= 18){
            println("Eres mayor de edad tienes ${edad4} años")
        }

        //IF ELSE
        if(edad4 >= 18){
            println("Eres mayor de edad tienes ${edad4} años")
        }else {
            println("Eres menor de edad tienes ${edad4} años")
        }

        //IF - ELSE / IF - ELSE
        if(nota >= 9){
            println("Tu nota es $nota, tienes un sobresaliente")
        }else if (nota >= 7) {
            println("Tu nota es $nota, tienes un notable")
        }else if (nota >= 6){
            println("Tu nota es $nota, tienes un bien")
        }else if (nota >= 5){
            println("Tu nota es $nota, tienes un suficiente")
        }else{
            println("Tu nota es $nota, tienes un suspenso")
        }

        //IF como expresion
        val resultado1 = if(edad4 >= 18) "Mayor" else "Menor"
        val categoria = if(nota >= 9) "Sobresaliente" else if(nota >= 7) "Notable" else if(nota >= 6) "Bien" else if(nota >= 5) "Suficiente" else "Suspenso"

        //BUCLES - FOR
        //Sobre rangos

        for (i in 1..5) {
            println(i)
        }

        //Rango exclusivo
        for (i in 1..<5) {
            println(i)
        }

        //Rango descendente
        for (i in 5 downTo 1) {
            println(i)
        }

        //Rango con paso
        for(i in 1..10 step 2) {
            println(i)
        }

        //for sobre lista
        val nombres = listOf("Ana", "Carlos", "Maria")
        for(nombre in nombres){
            println(nombre)
        }

        //For sobre mapa
        val mapa = mapOf("es" to "Español", "en" to "English", "fr" to "Francés")
        for((idioma, nombre) in mapa){
            println("$idioma: $nombre")
        }


    }


}
