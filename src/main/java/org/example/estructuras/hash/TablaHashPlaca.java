package org.example.estructuras.hash;

import org.example.objeto.Vehiculo;

public class TablaHashPlaca {


    private static final int CAPACIDAD = 60; // Tamaño inicial del arreglo
    private Vehiculo[] tabla;
    private int tamaño;

    public TablaHashPlaca() {
        tabla = new Vehiculo[CAPACIDAD];
        tamaño = 0;
    }

    // la funcion, pasar placa a indice
    private int hash(String placa) {
        int hash = 0;
        for (int i = 0; i < placa.length(); i++) {
            hash = (hash * 31 + placa.charAt(i)) % CAPACIDAD;
        }
        return hash;
    }

    // manejar coliciones
    private int obtenerIndice(String placa) {
        int indice = hash(placa);
        int intentos = 0;
        while (tabla[indice] != null && !tabla[indice].getPlaca().equals(placa)) {
            indice = (indice + 1) % CAPACIDAD; // Sondeo lineal
            intentos++;
            if (intentos >= CAPACIDAD) return -1; // Tabla llena
        }
        return indice;
    }

    // Insertar vehículo (evita duplicados)
    public boolean insertar(Vehiculo vehiculo) {
        if (tamaño >= CAPACIDAD) return false; // Tabla llena

        int indice = obtenerIndice(vehiculo.getPlaca());
        if (indice == -1) return false; // No se encontró espacio

        if (tabla[indice] == null) {
            tabla[indice] = vehiculo;
            tamaño++;
            return true;
        }
        return false; // Placa duplicada
    }

    // Buscar vehículo por placa (O(1) promedio)

    public Vehiculo buscar(String placa) {
        int indice = obtenerIndice(placa);
        if (indice != -1 && tabla[indice] != null) {
            return tabla[indice];
        }
        return null;
    }

}
