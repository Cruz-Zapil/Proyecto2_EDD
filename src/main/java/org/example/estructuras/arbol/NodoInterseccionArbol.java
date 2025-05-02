package org.example.estructuras.arbol;

import org.example.estructuras.NodoInterseccion;

public class NodoInterseccionArbol {

    NodoInterseccion interseccion;
    NodoInterseccionArbol izquierda;
    NodoInterseccionArbol derecha;
    int ordenIngreso;
    int altura;

    NodoInterseccionArbol(NodoInterseccion interseccion, int orden){
        this.interseccion = interseccion;
        this.ordenIngreso = orden;
        this.altura=1;
        this.izquierda = null;
        this.derecha = null;

    }

}
