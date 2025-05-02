package org.example.estructuras.colaPrioridad;


import org.example.objeto.Vehiculo;

public class NodoCola {

    public Vehiculo vehiculo;
    public NodoCola siguiente;

    public NodoCola(Vehiculo  vehiculo) {
        this.vehiculo = vehiculo;
    }



}
