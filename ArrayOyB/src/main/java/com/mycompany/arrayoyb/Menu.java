package com.mycompany.arrayoyb;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private Scanner scanner;
    private GestorPersonas gestor;
    private Persona[] personasActuales; 
    private static final String ARCHIVO_CSV="personas.csv";
    public Menu(){
        scanner =new Scanner (System.in);
        gestor=new GestorPersonas();
        personasActuales=null;
    }
    public void mostrarMenuPrincipal(){
        while(true){
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Generar nuevas personas aleatoriamente");
            System.out.println("2. Cargar personas desde archivo CSV");
            System.out.println("3. Ordenar personas");
            System.out.println("4. Buscar personas");
            System.out.println("5. Guardar personas en CSV");
            System.out.println("6. Mostrar personas actuales");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
           int opcion=Validacion.leerEnteroPositivo("");
           switch(opcion){
               case 1: 
                   generarPersonasAleatorias();break;
               case 2:
                   cargarDesdeCSV(); break;
               case 3:
                   menuOrdenamiento();
                    break;
                case 4:
                    menuBusqueda();
                    break;
                case 5:
                    guardarEnCSV();
                    break;
                case 6:
                    mostrarPersonasActuales();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opcion no valida.Intente nuevamente.");
               
           }
        }
    }
    private void generarPersonasAleatorias(){
        int cantidad=Validacion.leerEnteroPositivo("Ingrese la cantidad de personas a generar:");
        personasActuales=gestor.generarPersonas(cantidad);
        System.out.println("\nSe han generado " + cantidad + " personas aleatorias.");
        gestor.mostrarPersonas("Personas generadas:", personasActuales);
       }
        private void cargarDesdeCSV(){
            try{
               if (!Files.exists(Paths.get(ARCHIVO_CSV))) {
                System.out.println("El archivo " + ARCHIVO_CSV + " no existe.");
                return;
            }

            List<String> lineas = Files.readAllLines(Paths.get(ARCHIVO_CSV));
            
            // Eliminar duplicados usando un Set
            List<String> lineasUnicas = lineas.stream().distinct().collect(Collectors.toList());
             for (int i = 0; i < lineasUnicas.size(); i++) {
                String[] partes = lineasUnicas.get(i).split(",");
                if (partes.length >= 3) {
                    int id = Integer.parseInt(partes[0].trim());
                    String nombre = partes[1].trim();
                    int edad = Integer.parseInt(partes[2].trim());
                    personasActuales[i] = new Persona(nombre, edad, id);
            }
        }
         System.out.println("\nSe han cargado " + personasActuales.length + " personas desde el archivo CSV.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato del archivo CSV: " + e.getMessage());
        }
    }
    private void menuOrdenamiento() {
        if (personasActuales == null || personasActuales.length == 0) {
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
            case 1:
                ordenarYMostrar("Burbuja");
                break;
            case 2:
                ordenarYMostrar("Seleccion");
                break;
            case 3:
                ordenarYMostrar("Insercion");
                break;
            case 4:
                ordenarYMostrar("Shell");
                break;
            case 5:
                ordenarYMostrar("MergeSort");
                break;
            case 6:
                ordenarYMostrar("QuickSort");
                break;
            case 7:
                gestor.probarTodosOrdenamientos(personasActuales.clone());
                break;
            case 0:
                return;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void ordenarYMostrar(String metodo) {
        Persona[] copia = personasActuales.clone();
        gestor.probarOrdenamiento(copia, metodo);
        gestor.mostrarPersonas("Resultado del ordenamiento (" + metodo + "):", copia);
    }

    private void menuBusqueda() {
        if (personasActuales == null || personasActuales.length == 0) {
            System.out.println("No hay personas cargadas para buscar.");
            return;
        }

        System.out.println("\n=== MENU BUSQUEDA ===");
        System.out.println("1. Búsqueda lineal");
        System.out.println("2. Búsqueda binaria (requiere datos ordenados)");
        System.out.println("0. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = Validacion.leerEnteroPositivo("");
        
        switch (opcion) {
            case 1:
                int edadLineal = Validacion.leerEnteroPositivo("Ingrese la edad a buscar: ");
                gestor.buscarLineal(personasActuales, edadLineal);
                break;
            case 2:
                int edadBinaria = Validacion.leerEnteroPositivo("Ingrese la edad a buscar: ");
                Persona[] ordenadas = gestor.ordenarParaBusqueda(personasActuales);
                gestor.buscarBinaria(ordenadas, edadBinaria);
                break;
            case 0:
                return;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void guardarEnCSV() {
        if (personasActuales == null || personasActuales.length == 0) {
            System.out.println("No hay personas para guardar.");
            return;
        }

        try (FileWriter writer = new FileWriter(ARCHIVO_CSV)) {
            for (Persona persona : personasActuales) {
                if (persona instanceof Persona) {
                    Persona p = (Persona) persona;
                    writer.write(p.getId() + "," + p.getNombre() + "," + p.getEdad() + "\n");
                } else {
                    // Generar ID aleatorio si no es PersonaConID
                    Random rand = new Random();
                    int id = rand.nextInt(10000);
                    writer.write(id + "," + persona.getNombre() + "," + persona.getEdad() + "\n");
                }
            }
            System.out.println("Datos guardados correctamente en " + ARCHIVO_CSV);
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo CSV: " + e.getMessage());
        }
    }

    private void mostrarPersonasActuales() {
        if (personasActuales == null || personasActuales.length == 0) {
            System.out.println("No hay personas cargadas.");
            return;
        }
        gestor.mostrarPersonas("Personas actuales:", personasActuales);
    }
    }

    
    
    

