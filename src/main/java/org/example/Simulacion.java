package org.example;

import org.example.estructuras.Mapa;
import org.example.estructuras.NodoInterseccion;
import org.example.estructuras.arbol.ArbolAVL;
import org.example.estructuras.hash.TablaHashPlaca;
import org.example.estructuras.lista.ListaNewVehiculo;
import org.example.objeto.Vehiculo;
import org.example.util.utils;

import java.util.Scanner;

public class Simulacion {

    private Scanner scanner = new Scanner(System.in);
    private ListaNewVehiculo listaVehiculos;
    private TablaHashPlaca tabla = new TablaHashPlaca();
    private Mapa mapa;
    private ArbolAVL arbolAVL = new ArbolAVL();


    public void leerArchivo(){

    }

    public void ingresarVehiculo() {

        int contadorVehiculos = 0;
        int opcion;
        listaVehiculos = new ListaNewVehiculo();

        do {
            System.out.println("\n=== Menú de Vehículos ===");
            System.out.println("1. Ingresar nuevo vehículo");

            if (contadorVehiculos >= 5) {
                System.out.println("2. Comenzar simulación");
            }

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:

                    ingresarNuevoVehiculo();

                    contadorVehiculos++;

                    System.out.println("Total vehículos ingresados: " + contadorVehiculos);

                    break;

                case 2:
                    if (contadorVehiculos >= 5) {
                        System.out.println("\nIniciando simulación con " + contadorVehiculos + " vehículos...");
                        ingresarVehiculosMapa();
                        return; // detener el metodo despues de la simulacion
                    } else {
                        System.out.println("Debe ingresar al menos 5 vehículo antes de comenzar.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (true);
    }


    public void ingresarVehiculosMapa() {

        int tamanioTabla = listaVehiculos.getTamanioTabla();
        System.out.println(" El tamanio de la tabla seria: " + tamanioTabla);
        /// creamos la tabla con el nodo mayor.
         mapa = new Mapa(tamanioTabla);

         Vehiculo tmpVehiculo = listaVehiculos.getPrimerVehiculo();
         while (tmpVehiculo != null){

             String nodoOrigen = tmpVehiculo.getOrigen();
             String fila = utils.extraerFila(nodoOrigen);
             int columna = utils.extraerColumna(nodoOrigen);

             NodoInterseccion  nodo = mapa.getNodo(fila, columna);

             if (nodo != null){
               nodo.getColaDisponible().encolar(tmpVehiculo);

             }else{
                 System.out.println("Posicion fuera de rango no se puede ingresar el vehiculo");
             }
             tmpVehiculo = listaVehiculos.getPrimerVehiculo();
         }

         /// empezar a moverç
        guardarNodosAvl();
        iniciarSimulacion();

    }


    public void iniciarSimulacion(){

        int tmpOption =0;
        do {
            mapa.imprimirMapa();

            System.out.println();
            System.out.println();

            System.out.println("Seleccione una opcion: ");
            System.out.println("1) Ingresar nuevo vehiculo ");
            System.out.println("2) Mover trafico");
            System.out.println("3) Ver estado de trafico");
            System.out.println("4) Terminar Simulacion");

            tmpOption = scanner.nextInt();
            scanner.nextLine();
            switch (tmpOption) {
                case 1:
                    ingresarNuevoVehiculo();
                    break;
                case 2:
                    moverTrafico();
                    break;
                case 3:
                    verEstadoTrafico();
                    break;
                case 4:
                    verReporte();
                    break;
                default:
                    System.out.println(" Ingrese una opcion valida ");
            }

        }while (true);

    }

    /// mover trafico primero los mas congestionados
    public void moverTrafico(){

    }

    /// guardar nodos en el arbol avl
    public void guardarNodosAvl(){
        mapa.ordenarEnArbol(arbolAVL);
    }

    public void verEstadoTrafico(){

    }

    public void verReporte(){

    }

    public void ingresarNuevoVehiculo()  {

        System.out.println("\nIngresando nuevo vehículo...");
        System.out.print("Ingrese tipo (Ambulancia/Policia/Transport/Particular): ");
        String tipo = scanner.nextLine();

        System.out.print("Ingrese la placa: ");
        String placa = scanner.nextLine();

        System.out.print("Ingrese nodo origen (ej. A1): ");
        String origen = scanner.nextLine();

        System.out.print("Ingrese nodo destino (ej. F6): ");
        String destino = scanner.nextLine();

        // Crear y almacenar el vehículo

        Vehiculo nuevoVehiculo = new Vehiculo(tipo, placa ,origen, destino );

        listaVehiculos.ingresarOrdenado(nuevoVehiculo);
        tabla.insertar(nuevoVehiculo);
        System.out.println("¡Vehículo agregado exitosamente!");


    }






}
