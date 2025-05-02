package org.example.estructuras;

public class NodoInterseccion {

    private String fila;  //  letras  A-Z
    private int columna;
    private ColaPrioridad norte, sur, este, oeste;
    private NodoInterseccion arriba, abajo, izquierda, derecha;

    public NodoInterseccion(String fila, int columna) {
        this.fila = fila;
        this.columna =  columna;
        this.norte = new ColaPrioridad();
        this.sur = new ColaPrioridad();
        this.este = new ColaPrioridad();
        this.oeste = new ColaPrioridad();
        this.arriba = this.abajo = this.izquierda = this.derecha = null;

    }

    /// funciones
    /// obtener objetos o info de la cola norte
    /// verificar espacio disponible en carril

    public int getEspacioCarril(String direccion){

        if (direccion.equals("norte")){
           return norte.getEspacioDisponible();
        }else if (direccion.equals("sur")){
            return sur.getEspacioDisponible();
        }else if (direccion.equals("este")){
            return este.getEspacioDisponible();
        }else if (direccion.equals("oeste")) {
            return oeste.getEspacioDisponible();
        }
        return 0;
    }

    /// ingresar objetos o info de la cola norte



    /// obtener objetos o info de la cola sur

    /// ingresar objetos o info de la cola sur



    /// obtener objetos o info de la cola este

    /// ingresar objetos o info de la cola este



    /// obtener objetos o info de la cola oeste


    /// ingresar objetos o info de la cola oeste

    /// calcular complejiodad del insterseccion

    public int getComplejidad(){

        return 0;
    }

    /// getters and setters
    public String getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    // obtener colas de prioridad

    public ColaPrioridad getNorte() {
        return norte;
    }
    public ColaPrioridad getSur() {
        return sur;
    }
    public ColaPrioridad getEste() {
        return este;
    }
    public ColaPrioridad getOeste() {
        return oeste;
    }


    /// entrelazar nodos
    public NodoInterseccion getArriba() {
        return arriba;
    }
    public NodoInterseccion getAbajo() {
        return abajo;
    }
    public NodoInterseccion getIzquierda() {
        return izquierda;
    }
    public NodoInterseccion getDerecha() {
        return derecha;
    }
    public void setArriba(NodoInterseccion arriba){
        this.arriba = arriba;
    }
    public void setAbajo(NodoInterseccion abajo){
        this.abajo = abajo;
    }
    public void setIzquierda(NodoInterseccion izquierda){
        this.izquierda = izquierda;
    }
    public void setDerecha(NodoInterseccion derecha){
        this.derecha = derecha;
    }
}
