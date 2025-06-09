package com.mycompany.arrayoyb;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {
    private static final String ARCHIVO_CSV = "personas.csv";
    private HashMap<Integer, Persona> personasActuales = new HashMap<>();
    private final GestorPersonas gestor = new GestorPersonas();

    public void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Generar nuevas personas aleatoriamente");
            System.out.println("2. Cargar personas desde archivo CSV");
            System.out.println("3. Ordenar personas");
            System.out.println("4. Buscar personas");
            System.out.println("5. Guardar personas en CSV");
            System.out.println("6. Mostrar personas actuales");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = Validacion.leerEnteroPositivo("");

            switch (opcion) {
                case 1 -> generarPersonasAleatorias();
                case 2 -> cargarDesdeCSV();
                case 3 -> menuOrdenamiento();
                case 4 -> menuBusqueda();
                case 5 -> guardarEnCSV();
                case 6 -> mostrarPersonasActuales();
                case 0 -> {
                    System.out.println("Saliendo del programa...");
                    return;
                }
                default -> System.out.println("Opcion no valida. Intente nuevamente.");
            }
        }
    }

    private void generarPersonasAleatorias() {
        int cantidad = Validacion.leerEnteroPositivo("Ingrese la cantidad de personas a generar: ");
        personasActuales = gestor.generarPersonas(cantidad);
        System.out.println("\nSe han generado " + cantidad + " personas aleatorias.");
        gestor.mostrarPersonas("Personas generadas:", personasActuales);
    }

    private void cargarDesdeCSV() {
        try {
            if (!Files.exists(Paths.get(ARCHIVO_CSV))) {
                System.out.println("El archivo " + ARCHIVO_CSV + " no existe.");
                return;
            }

            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_CSV));
            List<String> lineasUnicas = lineas.stream().distinct().collect(Collectors.toList());

            personasActuales = new HashMap<>();

            for (String linea : lineasUnicas) {
                String[] partes = linea.split(",");
                if (partes.length >= 3) {
                    int id = Integer.parseInt(partes[0].trim());
                    String nombre = partes[1].trim();
                    int edad = Integer.parseInt(partes[2].trim());
                    personasActuales.put(id, new Persona(nombre, edad, id));
                }
            }

            System.out.println("\nSe han cargado " + personasActuales.size() + " personas desde el archivo CSV.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }

    private void menuOrdenamiento() {
        if (personasActuales.isEmpty()) {
            System.out.println("No hay personas cargadas para ordenar.");
            return;
        }

        System.out.println("\n=== MENU ORDENAMIENTO ===");
        System.out.println("1. Ordenar por Burbuja");
        System.out.println("2. Ordenar por Selección");
        System.out.println("3. Ordenar por Inserción");
        System.out.println("4. Ordenar por Shell");
        System.out.println("5. Ordenar por MergeSort");
        System.out.println("6. Ordenar por QuickSort");
        System.out.println("7. Probar todos los métodos");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = Validacion.leerEnteroPositivo("");

        switch (opcion) {
            case 1 -> ordenarYMostrar("Burbuja");
            case 2 -> ordenarYMostrar("Seleccion");
            case 3 -> ordenarYMostrar("Insercion");
            case 4 -> ordenarYMostrar("Shell");
            case 5 -> ordenarYMostrar("MergeSort");
            case 6 -> ordenarYMostrar("QuickSort");
            case 7 -> gestor.probarTodosOrdenamientos(new ArrayList<>(personasActuales.values()));
            case 0 -> { return; }
            default -> System.out.println("Opción no válida.");
        }
    }

    private void ordenarYMostrar(String metodo) {
        List<Persona> copia = new ArrayList<>(personasActuales.values());
        gestor.probarOrdenamiento(copia, metodo);
        gestor.mostrarPersonas("Resultado del ordenamiento (" + metodo + "):", new HashMap<Integer, Persona>() {{
            for (Persona p : copia) {
                put(p.getId(), p);
            }
        }});
    }

    private void menuBusqueda() {
        if (personasActuales.isEmpty()) {
            System.out.println("No hay personas cargadas para buscar.");
            return;
        }

        System.out.println("\n=== MENU BUSQUEDA ===");
        System.out.println("1. Búsqueda por ID");
        System.out.println("2. Búsqueda por edad");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = Validacion.leerEnteroPositivo("");

        switch (opcion) {
            case 1 -> {
                int idBuscar = Validacion.leerEnteroPositivo("Ingrese el ID a buscar: ");
                gestor.buscarPorId(personasActuales, idBuscar);
            }
            case 2 -> {
                int edadBuscar = Validacion.leerEnteroPositivo("Ingrese la edad a buscar: ");
                gestor.buscarPorEdad(personasActuales, edadBuscar);
            }
            case 0 -> { return; }
            default -> System.out.println("Opción no válida.");
        }
    }

    private void guardarEnCSV() {
        if (personasActuales.isEmpty()) {
            System.out.println("No hay personas para guardar.");
            return;
        }

        try (FileWriter writer = new FileWriter(ARCHIVO_CSV)) {
            for (Persona p : personasActuales.values()) {
                writer.write(p.getId() + "," + p.getNombre() + "," + p.getEdad() + "\n");
            }
            System.out.println("Datos guardados correctamente en " + ARCHIVO_CSV);
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo CSV: " + e.getMessage());
        }
    }

    private void mostrarPersonasActuales() {
        if (personasActuales.isEmpty()) {
            System.out.println("No hay personas cargadas.");
            return;
        }

        gestor.mostrarPersonas("Personas actuales:", personasActuales);
    }
}