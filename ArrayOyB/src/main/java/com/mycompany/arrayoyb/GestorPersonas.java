package com.mycompany.arrayoyb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GestorPersonas {
    private static final String[] NOMBRES = {
        "Juan", "Maria", "Carlos", "Ana", "Luis", "Laura",
        "Pedro", "Sofia", "Miguel", "Elena", "Jorge", "Lucia"
    };

    public HashMap<Integer, Persona> generarPersonas(int cantidad) {
        Random random = new Random();
        HashMap<Integer, Persona> personas = new HashMap<>();

        for (int i = 0; i < cantidad; i++) {
            String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
            int edad = 18 + random.nextInt(83);
            int id = 1000 + i;
            personas.put(id, new Persona(nombre, edad, id));
        }
        return personas;
    }

    void mostrarPersonas(String mensaje, Map<Integer, Persona> personas) {
        System.out.println(mensaje);
        int i = 1;
        for (Persona persona : personas.values()) {
            System.out.println(i++ + ". " + persona);
        }
    }

    public void ejecutarPrograma() {
        int cantidadPersonas = Validacion.leerEnteroPositivo("Ingrese la cantidad de personas a generar: ");
        HashMap<Integer, Persona> personas = generarPersonas(cantidadPersonas);
        mostrarPersonas("\nPersonas generadas:", personas);
        
        // Convertimos a lista para ordenar
        List<Persona> listaPersonas = new ArrayList<>(personas.values());
        probarTodosOrdenamientos(listaPersonas);
        
        // Búsquedas
        realizarBusquedas(personas);
    }

    void probarTodosOrdenamientos(List<Persona> personas) {
        probarOrdenamiento(new ArrayList<>(personas), "Burbuja");
        probarOrdenamiento(new ArrayList<>(personas), "Seleccion");
        probarOrdenamiento(new ArrayList<>(personas), "Insercion");
        probarOrdenamiento(new ArrayList<>(personas), "Shell");
        probarOrdenamiento(new ArrayList<>(personas), "MergeSort");
        probarOrdenamiento(new ArrayList<>(personas), "QuickSort");
    }

    void probarOrdenamiento(List<Persona> personas, String metodo) {
        System.out.println("\n=== PRUEBA DE ORDENAMIENTO ===");
        System.out.println("Método: " + metodo);
        System.out.println("Número de elementos: " + personas.size());

        long inicio = System.nanoTime();

        switch (metodo) {
            case "Burbuja":
                OrdenamientoPersonas.ordenarBurbuja(personas);
                break;
            case "Seleccion":
                OrdenamientoPersonas.ordenarSeleccion(personas);
                break;
            case "Insercion":
                OrdenamientoPersonas.ordenarInsercion(personas);
                break;
            case "Shell":
                OrdenamientoPersonas.ordenarShell(personas);
                break;
            case "MergeSort":
                OrdenamientoPersonas.ordenarMergeSort(personas);
                break;
            case "QuickSort":
                OrdenamientoPersonas.ordenarQuickSort(personas);
                break;
        }

        long fin = System.nanoTime();
        double tiempoMs = (fin - inicio) / 1e6;

        System.out.printf("Tiempo total de ordenamiento: %.4f ms%n", tiempoMs);
        System.out.println("=============================");
    }

    private void realizarBusquedas(HashMap<Integer, Persona> personas) {
        // Búsqueda por ID
        int idBuscar = Validacion.leerEnteroPositivo("\nIngrese el ID a buscar: ");
        buscarPorId(personas, idBuscar);
        
        // Búsqueda por edad
        int edadBuscar = Validacion.leerEnteroPositivo("\nIngrese la edad a buscar: ");
        buscarPorEdad(personas, edadBuscar);
    }

    void buscarPorId(HashMap<Integer, Persona> personas, int id) {
        System.out.println("\nRealizando búsqueda por ID...");
        long inicio = System.nanoTime();
        Persona resultado = BusquedaPersonas.busquedaPorId(personas, id);
        long fin = System.nanoTime();

        System.out.printf("\nResultado búsqueda por ID (%.4f ms):%n", (fin - inicio) / 1e6);
        if (resultado == null) {
            System.out.println("No se encontró persona con ID " + id);
        } else {
            System.out.println("Persona encontrada: " + resultado);
        }
    }

    void buscarPorEdad(HashMap<Integer, Persona> personas, int edad) {
        System.out.println("\nRealizando búsqueda por edad...");
        long inicio = System.nanoTime();
        List<Persona> resultados = BusquedaPersonas.busquedaPorEdad(personas, edad);
        long fin = System.nanoTime();

        System.out.printf("\nResultados búsqueda por edad (%.4f ms):%n", (fin - inicio) / 1e6);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron personas con edad " + edad);
        } else {
            System.out.println("Se encontraron " + resultados.size() + " personas:");
            resultados.forEach(p -> System.out.println(" - " + p));
        }
    }
}