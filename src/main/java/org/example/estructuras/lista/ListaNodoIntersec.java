package org.example.estructuras.lista;
import org.example.estructuras.NodoInterseccion;

public class ListaNodoIntersec {


        private NodoIntersec cabeza;

    /// ingresar vehiculo ordenado por posicion

    public void ingresarOrdenado(NodoInterseccion interseccion) {
        NodoIntersec nuevoNodo = new NodoIntersec(interseccion);

        //  lista vacia y  o nuevo vehículo tiene código menor al primero
        if (cabeza == null || interseccion.getComplejidad() > cabeza.getInterseccion().getComplejidad()) {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
        } else {
            // ingresar
            NodoIntersec actual = cabeza;
            while (actual.getSiguiente() != null &&
                    actual.getSiguiente().getInterseccion().getComplejidad() < interseccion.getComplejidad()) {
                actual = actual.getSiguiente();
            }
            nuevoNodo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevoNodo);
        }
    }

    //   imprimir la lista
    public void imprimirLista() {
        NodoIntersec actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getInterseccion());
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
        NodoIntersec actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    public NodoInterseccion getPrimerInterseccion() {
        if (cabeza == null) {
            return null; // la lista esta vacia
        }
        NodoInterseccion interseccion = cabeza.getInterseccion(); // obtenemos el primero
        cabeza = cabeza.getSiguiente(); // obtenemos el siguente
        return interseccion;
    }
    
}
