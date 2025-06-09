// Declaración del paquete en el que se encuentra la clase.
package com.mycompany.arrayoyb;
// Importamos la clase Scanner para poder leer datos ingresados por el usuario desde la consola.
import java.util.Scanner;
// Clase utilitaria para validar entradas del usuario desde consola.
public class Validacion {
    // Scanner estático y privado, utilizado para leer entradas de usuario.
    private static Scanner scanner = new Scanner(System.in);
    // Método estático que solicita al usuario un número entero positivo.
        public static int leerEdad(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int edad = Integer.parseInt(scanner.nextLine());
                if (edad >= 18 && edad <= 99) {
                    return edad;
                } else {
                    System.out.println("La edad debe estar entre 18 y 99 años.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
    }
    public static int leerEnteroPositivo(String mensaje) {
        // Bucle infinito que solo se rompe cuando el usuario ingresa un dato válido.
        while (true) {
            try {
                // Mostramos el mensaje al usuario.
                System.out.println(mensaje);
                // Leemos la línea, la convertimos a entero.
                int valor = Integer.parseInt(scanner.nextLine());
                // Verificamos que el número sea positivo.
                if (valor < 0) {
                    System.out.println("Error: No se permite valores negativos");
                } else {
                    // Si es válido, lo devolvemos.
                    return valor;
                }
            } catch (NumberFormatException e) {
                // Si no es un número válido, mostramos un mensaje de error.
                System.out.println("Error: Debe ingresar un numero entero valido");
            }
        }
    }
    // Método estático que solicita una cadena compuesta solo por letras y espacios.
    public static String leerSoloLetras(String mensaje) {
        while (true) {
            // Mostramos el mensaje y leemos la entrada.
            System.out.print(mensaje);
            String input = scanner.nextLine().trim(); // Eliminamos espacios al inicio y final
            // Validamos que solo tenga letras (incluyendo tildes y la ñ).
            if (input.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                return input; // Si es válido, lo devolvemos.
            } else {
                System.out.println("Error: Solo se permiten letras y espacios.");
            }
        }
    }
}