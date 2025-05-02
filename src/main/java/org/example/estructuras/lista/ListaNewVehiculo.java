package org.example.estructuras.lista;

import org.example.objeto.Vehiculo;

public class ListaNewVehiculo {

    private Nodo cabeza;

    /// ingresar vehiculo ordenado por posicion

    public void ingresarOrdenado(Vehiculo vehiculo) {
        System.out.println(" ingresar vehiculo ");
        Nodo nuevoNodo = new Nodo(vehiculo);

        //  lista vacia y  o nuevo vehículo tiene código menor al primero
        if (cabeza == null || vehiculo.getCodigo() < cabeza.getVehiculo().getCodigo()) {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
        } else {
            // ingresar
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null &&
                    actual.getSiguiente().getVehiculo().getCodigo() < vehiculo.getCodigo()) {
                actual = actual.getSiguiente();
            }
            nuevoNodo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevoNodo);
        }
    }

    //   imprimir la lista
    public void imprimirLista() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getVehiculo());
            actual = actual.getSiguiente();
        }
    }

    // verificar si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }

    // obtener el tamaño de la lista

    public int tamaño() {
        int contador = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }



}
