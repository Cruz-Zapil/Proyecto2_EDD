package org.example.estructuras.arbol;

import org.example.estructuras.NodoInterseccion;

public class ArbolAVL {

    private NodoInterseccionArbol raiz = null;  // es la interseccion
    int cont =0;

    NodoInterseccionArbol insertar (NodoInterseccionArbol nodo, NodoInterseccion interseccion){

        if ( nodo == null) return  new NodoInterseccionArbol(interseccion, ++cont);

        if (interseccion.getComplejidad() < nodo.interseccion.getComplejidad()){
            nodo.izquierda = insertar(nodo.izquierda , interseccion);

        }else if (interseccion.getComplejidad() > nodo.interseccion.getComplejidad()){
            nodo.derecha = insertar(nodo.derecha , interseccion);

        }else {
            return nodo;
        }

        /// chequear altura:

        nodo.altura = 1+ Math.max( altura(nodo.izquierda) , altura(nodo.derecha));

        int balance = obtenerBalance (nodo);

        // de izquieda a derecha

        if (balance>1 && interseccion.getComplejidad() < nodo.izquierda.interseccion.getComplejidad() ){
            return rotarDerecha(nodo);
        }

        /// de derecha a derecha

        if (balance < -1  && interseccion.getComplejidad() > nodo.derecha.interseccion.getComplejidad()){
            return rotarIzquierda(nodo);
        }

        /// de izquierda a derecha
        if (balance > 1 && interseccion.getComplejidad() > nodo.derecha.interseccion.getComplejidad()){
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarIzquierda (nodo);
        }

        /// de dercha a izquieda

        if (balance < -1 && interseccion.getComplejidad() < nodo.derecha.interseccion.getComplejidad()){
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);

        }
        return  nodo;
    }

    NodoInterseccionArbol rotarDerecha(NodoInterseccionArbol y ){
        NodoInterseccionArbol x= y.izquierda;
        NodoInterseccionArbol T2 = x.derecha;

        x.derecha = y;
        y.izquierda = T2;

        y.altura = Math.max(altura(y.izquierda), altura(y.derecha) ) +1;
        x.altura = Math.max(altura(x.izquierda), altura(x.derecha) ) +1;

        return x;

    }

    NodoInterseccionArbol rotarIzquierda(NodoInterseccionArbol x ){
        NodoInterseccionArbol y= x.derecha;
        NodoInterseccionArbol T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;

        x.altura= Math.max(altura(x.izquierda), altura(x.derecha))+1;
        y.altura = Math.max(altura(y.izquierda), altura(y.derecha))+1;

        return y;

    }

    int altura(NodoInterseccionArbol nodo) {
        if (nodo == null) return 0;
        return nodo.altura;
    }


    int obtenerBalance(NodoInterseccionArbol nodo) {
        if (nodo == null) return 0;
        return altura(nodo.izquierda)- altura(nodo.derecha);

    }

    public void insertar(NodoInterseccion interseccion){
        raiz = insertar(raiz, interseccion);

    }

}
