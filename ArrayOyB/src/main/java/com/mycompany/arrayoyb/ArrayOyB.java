// Declaramos el paquete donde se encuentra esta clase.
// Esto permite organizar el código en módulos o carpetas lógicas.
package com.mycompany.arrayoyb;

// Clase principal del programa. Esta es la que se ejecuta al correr el proyecto.
public class ArrayOyB {

    // Método main: punto de entrada del programa en Java.
    public static void main(String[] args) {

        // Se crea una nueva instancia de la clase GestorPersonas y se ejecuta el método ejecutarPrograma().
        // Esto permite delegar toda la lógica del programa (generar, ordenar y buscar personas)
        // a una clase especializada, manteniendo este archivo limpio y organizado.
           new Menu().mostrarMenuPrincipal();
        
    }
}