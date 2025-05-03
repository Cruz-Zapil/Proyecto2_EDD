package org.example;


import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        /// leer mi archivo.
        /// o ingresar carros

        // obtenemos el tamaño del nodo mas grande y generamos mapa,

        /// creamos vehiculos
        /// luego empezamos a mover

        /// Menú

        int opcion;
        boolean simulacion = true;


        System.out.println("__________________________");
        System.out.println("__________________________");
        System.out.println("_____   Bienvenido  ______");
        System.out.println("__________________________");
        System.out.println("__________________________");

        do {

            // crar una nueva silumacion:
           Simulacion simulacion1 = new Simulacion();

            System.out.println();
            System.out.println();
            System.out.println("__________________________");
            System.out.println("__________________________");
            System.out.println("________   MENÚ   ________");
            System.out.println("__________________________");
            System.out.println("__________________________");

            System.out.println();

            System.out.println("Seleccione una opcion:");

            System.out.println(" 1) Leer Archivo");
            System.out.println(" 2) Crear Vehiculo Manulamente");
            System.out.println(" 3) Salir");

            opcion = scanner.nextInt();
            System.out.println(opcion);

            switch (opcion){

                case 1:
                    System.out.println(" Leer Archivo ");
                   simulacion1.leerArchivo();
                    break;
                case 2:
                    System.out.println(" Crear vehiculo manulamente");
                   simulacion1.ingresarVehiculoManual();
                    break;
                case 3:

                    System.out.println("_________________________");
                    System.out.println("_______   Adios  ________");
                    System.out.println("_________________________");
                    simulacion = false;
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }

        } while (simulacion);

    }
}





