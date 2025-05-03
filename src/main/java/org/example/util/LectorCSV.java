package org.example.util;

import org.example.estructuras.lista.ListaNewVehiculo;
import org.example.objeto.Vehiculo;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;
import java.io.File;


public class LectorCSV {

    public static ListaNewVehiculo leerCSV() {

        ListaNewVehiculo vehiculos = new ListaNewVehiculo();


        try (BufferedReader br = new BufferedReader(new FileReader(elegirArchivo()))) {
            String linea;


            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");
                if (datos.length == 6) {
                    String tipo = datos[0].trim();
                    String placa = datos[1].trim();
                    String origen = datos[2].trim();
                    String destino = datos[3].trim();
                    int prioridad = Integer.parseInt(datos[4].trim());
                    int tiempo = Integer.parseInt(datos[5].trim());

                    Vehiculo v = new Vehiculo(tipo, placa, origen, destino, prioridad, tiempo);
                    vehiculos.ingresarOrdenado(v);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return vehiculos;
    }


    public static String elegirArchivo() {

        JFileChooser fileChooser = new JFileChooser();
        String rutaAbsoluta="";
        // Mostrar la ventana (modo selección de archivos)
        int resultado = fileChooser.showOpenDialog(null);

        // Verificar si el usuario seleccionó un archivo
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            rutaAbsoluta = archivoSeleccionado.getAbsolutePath();
            System.out.println("Archivo seleccionado: " + rutaAbsoluta);
        } else {
            System.out.println("Selección cancelada.");
        }
        return rutaAbsoluta;
    }


}
