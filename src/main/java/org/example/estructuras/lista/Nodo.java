package org.example.estructuras.lista;

import org.example.objeto.Vehiculo;

public class Nodo {

    private Vehiculo vehiculo;
    private Nodo siguiente;

    // contructor
    public Nodo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.siguiente = null;
    }
    /// gettes and setters
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

}
