package org.example.estructuras.arbol;



import org.example.estructuras.NodoInterseccion;

public class Arbol {

    private NodoArbol raiz = null;
    private int contador = 0;

    public void insertar(NodoInterseccion interseccion) {
        raiz = insertar(raiz, interseccion);
    }

    private NodoArbol insertar(NodoArbol nodo, NodoInterseccion interseccion) {
        if (nodo == null) return new NodoArbol(interseccion, ++contador);

        if (interseccion.getComplejidad() < nodo.getInterseccion().getComplejidad()) {
            nodo.setIzquierda(insertar(nodo.getIzquierda(), interseccion));
        } else if (interseccion.getComplejidad() > nodo.getInterseccion().getComplejidad()) {
            nodo.setDerecha(insertar(nodo.getDerecha(), interseccion));
        } else {
            nodo.setDerecha(insertar(nodo.getDerecha(), interseccion)); // Inserta duplicados a la derecha
        }

        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())));
        int balance = obtenerBalance(nodo);

        // Rotaciones
        if (balance > 1 && interseccion.getComplejidad() < nodo.getIzquierda().getInterseccion().getComplejidad()) {
            return rotarDerecha(nodo);
        }
        if (balance < -1 && interseccion.getComplejidad() > nodo.getDerecha().getInterseccion().getComplejidad()) {
            return rotarIzquierda(nodo);
        }
        if (balance > 1 && interseccion.getComplejidad() > nodo.getIzquierda().getInterseccion().getComplejidad()) {
            nodo.setIzquierda(rotarIzquierda(nodo.getIzquierda()));
            return rotarDerecha(nodo);
        }
        if (balance < -1 && interseccion.getComplejidad() < nodo.getDerecha().getInterseccion().getComplejidad()) {
            nodo.setDerecha(rotarDerecha(nodo.getDerecha()));
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    private NodoArbol rotarDerecha(NodoArbol y) {
        NodoArbol x = y.getIzquierda();
        NodoArbol T2 = (x != null) ? x.getDerecha() : null;

        if (x == null) return y;

        x.setDerecha(y);
        y.setIzquierda(T2);

        y.setAltura(Math.max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);
        x.setAltura(Math.max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);

        return x;
    }

    private NodoArbol rotarIzquierda(NodoArbol x) {
        NodoArbol y = x.getDerecha();
        NodoArbol T2 = (y != null) ? y.getIzquierda() : null;

        if (y == null) return x;

        y.setIzquierda(x);
        x.setDerecha(T2);

        x.setAltura(Math.max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);
        y.setAltura(Math.max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);

        return y;
    }

    private int altura(NodoArbol nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    private int obtenerBalance(NodoArbol nodo) {
        return (nodo == null) ? 0 : altura(nodo.getIzquierda()) - altura(nodo.getDerecha());
    }

    public NodoInterseccion getRaiz() {
        return (raiz != null) ? raiz.getInterseccion() : null;
    }


    public void imprimirInOrden() {
        System.out.println("Recorrido InOrden:");
        imprimirInOrden(raiz);
        System.out.println();
    }
    
    private void imprimirInOrden(NodoArbol nodo) {
        if (nodo != null) {
            imprimirInOrden(nodo.getIzquierda());
            System.out.print(nodo.getInterseccion().getPosicion() + " ");
            imprimirInOrden(nodo.getDerecha());
        }
    }

    public NodoArbol getNodoRaiz() {
        return raiz;
    }
    
}

