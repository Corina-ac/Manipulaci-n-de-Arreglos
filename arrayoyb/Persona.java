// Declaramos el paquete donde se encuentra esta clase.
// Esto permite que esté organizada junto a las demás clases del proyecto.
package com.mycompany.arrayoyb;
// Definición de la clase Persona.
// Esta clase representa a una persona con dos atributos: nombre y edad.
public class Persona {
    // Atributo privado que guarda el nombre de la persona.
    private String nombre;
    // Atributo privado que guarda la edad de la persona.
    private int edad;
    // Constructor de la clase. Se utiliza para crear objetos Persona asignando nombre y edad al instanciar.
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    // Método getter para obtener el nombre de la persona.
    public String getNombre() {
        return nombre;
    }
    // Método getter para obtener la edad de la persona.
    public int getEdad() {
        return edad;
    }
    // Método toString sobrescrito para mostrar la información de la persona como texto.
    // Esto es útil al imprimir objetos Persona en consola o listas.
    @Override
    public String toString() {
        return "Persona{nombre='" + nombre + "', edad=" + edad + "}";
    }
}
