# Laboratorio 1 - 19-10114 - CI2693
- Nombre: Stefano Casale
- Carnet: 1910114
- Universidad Simón Bolívar
- Trimestre Ene - Mar 26

# Pasos de Ejecución 
1. Instalar Kotlin:
    - Desde tu terminal de WSL ejecuta el siguiente comando:
        - sudo apt install kotlin
2. Ubica tu entorno:
    - Desde tu terminal de WSL ejecuta el siguiente comando:
        - cd ubicacion/de/tu/entorno
3. Crea un archivo "input.txt":
    - Este archivo debe contener por cada una de sus lineas, pares de nombres separados por un espacio " ". Un ejemplo podria ser el siguiente:
        Alejandro Gabriel
        Gabriel Jesus
        Daniel Juan
    - Puedes expandirlo la cantidad de lineas que quieras siempre y cuando se cumpla que por linea, sean exactamente 2 nombres.
4. Compila todos los archivos .kt que estan en este repositorio:
    - Desde tu terminal de WSL ejecuta el siguiente comando:
        - kotlinc *.kt -include-runtime -d DegreesOfSeparation.jar
5. Ejecuta el archivo DegreesOfSeparation.jar:
    - Desde tu terminal de WSL ejecuta el siguiente comando:
        - java -jar DegreesOfSeparation.jar <Nombre1> <Nombre2>
    El programa maneja errores con los parámetros, pero para garantizar su funcionalidad recomiendo que tanto <Nombre1> como <Nombre2> sean nombres que pertenezcan al archivo "input.txt"

# Funcionamiento
El programa calcula los grados de separacion que hay entre dos personas. Es el número de personas que hay que atravesar para pasar de una a otra.

Al ejecutarse, puede arrojarte las siguientes opciones:
    - "0" Si <Nombre1> y <Nombre2> son iguales
    - "-1" Si alguno de los nombres no existe en el grafo, o no hay un camino entre ellos
    - Los grados de separacion entre <Nombre1> y <Nombre2>
    - "Error" dependiendo del caso

# Decisiones de Implementación
Para este laboratorio decidi seguir la recomendacion y utilizar los métodos que implementamos en el primer proyecto class ListaAdyacenciaGrafo, esta modela un grafo dirigido y en esta ocasión debía modelar un grafo no dirigido, para ello, conecte en ambos sentidos los nombres del archivo "input.txt".

Luego le dimos uso al algoritmo "BFS" implementandolo a traves de una cola para verificar los nodos en el orden correcto y un mapa de distancias para almacenar distancias desde el nodo de origen <Nombre1> hasta el nodo destino <Nombre2>

Mantuve la implementación en varios archivos:
    - DegreesOfSeparation.kt
    - Grafo.kt
    - ListaAdyacenciaGrafo.kt
Para reutilizar lo que se trabajó en el proyecto anterior