// Declaramos el paquete en el que se encuentra esta clase
package com.mycompany.arrayoyb;
// Clase que contiene métodos estáticos para ordenar un arreglo de objetos Persona
// utilizando diferentes algoritmos de ordenamiento clásicos.
public class OrdenamientoPersonas {
        // Método que implementa el algoritmo de ordenamiento Burbuja.
    // Compara pares adyacentes y los intercambia si están en el orden incorrecto.
    public static void ordenarBurbuja(Persona[] personas) {
        int n = personas.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (personas[j].getEdad() > personas[j + 1].getEdad()) {
                    Persona temp = personas[j];
                    personas[j] = personas[j + 1];
                    personas[j + 1] = temp;
                }
            }
        }
    }
  // Ordenamiento por Selección.
    // Busca el valor mínimo en cada iteración y lo coloca en su posición correspondiente.
    public static void ordenarSeleccion(Persona[] personas) {
        int n = personas.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (personas[j].getEdad() < personas[minIdx].getEdad()) {
                    minIdx = j;
                }
            }
            Persona temp = personas[minIdx];
            personas[minIdx] = personas[i];
            personas[i] = temp;
        }
    }
 // Ordenamiento por Inserción.
    // Toma cada elemento y lo inserta en la posición correcta dentro de la parte ordenada del arreglo.
    public static void ordenarInsercion(Persona[] personas) {
        int n = personas.length;
        for (int i = 1; i < n; ++i) {
            Persona key = personas[i];
            int j = i - 1;

            while (j >= 0 && personas[j].getEdad() > key.getEdad()) {
                personas[j + 1] = personas[j];
                j = j - 1;
            }
            personas[j + 1] = key;
        }
    }
// Ordenamiento de Shell (mejora del Inserción).
    // Utiliza "gaps" para comparar elementos más lejanos y reduce el gap con cada pasada.
    public static void ordenarShell(Persona[] personas) {
        int n = personas.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                Persona temp = personas[i];
                int j;
                for (j = i; j >= gap && personas[j - gap].getEdad() > temp.getEdad(); j -= gap) {
                    personas[j] = personas[j - gap];
                }
                personas[j] = temp;
            }
        }
    }
// Método público que inicia el ordenamiento MergeSort.
    public static void ordenarMergeSort(Persona[] personas) {
        if (personas.length < 2) {
            return;
        }
        mergeSort(personas, 0, personas.length - 1);
    }
    // Método recursivo para dividir el arreglo en mitades
    private static void mergeSort(Persona[] personas, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;
            mergeSort(personas, izquierda, medio);
            mergeSort(personas, medio + 1, derecha);
            merge(personas, izquierda, medio, derecha);//cambia las mitades ordenadas
        }
    }
    // Método que une dos mitades ordenadas en una sola.
    private static void merge(Persona[] personas, int izquierda, int medio, int derecha) {
        // Implementación del merge
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        Persona[] L = new Persona[n1];
        Persona[] R = new Persona[n2];

        System.arraycopy(personas, izquierda, L, 0, n1);
        System.arraycopy(personas, medio + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = izquierda;
        while (i < n1 && j < n2) {
            if (L[i].getEdad() <= R[j].getEdad()) {
                personas[k] = L[i];
                i++;
            } else {
                personas[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            personas[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            personas[k] = R[j];
            j++;
            k++;
        }
    }
    // Método que inicia el ordenamiento QuickSort.
    public static void ordenarQuickSort(Persona[] personas) {
        quickSort(personas, 0, personas.length - 1);
    }
    // Método recursivo que divide el arreglo y ordena alrededor del pivote.
    private static void quickSort(Persona[] personas, int bajo, int alto) {
        if (bajo < alto) {
            int pi = particion(personas, bajo, alto);// Índice del pivote
            quickSort(personas, bajo, pi - 1); // Ordena izquierda del pivote
            quickSort(personas, pi + 1, alto);// Ordena derecha del pivote
        }
    }
    // Método que selecciona un pivote y organiza elementos menores a la izquierda y mayores a la derecha.
    private static int particion(Persona[] personas, int bajo, int alto) {
        Persona pivot = personas[alto];
        int i = (bajo - 1);
        for (int j = bajo; j < alto; j++) {
            if (personas[j].getEdad() < pivot.getEdad()) {
                i++;
                Persona temp = personas[i];
                personas[i] = personas[j];
                personas[j] = temp;
            }
        }
        Persona temp = personas[i + 1];
        personas[i + 1] = personas[alto];
        personas[alto] = temp;
        return i + 1;
    }
}