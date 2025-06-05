// Declaramos el paquete en el que se encuentra la clase.
package com.mycompany.arrayoyb;
// Importamos clases necesarias para trabajar con listas y generar números aleatorios.
import java.util.List;
import java.util.Random;
// Clase principal que organiza la lógica del programa: generación, ordenamiento y búsqueda de personas.
public class GestorPersonas {
    // Arreglo constante con posibles nombres para generar personas aleatorias.
    private static final String[] NOMBRES = {"Juan", "Maria", "Carlos", "Ana", "Luis", "Laura", 
                                             "Pedro", "Sofia", "Miguel", "Elena", "Jorge", "Lucia"};
    // Método que inicia todo el programa: se ejecuta desde el main.
    public void ejecutarPrograma() {
        // Se pide al usuario cuántas personas quiere generar.
        int cantidadPersonas = Validacion.leerEnteroPositivo("Ingrese la cantidad de personas a generar: ");
        // Se generan las personas con nombres y edades aleatorias.
        Persona[] personas = generarPersonas(cantidadPersonas);
        // Se muestran las personas generadas por consola.
        mostrarPersonas("\nPersonas generadas:", personas);
        // Se prueba cada uno de los métodos de ordenamiento con copias del arreglo.
        probarTodosOrdenamientos(personas);
        // Se ordena el arreglo original con MergeSort para preparar la búsqueda binaria.
        Persona[] personasOrdenadas = ordenarParaBusqueda(personas);
        // Se muestran las personas ordenadas por edad.
        mostrarPersonas("\nPersonas ordenadas por edad:", personasOrdenadas);
        // Se realizan las búsquedas con ambos métodos (lineal y binaria).
        realizarBusquedas(personas, personasOrdenadas);
    }
    // Método privado para generar un arreglo de personas con nombres y edades aleatorias.
    private Persona[] generarPersonas(int cantidad) {
        Random random = new Random();
        Persona[] personas = new Persona[cantidad];
        
        // Se llena el arreglo con objetos Persona generados aleatoriamente.
        for (int i = 0; i < cantidad; i++) {
            String nombre = NOMBRES[random.nextInt(NOMBRES.length)]; // Nombre aleatorio
            int edad = 18 + random.nextInt(83); // Edad entre 18 y 100
            personas[i] = new Persona(nombre, edad);
        }
        return personas;
    }
    // Método que muestra en consola todas las personas de un arreglo con un mensaje previo.
    private void mostrarPersonas(String mensaje, Persona[] personas) {
        System.out.println(mensaje);
        for (int i = 0; i < personas.length; i++) {
            System.out.println((i + 1) + ". " + personas[i]); // Se imprime el índice + la persona
        }
    }
    // Método que ejecuta todos los algoritmos de ordenamiento definidos, usando copias del arreglo.
    private void probarTodosOrdenamientos(Persona[] personas) {
        probarOrdenamiento(personas.clone(), "Burbuja");
        probarOrdenamiento(personas.clone(), "Seleccion");
        probarOrdenamiento(personas.clone(), "Insercion");
        probarOrdenamiento(personas.clone(), "Shell");
        probarOrdenamiento(personas.clone(), "MergeSort");
        probarOrdenamiento(personas.clone(), "QuickSort");
    }
    // Método que prueba un algoritmo de ordenamiento específico y mide su tiempo de ejecución.
    private void probarOrdenamiento(Persona[] personas, String metodo) {
        System.out.println("\n=== PRUEBA DE ORDENAMIENTO ===");
        System.out.println("Metodo: " + metodo);
        System.out.println("Numero de elementos: " + personas.length);
        // Se toma el tiempo antes de iniciar el ordenamiento.
        long inicio = System.nanoTime();
        // Se selecciona el método de ordenamiento según el nombre recibido.
        switch (metodo) {
            case "Burbuja":
                System.out.println("Proceso: Comparando e intercambiando elementos...");
                OrdenamientoPersonas.ordenarBurbuja(personas);
                break;
            case "Seleccion":
                System.out.println("Proceso: Buscando elementos minimos...");
                OrdenamientoPersonas.ordenarSeleccion(personas);
                break;
            case "Insercion":
                System.out.println("Proceso: Insertando elementos en posicion correcta...");
                OrdenamientoPersonas.ordenarInsercion(personas);
                break;
            case "Shell":
                System.out.println("Proceso: Aplicando saltos decrecientes...");
                OrdenamientoPersonas.ordenarShell(personas);
                break;
            case "MergeSort":
                System.out.println("Proceso: Dividiendo y combinando...");
                OrdenamientoPersonas.ordenarMergeSort(personas);
                break;
            case "QuickSort":
                System.out.println("Proceso: Seleccionando pivotes...");
                OrdenamientoPersonas.ordenarQuickSort(personas);
                break;
        }
        // Se toma el tiempo después de terminar el ordenamiento.
        long fin = System.nanoTime();
        double tiempoMs = (fin - inicio) / 1e6;
        // Se imprime el resultado del tiempo total.
        System.out.println("\nRESULTADO:");
        System.out.println("Tiempo total de ordenamiento: " + String.format("%.4f", tiempoMs) + " ms");
        System.out.println("=============================");
    }
    // Método que prepara el arreglo ordenado con MergeSort para poder hacer búsqueda binaria.
    private Persona[] ordenarParaBusqueda(Persona[] personas) {
        System.out.println("\n=== PREPARACION PARA BUSQUEDA BINARIA ===");
        System.out.println("Ordenando el arreglo con MergeSort...");
        // Se hace una copia del arreglo original para no modificarlo.
        Persona[] copia = personas.clone();
        long inicio = System.nanoTime();
        OrdenamientoPersonas.ordenarMergeSort(copia);
        long fin = System.nanoTime();

        double tiempoMs = (fin - inicio) / 1e6;
        System.out.println("Tiempo de ordenamiento: " + String.format("%.4f", tiempoMs) + " ms");
        System.out.println("=========================================");

        return copia; // Se devuelve el arreglo ordenado.
    }

    // Método que gestiona la ejecución de ambas búsquedas: lineal y binaria.
    private void realizarBusquedas(Persona[] original, Persona[] ordenadas) {
        // Se pide al usuario una edad para buscar.
        int edadBuscar = Validacion.leerEnteroPositivo("\nIngrese la edad a buscar: ");

        // Se busca en el arreglo original usando búsqueda lineal.
        buscarLineal(original, edadBuscar);

        // Se busca en el arreglo ordenado usando búsqueda binaria.
        buscarBinaria(ordenadas, edadBuscar);
    }
    // Método para ejecutar la búsqueda lineal, mostrar resultados y tiempo de ejecución.
    private void buscarLineal(Persona[] personas, int edad) {
        System.out.println("\nRealizando busqueda lineal...");
        long inicio = System.nanoTime();
        List<Persona> resultados = BusquedaPersonas.busquedaLineal(personas, edad);
        long fin = System.nanoTime();

        System.out.println("\nResultados busqueda lineal (" + (fin - inicio) / 1e6 + " ms):");

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron personas con edad " + edad);
        } else {
            System.out.println("Se encontraron " + resultados.size() + " personas:");
            resultados.forEach(p -> System.out.println(" - " + p));
        }
    }

    // Método para ejecutar la búsqueda binaria, mostrar resultados y tiempo de ejecución.
    private void buscarBinaria(Persona[] personas, int edad) {
        System.out.println("\nRealizando busqueda binaria...");
        long inicio = System.nanoTime();
        Persona resultado = BusquedaPersonas.busquedaBinaria(personas, edad);
        long fin = System.nanoTime();

        System.out.println("\nResultado busqueda binaria (" + (fin - inicio) / 1e6 + " ms):");

        if (resultado == null) {
            System.out.println("No se encontro ninguna persona con edad " + edad);
        } else {
            System.out.println("Primera persona encontrada: " + resultado);
        }
    }
}
