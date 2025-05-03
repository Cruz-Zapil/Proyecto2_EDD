package org.example;

import org.example.estructuras.Mapa;
import org.example.estructuras.NodoInterseccion;
import org.example.estructuras.arbol.Arbol;
import org.example.estructuras.hash.TablaHashPlaca;
import org.example.estructuras.lista.ListaNewVehiculo;
import org.example.estructuras.lista.ListaNodoIntersec;
import org.example.objeto.Vehiculo;
import org.example.util.LectorCSV;
import org.example.util.VisualizadorAVL;
import org.example.util.utils;

import java.util.Scanner;

public class Simulacion {

    private Scanner scanner = new Scanner(System.in);
    private ListaNewVehiculo listaVehiculos;
    private TablaHashPlaca tabla = new TablaHashPlaca();
    private Mapa mapa;
    private Arbol arbolAVL;
    private ListaNodoIntersec listaNodosInt = new ListaNodoIntersec();

    public void leerArchivo() throws Exception {

        listaVehiculos = LectorCSV.leerCSV();
        // creamos mapa primero:
        crearMapa();

        // ingresar vehiculos A LA tabla 

        ingresarVehiculosMapa();
        /// guardar el registro de los nodos
        guardarNodosAvl();
        /// guardar nodos en la lista
        guardarNodosLista();
        /// iniciar la simulacion
        iniciarSimulacion();

    }

    public void ingresarVehiculoManual() throws Exception {

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
                        // creamos mapa primero:
                        crearMapa();
                        ingresarVehiculosMapa();
                        /// guardar el registro de los nodos
                        guardarNodosAvl();
                        /// guardar nodos en la lista
                        guardarNodosLista();
                        /// iniciar la simulacion
                        iniciarSimulacion();

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

        Vehiculo tmpVehiculo = listaVehiculos.getPrimerVehiculo();
        while (tmpVehiculo != null) {

            /// ingresamos vehiculo a la tabla hash
            tabla.insertar(tmpVehiculo); 

            String nodoOrigen = tmpVehiculo.getOrigen();
            String fila = utils.extraerFila(nodoOrigen);
            int columna = utils.extraerColumna(nodoOrigen);

            NodoInterseccion nodo = mapa.getNodo(fila, columna);

            if (nodo != null) {
                if (nodo.getColaDisponible() != null) {
                    nodo.getColaDisponible().encolar(tmpVehiculo);
                } else {
                    System.out.println("Avenida llena de vehiculos, no se pueden colocar mas");
                }
            } else {
                System.out.println("Posicion fuera de rango no se puede ingresar el vehiculo");
            }
            tmpVehiculo = listaVehiculos.getPrimerVehiculo();
        }
    }

    public void iniciarSimulacion() throws Exception {

        int tmpOption = 0;
        do {
            mapa.imprimirMapa();
            System.out.println("Seleccione una opcion: ");
            System.out.println("1) Mover trafico ");
            System.out.println("2) Ingresar nuevo vehiculo");
            System.out.println("3) Ver estado de trafico");
            System.out.println("4) Hacer un Busqueda");
            System.out.println("5) Terminar Simulacion");

            tmpOption = scanner.nextInt();
            scanner.nextLine();
            switch (tmpOption) {
                case 1:
                    moverTrafico();
                    // limpiar arbol 
                    limpiarArbol();
                    /// guardar el registro de los nodos
                    guardarNodosAvl();
                    /// guardar nodos en la lista
                    guardarNodosLista();
                    break;
                case 2:
                    ingresarNuevoVehiculo();
                    ingresarVehiculosMapa();

                    break;
                case 3:
                    verEstadoTrafico();
                    break;
                case 4:
                    hacerBusqueda();
                    break;
                case 5:
                    verReporte();
                    System.out.println("Simulación terminada.");

                    return; // salir del ciclo
                default:
                    System.out.println(" Ingrese una opcion valida ");
            }

        } while (true);

    }

    /// mover trafico primero los mas congestionados
    public void moverTrafico() {
        /// cada moviento seria como un segundo cada enter seria un segundo acumulado 
        NodoInterseccion nodo;
        boolean vehiculoMovido = false;

        while (!listaNodosInt.estaVacia()) {
            nodo = listaNodosInt.getPrimerInterseccion();
         
            if (nodo != null) {
                vehiculoMovido= nodo.moverVehiculos();
                nodo.calcularComplejidad();
                nodo.actulizarTiempo();
            
                if (vehiculoMovido) {
                    mapa.imprimirMapa(); // imprimir cuando hubo movimiento
                }
            } 
        }
    }

    /// guardar nodos en el arbol avl
    public void guardarNodosAvl() {
        mapa.ordenarEnArbol(arbolAVL = new Arbol());
    }

    public void limpiarArbol(){
        arbolAVL = null;
    }

    public void guardarNodosLista() {
        mapa.ordenarEnLsita(listaNodosInt);
    }

    public void verEstadoTrafico() {


        /// estado de trafico // nodos 
        /// ver el estado de los nodos
        System.out.println("__________________________");
        System.out.println("__________________________");
        System.out.println("Estado de tráfico:");
        System.out.println("Ingrese el nodo que desea consultar (ej. A1): ");
        String nodoPosicion = scanner.nextLine();
        String fila = utils.extraerFila(nodoPosicion);
        int columna = utils.extraerColumna(nodoPosicion);
        NodoInterseccion nodo = mapa.getNodo(fila, columna);

        if (nodo != null) {
            System.out.println("Estado del nodo " + nodoPosicion + ":");
            System.out.println("Tipo de intersección: " + nodo.getTipo());
            System.out.println("Complejidad: " + nodo.getComplejidad());
          //  System.out.println("Tiempo de espera: " + nodo.getTiempoEspera());
            System.out.println("Vehículos en cola: " + nodo.getCantVehiculos()  );
        } else {
            System.out.println("Nodo no encontrado.");
        }

        /// ver reporte del arbol.
        /// ACTUALIZAR EL ARBOL
        limpiarArbol();
        guardarNodosAvl();
        verReporte();

        System.out.println("__________________________");
        System.out.println("__________________________");

    }

    public void verReporte()  {

        // insertar nodos...
        VisualizadorAVL.guardarArbol(arbolAVL.getNodoRaiz(), "ArbolNodos" );
        arbolAVL.imprimirInOrden();

    }

    public void ingresarNuevoVehiculo() {

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

        Vehiculo nuevoVehiculo = new Vehiculo(tipo, placa, origen, destino);

        listaVehiculos.ingresarOrdenado(nuevoVehiculo);
        tabla.insertar(nuevoVehiculo);
        System.out.println("¡Vehículo agregado exitosamente!");

    }

    public void crearMapa() {
        int tamanioTabla = listaVehiculos.getTamanioTabla();
        System.out.println("El tamanio de la tabla seria: " + tamanioTabla);
        /// creamos la tabla con el nodo mayor.
        mapa = new Mapa(tamanioTabla+1);
    }

    public void hacerBusqueda() {
        System.out.println("Ingrese la placa del vehículo que desea buscar: ");
        String placa = scanner.nextLine();
        Vehiculo vehiculoEncontrado = tabla.buscar(placa);

        if (vehiculoEncontrado != null) {
            System.out.println("Vehículo encontrado: " + vehiculoEncontrado);
            System.out.println("Tipo: " + vehiculoEncontrado.getLogo());
            System.out.println("Origen: " + vehiculoEncontrado.getOrigen());
            System.out.println("Destino: " + vehiculoEncontrado.getDestinoFila()+ vehiculoEncontrado.getDestinoColumna());

        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }

}
