package org.example.estructuras.lista;

import org.example.estructuras.NodoInterseccion;


public class NodoIntersec {

    private NodoInterseccion interseccion;
    private NodoIntersec siguiente;

     // contructor
    public NodoIntersec(NodoInterseccion interseccion) {
        this.interseccion = interseccion;
        this.siguiente = null;
    }
    /// gettes and setters
    public NodoInterseccion getInterseccion() {
        return interseccion;
    }
    public void setVehiculo(NodoInterseccion interseccion) {
        this.interseccion = interseccion;
    }

    public NodoIntersec getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoIntersec siguiente) {
        this.siguiente = siguiente;
    }
    


}
