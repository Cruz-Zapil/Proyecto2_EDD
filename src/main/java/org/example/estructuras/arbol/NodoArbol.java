package org.example.estructuras.arbol;

import org.example.estructuras.NodoInterseccion;

public class NodoArbol {

    private NodoInterseccion interseccion;
    private NodoArbol izquierda;
    private NodoArbol derecha;
    private int ordenIngreso;
    private int altura;

    NodoArbol(NodoInterseccion interseccion, int orden){
        this.interseccion = interseccion;
        this.ordenIngreso = orden;
        this.altura=1;
        this.izquierda = null;
        this.derecha = null;

    }

    public NodoInterseccion getInterseccion() {
        return interseccion;
    }
    public void setInterseccion(NodoInterseccion interseccion) {
        this.interseccion = interseccion;
    }
    public NodoArbol getIzquierda() {
        return izquierda;
    }
    public void setIzquierda(NodoArbol izquierda) {
        this.izquierda = izquierda;
    }
    public NodoArbol getDerecha() {
        return derecha;
    }
    public void setDerecha(NodoArbol derecha) {
        this.derecha = derecha;
    }

    public int getOrdenIngreso() {
        return ordenIngreso;
    }
    public void setOrdenIngreso(int ordenIngreso) {
        this.ordenIngreso = ordenIngreso;
    }
    public int getAltura() {
        return altura;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }


}
