package org.example;

import org.example.estructuras.hash.TablaHashPlaca;
import org.example.estructuras.lista.ListaNewVehiculo;
import org.example.objeto.Vehiculo;

import java.util.Scanner;

public class Simulacion {


    private ListaNewVehiculo listaVehiculos;
    TablaHashPlaca tabla = new TablaHashPlaca();


    public void leerArchivo(){

    }

    public void ingresarVehiculo() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
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
                    System.out.println(" hola ");
                    listaVehiculos.ingresarOrdenado(nuevoVehiculo);
                    tabla.insertar(nuevoVehiculo);

                    contadorVehiculos++;

                    System.out.println("¡Vehículo agregado exitosamente!");
                    System.out.println("Total vehículos ingresados: " + contadorVehiculos);
                    break;

                case 2:
                    if (contadorVehiculos >= 5) {
                        System.out.println("\nIniciando simulación con " + contadorVehiculos + " vehículos...");
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


    public void iniciarSimulacion() {


    }






}
