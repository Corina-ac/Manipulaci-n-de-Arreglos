// Declaración del paquete en el que se encuentra la clase
package com.mycompany.arrayoyb;
// Importamos las clases necesarias para trabajar con listas dinámicas
import java.util.ArrayList;
import java.util.List;
// Clase que contiene métodos estáticos para realizar búsquedas dentro de un arreglo de objetos Persona.
public class BusquedaPersonas {
    // Método que realiza una búsqueda lineal de personas por edad.
    // Recorre el arreglo completo y devuelve una lista con todas las coincidencias encontradas.
    public static List<Persona> busquedaLineal(Persona[] personas, int edad) {
        List<Persona> resultados = new ArrayList<>();
        // Recorremos cada persona en el arreglo.
        for (Persona persona : personas) {
                        // Si la edad coincide con la buscada, se añade a la lista de resultados.
            if (persona.getEdad() == edad) {
                resultados.add(persona);
            }
        }
                // Retornamos todas las personas que coinciden con la edad buscada.
        return resultados;
    }
// Método que realiza una búsqueda binaria.
    // Busca la primera persona con una edad específica en un arreglo previamente ordenado.
    public static Persona busquedaBinaria(Persona[] personas, int edad) {
        int izquierda = 0;
        int derecha = personas.length - 1;
        // Mientras haya un rango válido en el arreglo
        while (izquierda <= derecha) {
                        // Calculamos el punto medio
            int medio = izquierda + (derecha - izquierda) / 2;
            // Si encontramos la edad exacta, buscamos la primera coincidencia hacia atrás
            if (personas[medio].getEdad() == edad) {
                return encontrarPrimeraCoincidencia(personas, medio);
            }
            // Si la edad en el medio es menor, buscamos en la parte derecha
            if (personas[medio].getEdad() < edad) {
                izquierda = medio + 1;
            } else {
                                // Si es mayor, buscamos en la parte izquierda
                derecha = medio - 1;
            }
        }
        // Si no se encontró ninguna coincidencia, retornamos null
        return null;
    }
// Método auxiliar que retrocede desde una posición dada para encontrar
    // la primera persona con la edad buscada (por si hay varias seguidas).
    private static Persona encontrarPrimeraCoincidencia(Persona[] personas, int indice) {
        Persona persona = personas[indice];
        int edad = persona.getEdad();
                // Retrocedemos mientras la persona anterior tenga la misma edad
        while (indice > 0 && personas[indice - 1].getEdad() == edad) {
            indice--;
        }
                // Devolvemos la primera coincidencia encontrada
        return personas[indice];
    }
}