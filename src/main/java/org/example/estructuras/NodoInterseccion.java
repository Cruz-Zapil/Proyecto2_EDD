package org.example.estructuras;

import org.example.estructuras.colaPrioridad.ColaPrioridad;
import org.example.estructuras.pila.PilaEventos;
import org.example.objeto.Vehiculo;

import java.util.Random;

public class NodoInterseccion {

    private String fila;  //  letras  A-Z
    private int columna;
    private ColaPrioridad norte, sur, este, oeste, destino;
    private NodoInterseccion arriba, abajo, izquierda, derecha;
    private int complejidad =0;
    private int tipoInterseccion;
    /// 1 semaforo, 2 rotonda, 3 cruce, 4 bloqueo
    private String tipo="";
    private int catidadVehiculos=0;
    private int cantVehiculosAtendidos=0;

    /// pila de evento de este interseccion 
    private PilaEventos pilaEventos;

    private boolean pasoAbierto=false;
    private int tiempo=1;

    public NodoInterseccion(String fila, int columna) {
        this.fila = fila;
        this.columna =  columna;
        this.norte = new ColaPrioridad();
        this.sur = new ColaPrioridad();
        this.este = new ColaPrioridad();
        this.oeste = new ColaPrioridad();
        this.destino = new ColaPrioridad();
        this.arriba = this.abajo = this.izquierda = this.derecha = null;
        this.pilaEventos = new PilaEventos();

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

    public void actulizarTiempo(){
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
            this.tipo = "Semaforo";
        }else if (numero == 2 || numero==5) {
            /// rotonda
            this.tipoInterseccion = 2;
            this.tipo = "Rotonda";
        }else if ( numero == 3){
            /// cruce
            this.tipoInterseccion = 3;
            this.tipo = "Cruce";
        }else {
            //  bloqueo
            this.tipoInterseccion = 4;
            this.tipo = "Bloqueo";
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


    ///// sumulacion de trafico:
    /////
    /////

    public boolean moverVehiculos() {
      boolean algunMovimiento =  moverDesdeCola(norte) ||
        moverDesdeCola(sur)||
        moverDesdeCola(este)||
        moverDesdeCola(oeste);

        return algunMovimiento;

    }

    private boolean moverDesdeCola(ColaPrioridad cola) {

        /// verificar si se movio un vehiculo
        boolean vehiculoMovido = false;

        if (!cola.estaVacio()) {
            Vehiculo v = cola.desencolar(); // obtener vehiculo

            if (v != null) {
                if (esDestino(v)) {
                    System.out.println(" **************************+");
                    System.out.println("Llego a su destino: en " + this.fila + this.columna);
                    System.out.println(" **************************+");
                     // guardarlo en cola de destino
                    destino.encolar(v);
                } else {

                    /// guardar evento en la pila
                    /// guardar vehiculo en la pila
                    pilaEventos.apilar("El vehiculo:" +v.getPlaca() +" paso por: "+ this.fila + this.columna + " con destino: " + v.getDestinoFila() + v.getDestinoColumna());
                

                    moverVehiculoHaciaDireccion(v);
                    // Aquí podrías volver a encolarlo si aún no llega
                    cantVehiculosAtendidos++;



                }
            }

            vehiculoMovido = true;
        }

        return vehiculoMovido;
    }


    private void moverVehiculoHaciaDireccion(Vehiculo vehiculo) {
        char filaActual = this.fila.charAt(0);
        char filaDestino = vehiculo.getDestinoFila().charAt(0);
        int columnaDestino = vehiculo.getDestinoColumna();

        if (filaDestino > filaActual && this.abajo != null) {
            moverVehiculoHacia(this.abajo, vehiculo, "arriba");
        } else if (filaDestino < filaActual && this.arriba != null) {
            moverVehiculoHacia(this.arriba, vehiculo, "abajo");
        } else if (columnaDestino > this.columna && this.derecha != null) {
            moverVehiculoHacia(this.derecha, vehiculo, "izquierda");
        } else if (columnaDestino < this.columna && this.izquierda != null) {
            moverVehiculoHacia(this.izquierda, vehiculo, "derecha");
        }
    }

    private void moverVehiculoHacia(NodoInterseccion siguiente, Vehiculo vehiculo, String direccionOrigen) {
        siguiente.recibirVehiculo(vehiculo, direccionOrigen);
    }


    /// chequer si el auto llego a su destino:
    private boolean esDestino(Vehiculo vehiculo) {
        String filaDestino = vehiculo.getDestinoFila();  //  letras
        int columnaDestino = vehiculo.getDestinoColumna(); //  numero de columna

        return this.fila.equals(filaDestino) && this.columna == columnaDestino;
    }

    public void recibirVehiculo(Vehiculo vehiculo, String direccionOrigen) {
        switch (direccionOrigen) {
            case "arriba":
                this.sur.encolar(vehiculo);  // vino de arriba, entra por el sur
                break;
            case "abajo":
                this.norte.encolar(vehiculo); // vino de abajo, entra por el norte
                break;
            case "izquierda":
                this.este.encolar(vehiculo);  // vino de la izquierda, entra por el este
                break;
            case "derecha":
                this.oeste.encolar(vehiculo); // vino de la derecha, entra por el oeste
                break;
        }
    }
    ///// sumulacion de trafico:
    /////
    /// 

    /// mostrar eventos
    
    public void mostrarEventos() {

        if(cantVehiculosAtendidos != 0){
            System.out.println("--------------------");
        System.out.println("Eventos en la pila de " + this.fila + this.columna + ":");
        System.out.println("Vehiculos atendidos: " + cantVehiculosAtendidos);
        pilaEventos.imprimir();

        System.out.println("--------------------");

        }

   
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

        /// getters and setters
        public String getFila() {
            return fila;
        }
    
        public int getColumna() {
            return columna;
        }
    
        public String getPosicion() {
            return fila + String.valueOf(columna);
        }

    public String getTipo(){
        return this.tipo;
    }

    public int getTipoInterseccion() {
        return tipoInterseccion;
    }

    public int getCantVehiculos() {
        return this.norte.getCantCarros() +
                this.sur.getCantCarros() +
                this.este.getCantCarros() +
                this.oeste.getCantCarros();
    }


        

}
