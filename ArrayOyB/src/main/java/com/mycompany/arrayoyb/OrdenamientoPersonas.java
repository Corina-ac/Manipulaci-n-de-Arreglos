package com.mycompany.arrayoyb;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdenamientoPersonas {
    public static void ordenarBurbuja(List<Persona> personas) {
        int n = personas.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (personas.get(j).getEdad() > personas.get(j + 1).getEdad()) {
                    Collections.swap(personas, j, j + 1);
                }
            }
        }
    }

    public static void ordenarSeleccion(List<Persona> personas) {
        int n = personas.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (personas.get(j).getEdad() < personas.get(minIdx).getEdad()) {
                    minIdx = j;
                }
            }
            Collections.swap(personas, i, minIdx);
        }
    }

    public static void ordenarInsercion(List<Persona> personas) {
        int n = personas.size();
        for (int i = 1; i < n; ++i) {
            Persona key = personas.get(i);
            int j = i - 1;
            while (j >= 0 && personas.get(j).getEdad() > key.getEdad()) {
                personas.set(j + 1, personas.get(j));
                j--;
            }
            personas.set(j + 1, key);
        }
    }

    public static void ordenarShell(List<Persona> personas) {
        int n = personas.size();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Persona temp = personas.get(i);
                int j = i;
                while (j >= gap && personas.get(j - gap).getEdad() > temp.getEdad()) {
                    personas.set(j, personas.get(j - gap));
                    j -= gap;
                }
                personas.set(j, temp);
            }
        }
    }

    public static void ordenarMergeSort(List<Persona> personas) {
        if (personas.size() <= 1) return;
        List<Persona> izquierda = new ArrayList<>(personas.subList(0, personas.size() / 2));
        List<Persona> derecha = new ArrayList<>(personas.subList(personas.size() / 2, personas.size()));
        
        ordenarMergeSort(izquierda);
        ordenarMergeSort(derecha);
        
        merge(personas, izquierda, derecha);
    }

    private static void merge(List<Persona> resultado, List<Persona> izquierda, List<Persona> derecha) {
        int i = 0, j = 0, k = 0;
        
        while (i < izquierda.size() && j < derecha.size()) {
            if (izquierda.get(i).getEdad() <= derecha.get(j).getEdad()) {
                resultado.set(k++, izquierda.get(i++));
            } else {
                resultado.set(k++, derecha.get(j++));
            }
        }
        
        while (i < izquierda.size()) {
            resultado.set(k++, izquierda.get(i++));
        }
        
        while (j < derecha.size()) {
            resultado.set(k++, derecha.get(j++));
        }
    }

    public static void ordenarQuickSort(List<Persona> personas) {
        quickSort(personas, 0, personas.size() - 1);
    }

    private static void quickSort(List<Persona> personas, int bajo, int alto) {
        if (bajo < alto) {
            int pi = particion(personas, bajo, alto);
            quickSort(personas, bajo, pi - 1);
            quickSort(personas, pi + 1, alto);
        }
    }

    private static int particion(List<Persona> personas, int bajo, int alto) {
        Persona pivot = personas.get(alto);
        int i = bajo - 1;
        for (int j = bajo; j < alto; j++) {
            if (personas.get(j).getEdad() < pivot.getEdad()) {
                i++;
                Collections.swap(personas, i, j);
            }
        }
        Collections.swap(personas, i + 1, alto);
        return i + 1;
    }
}