package org.example.objeto;

import org.example.estructuras.Mapa;
import org.example.estructuras.NodoInterseccion;

import java.util.Map;

public class Vehiculo  {

    private String tipo;
    private String placa;
    private String origen;
    private String destino;
    private int prioridad;
    private int tiempoEspera;
    private int nivelUrgencia;

    private int tamanioTabla;
    private String logo;
  //  private Mapa mapa;

    // se usa este constructor para un nuevo vehiculo
    public Vehiculo(String tipo, String placa,String origen, String destino) throws InterruptedException {
        System.out.println(" nuevo vehiculo ");
        this.tipo = tipo;
        this.placa = placa;
        this.origen = origen;
        this.destino = destino;
        this.tamanioTabla = calcularCodigo();
        System.out.println("valor de codigo: "+ this.tamanioTabla);

        gerneradorPrioridad();
       // tiempoEspera();




    }
/// se usa para la lectura de archivo
    public Vehiculo(String tipo, String placa, String origen, String  destino, int prioridad, int tiempoEspera) throws InterruptedException {

        this.tipo = tipo;
        this.placa = placa;
        this.origen = origen;
        this.destino = destino;
        this.prioridad = prioridad;
        this.tiempoEspera = tiempoEspera;
        this.tamanioTabla = calcularCodigo();
        System.out.println("valor de codigo: "+ this.tamanioTabla);
      //  tiempoEspera();




    }

    /// funciones Internas

    private void mover(){

    }

    /// funcion de tiempo

    private void tiempoEspera() throws InterruptedException {
        while (true) { // Bucle infinito (detener manualmente)
            Thread.sleep(1000); // Pausa de 1 segundo (1000 ms)
            tiempoEspera++;
        }
    }




    private void gerneradorPrioridad(){
        /// se genera la prioridad aqui
    }
    /// getters and setters


    public void reiniciarTiempo(){
        tiempoEspera = 0;
    }

    public String getPlaca(){
        return placa;
    }

    public String getLogo(){

        boolean soportaEmiji  = checkEmojiSupport();
        if(soportaEmiji){
            switch (this.logo){
                case "AMBULANCIA":
                    return "🚑";

                case "POLICIA":
                    return "\uD83D\uDE93";

                case "TRASPORTE":
                    return "\uD83D\uDE8C";

                case "PARTICULAR":
                    return "🛻";
            }
        }else{

            switch (this.logo){

                case "AMBULANCIA":
                    return "A";

                case "POLICIA":
                    return "P";

                case "TRASPORTE":
                    return "T";

                case "PARTICULAR":
                    return "$";
            }
        }

        return "O";
    }

    private boolean checkEmojiSupport() {
        try {
            String testEmoji = "\uD83D\uDE97";
            System.out.print(testEmoji);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getPrioridad(){
        return prioridad;
    }

    @Override
    public String toString() {
        return "Vehículo [" + placa + "] (Prioridad: " + prioridad + ")";
    }


    /// get codigo
    public int getCodigo(){
        return tamanioTabla;
    }

    /// calcular el mayor para la tabla:

    // Método para calcular el código basado en origen/destino
    private int calcularCodigo() {
        String nodoMayor = obtenerNodoMayor(origen, destino);
        return convertirNodoACodigo(nodoMayor);
    }

    // Compara dos nodos y devuelve el "mayor" (según fila/columna)
    private String obtenerNodoMayor(String nodo1, String nodo2) {
        int codigoNodo1 = convertirNodoACodigo(nodo1);
        int codigoNodo2 = convertirNodoACodigo(nodo2);
        return (codigoNodo1 > codigoNodo2) ? nodo1 : nodo2;
    }

    // Convierte un nodo (ej: "F6") a un código entero (ej: 6*100 + 6 = 606)
    private int convertirNodoACodigo(String nodo) {
        char fila = nodo.charAt(0);       // Ej: 'F'
        int columna = Integer.parseInt(nodo.substring(1)); // Ej: 6
        int valorFila = fila - 'A' + 1;  // A=1, B=2, ..., F=6
        return  Math.max(valorFila, columna);

    }

}
