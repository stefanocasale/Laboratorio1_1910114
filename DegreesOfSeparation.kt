import java.io.File
import java.io.FileNotFoundException

fun almacenarTxtGrafo(): ListaAdyacenciaGrafo<String>? { // Para leer el .txt
    val grafo = ListaAdyacenciaGrafo<String>() 
    val lineas = File("input.txt").readLines()

    // Verificamos que el archivo no este vacío
    if (lineas.isEmpty()) {
        println("Error, el archivo debe conter lineas con pares de nombres.")
        return null 
    }
 
    // Iteramos para crear el grafo a partir de las líneas del archivo
    for (linea in lineas) {
        val partes = linea.trim().split(" ")

        // Verificamos que cada línea tenga exactamente dos nombres
        if (partes.size != 2) {
            println("Error, una línea no tiene exactamente dos nombres")
            return null
        }

        // Almacenamos los nombres
        val nombre1 = partes[0]
        val nombre2 = partes[1]

        // Agregamos los vértices
        grafo.agregarVertice(nombre1)
        grafo.agregarVertice(nombre2)

        // Conectamos en ambos sentidos
        grafo.conectar(nombre1, nombre2)
        grafo.conectar(nombre2, nombre1)
    }

    return grafo
}

fun bfs(grafo: ListaAdyacenciaGrafo<String>, origen: String, destino: String): Int { // Calcula la distancia mínima entre dos vértices

    // Si ambas son la misma persona
    if (origen == destino) {
        return 0
    }

    // Si no existe conexión entre ambas personas
    if (!grafo.contiene(origen) || !grafo.contiene(destino)) {
        return -1
    }

    
    val distancia = mutableMapOf<String, Int>()
    val cola = ArrayDeque<String>() // Cola para almacenar nodos por visitar

    distancia[origen] = 0 
    cola.addLast(origen) // Encolamos el nodo de origen para iniciar la búsqueda

    // Iteramos mientras haya nodos por visitar
    while (cola.isNotEmpty()) {
        val nodoActual = cola.removeFirst() // Sacamos el primer elemento de la cola
        val distanciaActual = distancia[nodoActual] // Obtenemos la distancia actual al nodo que estamos visitando
        val vecinos = grafo.obtenerArcosSalida(nodoActual) // Obtenemos los vecinos del nodo actual en ambos sentidos

        for (vecino in vecinos) {
            // Si un vecino no ha sido visitado
            if (vecino !in distancia) {
                distancia[vecino] = distanciaActual!! + 1
                cola.addLast(vecino)
            }

            // Si el vecino es el destino
            if (vecino == destino) {
                return distancia[vecino]!!
            }
        }
    }
    
    // Si la cola se vacía y no encontramos al destino, significa que no hay camino.
    return -1
}


fun main(args: Array<String>) {
    // Verificamos la cantidad de argumentos que se pasaron por consola
    if (args.size !=2) {
        println("Error, se necesitan exactamente dos nombres como argumentos.")
        return
    }

    // Almacenamos los argumentos 
    val persona1 = args[0]
    val persona2 = args[1]

    // Creamos el grafo con los nombres de input.txt
    val grafo = almacenarTxtGrafo()

    // Si el grafo es nulo, significa que hubo un error al leer el archivo
    if (grafo == null) {
        println("Error, revisa el archivo imput.txt")
        return
    }

    println(bfs(grafo, persona1, persona2)) 
}