package com.mycompany.arrayoyb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusquedaPersonas {
    // Búsqueda por edad (devuelve lista de personas con esa edad)
    public static List<Persona> busquedaPorEdad(HashMap<Integer, Persona> personas, int edad) {
        List<Persona> resultados = new ArrayList<>();
        for (Persona persona : personas.values()) {
            if (persona.getEdad() == edad) {
                resultados.add(persona);
            }
        }
        return resultados;
    }

    // Búsqueda por ID (muy eficiente con HashMap)
    public static Persona busquedaPorId(HashMap<Integer, Persona> personas, int id) {
        return personas.get(id);
    }

    static List<Persona> busquedaLineal(List<Persona> personas, int edad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
