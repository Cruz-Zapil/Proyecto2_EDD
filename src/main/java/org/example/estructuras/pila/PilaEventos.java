package org.example.estructuras.pila;


public class PilaEventos {

    private static class Nodo {
        String evento;
        Nodo siguiente;

        Nodo(String evento) {
            this.evento = evento;
            this.siguiente = null;
        }
    }

    private Nodo cima;
    private int tamaño;

    public PilaEventos() {
        cima = null;
        tamaño = 0;
    }

    // apliar evento nuevo 
    public void apilar(String evento) {
        Nodo nuevo = new Nodo(evento);
        nuevo.siguiente = cima;
        cima = nuevo;
        tamaño++;
    }

    // desapilar evento ultimo 
    public String desapilar() {
        if (estaVacia()) return null;

        String evento = cima.evento;
        cima = cima.siguiente;
        tamaño--;
        return evento;
    }

    // ver el ultimo evento 
    public String cima() {
        return estaVacia() ? null : cima.evento;
    }

    // esta vacia ?
    public boolean estaVacia() {
        return cima == null;
    }

    // ver el tamaño 
    public int tamaño() {
        return tamaño;
    }

    // imprimir eventos 
    public void imprimir() {
        Nodo actual = cima;
        System.out.println("Eventos en la pila:");
        while (actual != null) {
            System.out.println("- " + actual.evento);
            actual = actual.siguiente;
        }
    }
}
