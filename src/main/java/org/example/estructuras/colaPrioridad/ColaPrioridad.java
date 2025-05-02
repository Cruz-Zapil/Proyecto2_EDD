package org.example.estructuras.colaPrioridad;
import org.example.objeto.Vehiculo;
import java.util.NoSuchElementException;

public class ColaPrioridad {

    private NodoCola inicio;
    private int longitud;
    private final int longituMax = 7;

    public ColaPrioridad(){
        this.longitud =0;
    }

    /// ingresar vehiculo
    public void encolar(Vehiculo vehiculo){

        if (this.longitud >= longituMax ){
            throw  new IllegalStateException("La avenidad esta llena (maximo de 7)");
        }
        NodoCola nuevoNodo = new NodoCola(vehiculo);

        if (inicio == null || vehiculo.getPrioridad() > inicio.vehiculo.getPrioridad() ){
            nuevoNodo.siguiente = inicio;
            inicio = nuevoNodo;
        }else {
            NodoCola actual = inicio;
            while (actual.siguiente != null &&
                    vehiculo.getPrioridad()<=actual.siguiente.vehiculo.getPrioridad()){
                actual= actual.siguiente;
            }
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
        }
        longitud++;
    }

    public Vehiculo desencolar(){
        if(inicio==null){
            throw  new NoSuchElementException("La avenidad no tiene vehiculos");
        }
        Vehiculo vehiculo= inicio.vehiculo;
        inicio = inicio.siguiente;
        longitud--;
        return  vehiculo;
    }

    public StringBuilder colaVehiculoH1(){
        StringBuilder tmp = new StringBuilder();
        if (inicio!=null){
            tmp.append(inicio.vehiculo.getLogo());
            if (inicio.siguiente!=null){
                tmp.append(inicio.siguiente.vehiculo.getLogo());
            }else {
                tmp.append("⬅");
            }
        }else {
            tmp.append("⬅ ⬅️");
        }
        return tmp;
    }

    public StringBuilder colaVehiculoH2(){
        StringBuilder tmp = new StringBuilder();
        if (inicio!=null){
            tmp.append(inicio.vehiculo.getLogo());
            if (inicio.siguiente!=null){
                tmp.append(inicio.siguiente.vehiculo.getLogo());
            }else {
                tmp.append("➡");
            }
        }else {
            tmp.append("➡ ➡️");
        }
        return tmp;
    }

    public StringBuilder colaVehiculoV(){
        StringBuilder tmp = new StringBuilder();
        if (inicio!=null){
            tmp.append(inicio.vehiculo.getLogo());
        }else {
            tmp.append("-");
        }
        return tmp;
    }

    public StringBuilder colaVehiculoV2(){
        StringBuilder tmp = new StringBuilder();
        if (inicio !=null){
            if (inicio.siguiente!=null){
                tmp.append(inicio.vehiculo.getLogo());
            }else {
                tmp.append("-");
            }
        }else {
            tmp.append("-");
        }

        return tmp;
    }

    public boolean estaVacio(){
        return inicio==null;
    }

    public int getEspacioDisponible(){
        return longituMax-longitud;
    }

    public int getCantCarros(){
        return longitud;
    }



}




