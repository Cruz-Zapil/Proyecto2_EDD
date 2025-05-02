package org.example.estructuras;

import org.example.estructuras.colaPrioridad.ColaPrioridad;

import java.util.Random;

public class NodoInterseccion {

    private String fila;  //  letras  A-Z
    private int columna;
    private ColaPrioridad norte, sur, este, oeste;
    private NodoInterseccion arriba, abajo, izquierda, derecha;
    private int complejidad =0;
    private int tipoInterseccion;

    private boolean pasoAbierto=false;
    private int tiempo=1;

    public NodoInterseccion(String fila, int columna) {
        this.fila = fila;
        this.columna =  columna;
        this.norte = new ColaPrioridad();
        this.sur = new ColaPrioridad();
        this.este = new ColaPrioridad();
        this.oeste = new ColaPrioridad();
        this.arriba = this.abajo = this.izquierda = this.derecha = null;

        this.tiempo = this.tipoInterseccion;

        calcularTipo();
        calcularComplejidad();

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

    /// calcular timpo
    /// cada enter es un "Timepo" menos

    public void enter(){
        if(tiempo > 1){
            tiempo--;
            pasoAbierto = false;
        }else {
            pasoAbierto=true;
            this.tiempo = tipoInterseccion;
        }
    }

    /// obtener si el cruce esta libre:
    public boolean getPasoAbierto(){
        return pasoAbierto;
    }

    /// calcular complejidad del nodo
    public void calcularComplejidad(){
        int tmp = norte.getCantCarros() + sur.getCantCarros()+ este.getCantCarros() + oeste.getCantCarros();
        complejidad = tmp + tipoInterseccion;
    }

    /// asignarle un tipo de instesecion
    public void calcularTipo(){
        Random rand = new Random();
        int numero = rand.nextInt(5) + 1;

        if (numero == 1 || numero == 6){
            /// semaforo
            this.tipoInterseccion = 1;
        }else if (numero == 2 || numero==5) {
            /// rotonda
            this.tipoInterseccion = 2;
        }else if ( numero == 3){
            /// cruce
            this.tipoInterseccion = 3;
        }else {
            //  bloqueo
            this.tipoInterseccion = 4;
        }
    }

    /// obtener la complejidad
    public int getComplejidad(){
        return this.complejidad;
    }

    /// obtener cola disponible:

    public ColaPrioridad getColaDisponible(){
        if ( norte.getEspacioDisponible() != 0 ){
            return  norte;
        }else if (sur.getEspacioDisponible() !=0) {
            return sur;
        }else if (este.getEspacioDisponible() !=0 ){
            return este;
        }else if (oeste.getEspacioDisponible() !=0 ){
            return oeste;
        }
        return null;
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
