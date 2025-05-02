package org.example.estructuras;

import org.example.estructuras.arbol.ArbolAVL;

public class Mapa {

    private  int tamanio =0;
    private NodoInterseccion inicio;

    public Mapa(int tamanio) {
        this.tamanio = tamanio;
        construirMapaCuadrado();
    }

    private void construirMapaCuadrado() {

        NodoInterseccion filaAnterior = null;

        for (int i = 0; i < tamanio; i++) {
            NodoInterseccion columnaAnterior = null;
            NodoInterseccion nodoActualFila = null;

            for (int j = 0; j < tamanio; j++) {
                String newFila = String.valueOf((char) ('A' +i));
                        NodoInterseccion nuevoNodo = new NodoInterseccion(newFila, j);

                if (columnaAnterior != null) {
                    columnaAnterior.setDerecha(nuevoNodo);
                    nuevoNodo.setIzquierda(columnaAnterior);
                }

                if (filaAnterior != null) {
                    NodoInterseccion nodoArriba = filaAnterior;
                    for (int k = 0; k < j; k++) {
                        nodoArriba = nodoArriba.getDerecha();
                    }
                    nodoArriba.setAbajo(nuevoNodo);
                    nuevoNodo.setArriba(nodoArriba);
                }

                if (j == 0) {
                    if (inicio == null) {
                        inicio = nuevoNodo;
                    }
                    nodoActualFila = nuevoNodo;
                }
                columnaAnterior = nuevoNodo;
            }
            filaAnterior = nodoActualFila;
        }
    }

    public void agregarFilaYColumna() {
        tamanio++; // Aumentamos el tamaño del mapa (será NxN)

        // 1. Agregar una nueva columna al final de cada fila existente
        NodoInterseccion filaActual = inicio;
        while (filaActual != null) {
            NodoInterseccion columnaActual = filaActual;
            while (columnaActual.getDerecha() != null) {
                columnaActual = columnaActual.getDerecha();
            }

            // Crear nuevo nodo de columna (misma fila, nueva columna)
            String filaLetra = filaActual.getFila(); // la letra de la fila actual (A, B, C...)
            int nuevaColumna = tamanio - 1;
            NodoInterseccion nuevoNodoColumna = new NodoInterseccion(filaLetra, nuevaColumna);

            // Conectar
            columnaActual.setDerecha(nuevoNodoColumna);
            nuevoNodoColumna.setIzquierda(columnaActual);

            // Mover a la siguiente fila
            filaActual = filaActual.getAbajo();
        }

        // 2. Agregar una nueva fila completa (abajo del todo)
        NodoInterseccion ultimaFila = inicio;
        while (ultimaFila.getAbajo() != null) {
            ultimaFila = ultimaFila.getAbajo();
        }

        // Crear la nueva fila
        NodoInterseccion nuevoInicioFila = null;
        NodoInterseccion anteriorNuevo = null;
        NodoInterseccion columnaArriba = ultimaFila;

        String nuevaFilaLetra = String.valueOf((char) ('A' + (tamanio - 1))); // Ej: 'E' si es la fila 5

        for (int col = 0; col < tamanio; col++) {
            NodoInterseccion nuevoNodoFila = new NodoInterseccion(nuevaFilaLetra, col);

            // Conectar horizontalmente
            if (anteriorNuevo != null) {
                anteriorNuevo.setDerecha(nuevoNodoFila);
                nuevoNodoFila.setIzquierda(anteriorNuevo);
            }

            // Conectar verticalmente
            if (columnaArriba != null) {
                columnaArriba.setAbajo(nuevoNodoFila);
                nuevoNodoFila.setArriba(columnaArriba);
                columnaArriba = columnaArriba.getDerecha(); // Mover al siguiente de arriba
            }
            if (nuevoInicioFila == null) {
                nuevoInicioFila = nuevoNodoFila;
            }
            anteriorNuevo = nuevoNodoFila;
        }
    }


    public void imprimirMapa(){
        NodoInterseccion filaActual = inicio;

        while (filaActual != null){
            int contColum =0;
            NodoInterseccion columnaActual= filaActual;

            // Primera línea del nodo: [  ] [  ]  [  ] ...
            StringBuilder lineaSuperior = new StringBuilder();
            // Segunda línea del nodo: [  ]  [  ]  [  ] ...
            StringBuilder lineaInferior = new StringBuilder();

            StringBuilder linea1 = new StringBuilder();
            StringBuilder linea2 = new StringBuilder();

            while (columnaActual != null) {

                /// linea superior
                lineaSuperior.append("[").append(columnaActual.getFila()).append(" ]");
                /// la linea inferior
                if (contColum<10){
                    lineaInferior.append("["+columnaActual.getColumna()+" ]");
                }else{
                    lineaInferior.append("[").append(columnaActual.getColumna()).append("]");
                }


                if (columnaActual.getDerecha() !=null){
                    lineaSuperior.append(columnaActual.getOeste().colaVehiculoH1());
                    lineaInferior.append(columnaActual.getDerecha().getEste().colaVehiculoH2());
                }

                if (columnaActual.getAbajo() !=null){
                       linea1.append(" "+columnaActual.getNorte().colaVehiculoV());
                       linea2.append(" "+columnaActual.getNorte().colaVehiculoV2());

                       linea1.append(columnaActual.getAbajo().getSur().colaVehiculoV());
                       linea2.append(columnaActual.getAbajo().getSur().colaVehiculoV2());
                   }

                   linea1.append("    ");
                   linea2.append("    ");
                   contColum++;
                columnaActual = columnaActual.getDerecha();
            }

            /// imprimir las lineas
            System.out.println(lineaSuperior);
            System.out.println(lineaInferior);
            System.out.println(linea1);
            System.out.println(linea2);
            filaActual = filaActual.getAbajo();
            }
        }

     /// obtner nodo:

    public NodoInterseccion getNodo(String fila, int columna) {
        NodoInterseccion filaActual = inicio;

        while (filaActual != null) {
            if (filaActual.getFila().equals(fila)) {
                NodoInterseccion columnaActual = filaActual;

                while (columnaActual != null) {
                    if (columnaActual.getColumna() == columna) {
                        return columnaActual;
                    }
                    columnaActual = columnaActual.getDerecha();
                }
            }
            filaActual = filaActual.getAbajo();
        }
        return null;
    }

    public void ordenarEnArbol(ArbolAVL arbolAVL) {
        NodoInterseccion filaActual = inicio;

        while (filaActual != null) {
            NodoInterseccion columnaActual = filaActual;

            while (columnaActual != null) {
                // ingresar e en el arbol Alv
                arbolAVL.insertar(columnaActual);

                columnaActual = columnaActual.getDerecha();
            }

            filaActual = filaActual.getAbajo();
        }
    }






}

